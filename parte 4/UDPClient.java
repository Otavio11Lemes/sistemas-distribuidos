import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Criar um socket UDP
            socket = new DatagramSocket();

            // Obter o endereço do servidor e a porta
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            // Converter a temperatura para bytes
            double temperaturaCelsius = 20.5; // Exemplo de temperatura em graus Celsius
            byte[] sendData = Double.toString(temperaturaCelsius).getBytes();

            // Criar um pacote para enviar os dados ao servidor
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Enviar o pacote ao servidor
            socket.send(sendPacket);

            // Preparar para receber a resposta do servidor
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receber a resposta do servidor
            socket.receive(receivePacket);

            // Exibir a temperatura em Fahrenheit recebida do servidor
            String temperaturaFahrenheitStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
            double temperaturaFahrenheit = Double.parseDouble(temperaturaFahrenheitStr);
            System.out.println("Temperatura em Fahrenheit: " + temperaturaFahrenheit);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
