import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorThread extends Thread {
    private Socket socket = null;
    private ArrayList<ServidorThread> listaThread;//Arraylist que armazena todas as threads
    private PrintWriter saida;
    private String mensagem;
    private boolean fim = false;

    public ServidorThread(Socket socket, ArrayList<ServidorThread> listaThread) {
        this.socket = socket;
        this.listaThread = listaThread;
    }

    //Definir o comportamento da thread
    @Override
    public void run(){
        try {
            //Ler o que vem do cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Escrever o que sera enviado para o cliente
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            while (true) {
                //Ler mensagem do cliente
                mensagem = entrada.readLine();

                if (mensagem == null || mensagem.equals("Fim")) {
                    break;
                }

                //Exibir mensagens para todos os clientes conectados
                for (ServidorThread st : listaThread) {
                    st.saida.println(mensagem);
                }
                System.out.println("Servidor recebeu a mensagem " + mensagem);
            }

            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
