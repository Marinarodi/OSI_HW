import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("New connection accepted");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String name = in.readLine();
                    System.out.printf("Hi %s, your port is %n", name, clientSocket.getPort());
                    out.println(clientSocket.getPort());

                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }
        }
    }
}