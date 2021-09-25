package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    ServerSocket serverSocket;
    public Server(int port) {
        try {
            System.out.println(ANSI_GREEN + "Starting server...");
            this.serverSocket = new ServerSocket(port);
            System.out.println(ANSI_GREEN + "Server started!");

            while (true) {
                System.out.println(ANSI_YELLOW + "Waiting for connection!");
                Socket socket = serverSocket.accept();
                new ClientHandler(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
