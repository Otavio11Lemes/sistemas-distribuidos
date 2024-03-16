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

            // Enviar a temperatura em graus Celsius ao servidor
            double temperaturaCelsius = 20.5; // Exemplo de temperatura em graus Celsius
            output.println(temperaturaCelsius);

            // Receber e exibir a temperatura em graus Fahrenheit do servidor
            String temperaturaFahrenheitStr = input.readLine();
            double temperaturaFahrenheit = Double.parseDouble(temperaturaFahrenheitStr);
            System.out.println("Temperatura em Fahrenheit: " + temperaturaFahrenheit);
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
