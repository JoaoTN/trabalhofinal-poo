
package app;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositorioDadosImpl implements RepositorioDados{
    List<Usuario> dados = new ArrayList<>();
    @Override
    public void adicionar(Usuario novo) {
        this.dados.add(novo);
    }
    @Override
    public void excluir(int id){
        for(int i=0; i < this.dados.size(); i++){
            if(dados.get(i).getId() == id){
                this.dados.remove(i);
            }
        }
    }

    @Override
    public boolean addArquivo(Usuario novo){
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\users.txt");
        try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(url, true));
            int a = dados.size() - 1;
            escrita.write(this.dados.get(a).getNome() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getId() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getTipo() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getLogin() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getSenha() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getIdade() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getHistoria() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getPonto() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getNumPonto() + System.getProperty("line.separator"));
            escrita.write(this.dados.get(a).getTema() + System.getProperty("line.separator"));
            escrita.flush();
            escrita.close();
            } catch (IOException ex) {
            System.out.println("Não foi possível adicionar usuário");
            }
        return true;
    }

    @Override
    public boolean pegar(){
            File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\users.txt");
            boolean c = true;
            int id;
            int tipo;
            String login;
            String senha;
            String nome;
            int idade;
            String historia;
            double ponto;
            int numPonto;
            String tema;
            Usuario todo;
        try {
            BufferedReader leitura = new BufferedReader(new FileReader(url));
            //leitura.readLine()
            while(c){
                nome = leitura.readLine();
                if(nome == null){
                    break;
                }
                id = Integer.parseInt(leitura.readLine());
                tipo = Integer.parseInt(leitura.readLine());
                login = leitura.readLine();
                senha = leitura.readLine();
                idade = Integer.parseInt(leitura.readLine());
                historia = leitura.readLine();
                ponto = Double.parseDouble(leitura.readLine());
                numPonto = Integer.parseInt(leitura.readLine());
                tema = leitura.readLine();
                if(tipo == 1){
                    todo = new Novato(id, login, senha, nome, idade, historia, ponto, numPonto,tipo, tema);
                }
                else{
                    todo = new Master(id, login, senha, nome, idade, historia, ponto, numPonto, tipo, tema);
                }
                this.dados.add(todo);
            }
            leitura.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException ex) {
            System.out.println("Erro: Nenhum usuário encontrado");
        }
            return true;
    }

    @Override
    public int verifica(String log, String sen) {
        for(int i = 0; i < this.dados.size(); i++){
            if((log.equals(this.dados.get(i).getLogin())) && (sen.equals(this.dados.get(i).getSenha()))){
                return this.dados.get(i).getId();
            }
        }
        return 0;
    }

    @Override
    public int pegaId(){
        return this.dados.size();
    }

    @Override
    public boolean teste(String login) {
        for(int i = 0; i < this.dados.size(); i++){
            if((login.equals(this.dados.get(i).getLogin()))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int tipoUser(int id){
        for(int i = 0; i < this.dados.size(); i++){
            if((id == this.dados.get(i).getId())){
                return this.dados.get(i).getTipo();
            }
        }
        return 0;
    }

    @Override
    public int buscarConversa(){
        String recebe;
        for(int i = 0; i < this.dados.size(); i++){
            if(this.dados.get(i).getTipo() == 2){
                System.out.println("Nome: " + this.dados.get(i).getNome());
                System.out.println("Média de avaliação: " + this.dados.get(i).getPonto());
                System.out.println("Sobre: " + this.dados.get(i).getHistoria());
                System.out.println("Tema: " + this.dados.get(i).getTema());
                System.out.println("Começar conversa? Digite 'sim' ou 'nao'");
                recebe = io.nextLine();
                if(recebe.equals("sim")){
                    return this.dados.get(i).getId();
                }
            }
        }
        return -1;
    }

    @Override
    public void mostrar(int id){
        for(int i = 0; i < dados.size(); i++){
            if(id == dados.get(i).getId()){
                System.out.println("Nome: " + this.dados.get(i).getNome());
                System.out.println("Média de avaliação: " + this.dados.get(i).getPonto());
                System.out.println("Sobre: " + this.dados.get(i).getHistoria());
                System.out.println("Tema: " + this.dados.get(i).getTema());
                System.out.println("Começar conversa? Digite 'sim' ou 'nao'");
                break;
            }
        }
    }

    @Override
    public String nome(int id) {
        for(int i = 0; i < dados.size(); i++){
            if(id == dados.get(i).getId()){
                return dados.get(i).getNome();
            }
        }
        return null;
    }

    @Override
    public void contaPonto(double ponto, int user){
        double pontua = 0;
        int id = 0;
        int num = 0;
        for(int i = 0; i < dados.size(); i++){
            if(user == dados.get(i).getId()){
                pontua = dados.get(i).getPonto();
                num = dados.get(i).getNumPonto();
                id = i;
            }
        }
        if(pontua != 0){
            pontua = (pontua + ponto)/2;
        }else{
            pontua = pontua + ponto;
        }
        if(num > 5){
            if(pontua > 4.0){
                dados.get(id).setTipo(2);
            }
            else{
                dados.get(id).setTipo(1);
            }
        }
        dados.get(id).setPonto(pontua);
        dados.get(id).setNumPonto(num+1);
        
    }
    @Override
    public void verificaPonto(int user){
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\users.txt");
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\novousers.txt");
        try {
            novoArq.createNewFile();
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            for(int i = 0; i < dados.size(); i++){
                escrita.write(this.dados.get(i).getNome() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getId() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getTipo() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getLogin() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getSenha() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getIdade() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getHistoria() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getPonto() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getNumPonto() + System.getProperty("line.separator"));
                escrita.write(this.dados.get(i).getTema() + System.getProperty("line.separator"));
            }
            escrita.flush();
            escrita.close();
            url.delete();
            novoArq.renameTo(new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\users.txt"));
        } catch (IOException ex) {
            System.out.println("Não foi possível atualizar pontuação");
        }
    }


}

