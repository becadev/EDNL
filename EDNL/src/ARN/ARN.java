package ARN;

import ABP.NoABP;
import AVL.No;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class ARN{
    protected NoARN root;
    protected int qtd_no;
    protected int negro = 0;
    protected int rubro = 1;

    ARN() {
        this.root = null;
        this.qtd_no = 0;
    }

    public Object leftchild(NoARN v) {
        if(hasLeft(v))
            return v.filho_esquerdo.chave;
        return "Nó não tem filho esquerdo";
    }
    public Object rightchild(NoARN v) {
        if(hasRight(v))
            return v.filho_direito.chave;
        return "Nó não tem filho direito";
    }
     public boolean hasLeft(NoARN v) {
        return v.filho_esquerdo != null;
    }

    public boolean hasRight(NoARN v) {
        return v.filho_direito != null;
    }

    // Métodos genéricos
    public int size() {
        return this.qtd_no;
    }

    public int height(NoARN v) {
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
    public NoARN root() {
        return this.root;
    }

    public NoARN parent(int k) {
        NoARN no = treeSearch(k, this.root);
        if (no == null || isRoot(no))
            return null;
        return no.pai;
    }

    // Métodos de consulta
    public boolean isInternal(NoARN no) {
        return no.filho_esquerdo != null || no.filho_direito != null;
    }

    public boolean isExternal(NoARN no) { // folha
        return no.filho_esquerdo == null && no.filho_direito == null;
    }

    public boolean isRoot(NoARN no) {
        return no.pai == null;
    }
    public int depth(NoARN no) { // profundidade recursiva 
        return profundidade(no);
    }

    public int profundidade(NoARN v) {
        if (v == this.root)
            return 0;
        return (1 + profundidade(v.pai));
    }

    // Métodos de atualização
    public void replace(NoARN no, int o) {
        no.chave = o;
    }

    // Método de busca
    public NoARN treeSearch(int k, NoARN v) {
        if (v == null)
            return null;
        if (k == v.chave)
            return v;
        if (k < v.chave)
            return treeSearch(k, v.filho_esquerdo);
        return treeSearch(k, v.filho_direito);
    }

    public NoARN inserir(int v) {
        NoARN novo = new NoARN(v);
        if (this.root == null) {
            this.root = novo;
            this.root.cor = negro;
        } else {
            novo.cor = rubro;
            insercao(root, novo);
        }
        qtd_no++;
        return novo;
    }

    public int alturaNegra(NoARN atual) {
        int alturaNegra = 0;
        while(atual != null) {
            if(atual.pai.cor == negro) 
                alturaNegra++;
            atual = atual.pai;
        }
        return alturaNegra;
    }

    public void insercao(NoARN antecessor, NoARN atual) {
        if (atual.chave < antecessor.chave) {
            if (antecessor.filho_esquerdo == null) {
                antecessor.filho_esquerdo = atual;
                atual.pai = antecessor;
                balancear(atual, antecessor);
            } else {
                insercao(antecessor.filho_esquerdo, atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                antecessor.filho_direito = atual;
                atual.pai = antecessor;
                balancear(atual, antecessor);
            } else {
                insercao(antecessor.filho_direito, atual);
            }
        }
    }

    // public NoARN atualiza_cor(NoARN atual) {

    // }

    // Balanceamento
    public NoARN balancear(NoARN atual, NoARN antecessor) {
        if(antecessor.cor == rubro) {
            NoARN avo = antecessor.pai;

            // caso 1
            if(avo.filho_esquerdo != null && avo.filho_esquerdo.cor == rubro) { // tio esquerdo
                avo.filho_esquerdo.cor = negro;
                antecessor.cor = negro;
                if(!isRoot(avo))
                    avo.cor = rubro;
                return atual;
            }//espelhamento
            if(avo.filho_direito != null && avo.filho_direito.cor == rubro) { // tio direito
                avo.filho_direito.cor = negro;
                antecessor.cor = negro;
                if(!isRoot(avo))
                    avo.cor = rubro;
                return atual;
            }
            //
            // caso 2 - leva para o caso 3
            if (avo.filho_direito != null 
                && avo.filho_direito.cor == negro 
                && antecessor.filho_direito.chave == atual.chave ) { // z tem um tio y negro e é filho direito, rotação a esquerda
                rotacao_simples_esquerda(atual, antecessor);
                rotacao_simples_direita(antecessor, avo);
                // colore o pai do atual de preto e antigo pai do pai de vermelho
                antecessor.cor = negro;
                antecessor.filho_direito.cor = rubro;
                return atual;
            }else if // espelhamento
                (avo.filho_esquerdo != null
                && avo.filho_esquerdo.cor == negro
                && antecessor.filho_esquerdo.chave == atual.chave) {
                
                rotacao_simples_direita(atual, antecessor);
                rotacao_simples_esquerda(antecessor, avo);

                antecessor.cor = negro;
                antecessor.filho_esquerdo.cor = rubro;
                return atual;
            }

            // caso 3
            if (atual.chave < antecessor.chave) { // é filho esquerdo entao verificar o tio que é direito
                if(antecessor.filho_direito == null) { // Caso 3a
                    rotacao_simples_direita(antecessor, antecessor.pai);
                    antecessor.cor = negro;
                    antecessor.filho_direito.cor = rubro;
                }
                return atual;
            } 
            if (atual.chave > antecessor.chave) { // é filho esquerdo entao verificar o tio que é direito
                if(antecessor.filho_esquerdo == null) { // Caso 3b
                    rotacao_simples_esquerda(antecessor, antecessor.pai);
                    antecessor.cor = negro;
                    antecessor.filho_esquerdo.cor = rubro;
                }
                return atual;
            } 
        }
        return atual;
    }

    // public void repinta(NoARN atual, NoARN filho) {
    //     atual.cor = negro;
    //     filho.cor = rubro;
    // }

    public void rotacao_simples_direita(NoARN A, NoARN B) {
        NoARN vo = B.pai;
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
    }

    public void rotacao_simples_esquerda(NoARN atual, NoARN antecessor) {
        NoARN vo = antecessor.pai;
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
    }

    // Remoção
    public void remocao(int k) {

    }

    public void Caso01remocao(NoARN k) {

    }
    public void Caso02remocao(NoARN removido) {

    }
    public void Caso03remocao(NoARN v) {

    }
    public NoARN maiorMenor(NoARN v) {
        while (v.filho_esquerdo != null) {
            v = v.filho_esquerdo;
        }
        return v;
    }
    
    public void estrutura_no(Object[][] AVL, NoABP atual, int coluna, int linha){
        if (atual == null){ // fim
            return;
        }
        AVL[linha][coluna-1] = atual.chave + "[" + ((No) atual).fb + "]"; // mudar estrutura para pintura
        int aux = (int) Math.pow(2, height(this.root) - linha - 2); // vai definir a distancia da coluna dos filhos  em relação ao no atual qu é pai

        if (atual.filho_esquerdo != null) {
            estrutura_no(AVL, atual.filho_esquerdo, coluna - aux, linha + 1);
        }

        if (atual.filho_direito != null) {
            estrutura_no(AVL, atual.filho_direito, coluna + aux, linha + 1);
        }
    }

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
