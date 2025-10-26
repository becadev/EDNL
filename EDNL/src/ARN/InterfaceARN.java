package ARN;

public interface InterfaceARN {
    // Métodos da AB
    public Object leftchild(NoARN v);
    public Object rightchild(NoARN v);
    public boolean hasLeft(NoARN v);
    public boolean hasRight(NoARN v);

   // Métodos genéricos
    public int size();
    public int height(NoARN v);
    public boolean isEmpty();

    public // Métodos de acesso
    public NoARN root();
    public NoARN parent(int k);

     // Métodos de consulta
    public boolean isInternal(NoARN no);
    public boolean isExternal(NoARN no);
    public boolean isRoot(NoARN no);
    public int depth(NoARN no);
    public int profundidade(NoARN v);

   // Métodos de atualização
    public void replace(NoARN no, int o);

    public // Método de busca
    public NoARN treeSearch(int k, NoARN v);

    // Inserção
    public NoARN inserir(int v);
    public void insercao(NoARN nantecessor, NoARN natual);
    public NoARN atualiza_FB_insercao(NoARN atual);

     // Balanceamento
    public NoARN balancear(NoARN atual, NoARN antecessor);
    public NoARN rotacao_simples_direita(NoARN A, NoARN B);
    public NoARN rotacao_simples_esquerda(NoARN atual, NoARN antecessor);

    // Remoção
    public void remocao(int k);
    public NoARN atualiza_FB_remocao(NoARN atual);
    public void Caso01remocao(NoARN k);
    public void Caso02remocao(NoARN removido);
    public void Caso03remocao(NoARN v);
    public NoARN maiorMenor(NoARN v);
    
    // Estrutura e exibição
    public void estrutura_no(Object[][] AVL, NoARN atual, int coluna, int linha);
    public void mostrar();
} 