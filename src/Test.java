import Client.PingPongClient;
import Server.PingPongServer;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        new Thread(new PingPongServer()).start();
        new Thread(new PingPongClient("localhost",11111)).start();
        new Thread(new PingPongClient("localhost",11112)).start();

    }

}
