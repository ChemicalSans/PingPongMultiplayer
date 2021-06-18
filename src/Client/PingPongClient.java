package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class PingPongClient extends TreeFrame {

    public static void main(String[] args) {
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

    public Boolean connected = false;



    public PingPongClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.playerOne = new Player();
        this.playerTwo = new Player();

        try {
            System.out.println("[INFO] PingPongClient: " + hostname + ":" + port);

            this.socket = new Socket(this.hostname, this.port);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();

            this.dataInputStream = new DataInputStream(inputStream);
            this.dataOutputStream = new DataOutputStream(outputStream);

            this.objectOutputStream = new ObjectOutputStream(outputStream);
            this.objectInputStream = new ObjectInputStream(inputStream);

            connected = true;
        } catch (Exception e) { }

        if(connected) {
            System.out.println("[INFO] PingPongClient: Receiving data!");

            while (true) {
                try {
                    Object o = objectInputStream.readObject();
                    if(o.getClass().isInstance(new Player())) {
                        Player p = (Player) o;

                        if(p.id == 0) {
                            playerOne = p;
                            System.out.println("[INFO] PingPongClient: PlayerOne overwritten " + playerOne.x + "x" + playerOne.y);
                        }

                        switch (p.id) {
                            case 0:
                                playerOne = p;
                                break;
                            case 1:
                                playerTwo = p;
                            default:
                                System.out.println("[ERROR] PingPongClient: Received player had invalid Id!");
                            break;
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
