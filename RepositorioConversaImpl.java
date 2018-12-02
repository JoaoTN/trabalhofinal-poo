package app;

import static app.Principal.io;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositorioConversaImpl implements RepositorioConversa{
    List<Conversa> conv = new ArrayList<>();
    @Override
    public void pegar(String hash){
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + hash + "n.txt");
        int user1;
        int user2;
        int flag;
        boolean b = true;
        int id;
        String msg;
        String teste;
        Conversa novo;
        try {
            BufferedReader leitura = new BufferedReader(new FileReader(url));
            user1 = Integer.parseInt(leitura.readLine());
            user2 = Integer.parseInt(leitura.readLine());
            flag = Integer.parseInt(leitura.readLine());
            novo = new Conversa(user1, user2, flag);
            this.conv.add(novo);
            id = this.conv.size() - 1;
            while(b){
                teste = leitura.readLine();
                if(teste == null){
                    break;
                }
                msg = teste;
                this.conv.get(id).adicionar(msg);
            }
            leitura.close();
        }catch (FileNotFoundException ex) {
             System.out.println("Não foi possível encontrar arquivo de mensagens");
         } catch (IOException ex) {
             System.out.println("Não foi possível  pegar as mensagens da conversa");
         }
         
    }
    @Override
    public void adicionar(int user1, int user2, String arquivo){
        Conversa novo = new Conversa(user1, user2);
        this.conv.add(novo);
        int id = this.conv.size() - 1;
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "n.txt");
        try {
            novoArq.createNewFile();
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            escrita.write(this.conv.get(id).getIdUser1() + System.getProperty("line.separator"));
            escrita.write(this.conv.get(id).getIdUser2() + System.getProperty("line.separator"));
            escrita.write(this.conv.get(id).getFlag() + System.getProperty("line.separator"));
            escrita.flush();
            escrita.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível criar conversa");
        }
    }
    @Override
    public boolean escrever(int user1, int user2,String msg, String nome, String arquivo){
        String mensagem = nome + ": " + msg;
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "n.txt");
        try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            escrita.write(mensagem + System.getProperty("line.separator"));
            escrita.flush();
            escrita.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível enviar a mensagem");
        }
        for(int i = 0; i < this.conv.size();i++){
            if(user1 == this.conv.get(i).getIdUser1() && user1 == this.conv.get(i).getIdUser1()){
                this.conv.get(i).adicionar(mensagem);
            }
        }
        return true;
    }
    @Override
    public void acabar(int user,String arquivo){
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "n.txt");
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "novo.txt");
        try {
            novoArq.createNewFile();
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            escrita.write(this.conv.get(0).getIdUser1() + System.getProperty("line.separator"));
            escrita.write(this.conv.get(0).getIdUser2() + System.getProperty("line.separator"));
            escrita.write(user + System.getProperty("line.separator"));
            for(int i = 0; i < this.conv.get(0).tamanho(); i++){
                escrita.write(this.conv.get(0).mensagemPega(i) + System.getProperty("line.separator"));
            }
            escrita.flush();
            escrita.close();
            url.delete();
            novoArq.renameTo(new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "n.txt"));
        }
        catch (FileNotFoundException ex) {
             System.out.println("Não foi possível encontrar arquivo de mensagens");
         } catch (IOException ex) {
             System.out.println("Não foi possível terminar a conversa");
         }
    }
    @Override
    public boolean verifica(int userOutro){
        for(int i = 0; i < conv.size(); i++){
            if(userOutro == conv.get(i).getFlag()){
                return true;
            }
        }
        return false;
    }
    @Override
    public void apagar(String arquivo){
        this.conv.remove(0);
        File arq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\" + arquivo + "n.txt");
        arq.delete();
    }
    @Override
    public void mostrarMensagens(){
        int tam = this.conv.get(0).tamanho();
        for(int i = 0; i < tam; i++){
            System.out.println(this.conv.get(0).mensagemPega(i));
        }
    }
}
