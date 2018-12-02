package app;

public interface RepositorioInvite {
    public boolean pegar();
    public boolean fazerInvite(int user1, int user2);
    public int verificarInvite(int identidade);
    public boolean aceitarInvite(String res, int user1, int user2);
    public void apagar(int user1, int user2);
    public boolean aceitado(int user1, int user2);
    public int verifica(int user1);
}