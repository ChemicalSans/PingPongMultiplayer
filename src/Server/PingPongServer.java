package Server;

import java.io.IOException;

public class PingPongServer {
    int portPlayerOne = 11111;
    int portPlayerTwo = 11112;

    public static void main(String[] args) throws IOException {
        new PingPongServer();
    }

    public PingPongServer () throws IOException {
        PlayerConnection playerOneConnection = new PlayerConnection(portPlayerOne);




    }



}
