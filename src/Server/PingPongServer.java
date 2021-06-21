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

    Ball ball = new Ball(new Vec2d(2,2),new Vec2d(2,2),new Vector<>());
    Player playerOne = new Player(0);
    Player playerTwo = new Player(1);

    ServerSocket serverSocket = new ServerSocket(port);
    Client cOne = null;
    Client cTwo = null;

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        //GAME STARTUP CODE HERE >>
        playerOne.x = 100;
        playerOne.y = 200;



        //Client connection stuff >>>
        System.out.println("[INFO] Server IP address: " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
        System.out.println("[INFO] Server: Waiting for player one!");
        cOne = new Client(serverSocket.accept(),this);
        new Thread(cOne).start();
        System.out.println("[INFO] Server: Player one connected!");
        System.out.println("[INFO] Server: Waiting for player two!");
        cTwo = new Client(serverSocket.accept(),this);
        new Thread(cTwo).start();
        System.out.println("[INFO] Server: Player two connected!");

        new Thread(this).start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);

                cOne.pps = this;
                cTwo.pps = this;

                playerOne.x += 1;
                playerOne.y += 1;


            } catch (Exception e) {
                System.out.println("[ERROR] PingPongServer: " + e.getMessage());
            }
        }
    }


}
