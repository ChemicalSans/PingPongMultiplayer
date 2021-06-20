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

    public static void main(String[] args) {
        try {
            new PingPongClient("192.168.8.106",11111);
            TimeUnit.SECONDS.sleep(3);
            new PingPongClient("192.168.8.106",11111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Player playerOne = new Player(0);
    public Player playerTwo = new Player(1);

    public String hostname = "192.168.8.106";
    public int port = 11111;
    public String preFix = "[INFO] PingPongClient: ";


    public PingPongClient(String hostname,int port) {
        System.out.println(preFix + "Connecting to " + hostname + ":" + port);
        this.hostname  = hostname;
        this.port = port;

    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawLine(0,0,this.getWidth(),this.getHeight());
        g.fillRect(playerOne.x,playerTwo.y,200,200);
        System.out.println(preFix + "PlayerOne at " + playerOne.x + "x" + playerOne.y);
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            long timeStart = System.currentTimeMillis();

            while (true) {
                try {
                    long timeNow = System.currentTimeMillis();
                    long deltaTime = timeNow-timeStart;
                    if(deltaTime > 20) {
                        this.repaint();
                        timeStart = timeNow;
                    }

                    //System.out.println(dataInputStream.readUTF());

                    playerOne = (Player) objectInputStream.readObject();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
