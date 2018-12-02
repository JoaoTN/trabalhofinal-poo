package app;

public abstract class Usuario {
    private int id;
    private String login;
    private String senha;
    private String nome;
    private int idade;
    private double ponto;
    private int numPonto;
    private int tipo;
    private String historia;
    private String tema;
    public Usuario(int id, String login, String senha, String nome, int idade, String historia, double ponto, int numPonto, int tipo, String tema){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
        this.ponto = ponto;
        this.numPonto = numPonto;
        this.tipo = tipo;
        this.historia = historia;
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPonto() {
        return ponto;
    }

    public void setPonto(double ponto) {
        this.ponto = ponto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public int getNumPonto() {
        return numPonto;
    }

    public void setNumPonto(int numPonto) {
        this.numPonto = numPonto;
    }
    
}
