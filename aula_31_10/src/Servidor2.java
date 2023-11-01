import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    public final static int PORTA = 1025;
    public static void main(String[] args) {
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;
        String mensagem;

        try {
            //Estabelecer a comunicação
            System.out.println("Ouvindo a porta "+PORTA+"...");
            ServerSocket servidor = new ServerSocket(PORTA);
            cliente = servidor.accept();

            //Preparar strems de entrada e de saida
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            saida.println("Conexão estebelecida com "+cliente.getInetAddress());
            saida.flush();

            //Ler varias mensagens do cliente e retorna-las
            do {
                mensagem = entrada.readLine();
                //Retornar um ACK para o clima
                saida.println("Recebida a mensagem"+mensagem );
                saida.flush();
                //Imprimir mensagem do cliente na tela
                System.out.println("Cliente >"+mensagem);
            }while (mensagem.equals("Fim"));

            entrada.close();
            saida.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            System.out.println("Esperando conexão...");
            try {
                if (cliente != null) {
                    cliente.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
