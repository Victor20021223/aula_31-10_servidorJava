import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        PrintWriter saida;
        BufferedReader entrada;
        Socket client = null;

        try {
            //Estabelecer a conexão
            System.out.println("Ouvindo a porta "+PORTA+"...");
            ServerSocket servidor = new ServerSocket(PORTA);
            client  = servidor.accept();//Aceita conexão do cliente
            System.out.println("Conexão estebelicida com "+client.getInetAddress());

            //Preparar streams de entrada e saida
            entrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            //Lê mensagem de cliente
            String mensagem = entrada.readLine();
            System.out.println("Cliente > "+mensagem);

            //Responder mensagem ao cliente
            saida.println("Òla Cliente, sua conexão foi bem sucedida ao servidor");
            saida.flush();
            entrada.close();
            saida.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            System.out.println("Encerrendo conexão....");
            try {
                if (client != null){
                    client.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
