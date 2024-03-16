import java.io.*;
import java.net.*;
import java.util.Random;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Criar um servidor na porta 6789
            serverSocket = new ServerSocket(6789);
            System.out.println("Servidor TCP iniciado. Aguardando conexões...");

            while (true) {
                // Aceitar conexões de clientes
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão estabelecida com " + clientSocket);

                // Obter os streams de entrada e saída
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                // Escolher uma palavra aleatória do array
                String palavraOriginal = escolherPalavraAleatoria();
                String palavraEmbaralhada = embaralharPalavra(palavraOriginal);

                // Enviar a palavra embaralhada ao cliente
                output.println(palavraEmbaralhada);

                // Receber a resposta do cliente
                String resposta = input.readLine();

                // Verificar se a resposta do cliente está correta
                if (resposta.equalsIgnoreCase(palavraOriginal)) {
                    output.println("correta");
                } else {
                    output.println("incorreta");
                }

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

    // Método para escolher uma palavra aleatória
    public static String escolherPalavraAleatoria() {
        String[] palavras = {"banana", "abacaxi", "morango", "laranja", "uva", "melancia"};
        Random random = new Random();
        return palavras[random.nextInt(palavras.length)];
    }

    // Método para embaralhar uma palavra
    public static String embaralharPalavra(String palavra) {
        char[] chars = palavra.toCharArray();
        Random random = new Random();

        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
