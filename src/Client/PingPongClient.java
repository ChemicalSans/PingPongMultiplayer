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
        new PingPongClient("192.168.8.106",11111);
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


                    while (dataInputStream.available()>0) {
                        String data = dataInputStream.readUTF();
                        String index = data.substring(data.indexOf(":")+1);
                        String valueTemp = data.substring(0,data.indexOf(":"));
                        int value = Integer.valueOf(valueTemp);

                        //System.out.println("Data: " + data);
                        //System.out.println("Index: " + index + "   Value: " + value);

                        switch (index) {
                            case "p1x":
                                playerOne.x = value;
                                break;
                            case "p1y":
                                playerOne.y = value;
                                break;
                            case "p2x":
                                playerTwo.x = value;
                                break;
                            case "p2y":
                                playerTwo.y = value;
                                break;
                            default:
                                System.out.println(preFix+"Unknowen data index! " + index);
                                break;
                        }

                    }


                    /*
                    System.out.println(preFix + "objects available = " + objectInputStream.available());
                    while(objectInputStream.available()>0) {
                        Object o = objectInputStream.readObject();
                        if(o.getClass().isInstance(playerOne)) {
                            playerOne = (Player) o;
                            System.out.println(preFix+"PlayerOne overwritten!");
                        }
                        objectInputStream.reset();
                    }

                     */


                    /*
                    System.out.println(preFix+"DataAvailable " + dataInputStream.available());

                    for(int i = 0; i < 2; i++) {
                        switch (i) {
                            case 0:
                                playerOne.x = dataInputStream.readInt();
                                break;
                            case 1:
                                playerOne.y = dataInputStream.readInt();
                                break;
                            default:
                                System.out.println(preFix+"InvalidId " + i);
                                dataInputStream.skipBytes(dataInputStream.available()-2);
                                break;
                        }
                    }

                    dataInputStream.reset();

                     */

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
