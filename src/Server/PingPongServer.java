package Server;

import java.io.IOException;

public class PingPongServer implements Runnable {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;


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
        }
    }
}
