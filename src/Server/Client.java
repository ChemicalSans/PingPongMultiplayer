package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    Socket socket;
    PingPongServer pps;

    ObjectOutputStream objectOutputStream;
    DataOutputStream dataOutputStream;

    public Client(Socket s, PingPongServer pps) throws IOException {
        this.socket = s;
        this.pps = pps;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                //dataOutputStream.writeUTF("Hello World!");
                objectOutputStream.writeObject(pps.playerOne);
                objectOutputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
