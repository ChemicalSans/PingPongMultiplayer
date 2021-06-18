package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;

import java.awt.*;

public class PingPongClient extends TreeFrame {
    public static void main(String[] args) {
        new PingPongClient();
    }

    int GUI = 0;
    /**GUI = 0 --> MainMenu
     * GUI = 1 -->
     */

    public Player PlayerOne = new Player();
    public Player PlayerTwo = new Player();

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
