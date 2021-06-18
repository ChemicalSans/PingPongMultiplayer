package Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayerConnection implements Runnable {

    public Socket socket;
    public ServerSocket serverSocket;
    public InputStream inputStream;
    public OutputStream outputStream;

    public PlayerConnection(int port) throws IOException {
        serverSocket = new ServerSocket(port);


    }

    @Override
    public void run() {
        while (true) {
            try     {
                System.out.println("[INFO] PlayerConnectionServerSocket: " + InetAddress.getLocalHost());
                socket = serverSocket.accept();

                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                System.out.println(reader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
