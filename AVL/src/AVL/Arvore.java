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
        if (v == null)
            return 0; // caso base
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
                atualiza_FB_insercao(atual);
            } else {
                insercao(antecessor.filho_esquerdo, atual);
            }
        } else if (atual.chave > antecessor.chave) {
            if (antecessor.filho_direito == null) {
                System.out.println(atual.chave +  " " + atual.fb);
                System.out.println(antecessor.chave +  " " + antecessor.fb);
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
        System.out.println(novo.chave + " " + novo.pai.chave);
        imprimir();
        if (novo.chave < novo.pai.chave)
            novo.pai.fb++;
        if (novo.chave > novo.pai.chave)
            novo.pai.fb--;
        if (novo.pai != null && novo.pai.fb == 0)
            return novo;
        if (novo.pai != null && (novo.pai.fb >= 2 || novo.pai.fb <= -2)) {
            balancear(novo, novo.pai);
        }
        return atualiza_FB_insercao(novo.pai);
    }

    public No atualiza_FB_remocao(No atual) {
        No pai = atual.pai;
        if(pai == null) 
            return atual;
        
        if(pai != null) {
            if (atual.fb != 0 && (atual.fb < 2 && atual.fb > -2 )){ // caso base
                System.out.println("parada" + atual.chave + " " + atual.fb + " " +  pai.chave + " " + pai.fb);
                return atual;
            }
            if (atual.chave < pai.chave){
                System.out.println(" antes a esquerda");
                System.out.println(pai.fb + " " + pai.chave);
                System.out.println(atual.fb + " " + atual.chave);
                atual.fb--;
                System.out.println(" depois");
                System.out.println(pai.fb + " " + pai.chave);
                System.out.println(atual.fb + " " + atual.chave);
            }

            if (atual.chave > pai.chave) {
                System.out.println(" antes a direita");
                System.out.println(pai.fb + " " + pai.chave);
                System.out.println(atual.fb + " " + atual.chave);
                atual.fb++;
                System.out.println(" depois");
                System.out.println(pai.fb + " " + pai.chave);
                System.out.println(atual.fb + " " + atual.chave);
            }
        }
        
        if (pai.fb >= 2 || pai.fb <= -2) {
            System.out.println("balancear" + atual.chave + " " + atual.fb + " " +  atual.pai.chave + " " + atual.pai.fb);
            if(pai.fb >= 2) // desbalanceou pra esq
                balancear(atual.pai, atual.pai.filho_esquerdo); 
            else if(pai.fb <= -2) // desbalanceou pra dir
                balancear(atual, atual.pai.filho_direito);
            return atual;
        }
        return atualiza_FB_remocao(pai);
    }

    public void balancear(No atual, No antecessor) { // comparar se sao sinais iguais ou sinais diferentes
        if ( atual.fb > 0 && antecessor.fb >= 2) { // positivo = a rotação direita simples
            imprimir();
            System.out.println("Realizando RSD");
             System.out.println("parada" + atual.chave + " " + atual.fb + " " +  antecessor.chave + " " + antecessor.fb);
            rotacao_simples_direita(atual, antecessor);
            return;
        }
        if (atual.fb > 0 && antecessor.fb <= -2) {
            imprimir(); // primeiro faz a rotação do atual e depois a do antecessor
            // então a esquerda que ta desbalanceada
            System.out.println("Realizando RDE");
            System.out.println("parada" + atual.chave + " " + atual.fb + " " +  antecessor.chave + " " + antecessor.fb);
            System.out.println("1ª rotação direita");
            rotacao_simples_direita(atual.filho_esquerdo, atual);
            imprimir();
            System.out.println("2ª rotação esquerda");
            rotacao_simples_esquerda(atual.pai.filho_direito, atual.pai);
            return;
        }
        if (atual.fb < 0 && antecessor.fb >= 2) { // primeiro faz a rotação do atual e depois a do antecessor
            System.out.println("Realizando RDD");
            imprimir();
            System.out.println("parada" + atual.chave + " " + atual.fb + " " +  antecessor.chave + " " + antecessor.fb);
            System.out.println("1ª rotação esquerda");
            rotacao_simples_esquerda(atual.filho_direito, atual);
            imprimir();
            System.out.println(antecessor.filho_esquerdo.fb +" "+ antecessor.fb);
            System.out.println("2ª rotação direita");
            rotacao_simples_direita(antecessor.filho_esquerdo, antecessor);
            return;
        }
        if ( atual.fb < 0 && antecessor.fb <= -2) { // negativo = esquerda 
            imprimir();
            System.out.println("Realizando RSE");
            System.out.println("parada" + atual.chave + " " + atual.fb + " " +  antecessor.chave + " " + antecessor.fb);
            rotacao_simples_esquerda(atual, antecessor);
        }
    }

    public void rotacao_simples_direita(No A, No B) {
        A.pai = B.pai;
        System.out.println(A.chave);
        System.out.println(B.chave);
        if (B.pai != null) { // caso o B n for raiz 
            B.pai.filho_direito = A; // o filho direito do pai dele vai ser o A
            B.pai = A;
        }
        if (B.pai == null) { // se o pai do B for nulo é pq o B é raiz
            this.root = A;
            A.pai = null;
            B.pai =  A;
        }
        if (A.filho_direito != null) {
            B.filho_esquerdo = A.filho_direito; // salva e esse filho esquerdo A e ele sera filho do B
        } else {
            B.filho_esquerdo = null;
        }
        A.filho_direito = B;
        B.fb = B.fb - 1 - max(A.fb, 0);
        A.fb = A.fb - 1 + min(B.fb, 0);
    }

    public void rotacao_simples_esquerda(No atual, No antecessor) {
        atual.pai = antecessor.pai;
        System.out.println(antecessor.chave);
        System.out.println(atual.chave);
        if (antecessor.pai != null) {// caso o antecessor nao for raiz 
            System.out.println(antecessor.pai.chave);
            antecessor.pai.filho_esquerdo = atual; // o filho esquerdo dele vai ser o atual
            antecessor.pai = atual;
        }
        if (antecessor.pai == null) { // se o pai do antecessor for nulo é pq o antecessor é raiz
            this.root = atual;
            atual.pai = null;
        }
        if (atual.filho_esquerdo != null) { // se o atual tiver filho esquerdo 
            antecessor.filho_direito = atual.filho_esquerdo; // esse filho esquerdo agora sera filho direito do antecessor
        } else {
            antecessor.filho_direito = null;
        }
        atual.filho_esquerdo = antecessor; // antecessor agora é filho do atual
        antecessor.fb = antecessor.fb + 1 - min(atual.fb, 0);
        atual.fb = atual.fb + 1 + max(antecessor.fb, 0);
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
            System.out.print("filho esquerdo");
            k.pai.fb--;
            atualiza_FB_remocao(k.pai);
            k.pai.filho_esquerdo = null;
        } else {
            System.out.print("filho direto");
            k.pai.fb++;
            atualiza_FB_remocao(k.pai);
            k.pai.filho_direito = null;
        }
    }

    public void Caso02remocao(No k) {
        No filho;
        if (k.filho_esquerdo != null) {
            filho = k.filho_esquerdo;
        } else {
            filho = k.filho_direito;
        }
        if (k.pai == null) {
            root = filho;
            root.pai = null;
        } else {
            if (k.pai.filho_esquerdo == k) {
                k.pai.fb--;
                k.pai.filho_esquerdo = filho;
                atualiza_FB_remocao(k.pai); 
            } else{
                k.pai.filho_direito = filho;
                k.pai.fb++;
                atualiza_FB_remocao(k.pai); 
            }
            filho.pai = k.pai;
        }
    }

    public void Caso03remocao(No v) {
        No substituto = maiorMenor(v.filho_direito);
        v.chave = substituto.chave;
        if (isExternal(substituto)) { // maior menor n tem filho
            Caso01remocao(substituto);
        } else { // maior menor tem dois filho
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
        java.util.Queue<No> fila = new java.util.LinkedList<>(); fila.add(root); 
        int noNulls = 0; // vai contar pra ver se a fila ta so com nulls 

        for (int nivel = 0; nivel <= altura; nivel++) { 
            int tamanho = fila.size(); 
            // se os nós da mesmo nivel for nulo ele ja para aqui mesmo 
            if (noNulls == tamanho) 
                break; 
            noNulls = 0; 
            // o espaço lateral antes do primeiro no 
            int espacoAntes = (larguraTotal / (int) Math.pow(2, nivel + 1)); // o espaço entre os nós do mesmo nivel 
            int espacoEntreNo = (larguraTotal / (int) Math.pow(2, nivel)); 
            // espaço innicial dos nos 
            printEspacos(espacoAntes); 
            for (int i = 0; i < tamanho; i++) {
                No atual = fila.poll(); 
                if (atual != null) { 
                    System.out.printf("%-4s", atual.chave + "[" + atual.fb + 
                    "]"); // chave e o fb 
                    fila.add(atual.filho_esquerdo); 
                    fila.add(atual.filho_direito); 
                } else { 
                    // mostraos espaços no lugar do no nulo para manter a estrutura 
                    System.out.printf("%-4s", " "); 
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
