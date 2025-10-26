package ARN;

public class NoARN {
    public NoARN filho_esquerdo;
    public NoARN filho_direito;
    public int chave;
    public int cor;
    public NoARN pai;

    public NoARN(int chave) {
        this.chave = chave;
    }

    public NoARN[] getFilhos() {
        NoARN[] filhos = new NoARN[2];
        filhos[0] = this.filho_esquerdo;
        filhos[1] = this.filho_direito;
        return filhos;
    }

    public int qtd_filhos() {
        int cont = 0;
        for(NoARN filho : getFilhos()) {
            if (filho != null)
                cont++;
        }
        return cont;
    }

    public boolean isRubro() {
        return this.cor == 1;
    }

    public boolean isNegro() {
        return this.cor == 0;
    }
}
