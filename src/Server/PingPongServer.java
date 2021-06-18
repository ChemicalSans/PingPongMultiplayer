package Server;

import ClientAndServer.Ball;
import ClientAndServer.Player;
import com.sun.javafx.geom.Vec2d;

import java.io.IOException;
import java.util.Vector;

public class PingPongServer implements Runnable {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;

    Vector<Ball> balls = new Vector<Ball>();
    Player playerOne = new Player();
    Player playerTwo = new Player();

    boolean playerOneConnected = false;
    PlayerConnection playerOneConnection = null;
    Thread playerOneConnectionThread = null;

    boolean playerTwoConnected = false;
    PlayerConnection playerTwoConnection = null;
    Thread playerTwoConnectionThread = null;

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        playerOneConnection = new PlayerConnection(portPlayerOne);
        playerOneConnectionThread = new Thread(playerOneConnection);
        playerOneConnectionThread.start();

        playerTwoConnection = new PlayerConnection(portPlayerTwo);
        playerTwoConnectionThread = new Thread(playerTwoConnection);
        playerTwoConnectionThread.start();

        //GAME STARTUP CODE HERE >>
        playerOne.x = 100;
        playerOne.y = 200;


    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);

                if(playerOneConnection.socket.isConnected() == true && playerOneConnected == false) {
                    //Gets called ONCE if playerOne is connected >>
                    playerOneConnected = true;
                    System.out.println("[INFO] PingPongServer: Player one connected!");
                }

                if(playerTwoConnection.socket.isConnected() == true && playerTwoConnected == false) {
                    //Gets called ONCE if playerOne is connected >>
                    playerTwoConnected = true;
                    System.out.println("[INFO] PingPongServer: Player two connected!");
                }



                //Loopt über alle bälle und fürt bei jedem Ball move(); aus
                for(Ball b : balls) {
                    b.move();
                }

                //Sending player Objects to playerOne
                playerOneConnection.sendPlayer(playerOne);
                playerOneConnection.sendPlayer(playerTwo);

                //Sending player Objects to playerOne
                playerOneConnection.sendPlayer(playerOne);
                playerOneConnection.sendPlayer(playerTwo);

            } catch (Exception e) {
                System.out.println("[ERROR] PingPongServer: ");
                e.printStackTrace();
            }
        }
    }
}
