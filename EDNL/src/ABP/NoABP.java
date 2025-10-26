package ABP;

public class NoABP {
    public NoABP filho_esquerdo;
    public NoABP filho_direito;
    public int chave;
    public NoABP pai;

    public NoABP(int chave) {
        this.chave = chave;
    }

    public NoABP[] getFilhos() {
        NoABP[] filhos = new NoABP[2];
        filhos[0] = this.filho_esquerdo;
        filhos[1] = this.filho_direito;
        return filhos;
    }

    public int qtd_filhos() {
        int cont = 0;
        for(NoABP filho : getFilhos()) {
            if (filho != null)
                cont++;
        }
        return cont;
    }
}
