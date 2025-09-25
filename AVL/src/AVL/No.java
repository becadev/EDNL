package AVL;

public class No {
    public No filho_esquerdo;
    public No filho_direito;
    public int chave;
    public No pai;
    public int fb;

    No(int chave) {
        this.chave = chave;
        this.filho_direito = null;
        this.filho_esquerdo = null;
        this.pai = null;
        this.fb = 0;
    }

    public No getFilho_esquerdo() {
        return filho_esquerdo;
    }

    public No getFilho_direito() {
        return filho_direito;
    }

    public int getChave() {
        return chave;
    }

    public No getPai() {
        return pai;
    }

    public void setFilho_esquerdo(No filho_esquerdo) {
        this.filho_esquerdo = filho_esquerdo;
    }

    public void setFilho_direito(No filho_direito) {
        this.filho_direito = filho_direito;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No[] getFilhos() {
        No[] filhos = new No[2];
        filhos[0] = this.filho_esquerdo;
        filhos[1] = this.filho_direito;
        return filhos;
    }

    public int qtd_filhos() {
        int cont = 0;
        for(No filho : getFilhos()) {
            if (filho != null)
                cont++;
        }
        return cont;
    }

    public boolean is_left() {
        return this.chave <  this.pai.chave;
    }

    public boolean is_rigth() {
        return this.chave > this.pai.chave;
    }

}