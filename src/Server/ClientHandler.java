package Server;

import Game.Player;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Thread thread = new Thread(this);
    Socket socket;
    Server server;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
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
                    String subData = data.substring(data.indexOf(":")+1);

                    if(subData.equals("?")) {
                        dataOutputStream.writeInt(server.game.players.size());
                        dataOutputStream.flush();

                    } else {
                        int d = Integer.valueOf(subData);


                        objectOutputStream.writeObject(server.game.getPlayer(d).);
                        objectOutputStream.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
