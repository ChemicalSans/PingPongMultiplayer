package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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
                dataOutputStream.writeUTF(pps.playerOne.x+":p1x");
                dataOutputStream.writeUTF(pps.playerOne.y+":p1y");
                dataOutputStream.writeUTF(pps.playerTwo.x+":p2x");
                dataOutputStream.writeUTF(pps.playerTwo.y+":p2y");


            } catch (SocketException e) {
                System.exit(444);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
