package Client;

import TreePackage.TreeFrame;

import java.awt.*;

public class PingPongClient extends TreeFrame {
    public static void main(String[] args) {
        new PingPongClient();
    }

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
