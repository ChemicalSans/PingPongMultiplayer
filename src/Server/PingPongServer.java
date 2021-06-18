package Server;

import ClientAndServer.Ball;
import ClientAndServer.Player;
import com.sun.javafx.geom.Vec2d;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class PingPongServer implements Runnable {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;

    Vector<Ball> balls = new Vector<Ball>();
    Player playerOne = new Player();
    Player playerTwo = new Player();

    boolean playerOneConnected = false;
    ServerSocket serverSocketOne = new ServerSocket(portPlayerOne);
    Socket socketOne;

    boolean playerTwoConnected = false;
    ServerSocket serverSocketTwo = new ServerSocket(portPlayerTwo);
    Socket socketTwo;

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        System.out.println("[INFO] Server: Waiting for player one!");
        socketOne = serverSocketOne.accept();
        System.out.println("[INFO] Server: Player one connected!");
        System.out.println("[INFO] Server: Waiting for player one!");
        socketTwo = serverSocketTwo.accept();
        System.out.println("[INFO] Server: Player two connected!");

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

                if(socketOne.isConnected() == true && playerOneConnected == false) {
                    //Gets called ONCE if playerOne is connected >>
                    playerOneConnected = true;
                    System.out.println("[INFO] PingPongServer: Player one connected!");
                }

                if(socketTwo.isConnected() == true && playerTwoConnected == false) {
                    //Gets called ONCE if playerOne is connected >>
                    playerTwoConnected = true;
                    System.out.println("[INFO] PingPongServer: Player two connected!");
                }



                //Loopt über alle bälle und fürt bei jedem Ball move(); aus
                for(Ball b : balls) {
                    b.move();
                }

                //Sending player Objects to playerOne
                sendPlayer(socketOne,playerOne);
                sendPlayer(socketOne,playerTwo);

                //Sending player Objects to playerOne
                sendPlayer(socketTwo,playerOne);
                sendPlayer(socketTwo,playerTwo);

            } catch (Exception e) {
                System.out.println("[ERROR] PingPongServer: ");
                e.printStackTrace();
            }
        }
    }

    public void sendPlayer(Socket socket, Player p) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(p);
        objectOutputStream.close();
    }

}
