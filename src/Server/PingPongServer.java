package Server;

import ClientAndServer.Ball;

import java.io.IOException;
import java.util.Vector;

public class PingPongServer implements Runnable {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;

    Vector<Ball> balls = new Vector<Ball>();


    PlayerConnection playerOneConnection = null;
    Thread playerOneConnectionThread = null;

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        playerOneConnection = new PlayerConnection(portPlayerOne);
        playerOneConnectionThread = new Thread(playerOneConnection);
        playerOneConnectionThread.start();

    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);

                if(playerOneConnection.socket.isConnected()) {
                    //Gets called if playerOne is conected >>
                    System.out.println("[INFO] Player one connected!");
                }




                //Loopt über alle bälle und fürt bei jedem Ball move(); aus
                for(Ball b : balls) {
                    b.move();
                }
            } catch (InterruptedException e) {
                System.out.println("[ERROR] PingPongServer: ");
                e.printStackTrace();
            }
        }
    }
}
