package AVL;

public class Arvore implements ArvoreInterface {
    protected No root;
    protected int qtd_no;

    Arvore() {
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
        if (v == null) return 0;
        if (isExternal(v)) return 0;
        int h = 0;
        if (v.filho_esquerdo != null)
            h = Math.max(h, height(v.filho_esquerdo));
        if (v.filho_direito != null)
            h = Math.max(h, height(v.filho_direito));
        return 1 + h;
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
        if (v == null) return null;
        if (k == v.chave) return v;
        if (k < v.chave) return treeSearch(k, v.filho_esquerdo);
        return treeSearch(k, v.filho_direito);
    }

    public No inserir(No v) {
        if (root == null) {
            root = v;
            qtd_no++;
        } else {
            insercao(root, v);
        }
        qtd_no++;
        return v;
    }
    // inserção recursiva
    public void insercao(No atual, No novo) {
        if (novo.chave < atual.chave) {
            if (atual.filho_esquerdo == null) {
                atual.filho_esquerdo = novo;
                novo.pai = atual;
                atualiza_FB(novo);
            } else {
                insercao(atual.filho_esquerdo, novo);
            }
        } else if (novo.chave > atual.chave) {
            if (atual.filho_direito == null) {
                atual.filho_direito = novo;
                novo.pai = atual;
                atualiza_FB(novo);
            } else {
                insercao(atual.filho_direito, novo);
            }
        }
    }

    // função para balancear
    public No atualiza_FB(No no) {
        if(no.is_left())
            no.pai.fb++;
        if(no.is_rigth())
            no.pai.fb--;
        if(no.pai.fb == 0)
            return no;
        if(no.pai.fb > 1 || no.pai.fb < 0)
            balancear(no,no.pai);
        return atualiza_FB(no.pai);
    }

    public No balancear(No no, No antecessor) { // comparar se sao sinais iguais ou sinais diferentes
        if(no.fb > 0 && antecessor.fb > 0) { // positivo = a rotação direita simples
            rotacao_simples_direita(no,antecessor);
        }
        if(no.fb > 0 && antecessor.fb < 0) { // primeiro faz a rotação do no e depois a do antecessor
            rotacao_dupla_esquerda();
        }
        if(no.fb < 0 && antecessor.fb > 0) { // primeiro faz a rotação do no e depois a do antecessor
            rotacao_dupla_direita();
        }
        if(no.fb < 0 && antecessor.fb < 0) { // negativo = esquerda
            rotacao_simples_esquerda();
        }
    }

    /*
    simples
    antecessor vira filho e sua subarvore o acompanha
    verifica se o atual filho era esquerdo ou direito,
        se esquerdo: atual_filho.esquerdo == antecessor
            salva o antigo filho.esquerdo do antecessor
        se direito: atual_filho.direito == antecessor
            salva o antigo filho.direito do antecessor
     o resto da arvore permanece igual


    */

    public void rotacao_simples_direita(No no, No antecessor) {

    }

    // remocao
    public void remocao(int k) {
        No v = treeSearch(k, root);
        if (v == null) return;

        if (isExternal(v)) { // Caso 1
            Caso01remocao(v);
        } else if (v.filho_esquerdo == null || v.filho_direito == null) { // Caso 2
            Caso02remocao(v);
        } else { // Caso 3
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
            k.pai.filho_esquerdo = null;
        } else {
            k.pai.filho_direito = null;
        }
    }

    public void Caso02remocao(No k) {
        No filho = (k.filho_esquerdo != null) ? k.filho_esquerdo : k.filho_direito;
        if (k.pai == null) {
            root = filho;
            root.pai = null;
        } else {
            if (k.pai.filho_esquerdo == k)
                k.pai.filho_esquerdo = filho;
            else
                k.pai.filho_direito = filho;
            filho.pai = k.pai;
        }
    }

    public void Caso03remocao(No v) {
        No substituto = maiorMenor(v.filho_direito);
        v.chave = substituto.chave;
        if (isExternal(substituto)) {
            Caso01remocao(substituto);
        } else {
            Caso02remocao(substituto);
        }
    }

    public No maiorMenor(No v) {
        while (v.filho_esquerdo != null) {
            v = v.filho_esquerdo;
        }
        return v;
    }

    public void imprimir(No root) {
        if (isEmpty()) {
            System.out.println("(tá vazia)");
            return;
        }

        int altura = height(root); // altura da arv
        int largura = (int) Math.pow(2, altura + 1);

        java.util.Queue<No> fila = new java.util.LinkedList<>();
        fila.add(root);

        for (int nivel = 0; nivel <= altura; nivel++) {
            int tamanho = fila.size();
            int espacoEntre = largura / (int) Math.pow(2, nivel + 1);

            // espaco inicial antes do primeiro no
            printEspacos(espacoEntre);

            for (int i = 0; i < tamanho; i++) {
                No atual = fila.poll();

                if (atual != null) {
                    System.out.print(atual.chave+'['+atual.fb+']');
                    fila.add(atual.filho_esquerdo);
                    fila.add(atual.filho_direito);
                } else {
                    System.out.print(" ");
                    fila.add(null);
                    fila.add(null);
                }

                // espaço entre os nós do mesmo nível
                printEspacos(espacoEntre * 2);
            }
            System.out.println(); // próxima linha
        }
    }

    // func auxiliar para imprimir espaços
    private void printEspacos(int qtd) {
        for (int i = 0; i < qtd; i++) {
            System.out.print(" ");
        }
    }

}
