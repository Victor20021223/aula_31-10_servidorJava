import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor3 {
    public final static int PORTA=1025;
    public static Socket cliente;

    public static void main(String[] args) {
        //Criar lista de Threads ativas
        ArrayList<ServidorThread> listaThread = new ArrayList<ServidorThread>();
        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            //Deixar o servidor em loop para ele sempre aceitar novas conexões
            while (true){
                System.out.println("Ouvindo a porta "+PORTA+"...");
                cliente = servidor.accept();

                //Criar a thread para socket estabelecido
                ServidorThread thread = new ServidorThread(cliente,listaThread);

                //Adicionar essa thread a lista de todas as threads ativas
                listaThread.add(thread);

                //Inicializar a thread
                thread.start();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            System.out.println("Encerrando conexão...");
            try {
                if (cliente != null){
                    cliente.close();
                }
            }catch (IOException e){
                throw  new RuntimeException(e);
            }
        }
    }
}
