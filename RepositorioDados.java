package app;

import java.util.Scanner;

public interface RepositorioDados{
    public static Scanner io = new Scanner(System.in);
    public void adicionar(Usuario novo);
    public void excluir(int id);
    public boolean addArquivo(Usuario novo);
    public boolean pegar();
    public int verifica(String log, String sen);
    public int pegaId();
    public boolean teste(String login);
    public int tipoUser(int id);
    public int buscarConversa();
    public void mostrar(int id);
    public String nome(int id);
    public void contaPonto(double ponto, int user);
    public void verificaPonto(int user);
}
