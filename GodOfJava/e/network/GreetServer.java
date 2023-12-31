package e.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Server wait...");
            clientSocket = serverSocket.accept();
            System.out.println("Server accept...");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                System.out.println("hello server received");
                out.println("hello client");
            } else {
                System.out.println("any string received");
                out.println("unrecognised greeting");
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws Exception {
        GreetServer server=new GreetServer();
        server.start(6666);
    }
}