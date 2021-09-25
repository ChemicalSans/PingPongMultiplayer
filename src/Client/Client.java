package Client;

import Game.Player;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        new Client("localhost",6969);
    }

    Socket socket = new Socket();
    BufferedInputStream bufferedInputStream;
    BufferedOutputStream bufferedOutputStream;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public Client(String host,int port) {
        try {
            while (!socket.isBound()) {
                try {
                    System.out.println("Try connecting...");
                    this.socket = new Socket(host,port);
                    System.out.println("Connected!");
                } catch (IOException e) {
                    System.out.println("Connection refused!");
                    TimeUnit.MILLISECONDS.sleep(3000);
                }
            }

            System.out.println("Connecting Streams... ");
            try {
                this.bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                this.bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

                //this.dataInputStream = new DataInputStream(bufferedInputStream);
                this.dataOutputStream = new DataOutputStream(bufferedOutputStream);
                this.objectInputStream = new ObjectInputStream(bufferedInputStream);
                //this.objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Streams connected!");


            while (true) {
                try {
                    System.out.println("Sending: " + "player:0");
                    dataOutputStream.writeUTF("player:0");
                    dataOutputStream.flush();

                    Player player = (Player) objectInputStream.readObject();
                    System.out.println("Receiving: " + player);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
