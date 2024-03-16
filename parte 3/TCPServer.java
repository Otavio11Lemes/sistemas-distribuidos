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

                // Receber a temperatura em graus Celsius do cliente
                double temperaturaCelsius = Double.parseDouble(input.readLine());

                // Converter a temperatura para graus Fahrenheit
                double temperaturaFahrenheit = (temperaturaCelsius * 9 / 5) + 32;

                // Enviar a temperatura em graus Fahrenheit ao cliente
                output.println(temperaturaFahrenheit);

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
}
