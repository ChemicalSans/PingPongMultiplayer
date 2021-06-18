package Server;

import java.io.IOException;
import java.util.Vector;

public class PingPongServer implements Runnable {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;
    int vx=3;//Geschwindigkeit X
    int vy=3;//Geschwindigkeit Y
    int bx;//Ball position X
    int by;//Ball position Y
    boolean col;//collision


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
            if(playerOneConnection.socket.isConnected()) {
                System.out.println("[INFO] Player one connected!");
            }

            //MOVE





            //überprüft ob der ball mit einemobjekt kolidiert ist was vorher überprüft wurde
            if(col == true){
                vx*=-1;vy*=-1;
            }
        }
    }
}
