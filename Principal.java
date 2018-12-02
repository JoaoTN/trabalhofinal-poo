package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static Scanner io = new Scanner(System.in);
    public static RepositorioDados dado = new RepositorioDadosImpl();
    public static RepositorioInvite invite = new RepositorioInviteImpl();
    public static RepositorioListaConversa lista = new RepositorioListaConversaImpl();
    public static RepositorioConversa conversa = new RepositorioConversaImpl();
    public static void main(String[] args){
        boolean pass = false;
        String tec;
        String msgIni = "  ---------------------\n"
                       +"Digite seu Login e Senha";
        dado.pegar();
        invite.pegar();
        lista.pegar();
        int cont = 0;
        int identidade = 0;
        System.out.println("Bem vindo a Rede Social x, onde você desabaja sobre os seus problemas com outras pessoas!\n"
                         +"Digite 'cadastrar' para realizar o seu cadastro ou 'logar' para entrar na sua conta");
        while(!pass){
            tec = io.nextLine();
            switch(tec){
                case "logar" :
                    while(!pass){
                        String log;
                        String sen;
                        String mudar;
                        if(cont > 0){
                            System.out.println("Erro na " + cont + "º tentativa");
                            System.out.println("Digite 'sair' caso não tenha uma conta ou 'continuar' para outra tentativa de login");
                            mudar = io.nextLine();
                            if(mudar.equals("sair")){
                                break;
                            }
                        }
                        System.out.println(msgIni);
                        System.out.println("Login:");
                        log = io.nextLine();
                        System.out.println("Senha:");
                        sen = io.nextLine();
                        identidade = dado.verifica(log, sen);
                        if(identidade != 0){
                            pass = true;
                        }
                        cont++;
                    }
                    break;
                case "cadastrar" :
                    while(!pass){
                        int contador = 0;
                        String nome;
                        String login;
                        String senha;
                        int idade;
                        int id = dado.pegaId() + 1;
                        String historia;
                        String tema;
                        if(contador > 0){
                            System.out.println("Login já existe.\n"
                                               + "Digite um novo nome de login.");
                        }
                        System.out.println("Nome:");
                        nome = io.nextLine();
                        System.out.println("Login:");
                        login = io.nextLine();
                        System.out.println("Senha:");
                        senha = io.nextLine();
                        System.out.println("idade:");
                        idade = io.nextInt();
                        io.nextLine();
                        System.out.println("Conte a sua história:");
                        historia = io.nextLine();
                        System.out.println("Tema que você quer conversar: ");
                        tema = io.nextLine();
                        //Teste para ver se login já existe;
                        if(dado.teste(login)){
                            Usuario novo = new Novato(id, login, senha, nome, idade, historia, 0.0, 0, 1, tema);
                            dado.adicionar(novo);
                            dado.addArquivo(novo);
                            pass = true;
                            identidade = id;
                        }
                        contador++;
                    }
                    break;
                default:
                    System.out.println("Comando não entendido");
            }
        }
        int tipo = dado.tipoUser(identidade);
        int idConversa;
        int contado = 0;
        int uso;
        String sair = "";
        String res = "";
        String msg = "";
        String nome = "";
        String hash = "";
        String escolha = "";
        String fazer = "";
        boolean valida = false;
        double nota;
        nome = dado.nome(identidade);
        if(tipo == 1) {
            //Testar se existe conversa com usuário
            uso = lista.verifica(identidade);
            if(uso > -1){
                hash = lista.pegaHash(identidade, uso);
                conversa.pegar(hash);
                //verifica se conversa foi terminada;
                if(conversa.verifica(uso)){
                    System.out.println("Você tem uma conversa que acabou e não deu a pontuação da pessoa com quem você conversou\n"
                                                 + "Dê uma pontuação de zero a cinco para a pessoa que você conversou(De 0 a 5)");
                    nota = io.nextInt();
                    dado.contaPonto(nota, uso);
                    dado.verificaPonto(uso);
                    lista.apagar(identidade, uso);
                    conversa.apagar(hash);
                }
                else{
                    valida = true;
                    //Conversa ativa;
                    conversa.mostrarMensagens();
                    System.out.println("Conversa ativa");
                    while(!escolha.equals("terminar") && !escolha.equals("sair")){
                        System.out.println("Digite 'mensagem' para escrever uma mensagem ou 'sair' para sair do programa ou 'terminar' para terminar a conversa");
                        escolha = io.nextLine();
                        switch(escolha){
                            case "mensagem":
                                System.out.println("Digite uma mensagem!");
                                msg = io.nextLine();
                                if(conversa.escrever(uso, identidade, msg, nome, hash)){
                                    System.out.println(nome + ": " + msg);
                                }
                                break;
                            case "sair":
                                break;
                            case "terminar":
                                System.out.println("Você saiu da conversa \n"
                                                 + "Dê uma pontuação de zero a cinco para a pessoa que você conversou(De 0 a 5)");
                                nota = io.nextInt();
                                dado.contaPonto(nota, uso);
                                dado.verificaPonto(uso);
                                conversa.acabar(identidade, hash);
                                break;
                            default:
                                System.out.println("Comando não entendido");
                        }
                    }
                }
            }
            uso = invite.verifica(identidade);
            //Invitou
            if(invite.aceitado(identidade, uso)){
                valida = true;
                System.out.println("Usuário convidado para a conversa não respondeu ao convite ainda\n"
                                  +"Volte mais tarde");
            }
            //Pesquisa
            if(valida == false){
                System.out.println("Olá, navegue e converse sobre tudo que você deseja com outros usuários \n"
                                  +"Escolha alguém para conversar \n"
                                  +"Digite 'procurar' para procurar uma conversa ou 'sair' para encerrar o programa");
                fazer = io.nextLine();
                while(!fazer.equals("sair")){
                    switch(fazer){
                        case "procurar":
                            boolean match = false;
                            while(match == false && !fazer.equals("sair")){
                                if(contado != 0){
                                    System.out.println("Procure denovo por pessoas para conversar");
                                }
                                idConversa = dado.buscarConversa();
                                if(idConversa != -1){
                                    if(lista.teste(idConversa)){
                                        if(invite.fazerInvite(identidade, idConversa)){
                                        match = true;
                                        System.out.println("Convite enviado!");
                                        }else{
                                            System.out.println("Usuário indisponível para conversa");
                                        }
                                    }else{
                                        System.out.println("Usuário indisponível para uma nova conversa");
                                    }
                                }
                                else{
                                    System.out.println("Nenhuma conversa encontrada\n"
                                                     + "Digite 'sair' para sair do programa ou 'procurar' procurar denovo");
                                    fazer = io.nextLine();
                                }
                            }
                            break;
                        case "sair":
                            break;
                        default:
                            System.out.println("Comando não entendido, digite denovo");
                    }
                    System.out.println("Programa encerrado");
                    System.out.println("Fim do programa!\n"
                                     + "Volte mais tarde");
                    break;
                }    
            }
        }
        else if(tipo == 2){
            //Testar se existe conversa com usuário
            uso = lista.verifica2(identidade);
            if(uso > -1){
                hash = lista.pegaHash(uso, identidade);
                conversa.pegar(hash);
                //Verifica se conversa foi terminada
                if(conversa.verifica(uso)){
                    System.out.println("Você tem uma conversa que acabou e não deu a pontuação da pessoa com quem você conversou\n"
                                                 + "Dê uma pontuação de zero a cinco para a pessoa que você conversou(De 0 a 5)");
                    nota = io.nextInt();
                    dado.contaPonto(nota, uso);
                    dado.verificaPonto(uso);
                    lista.apagar(uso, identidade);
                    conversa.apagar(hash);
                }
                else{
                    valida = true;
                    //Conversa ativa;
                    conversa.mostrarMensagens();
                    System.out.println("Conversa ativa");
                    while(!escolha.equals("terminar") && !escolha.equals("sair")){
                        System.out.println("Digite 'mensagem' para escrever uma mensagem ou 'sair' para sair do programa ou 'terminar' para terminar a conversa");
                        escolha = io.nextLine();
                        switch(escolha){
                            case "mensagem":
                                System.out.println("Digite uma mensagem!");
                                msg = io.nextLine();
                                if(conversa.escrever(uso, identidade, msg, nome, hash)){
                                    System.out.println(nome + ": " + msg);
                                }
                                break;
                            case "sair":
                                break;
                            case "terminar":
                                System.out.println("Você saiu da conversa \n"
                                                 + "Dê uma pontuação de zero a cinco para a pessoa que você conversou(De 0 a 5)");
                                nota = io.nextInt();
                                dado.contaPonto(nota, uso);
                                dado.verificaPonto(uso);
                                conversa.acabar(identidade, hash);
                                break;
                            default:
                                System.out.println("Comando não entendido");
                        }
                    }
                }
            }
            //Verifica convite;
            uso = invite.verificarInvite(identidade);
            if(uso != -1){
                System.out.println("Você possui uma solicitação de conversa");
                dado.mostrar(uso);
                if(invite.aceitarInvite(res, uso, identidade)){
                    invite.apagar(uso, identidade);
                    lista.adicionar(uso, identidade);
                    hash = lista.pegaHash(uso, identidade);
                    conversa.adicionar(uso, identidade, hash);
                    System.out.println("Você está agora na conversa\n");
                    while(!escolha.equals("terminar") && !escolha.equals("sair")){
                        System.out.println("Digite 'mensagem' para escrever uma mensagem ou 'sair' para sair do programa ou 'terminar' para terminar a conversa");
                        escolha = io.nextLine();
                        switch(escolha){
                            case "mensagem":
                                    System.out.println("Digite uma mensagem!");
                                    msg = io.nextLine();
                                    if(conversa.escrever(uso, identidade, msg, nome, hash)){
                                        System.out.println(nome + ": " + msg);
                                    }
                                break;
                            case "sair":
                                break;
                            case "terminar":
                                System.out.println("Você saiu da conversa \n"
                                                 + "Dê uma pontuação de zero a cinco para a pessoa que você conversou(De 0 a 5)");
                                nota = io.nextInt();
                                dado.contaPonto(nota, uso);
                                dado.verificaPonto(uso);
                                conversa.acabar(identidade, hash);
                                break;
                            default:
                                System.out.println("Comando não entendido");
                        }
                    }
                }
                else{
                    invite.apagar(uso, identidade);
                }
            }
            else{
                    System.out.println("Você não possui nenhuma solicitação de conversa");
            }
        }
        System.out.println("Olá, o programa terminou mas venha depois\n");
    }
}
