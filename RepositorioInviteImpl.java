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

public class RepositorioInviteImpl implements RepositorioInvite{
    List<Invite> convites = new ArrayList<>();
    
    @Override
    public boolean pegar(){
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\invite.txt");
        String teste;
        int user1;
        int user2;
        boolean b = true;
        Invite novo;
        try {
            BufferedReader leitura = new BufferedReader(new FileReader(url));
            while(b){
                teste = leitura.readLine();
                if(teste == null){
                    break;
                }
                user1 = Integer.parseInt(teste);
                user2 = Integer.parseInt(leitura.readLine());
                novo = new Invite(user1, user2);
                this.convites.add(novo);
            }
            leitura.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Não foi possível encontrar arquivo de convites");
        } catch (IOException ex) {
            System.out.println("Não foi possível pegar a lista com todos os convites´para conversa");
        }
        
        return true;
    }
    
    @Override
    public boolean fazerInvite(int user1, int user2){
        for(int i = 0; i < convites.size(); i++){
            if(user2 == convites.get(i).getUser2()){
                return false;
            }
        }
        Invite novo = new Invite(user1, user2);
        convites.add(novo);
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\invite.txt");
        try {
            int num = convites.size() - 1;
            BufferedWriter escrita = new BufferedWriter(new FileWriter(url, true));
            escrita.write(this.convites.get(num).getUser1() + System.getProperty("line.separator"));
            escrita.write(this.convites.get(num).getUser2() + System.getProperty("line.separator"));
            escrita.flush();
            escrita.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível enviar convite para começar uma conversa");
        }
        return true;
    }
    @Override
    public int verificarInvite(int identidade){
        for(int i = 0; i < convites.size(); i++){
            if(identidade == convites.get(i).getUser2()){
                return convites.get(i).getUser1();
            }
        }
        return -1;
    }
    @Override
    public boolean aceitarInvite(String res, int user1, int user2){
        int id = -1;
        for(int i = 0; i < this.convites.size(); i++){
            if(user1 == this.convites.get(i).getUser1() && user2 == this.convites.get(i).getUser2()){
                id = i;
            }
        }
        while(!res.equals("sim") || !res.equals("nao")){
            res = io.nextLine();
            switch (res) {
                case "sim":
                    this.convites.remove(id);
                    //remover do arquivo
                    //nova conversa
                    return true;
                case "nao":
                    this.convites.remove(id);
                    //remover do arquivo
                    return false;
                default:
                    System.out.println("Opção errada");
                    break;
            }
        }
        return false;
    }
    @Override
    public void apagar(int user1, int user2){
        int id = 0;
        File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\invite.txt");
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\novoinvite.txt");
        for(int i = 0; i < this.convites.size(); i++){
                if(user1 == this.convites.get(i).getUser1() && user2 == this.convites.get(i).getUser2()){
                    this.convites.remove(i);
                    id = i;
                }
        }
        try {
            novoArq.createNewFile();
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            for(int i = 0; i < this.convites.size(); i++){
                if(i != id){
                    escrita.write(this.convites.get(i).getUser1() + System.getProperty("line.separator"));
                    escrita.write(this.convites.get(i).getUser2() + System.getProperty("line.separator"));
                }
            }
            escrita.flush();
            escrita.close();
            url.delete();
            novoArq.renameTo(new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\invite.txt"));
        } catch (IOException ex) {
            System.out.println("Não foi possível atualizar lista de convites para bate-papo");
        }
    }
    @Override
    public boolean aceitado(int user1, int user2){
        for(int i = 0; i < this.convites.size(); i++){
            if(user1 == this.convites.get(i).getUser1() && user2 == this.convites.get(i).getUser2()){
                return true;
            }
        }
        return false;
    }
    @Override
    public int verifica(int user1){
        for(int i = 0; i < convites.size(); i++){
            if(user1 == convites.get(i).getUser1()){
                return convites.get(i).getUser2();
            }
        }
        return -1;
    }
}
