package app;

public interface RepositorioConversa {
    public void pegar(String hash);
    public void adicionar(int user1, int user2, String arquivo);
    public boolean escrever(int user1, int user2,String msg, String nome, String arquivo);
    public void acabar(int user,String arquivo);
    public boolean verifica(int userOutro);
    public void apagar(String arquivo);
    public void mostrarMensagens();
}