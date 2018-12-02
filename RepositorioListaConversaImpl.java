package app;

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

public class RepositorioListaConversaImpl implements RepositorioListaConversa{
    List<Invite> idConversas = new ArrayList<>();
     
    @Override
     public void adicionar(int user1, int user2){
         int id;
         Invite novo = new Invite(user1, user2);
         this.idConversas.add(novo);
         id = idConversas.size() - 1;
         File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\listaConversas.txt");
         try {
            BufferedWriter escrita = new BufferedWriter(new FileWriter(url, true));
            escrita.write(this.idConversas.get(id).getUser1() + System.getProperty("line.separator"));
            escrita.write(this.idConversas.get(id).getUser2() + System.getProperty("line.separator"));
            escrita.flush();
            escrita.close();
         } catch (IOException ex) {
             System.out.println("Não foi possível adicionar uma nova lista de usuarios da conversa");
         }
     }
     
    @Override
     public void pegar(){
         File url = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\listaConversas.txt");
         int user1;
         int user2;
         boolean b = true;
         String teste;
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
                this.idConversas.add(novo);
            }
             leitura.close();
         } catch (FileNotFoundException ex) {
             System.out.println("Não foi possível encontrar arquivo de lista de quem tá na conversa");
         } catch (IOException ex) {
             System.out.println("Não foi possível pegar a lista de quem tá na conversa");
         }
         
     }
    @Override
     public String pegaHash(int user1, int user2){
        String res; 
        Invite novo = new Invite(user1, user2);
        res = Integer.toString(novo.hashCode());
        return res;
     }
    @Override
     public int verifica(int id){
         for(int i = 0; i < this.idConversas.size(); i++){
             if(id == this.idConversas.get(i).getUser1()){
                 return this.idConversas.get(i).getUser2();
             }
         }
         return -1;
     }
    @Override
     public int verifica2(int id){
         for(int i = 0; i < this.idConversas.size(); i++){
             if(id == this.idConversas.get(i).getUser2()){
                 return this.idConversas.get(i).getUser1();
             }
         }
         return -1;
     }
    @Override
    public void apagar(int user1, int user2){
        int id = -1;
        for(int i = 0; i < this.idConversas.size(); i++){
            if(user1 == this.idConversas.get(i).getUser1() && user2 == this.idConversas.get(i).getUser2()){
                 id = i;
            }
        }
        this.idConversas.remove(id);
        File arq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\listaConversas.txt");
        File novoArq = new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\listaConversasnovo.txt");
         try {
            novoArq.createNewFile();
            BufferedWriter escrita = new BufferedWriter(new FileWriter(novoArq, true));
            for(int i = 0; i < idConversas.size(); i++){
                if(id != i){
                    escrita.write(this.idConversas.get(i).getUser1() + System.getProperty("line.separator"));
                    escrita.write(this.idConversas.get(i).getUser2() + System.getProperty("line.separator"));
                }
            }
            escrita.flush();
            escrita.close();
            arq.delete();
            novoArq.renameTo(new File("C:\\Users\\Drielle\\Documents\\NetBeansProjects\\app\\src\\app\\listaConversas.txt"));
         }catch (IOException ex) {
             System.out.println("Não foi possível atualizar a lista de quem está nas conversas");
         }
    }
    @Override
    public boolean teste(int id){
        for(int i = 0; i < this.idConversas.size(); i++){
            if(id == this.idConversas.get(i).getUser2())
                return false;
        }
        return true;
    }
}
