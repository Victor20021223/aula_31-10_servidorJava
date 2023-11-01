import java.io.*;
import java.net.Socket;

public class Client2 {
    public final static int PORTA=1025;

    public static void main(String[] args) {
        Socket s = null;
        PrintWriter saida;
        BufferedReader entrada;
        BufferedReader teclado;//Ler entradas ou teclado
        String mensagem;
        boolean fim = false;

        try {
            s = new Socket("localhost",PORTA);
            //Inicializar strems de entrada , saida e teclado
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));

            //Informar conexão com servidor
            System.out.println("Conectado com sucesso ao servidor : "+s.getInetAddress() + " : "+s.getPort());

            //Ler a primeira resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor :"+resposta);

            //Enviar varias mensagens para o servidor
            while (true){
                System.out.print("Cliente > ");
                System.out.flush();
                mensagem = teclado.readLine();
                if (mensagem.equals("Fim")){
                    fim = true;
                }
                saida.println(mensagem);
                saida.flush();

                //Ler resposta do servidor, caso seja vazia a conexão vai ser encerrada
                //pelo servidor, caso contrário imprima o que servidor enviou
                mensagem = entrada.readLine();
                if (mensagem == null){
                    System.out.println("Conexão encerrada pelo servidor");
                    break;
                }
                if (fim){
                    break;
                }
                System.out.println("Servidor >"+mensagem);
            }

            entrada.close();
            saida.close();
        }catch (IOException e){
            throw  new RuntimeException(e);
        }finally {
            try {
                if (s != null){
                    s.close();
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
