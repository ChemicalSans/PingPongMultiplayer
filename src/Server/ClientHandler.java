package Server;

import Game.Player;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Thread thread = new Thread(this);
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    Player testPlayer = new Player(400,500);

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

            this.thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = dataInputStream.readUTF();

                if(data.contains("player:")) {
                    objectOutputStream.writeObject(testPlayer);
                    objectOutputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
