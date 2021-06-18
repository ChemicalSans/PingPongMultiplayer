package TreePackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TreeFrame extends Frame implements Runnable , WindowListener , ComponentListener , KeyListener , MouseListener , MouseMotionListener , MouseWheelListener {
    Thread thread = new Thread(this);
    Graphics graphicsBuffer = this.getGraphics();

    public static void main(String[] args) {
        TreeFrame treeFrame = new TreeFrame();
    }

    public TreeFrame() {
        preInit();
        this.setSize(800,800);
        this.setBackground(Color.black);
        this.setTitle("TreeFrame [" + this.getWidth() + "x" + this.getHeight() + "]");
        this.setIconImage(getIcon());
        this.setVisible(true);

        this.addWindowListener(this);
        this.addComponentListener(this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        thread.start();
    }

    public void preInit() {

    }

    public Image getIcon()  {
        Image img = null;
        try {
            img = ImageIO.read(new File("src/TreePackage/Tree.png"));
        } catch (IOException e) {
            System.out.println("[ERROR] TreeFrame: Failed to read Frame Icon");
        }
        return img;
    }

    @Override
    public void run() {
        graphicsBuffer = this.getGraphics();
        while (true) {
            try {
                Thread.sleep(20);
                this.paint(graphicsBuffer);
                graphicsBuffer = this.getGraphics();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Color colorBuffer = g.getColor();
        g.setColor(this.getBackground());
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(colorBuffer);
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
