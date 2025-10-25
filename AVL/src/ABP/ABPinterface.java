package ABP;

public interface ABPinterface{
    //  Métodos da AB
    public int leftchild(NoABP v);
    public int rightchild(NoABP v);
    public boolean hasLeft(NoABP v);
    public boolean hasRight(NoABP v);

    //  Métodos genéricos
    public int size();
    public int height(NoABP v) ;
    public boolean isEmpty();

    public NoABP root();
    public NoABP parent(int no);


    // Métodos de consulta
    public boolean isInternal(NoABP no);
    public boolean isExternal(NoABP no);
    public boolean isRoot(NoABP no);
    public int depth(NoABP no);

    // Métodos de atualização
    public void replace(NoABP no, int o);

    // Método de busca
    public NoABP treeSearch(int k, NoABP v); // k = elemento que será buscado, v = pai dele

    // Método de inserção
    public void insercao(NoABP v, NoABP k);
    // primeiro verifica se K ta na arvore

    // Método de remoção
    public void remocao(int k);
}
