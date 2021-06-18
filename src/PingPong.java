import TreePackage.TreeFrame;

import java.awt.*;

public class PingPong extends TreeFrame {
    public static void main(String[] args) {
        new PingPong();
    }

    public PingPong () {

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
