package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class PingPongClient extends TreeFrame {

    public static void main(String[] args) throws IOException {
        new PingPongClient("26.103.63.212",11111);
    }


    int GUI = 0;
    /**GUI = 0 --> MainMenu
     * GUI = 1 -->
     */

    public Player playerOne;
    public Player playerTwo;

    public String hostname = "localhost";
    public int port = 11111;

    public Socket socket;

    public InputStream inputStream;
    public OutputStream outputStream;

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;


    public PingPongClient(String hostname, int port) throws IOException {
        this.hostname = hostname;
        this.port = port;
        this.playerOne = new Player();
        this.playerTwo = new Player();

        System.out.println("[INFO] PingPongClient: " + hostname + ":" + port);

        this.socket = new Socket(hostname, port);
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();

        this.dataInputStream = new DataInputStream(inputStream);
        this.dataOutputStream = new DataOutputStream(outputStream);

        this.objectOutputStream = new ObjectOutputStream(outputStream);
        this.objectInputStream = new ObjectInputStream(inputStream);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
    }

    @Override
    public void run() {
        graphicsBuffer = this.getGraphics();

        while (true) {
            try {
                Thread.sleep(20);
                this.paint(graphicsBuffer);
                graphicsBuffer = this.getGraphics();

                Object o = objectInputStream.readObject();
                if(o.getClass().isInstance(new Player())) {
                    Player p = (Player) o;
                    System.out.println("[INFO] PingPongClient: Received Player!");

                    switch (p.getId()) {
                        case 0:
                            playerOne = p;
                            break;
                        case 1:
                            playerTwo = p;
                            break;
                        default:
                            System.out.println("[ERROR] PingPongClient: Received player had invalid Id! --> " + p.id);
                            break;
                    }

                }

            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
