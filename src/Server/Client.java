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
                System.out.println("Client: " + pps.playerOne.x + "x" + pps.playerOne.y);
                objectOutputStream.writeObject(pps.playerOne);
                objectOutputStream.writeObject(pps.playerTwo);
                objectOutputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
