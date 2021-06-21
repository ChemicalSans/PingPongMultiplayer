package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class PingPongClient extends TreeFrame {

    public static void main(String[] args) {
        new PingPongClient("26.103.63.212",11111);
        //TimeUnit.SECONDS.sleep(3);
        //new PingPongClient("26.103.63.212",11111);
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
        g.fillRect(playerOne.x,playerOne.y,200,200);
    }


    @Override
    public void run() {
        try {
            Socket socket = null;

            while (true) {
                try {
                    socket = new Socket(hostname, port);
                    if(socket.isConnected()) break;
                } catch (ConnectException e) {
                    System.out.println(preFix + "Couldn't connect to the server! Reconnecting...");
                }
            }

            System.out.println(preFix + "Connected!");


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
                    /*
                    System.out.println(preFix + "objects available = " + objectInputStream.available());
                    while(objectInputStream.available()>0) {
                        Object o = objectInputStream.readObject();
                        if(o.getClass().isInstance(playerOne)) {
                            playerOne = (Player) o;
                            System.out.println(preFix+"PlayerOne overwritten!");
                        }
                    }
                     */

                    System.out.println(preFix+"DataAvailable " + dataInputStream.available());

                    for(int i = 0; i < dataInputStream.available(); i++) {
                        switch (i) {
                            case 0:
                                playerOne.x = dataInputStream.readInt();
                                break;
                            case 1:
                                playerOne.x = dataInputStream.readInt();
                                break;
                            default:
                                System.out.println(preFix+"InvalidId " + i);
                                break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
