package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;

import java.awt.*;
import java.net.Socket;

public class PingPongClient extends TreeFrame {
    public static void main(String[] args) {
        new PingPongClient();
    }

    String hostname = "localhost";
    int port = 11111;

    int GUI = 0;
    /**GUI = 0 --> MainMenu
     * GUI = 1 -->
     */

    public Player PlayerOne = new Player();
    public Player PlayerTwo = new Player();
    public Socket socket = new Socket(hostname,port);

    public PingPongClient() {


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
    }

    @Override
    public void preInit() {
        this.setUndecorated(true);
    }
}
