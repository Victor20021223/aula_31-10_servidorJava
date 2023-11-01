import java.io.*;
import java.net.Socket;

public class Client1 {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        Socket s = null;
        BufferedReader entrada;//Armezena o que ve no servidor
        PrintWriter saida;//Armazena o que será enviado ao servidor
        try {
            // Estabelicer a comunicação
            s = new Socket("",PORTA);

            //Criar streams de entrada e saida
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            //Informa o status da conexão
            System.out.println("Conectado ao Servidor : " +s.getInetAddress()+" : "+s.getPort());

            //Escrever algo para o servidor
            saida.println("Teste de conexão de importancia nenhuma, mas sou obrigado a fazer ou não");
            saida.flush();

            //Ler a resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor > "+resposta);
            entrada.close();
            saida.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                if (s != null) {
                    s.close();
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
