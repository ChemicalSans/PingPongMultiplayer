package Server;

import java.net.Socket;

public class Client implements Runnable {
    Socket socket;


    public Client(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {

    }
}
