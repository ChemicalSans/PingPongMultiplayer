package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class PingPongClient extends TreeFrame {

    public static void main(String[] args) throws IOException {
        new PingPongClient();
    }

    public Player playerOne = new Player(0);
    public Player playerTwo = new Player(1);

    public String hostname = "192.168.8.106";
    public int port = 11111;
    public String preFix = "[INFO] PingPongClient: ";


    public PingPongClient() {
        System.out.println(preFix + "Connecting to " + hostname + ":" + port);


    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
        g.drawRect(playerOne.x,playerTwo.y,20,20);
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                try {

                    //System.out.println(objectInputStream.read());
                    System.out.println(dataInputStream.readUTF());

                    /*
                    Object o = objectInputStream.read();
                    if(o.getClass().isInstance(new Player(0))) {
                        Player p = (Player) o;
                        switch (p.id) {
                            case 0:
                                playerOne = p;
                                break;
                            case 1:
                                playerTwo = p;
                                break;
                            default:
                                System.out.println(preFix + "Object had invalid id! ");
                                break;
                        }
                    }

                     */


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
