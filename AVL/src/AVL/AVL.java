package AVL;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AVL implements ArvoreInterface {
    protected No root;
    protected int qtd_no;

    AVL() {
        this.root = null;
        this.qtd_no = 0;
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

    public No parent(int k) {
        No no = treeSearch(k, this.root);
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
        no.setChave(o);
    }

    // Método de busca
    public No treeSearch(int k, No v) {
        if (v == null)
            return null;
        if (k == v.chave)
            return v;
        if (k < v.chave)
            return treeSearch(k, v.filho_esquerdo);
        return treeSearch(k, v.filho_direito);
    }

    public No inserir(int v) {
        No novo = new No(v);
        if (this.root == null) {
            this.root = novo;
        } else {
            insercao(root, novo);
        }
        qtd_no++;
        return novo;
    }

    // inserção recursiva
    public void insercao(No antecessor, No atual) {
        if (atual.chave < antecessor.chave) {
            if (antecessor.filho_esquerdo == null) {
                antecessor.filho_esquerdo = atual;
                atual.pai = antecessor;
                atual.pai.fb++;
                atualiza_FB_insercao(atual.pai);
            } else {
                insercao(antecessor.filho_esquerdo, atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                antecessor.filho_direito = atual;
                atual.pai = antecessor;
                atual.pai.fb--;
                atualiza_FB_insercao(atual.pai);
            } else {
                insercao(antecessor.filho_direito, atual);
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
            atual.pai.fb++;
        }

        if (atual.chave > atual.pai.chave){
            atual.pai.fb--;
        }

        if (atual.pai != null && (atual.pai.fb >= 2 || atual.pai.fb <= -2)) {
            System.out.println("Antes do balancemento");
            imprimir();
            System.out.println();
            balancear(atual, atual.pai);
            return atual;
        }
        return atualiza_FB_insercao(atual.pai);
    }

    public No atualiza_FB_remocao(No atual) {
        if (atual == null) {
            No aux = new No(0); // só para poder para qunaod for null
            return aux;
        }
        if (atual.fb >= 2 || atual.fb <= -2) {
            if(atual.fb >= 2){ // desbalanceou pra esq
                atual = balancear(atual.filho_esquerdo, atual);
            }
            else if(atual.fb <= -2){ // desbalanceou pra dir
                atual = balancear(atual.filho_direito, atual);
            }
        }
            
        No pai = atual.pai;
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
                rotacao_simples_direita(atual.filho_esquerdo, atual);
                imprimir();
                System.out.println("2ª rotação: esquerda");
                atual = rotacao_simples_esquerda(antecessor.filho_direito, antecessor);
                return atual;
            }
            if (atual.fb < 0 && antecessor.fb >= 2) { // primeiro faz a rotação do atual e depois a do antecessor
                System.out.println("Realizando RDD");
                System.out.println("1ª rotação: esquerda");
                rotacao_simples_esquerda(atual.filho_direito, atual);
                imprimir();
                System.out.println("2ª rotação: direita");
                atual = rotacao_simples_direita(antecessor.filho_esquerdo, antecessor);
                return atual;
            }
            if ( atual.fb < 0 && antecessor.fb <= -2) { // negativo = esquerda 
                imprimir();
                System.out.println("Realizando RSE");
                rotacao_simples_esquerda(atual, antecessor);
                return atual;
            }
        }
        return atual;
    }

    public No rotacao_simples_direita(No A, No B) {
        No vo = B.pai;
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
        No vo = antecessor.pai;
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
        No v = treeSearch(k, root);
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
            k.pai.fb--;
            k.pai.filho_esquerdo = null;
            atualiza_FB_remocao(k.pai);
            
        } else {
            k.pai.fb++;
            k.pai.filho_direito = null;
            atualiza_FB_remocao(k.pai);
           
        }
    }

    public void Caso02remocao(No removido) {
        No filho;
        if (removido.filho_esquerdo != null) {
            filho = removido.filho_esquerdo;
        } else {
            filho = removido.filho_direito;
        }
        if (removido.pai == null) {
            root = filho;
            root.pai = null;
        } else {
            if (removido.pai.filho_esquerdo == removido) {
                removido.pai.filho_esquerdo = filho;
                removido.pai.fb--;
                atualiza_FB_remocao(removido.pai); 
            } else{
                removido.pai.filho_direito = filho;
                removido.pai.fb++;
                atualiza_FB_remocao(removido.pai); 
            }
            filho.pai = removido.pai;
        }
    }

    public void Caso03remocao(No v) {
        No substituto = maiorMenor(v.filho_direito);
        No old_pai_substituo = substituto.pai;
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
            v = v.filho_esquerdo;
        }
        return v;
    }

    public void estrutura_no(Object[][] AVL, No no, int linha, int coluna){
        if (no == null){ //fim
            return;
        }
        AVL[linha][coluna] = no.chave + "[" + no.fb + "]";
        int aux = (int) Math.pow(2, AVL.length - linha - 2);

        if (no.filho_direito != null) {
            estrutura_no(AVL, no.filho_direito, linha + 1, coluna + aux);
        }

        if (no.filho_esquerdo != null) {
            estrutura_no(AVL, no.filho_esquerdo, linha + 1, coluna - aux);
        }
    }

    public void imprimir(){
        int altura = height(this.root)+2; // mais um pra n começar do 0
        int linha = altura+1;
        int colunas = (int) Math.pow(2, altura) * 2; ;  // qtd nos por nivel em AB * qtd de espaço entre os nos

        Object[][] AVL = new Object[linha][colunas];
        
        estrutura_no(AVL, this.root, 0, (int) colunas /2); // deixa tudo organizado na matriz
        for (int i = 0; i < linha; i++){
           
            for (int j = 0; j < colunas; j++){
                if (AVL[i][j] == null){
                    System.out.print(" "); 
                    // \t
                } else {
                    System.out.printf("%s", AVL[i][j]);
                }
            }
            System.out.println();
        }
    }
}
