package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PingPongClient extends TreeFrame {
    public static void main(String[] args) {
        new PingPongClient("localhost",11111);
    }

    String hostname = "localhost";
    int port = 11111;

    int GUI = 0;
    /**GUI = 0 --> MainMenu
     * GUI = 1 -->
     */

    public Player PlayerOne = new Player();
    public Player PlayerTwo = new Player();
    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;

    public PingPongClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;

        try {
            this.socket = new Socket(this.hostname, this.port);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {

        }

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
