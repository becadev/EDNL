package AVL;

import static java.lang.Math.max;
import static java.lang.Math.min;

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
        if (v == null) return -1; // caso base

        int h_esq = height(v.filho_esquerdo);
        int h_dir = height(v.filho_esquerdo);

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
        if (v == null) return null;
        if (k == v.chave) return v;
        if (k < v.chave) return treeSearch(k, v.filho_esquerdo);
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
                atualiza_FB_insercao(atual);

            } else {
                insercao(antecessor.filho_esquerdo, atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                antecessor.filho_direito = atual;
                atual.pai = antecessor;
                atualiza_FB_insercao(atual);
            } else {
                insercao(antecessor.filho_direito, atual);
            }
        }
    }

    // função para balancear
    public No atualiza_FB_insercao(No novo) {
        if (novo == null || novo.pai == null) {
            return novo;
        }
        if(novo.chave < novo.pai.chave)
            novo.pai.fb++;
        if(novo.chave > novo.pai.chave)
            novo.pai.fb--;
        if(novo.pai != null && novo.pai.fb == 0)
            return novo;
        if(novo.pai != null && ( novo.pai.fb >= 2 || novo.pai.fb <= -2))
            balancear(novo,novo.pai);
        return atualiza_FB_insercao(novo.pai);
    }

    public No atualiza_FB_remocao(No no) {
        if(no.is_left())
            no.pai.fb--;
        if(no.is_rigth())
            no.pai.fb++;
        if(no.pai.fb >= 2 || no.pai.fb <= -2) {
            balancear(no, no.pai);
            return no;
        }
        if(no.pai.fb != 0)
            return no;
        return atualiza_FB_remocao(no.pai);
    }


    public void balancear(No atual, No antecessor) { // comparar se sao sinais iguais ou sinais diferentes
        if(atual.fb > 0 && antecessor.fb > 0) { // positivo = a rotação direita simples
            rotacao_simples_direita(atual,antecessor);
        }
        if(atual.fb > 0 && antecessor.fb < 0) { // primeiro faz a rotação do atual e depois a do antecessor
            rotacao_simples_direita(atual, antecessor);
            rotacao_simples_esquerda(antecessor, antecessor.pai);
        }
        if(atual.fb < 0 && antecessor.fb > 0) { // primeiro faz a rotação do atual e depois a do antecessor
            rotacao_simples_esquerda(atual, antecessor);
            rotacao_simples_direita(antecessor, antecessor.pai);
        }
        if(atual.fb < 0 && antecessor.fb < 0) { // negativo = esquerda
            rotacao_simples_esquerda(atual, antecessor);
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

    public void rotacao_simples_direita(No atual, No antecessor) {
        atual.pai = antecessor.pai;
        if(antecessor.pai != null ) { // caso o antecessor fosse raiz
            antecessor.pai.filho_esquerdo = atual; // o filho esquerdo dele vai ser o atual
        }

        if(antecessor.pai == null) { // se o pai do antecessor for nulo é pq o antecessor é raiz12
            this.root = atual;
        }

        if(atual.filho_direito != null) {
            antecessor.filho_direito = atual.filho_direito; // salva e esse filho sera filho do antecessor
        }
        atual.filho_direito = atual;
        antecessor.fb = antecessor.fb - 1 - max(atual.fb, 0);
        atual.fb = atual.fb - 1 + min(antecessor.fb, 0);
    }


    public void rotacao_simples_esquerda(No atual, No antecessor) {
        atual.pai = antecessor.pai;
        if(antecessor.pai != null) // caso o antecessor nao for raiz
            antecessor.pai.filho_direito = atual; // o filho direito dele vai ser o atual

        if(antecessor.pai == null) { // se o pai do antecessor for nulo é pq o antecessor é raiz
            this.root = atual;
        }
        if(atual.filho_esquerdo != null) // se o atual tiver filho esquerdo
            antecessor.filho_direito = atual.filho_esquerdo; // esse filho esquerdo agora sera filho direito do antecessor

        atual.filho_esquerdo = antecessor; // antecessor agora é filho do atual
        antecessor.fb = antecessor.fb + 1 - min(atual.fb, 0);
        atual.fb = atual.fb + 1 + max(antecessor.fb, 0);
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



    // Assumindo que a classe Arvore/AVL tem o método height(No) e isEmpty()
    public void imprimir() {
        if (isEmpty()) {
            System.out.println("(Árvore vazia)");
            return;
        }

        int altura = height(root);
        // Largura total: 2^(altura + 1) para ter espaço suficiente, ou 2^altura * 4
        int larguraTotal = (int) Math.pow(2, altura) * 4;

        java.util.Queue<No> fila = new java.util.LinkedList<>();
        fila.add(root);
        int noNulls = 0; // Contador para saber se a fila só tem nulos

        for (int nivel = 0; nivel <= altura; nivel++) {
            int tamanho = fila.size();

            // Se todos os elementos restantes na fila são nulos, podemos parar.
            if (noNulls == tamanho) break;
            noNulls = 0;

            // Calcula o espaçamento
            // O espacoAntes será o espaço lateral antes do primeiro nó
            int espacoAntes = (larguraTotal / (int) Math.pow(2, nivel + 1));
            // O espacoEntreNo será o espaço entre os nós do mesmo nível
            int espacoEntreNo = (larguraTotal / (int) Math.pow(2, nivel));

            // 1. Espaço inicial antes do primeiro nó
            printEspacos(espacoAntes);

            for (int i = 0; i < tamanho; i++) {
                No atual = fila.poll();

                if (atual != null) {
                    // Imprime a chave e o FB sem quebra de linha (%n)
                    // Use format("%-4s", ...) para garantir que a impressão tenha tamanho fixo (ex: 4)
                    System.out.printf("%-4s", atual.chave + "[" + atual.fb + "]");

                    fila.add(atual.filho_esquerdo);
                    fila.add(atual.filho_direito);
                } else {
                    // Imprime espaços no lugar do nó nulo para manter a estrutura
                    System.out.printf("%-4s", "    "); // Ou use "    " (4 espaços)

                    fila.add(null);
                    fila.add(null);
                    noNulls++; // Conta o nó nulo
                }

                // 2. Espaço entre os nós do mesmo nível
                // O último elemento de cada nível não precisa do espaçoEntreNo
                if (i < tamanho - 1) {
                    printEspacos(espacoEntreNo - 4); // subtrai o tamanho da string impressa
                } else {
                    // add o espaçamento do prox nível após o último nó
                    printEspacos(espacoAntes);
                }
            }
            System.out.println(); // quebra de linha para o próximo nível
        }
    }

    // função auxiliar printEspacos está correta:
    private void printEspacos(int qtd) {
        // garante que a quantidade não seja negativa
        qtd = Math.max(0, qtd);
        for (int i = 0; i < qtd; i++) {
            System.out.print(" ");
        }
    }
}
