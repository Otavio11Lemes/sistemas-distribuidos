import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Conectar ao servidor na porta 6789
            socket = new Socket("localhost", 6789);

            // Obter os streams de entrada e saída
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Receber a palavra embaralhada do servidor
            String palavraEmbaralhada = input.readLine();
            System.out.println("Adivinhe a palavra embaralhada: " + palavraEmbaralhada);

            // Solicitar ao jogador adivinhar a palavra
            System.out.print("Sua resposta: ");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String resposta = userInput.readLine();

            // Enviar a resposta ao servidor
            output.println(resposta);

            // Receber e exibir o resultado do servidor
            String resultado = input.readLine();
            System.out.println("Resultado: " + resultado);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o socket: " + e.getMessage());
            }
        }
    }
}
