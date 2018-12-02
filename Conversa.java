package app;

import java.util.ArrayList;
import java.util.List;

public class Conversa {
    private int idUser1;
    private int idUser2;
    private int flag;
    List<Mensagem> mensagens = new ArrayList<>();
    public Conversa(int idUser1, int idUser2, int flag){
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.flag = flag;
    }
    public Conversa(int idUser1, int idUser2){
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.flag = -1;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }
    public void adicionar(String msg){
        Mensagem novo = new Mensagem(msg);
        this.mensagens.add(novo);
    }
    public int tamanho(){
        return this.mensagens.size();
    }
    public String mensagemPega(int id){
        return this.mensagens.get(id).getMensagem();
    }
}
