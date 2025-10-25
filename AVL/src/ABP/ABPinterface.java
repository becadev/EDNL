package ABP;

public interface ABPinterface<T extends NoABP> {
    //  Métodos da AB
    public int leftchild(T v);
    public int rightchild(T v);
    public boolean hasLeft(T v);
    public boolean hasRight(T v);

    //  Métodos genéricos
    public int size();
    public int height(T v) ;
    public boolean isEmpty();

    public T root();
    public T parent(int no);


    // Métodos de consulta
    public boolean isInternal(T no);
    public boolean isExternal(T no);
    public boolean isRoot(T no);
    public int depth(T no);

    // Métodos de atualização
    public void replace(T no, int o);

    // Método de busca
    public T treeSearch(int k, T v); // k = elemento que será buscado, v = pai dele

    // Método de inserção
    public void insercao(T v, T k);
    // primeiro verifica se K ta na arvore

    // Método de remoção
    public void remocao(int k);
}
