package AVL;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.List;
import ABP.*;

public class AVL extends ArvoreABP {
    protected No root;
    protected int qtd_no;
    protected List<Integer> arvore;

    public AVL() {
        super();
    }

    // Métodos da AB
    public int leftchild(No v) {
        return v.filho_esquerdo.chave;
    }

    public int rightchild(No v) {
        return v.filho_direito.chave;
    }

    public boolean hasLeft(No v) {
        return v.filho_esquerdo != null;
    }

    public boolean hasRight(No v) {
        return v.filho_direito != null;
    }

    // Métodos genéricos
    public int size() {
        return this.qtd_no;
    }

    public int height(No v) {
        if (v == null)
            return 0; // caso base
        int h_esq = height(v.filho_esquerdo);
        int h_dir = height(v.filho_direito);
        return max(h_esq, h_dir) + 1;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // Métodos de acesso
    public No root() {
        return this.root;
    }

    public NoABP parent(int k) {
        No no = (No)treeSearch(k, this.root);
        if (no == null || isRoot(no))
            return null;
        return no.pai;
    }

    // Métodos de consulta
    public boolean isInternal(No no) {
        return no.filho_esquerdo != null || no.filho_direito != null;
    }

    public boolean isExternal(No no) { // folha
        return no.filho_esquerdo == null && no.filho_direito == null;
    }

    public boolean isRoot(No no) {
        return no.pai == null;
    }
    public int depth(No no) { // profundidade recursiva 
        return profundidade(no);
    }

    public int profundidade(No v) {
        if (v == this.root)
            return 0;
        return (1 + profundidade(v.pai));
    }

    // Métodos de atualização
    public void replace(No no, int o) {
        no.chave = o;
    }

    // Método de busca
    public NoABP treeSearch(int k, NoABP v) {
        if (v == null)
            return null;
        if (k == v.chave)
            return v;
        if (k < v.chave)
            return (No)treeSearch(k, v.filho_esquerdo);
        return (No)treeSearch(k, v.filho_direito);
    }

    @Override
    public No inserir(int v) {
        No novo = new No(v);
        if (this.root == null) {
            this.root = novo;
        } else {
            insercao((NoABP)root, (NoABP)novo);
        }
        qtd_no++;
        return novo;
    }

    // inserção recursiva
    @Override
    public void insercao(NoABP nantecessor, NoABP natual) {
        No antecessor = (No) nantecessor;
        No atual = (No) natual;

        if (atual.chave < antecessor.chave) {
            if (antecessor.filho_esquerdo == null) {
                antecessor.filho_esquerdo = (No) atual;
                atual.pai = antecessor;
                ((No)atual.pai).fb++;
                atualiza_FB_insercao((No)atual.pai);
            } else {
                insercao(antecessor.filho_esquerdo,(No)atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                antecessor.filho_direito = atual;
                atual.pai = antecessor;
                ((No)atual.pai).fb--;
                atualiza_FB_insercao((No)atual.pai);
            } else {
                insercao((No)antecessor.filho_direito, (No)atual);
            }
        }
    }

    public No atualiza_FB_insercao(No atual) {// balancea os fb de remocao
        if (atual == null || atual.pai == null) {
            return atual;
        }
        if (atual != null && atual.fb == 0){
            return atual;}

        if (atual.chave < atual.pai.chave){
            ((No)atual.pai).fb++;
        }

        if (atual.chave > atual.pai.chave){
            ((No)atual.pai).fb--;
        }

        if (atual.pai != null && (((No)atual.pai).fb >= 2 || ((No)atual.pai).fb <= -2)) {
            System.out.println("Antes do balancemento");
            mostrar();
            System.out.println();
            balancear((No)atual, (No)atual.pai);
            return atual;
        }
        return atualiza_FB_insercao((No)atual.pai);
    }

    public No atualiza_FB_remocao(No atual) {

        if (atual == null) {
            No aux = new No(0); // só para poder para qunaod for null
            return aux;
        }
        if (atual.fb >= 2 || atual.fb <= -2) {
            if(atual.fb >= 2){ // desbalanceou pra esq
                atual = balancear((No)atual.filho_esquerdo,(No)atual);
            }
            else if(atual.fb <= -2){ // desbalanceou pra dir
                atual = balancear((No)atual.filho_direito, (No)atual);
            }
        }
            
        No pai = (No) atual.pai;
        if (pai != null ) {
            if (pai.fb != 0 && (pai.fb < 2 && pai.fb > -2 )){ // caso base
                return atual;
            }

            if (atual.chave < pai.chave){
                pai.fb--;
            }

            if (atual.chave > pai.chave) {
                pai.fb++;
            }
        }
        return atualiza_FB_remocao(pai);
    }

    public No balancear(No atual, No antecessor) { // comparar se sao sinais iguais ou sinais diferentes
        if (atual != null){
            if ( atual.fb > 0 && antecessor.fb >= 2) { // positivo = a rotação direita simples
                System.out.println("Realizando RSD");
                rotacao_simples_direita(atual, antecessor);
                return atual;
            }
            if (atual.fb > 0 && antecessor.fb <= -2) {
                // primeiro faz a rotação do atual e depois a do antecessor
                // então a esquerda que ta desbalanceada
                System.out.println("Realizando RDE");
                System.out.println("1ª rotação: direira");
                rotacao_simples_direita((No)atual.filho_esquerdo, (No)atual);
                mostrar();
                System.out.println("2ª rotação: esquerda");
                atual = rotacao_simples_esquerda((No)antecessor.filho_direito, (No)antecessor);
                return atual;
            }
            if (atual.fb < 0 && antecessor.fb >= 2) { // primeiro faz a rotação do atual e depois a do antecessor
                System.out.println("Realizando RDD");
                System.out.println("1ª rotação: esquerda");
                rotacao_simples_esquerda((No)atual.filho_direito,(No) atual);
                mostrar();
                System.out.println("2ª rotação: direita");
                atual = rotacao_simples_direita((No)antecessor.filho_esquerdo,(No)antecessor);
                return atual;
            }
            if ( atual.fb < 0 && antecessor.fb <= -2) { // negativo = esquerda 
                mostrar();
                System.out.println("Realizando RSE");
                rotacao_simples_esquerda((No)atual,(No)antecessor);
                return atual;
            }
        }
        return atual;
    }

    public No rotacao_simples_direita(No A, No B) {
        No vo = (No) B.pai;
        if (vo != null) { // caso o B n for raiz 
            if(vo.filho_direito.chave == B.chave){
                vo.filho_direito = A;
            }else{
                vo.filho_esquerdo = A;
            }
            A.pai = vo;
            
        }else{ // se o pai do B for nulo é pq o B é raiz
            this.root = A;
            A.pai = null;
        }
        if (A.filho_direito != null) {
            B.filho_esquerdo = A.filho_direito; // salva e esse filho esquerdo A e ele sera filho do B
            A.filho_direito.pai = B;
        } else {
            B.filho_esquerdo = null;
        }
        A.filho_direito = B;
        B.pai = A;
        B.fb = B.fb - 1 - max(A.fb, 0);
        A.fb = A.fb - 1 + min(B.fb, 0);
        return A;
    }
    

    public No rotacao_simples_esquerda(No atual, No antecessor) {
        No vo = (No) antecessor.pai;
        if (vo != null) { // caso o antecessor nao for raiz 
            if(vo.filho_direito!=null && vo.filho_direito.chave == antecessor.chave) { // verifica para coisar casos espelhados
                vo.filho_direito = atual;
            }else if(vo.filho_esquerdo!=null && vo.filho_esquerdo.chave == antecessor.chave){
                vo.filho_esquerdo = atual; 
            }
            atual.pai = vo;
        }else{// se vo for nulo é pq o antecessor era raiz
            this.root = atual;
            atual.pai = null;
        }

        if (atual.filho_esquerdo != null) { // se o atual tiver filho esquerdo 
            antecessor.filho_direito = atual.filho_esquerdo; // esse filho esquerdo agora sera filho direito do antecessor
            atual.filho_esquerdo.pai = antecessor;
        } else {
            antecessor.filho_direito = null;
        }
        atual.filho_esquerdo = antecessor; // antecessor agora é filho do atual
        antecessor.pai = atual;
        antecessor.fb = antecessor.fb + 1 - min(atual.fb, 0);
        atual.fb = atual.fb + 1 + max(antecessor.fb, 0);
        return atual;
    }

    public void remocao(int k) {
        No v = (No)treeSearch(k, root);
        if (v == null)
            return;
        if (isExternal(v)) {
            System.out.println("CASO 01 DE REMOÇÃO: ");
            Caso01remocao(v);
        } else if (v.filho_esquerdo == null || v.filho_direito == null) {
            System.out.println("CASO 02 DE REMOÇÃO: ");
            Caso02remocao(v);
        } else {
            System.out.println("CASO 03 DE REMOÇÃO: ");
            Caso03remocao(v);
        }
        qtd_no--;
    }

    public void Caso01remocao(No k) {
        if (k.pai == null) {
            root = null;
            return;
        }
        if (k.pai.filho_esquerdo == k) {
            ((No)k.pai).fb--;
            k.pai.filho_esquerdo = null;
            atualiza_FB_remocao((No)k.pai);
            
        } else {
            ((No)k.pai).fb++;
            k.pai.filho_direito = null;
            atualiza_FB_remocao((No)k.pai);
           
        }
    }

    @Override
    public void Caso02remocao(NoABP removido) {
        No filho;
        if (removido.filho_esquerdo != null) {
            filho = (No)removido.filho_esquerdo;
        } else {
            filho = (No)removido.filho_direito;
        }
        if (removido.pai == null) {
            root = filho;
            root.pai = null;
        } else {
            if (removido.pai.filho_esquerdo == removido) {
                removido.pai.filho_esquerdo = filho;
                ((No)removido.pai).fb--;
                atualiza_FB_remocao((No)removido.pai); 
            } else{
                removido.pai.filho_direito = filho;
                ((No)removido.pai).fb++;
                atualiza_FB_remocao((No)removido.pai); 
            }
            filho.pai = removido.pai;
        }
    }

    public void Caso03remocao(No v) {
        No substituto = maiorMenor((No)v.filho_direito);
        No old_pai_substituo = (No)substituto.pai;
        atualiza_FB_remocao(substituto); // remoção fisica
        v.chave = substituto.chave;
        if((old_pai_substituo.filho_direito != null) && 
            old_pai_substituo.filho_direito.chave == substituto.chave){
            old_pai_substituo.filho_direito = null;
        }
        else if((old_pai_substituo.filho_esquerdo != null) && 
                  old_pai_substituo.filho_esquerdo.chave == substituto.chave) { 
            old_pai_substituo.filho_esquerdo = null;
        }
    }

    public No maiorMenor(No v) {
        while (v.filho_esquerdo != null) {
            v = (No)v.filho_esquerdo;
        }
        return v;
    }

    @Override
    public void estrutura_no(Object[][] AVL, NoABP atual, int coluna, int linha){
        if (atual == null){ // fim
            return;
        }
        AVL[linha][coluna-1] = atual.chave + "[" + ((No) atual).fb + "]";
        int aux = (int) Math.pow(2, height(this.root) - linha - 2); // vai definir a distancia da coluna dos filhos  em relação ao no atual qu é pai

        if (atual.filho_esquerdo != null) {
            estrutura_no(AVL, atual.filho_esquerdo, coluna - aux, linha + 1);
        }

        if (atual.filho_direito != null) {
            estrutura_no(AVL, atual.filho_direito, coluna + aux, linha + 1);
        }
    }

    @Override
    public void mostrar(){
        int altura = height(this.root); 
        int colunas = (int) Math.pow(2, altura);  //  2 pq é binaria, altura pq vai ser cada nivel pode ter a quantidade filho*2 pq cada filho pode ter filho 
        Object[][] AVL = new Object[altura][colunas];
        estrutura_no(AVL, this.root, (int) colunas/2, 0); // dividido por dois pra ele começar no meio da matriz
        for (int i = 0; i < altura; i++){
            for (int j = 0; j < colunas; j++){
                if (AVL[i][j] == null){
                    System.out.print("     "); // espaço de: no[fb] que vai ser substituido pelo vazio
                    // \t
                } else {
                    System.out.print(AVL[i][j]);
                }
            }
            System.out.println();
        }
    }
}
