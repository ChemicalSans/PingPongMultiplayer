package Server;

import ClientAndServer.Player;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayerConnection implements Runnable {

    public Socket socket = new Socket();
    public ServerSocket serverSocket;

    public InputStream inputStream;
    public OutputStream outputStream;

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;

    public PlayerConnection(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }

    @Override
    public void run() {
        try {
            System.out.println("[INFO] PlayerConnection: " + InetAddress.getLocalHost());
            socket = serverSocket.accept();

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            dataOutputStream = new DataOutputStream(outputStream);
            dataInputStream = new DataInputStream(inputStream);

            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
         while (true) {
            try     {
                String massage = "Hello World!";
                dataOutputStream.writeUTF(massage);
                dataOutputStream.flush(); // send the message

                System.out.println("[INFO] PlayerConnection: Reader read: " + massage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

         */
    }

    public void sendPlayer(Player p) throws IOException {
        try {
            objectOutputStream.writeObject(p);
            objectOutputStream.flush();
        } catch (NullPointerException e) {

        }
    }

}
