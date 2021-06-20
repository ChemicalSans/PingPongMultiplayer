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
    Player playerOne = new Player(0);
    Player playerTwo = new Player(1);

    ServerSocket serverSocket = new ServerSocket(port);

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        System.out.println("[INFO] Server IP address: " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
        System.out.println("[INFO] Server: Waiting for player one!");
        new Thread(new Client(serverSocket.accept(),this)).start();
        System.out.println("[INFO] Server: Player one connected!");
        System.out.println("[INFO] Server: Waiting for player two!");
        new Thread(new Client(serverSocket.accept(),this)).start();
        System.out.println("[INFO] Server: Player two connected!");

        new Thread(this).start();
        //GAME STARTUP CODE HERE >>
        playerOne.x = 100;
        playerOne.y = 200;


    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);





                //Loopt über alle bälle und fürt bei jedem Ball move(); aus
                for(Ball b : balls) {
                    b.move();
                }
            } catch (Exception e) {
                System.out.println("[ERROR] PingPongServer: " + e.getMessage());
            }
        }
    }


}
