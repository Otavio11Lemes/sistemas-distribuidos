import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Criar um servidor na porta 6789
            serverSocket = new ServerSocket(6789);

            while (true) {
                // Aceitar conexões de clientes
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão estabelecida com " + clientSocket);

                // Obter os streams de entrada e saída
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                // Receber a palavra encriptada do cliente
                String palavraEncriptada = input.readLine();

                // Decifrar a palavra
                String palavraOriginal = decifrar(palavraEncriptada);

                // Enviar a palavra encriptada de volta ao cliente
                output.println(palavraEncriptada);

                // Exibir a palavra encriptada e a palavra original no servidor
                System.out.println("Palavra Encriptada: " + palavraEncriptada);
                System.out.println("Palavra Original Recebida: " + palavraOriginal);

                // Fechar o socket do cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o servidor: " + e.getMessage());
            }
        }
    }

    // Método para decifrar uma palavra usando a cifra de César
    private static String decifrar(String palavraEncriptada) {
        StringBuilder original = new StringBuilder();
        for (int i = 0; i < palavraEncriptada.length(); i++) {
            char c = palavraEncriptada.charAt(i);
            if (Character.isLetter(c)) {
                // Desloca a letra três posições para trás no alfabeto
                c = (char) (((c - 'a' - 3 + 26) % 26) + 'a');
            }
            original.append(c);
        }
        return original.toString();
    }
}
