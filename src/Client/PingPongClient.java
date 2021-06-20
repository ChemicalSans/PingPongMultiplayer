package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PingPongClient extends TreeFrame {

    public static void main(String[] args) throws IOException {
        new PingPongClient();
    }

    public Player playerOne = new Player();
    public Player playerTwo = new Player();

    public String hostname = "192.168.8.106";
    public int port = 11111;
    public Socket socket;
    public String preFix = "[INFO] PingPongClient: ";

    public PingPongClient() throws IOException {
        System.out.println(preFix + "Connecting to " + hostname + ":" + port);
        socket = new Socket(this.hostname,this.port);
    }

    @Override
    public void paint(Graphics g) {
        if(socket.isConnected()) {
            System.out.println(preFix + "Socket connected!");

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println(objectInputStream.read());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        g.setColor(Color.WHITE);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
        g.drawRect(playerOne.x,playerTwo.y,20,20);
    }

}
