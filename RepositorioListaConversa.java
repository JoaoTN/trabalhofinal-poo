package app;


public interface RepositorioListaConversa {
     public void adicionar(int user1, int user2);
     public void pegar();
     public String pegaHash(int user1, int user2);
     public int verifica(int id);
     public int verifica2(int id);
     public void apagar(int user1, int user2);
     public boolean teste(int id);
}



