package Server;

import ClientAndServer.Ball;
import ClientAndServer.Player;
import com.sun.javafx.geom.Vec2d;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class PingPongServer implements Runnable {
    int port = 11111;

    Vector<Ball> balls = new Vector<Ball>();
    Player playerOne = new Player();
    Player playerTwo = new Player();

    ServerSocket serverSocket = new ServerSocket(port);
    Socket socket;



    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        System.out.println("[INFO] Server IP address: " + InetAddress.getLocalHost());
        System.out.println("[INFO] Server: Waiting for player one!");
        new Thread(new Client(serverSocket.accept())).start();
        System.out.println("[INFO] Server: Waiting for player two!");
        new Thread(new Client(serverSocket.accept())).start();

        new Thread(this).start();
        //GAME STARTUP CODE HERE >>
        playerOne.x = 100;
        playerOne.y = 200;


    }


    @Override
    public void run() {
        int movement = 1;
        while (true) {
            try {
                Thread.sleep(1);

                if(playerOne.x>300) movement*=-1;
                if(playerOne.x<100) movement*=-1;
                playerOne.x += movement;






                //Loopt über alle bälle und fürt bei jedem Ball move(); aus
                for(Ball b : balls) {
                    b.move();
                }
            } catch (Exception e) {
                System.out.println("[ERROR] PingPongServer: " + e.getMessage());
            }
        }
    }

    public void sendPlayer(Socket socket, Player p) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(p);
        objectOutputStream.close();
    }

}
