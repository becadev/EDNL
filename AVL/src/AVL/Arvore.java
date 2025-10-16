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
        if (v == null) return 0; // caso base

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
        if(novo.pai != null && ( novo.pai.fb == 2 || novo.pai.fb == -2)) {
            balancear(novo, novo.pai);
        }
        return atualiza_FB_insercao(novo.pai);
    }

    public No atualiza_FB_remocao(No no) {
        System.out.println(no.chave);
        if(no.chave < no.pai.chave){
            System.out.println("remocao a esquerda");
            System.out.println(no.pai.fb);
            System.out.println(no.chave);
            no.pai.fb--;}
        if(no.chave > no.pai.chave){
            System.out.println("remocao a direita");
            System.out.println(no.pai.fb);
            System.out.println(no.chave);
            no.pai.fb++;}

        if(no.pai.fb >= 2 || no.pai.fb <= -2) {
            System.out.println("caso de balanceamento");
            System.out.println(no.pai.fb);
            System.out.println(no.chave);
            balancear(no, no.pai);
            return no;
        }
        if(no.pai.fb != 0){
            System.out.println("caso de parada");
            System.out.println(no.pai.fb);
            System.out.println(no.chave);
            return no;}
        return atualiza_FB_remocao(no.pai);
    }


    public void balancear(No atual, No antecessor) { // comparar se sao sinais iguais ou sinais diferentes
        System.out.println("atual fb" + atual.fb +  "  " + atual.chave);
        System.out.println("antecessor fb" + antecessor.fb + "  " + antecessor.chave);
        if(atual.fb >= 0 && antecessor.fb > 0) { // positivo = a rotação direita simples
            System.out.println("Realizando RSD");
            rotacao_simples_direita(atual,antecessor);
            return;
        }
        if(atual.fb >= 0 && antecessor.fb < 0) { // primeiro faz a rotação do atual e depois a do antecessor
            // então a esquerda que ta desbalanceada
            System.out.println("Realizando RDE");
            rotacao_simples_direita(atual.filho_esquerdo, atual);
            imprimir();
            System.out.println("is here");
            System.out.println(atual.pai.filho_direito.chave);
            System.out.println(atual.pai.chave);
            imprimir();
            rotacao_simples_esquerda(atual.pai.filho_direito, atual.pai);
            return;
        }
        if(atual.fb <= 0 && antecessor.fb > 0) { // primeiro faz a rotação do atual e depois a do antecessor
            System.out.println("Realizando RDD");
            rotacao_simples_esquerda(atual.filho_direito, atual);
            rotacao_simples_direita(atual.pai.filho_esquerdo,atual.pai);
            return;
        }
        if(atual.fb <= 0 && antecessor.fb < 0) { // negativo = esquerda
            System.out.println("Realizando RSE");
            rotacao_simples_esquerda(atual, antecessor);
            return;
        }
        System.out.println("OXe nenhum");

    }

    public void rotacao_simples_direita(No atual, No antecessor) {
        atual.pai = antecessor.pai;
        if(antecessor.pai != null ) { // caso o antecessor fosse raiz
            antecessor.pai.filho_direito = atual; // o filho esquerdo dele vai ser o atual
        }

        if(antecessor.pai == null) { // se o pai do antecessor for nulo é pq o antecessor é raiz12
            this.root = atual;
        }

        if(atual.filho_direito != null) {
            antecessor.filho_esquerdo = atual.filho_direito; // salva e esse filho  esquerdo atual e ele sera filho do antecessor
        }else{
            antecessor.filho_esquerdo = null;
        }

        atual.filho_direito = antecessor;
        antecessor.fb = antecessor.fb - 1 - max(atual.fb, 0);
        atual.fb = atual.fb - 1 + min(antecessor.fb, 0);
    }

    public void rotacao_simples_esquerda(No atual, No antecessor) {
        atual.pai = antecessor.pai;
        if(antecessor.pai != null) {// caso o antecessor nao for raiz
            antecessor.pai.filho_esquerdo = atual; // o filho direito dele vai ser o atual
        }

        if(antecessor.pai == null) { // se o pai do antecessor for nulo é pq o antecessor é raiz
            this.root = atual;
        }

        if(atual.filho_esquerdo != null) { // se o atual tiver filho esquerdo
            antecessor.filho_direito = atual.filho_esquerdo; // esse filho esquerdo agora sera filho direito do antecessor
        }else{
            antecessor.filho_direito = null;
        }

        atual.filho_esquerdo = antecessor; // antecessor agora é filho do atual
        antecessor.fb = antecessor.fb + 1 - min(atual.fb, 0);
        atual.fb = atual.fb + 1 + max(antecessor.fb, 0);
    }

    public void remocao(int k) {
        No v = treeSearch(k, root);
        if (v == null) return;

        if (isExternal(v)) { // nao tem filhos
            System.out.println("CASO 01 DE REMOÇÃO: ");
            Caso01remocao(v);
        } else if (v.filho_esquerdo == null || v.filho_direito == null) { // tem um filho
            System.out.println("CASO 02 DE REMOÇÃO: ");
            Caso02remocao(v);
        } else {
            System.out.println("CASO 03 DE REMOÇÃO: "); 
            Caso03remocao(v); // tem 2 filhos
        }
        qtd_no--;
    }

    public void Caso01remocao(No k) {
        if (k.pai == null) {
            root = null;
            return;
        }
        if (k.pai.filho_esquerdo == k) {
            atualiza_FB_remocao(k.pai.filho_esquerdo);
            k.pai.filho_esquerdo = null;
        } else {
            atualiza_FB_remocao(k.pai.filho_direito);
            k.pai.filho_direito = null;
        }
    }

    public void Caso02remocao(No k) {
        No filho;
        if(k.filho_esquerdo != null){
            filho = k.filho_esquerdo;
        }else{
            filho = k.filho_direito;
        }
        if (k.pai == null) {
            root = filho;
            root.pai = null;
        } else {
            if (k.pai.filho_esquerdo == k) {
                atualiza_FB_remocao(k);
                k.pai.filho_esquerdo = filho;
            }
            else {
                atualiza_FB_remocao(k);
                k.pai.filho_direito = filho;
            }
            filho.pai = k.pai;
        }
    }

    public void Caso03remocao(No v) {
        No substituto = maiorMenor(v.filho_direito);
        v.chave = substituto.chave;

        if (isExternal(substituto)) { // remocao fisica do maior menor
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

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("(Árvore vazia)");
            return;
        }

        int altura = height(root) + 1;
        //System.out.println(altura);
        // elevado pra tem espaçamento suficiente
        int larguraTotal = (int) Math.pow(2, altura) * 4;

        java.util.Queue<No> fila = new java.util.LinkedList<>();
        fila.add(root);
        int noNulls = 0; // vai contar pra ver se a fila ta so com nulls

        for (int nivel = 0; nivel <= altura; nivel++) {
            int tamanho = fila.size();

            // se os nós da mesmo nivel for nulo ele ja para aqui mesmo
            if (noNulls == tamanho) break;
            noNulls = 0;


            // o espaço lateral antes do primeiro no
            int espacoAntes = (larguraTotal / (int) Math.pow(2, nivel + 1));
            // o espaço entre os nós do mesmo nivel
            int espacoEntreNo = (larguraTotal / (int) Math.pow(2, nivel));

            // espaço innicial dos nos
            printEspacos(espacoAntes);

            for (int i = 0; i < tamanho; i++) {
                No atual = fila.poll();

                if (atual != null) {
                    System.out.printf("%-4s", atual.chave + "[" + atual.fb + "]"); // chave e o fb

                    fila.add(atual.filho_esquerdo);
                    fila.add(atual.filho_direito);
                } else {
                    // mostraos espaços no lugar do no nulo para manter a estrutura
                    System.out.printf("%-4s", "    ");

                    fila.add(null);
                    fila.add(null);
                    noNulls++; // nos nulls
                }

                // espaço dos nós da mesma linha
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

    private void printEspacos(int qtd) {
        // garante que a qtd não seja negativa
        qtd = Math.max(0, qtd);
        for (int i = 0; i < qtd; i++) {
            System.out.print(" ");
        }
    }
}
