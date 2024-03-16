import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Conectar ao servidor na porta 6789
            socket = new Socket("localhost", 6789);

            // Obter os streams de entrada e saída
            OutputStream out = socket.getOutputStream();
            PrintWriter output = new PrintWriter(out, true);
            InputStream in = socket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            // Palavra a ser enviada
            String palavraOriginal = "abacaxi";
            // Encriptar a palavra
            String palavraEncriptada = encriptar(palavraOriginal);
            // Enviar a palavra encriptada ao servidor
            output.println(palavraEncriptada);

            // Receber e exibir a palavra encriptada e a palavra original do servidor
            String respostaServidor = input.readLine();
            System.out.println("Palavra Encriptada Recebida: " + respostaServidor);
            System.out.println("Palavra Original: " + palavraOriginal);
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

    // Método para encriptar uma palavra usando a cifra de César
    private static String encriptar(String palavra) {
        StringBuilder encriptada = new StringBuilder();
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            if (Character.isLetter(c)) {
                // Desloca a letra três posições para frente no alfabeto
                c = (char) (((c - 'a' + 3) % 26) + 'a');
            }
            encriptada.append(c);
        }
        return encriptada.toString();
    }
}
