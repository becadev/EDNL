package ABP;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class ArvoreABP implements ABPinterface{
    protected NoABP root;
    protected int qtd_no;

    public ArvoreABP() {
        root = null;
        qtd_no = 0;
    }

    // Métodos da AB
    public int leftchild(NoABP v) {
        return v.filho_esquerdo.chave;
    }

    public int rightchild(NoABP v) {
        return v.filho_direito.chave;
    }

    public boolean hasLeft(NoABP v) {
        return v.filho_esquerdo != null;
    }

    public boolean hasRight(NoABP v) {
        return v.filho_direito != null;
    }

    // Métodos genéricos
    public int size() {
        return this.qtd_no;
    }

    public int height(NoABP v) {
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
    public NoABP root() {
        return this.root;
    }

    public NoABP parent(int k) {
        NoABP no = treeSearch(k, this.root);
        if (no == null || isRoot(no))
            return null;
        return no.pai;
    }

    // Métodos de consulta
    public boolean isInternal(NoABP no) {
        return no.filho_esquerdo != null || no.filho_direito != null;
    }

    public boolean isExternal(NoABP no) { // folha
        return no.filho_esquerdo == null && no.filho_direito == null;
    }

    public boolean isRoot(NoABP no) {
        return no.pai == null;
    }
    public int depth(NoABP no) { // profundidade recursiva 
        return profundidade(no);
    }

    public int profundidade(NoABP v) {
        if (v == this.root)
            return 0;
        return (1 + profundidade(v.pai));
    }

    // Métodos de atualização
    public void replace(NoABP no, int o) {
        no.chave = o;
    }

    // Método de busca
    public NoABP treeSearch(int k, NoABP v) {
        if (v == null)
            return null;
        if (k == v.chave)
            return v;
        if (k < v.chave)
            return treeSearch(k, v.filho_esquerdo);
        return treeSearch(k, v.filho_direito);
    }

    public NoABP inserir(int v) {
        NoABP novo = new NoABP(v);
        if (this.root == null) {
            this.root = novo;
        } else {
            insercao(root, novo);
        }
        qtd_no++;
        return novo;
    }

    // inserção recursiva
    public void insercao(NoABP antecessor, NoABP atual) {
        if (atual.chave < antecessor.chave) {
            if (antecessor.filho_esquerdo == null) {
                antecessor.filho_esquerdo = atual;
                atual.pai = antecessor;
            } else {
                insercao(antecessor.filho_esquerdo, atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                antecessor.filho_direito = atual;
                atual.pai = antecessor;
            } else {
                insercao(antecessor.filho_direito, atual);
            }
        }
    }

    public void remocao(int k) {
        NoABP v = treeSearch(k, root);
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

    public void Caso01remocao(NoABP k) {
        if (k.pai == null) {
            root = null;
            return;
        }
        if (k.pai.filho_esquerdo == k) {
            k.pai.filho_esquerdo = null;
            
        } else {
            k.pai.filho_direito = null;
        }
    }

    public void Caso02remocao(NoABP removido) {
        NoABP filho;
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
            } else{
                removido.pai.filho_direito = filho;
            }
            filho.pai = removido.pai;
        }
    }

    public void Caso03remocao(NoABP A) {
        NoABP F = maiorMenor(A.filho_direito);

        if (F.filho_direito != null) {
            if(F.chave != A.filho_direito.chave){
                F.pai.filho_esquerdo = F.filho_direito;
                F.filho_direito.pai = F.pai;
                F.filho_direito = A.filho_direito;
                F.filho_direito.pai = F;
            }
        }

        F.pai = A.pai;
        F.filho_esquerdo = A.filho_esquerdo;
        A.filho_esquerdo.pai = F;

        if(A.pai == null) {
            this.root = F;
        }    
    }

    public NoABP maiorMenor(NoABP v) {
        while (v.filho_esquerdo != null) {
            v = v.filho_esquerdo;
        }
        return v;
    }

    public void estrutura_no(Object[][] AVL, NoABP atual, int coluna, int linha){
        if (atual == null){ // fim
            return;
        }
        AVL[linha][coluna-1] = atual.chave;
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
                    System.out.print("  "); // espaço do no que vai ser substituido pelo vazio
                    // \t
                } else {
                    System.out.print(AVL[i][j]);
                }
            }
            System.out.println();
        }
    }
}
