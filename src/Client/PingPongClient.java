package Client;

import ClientAndServer.Player;
import TreePackage.TreeFrame;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PingPongClient extends TreeFrame {
    public static void main(String[] args) {
        new PingPongClient("26.103.63.212",11111);
    }


    int GUI = 0;
    /**GUI = 0 --> MainMenu
     * GUI = 1 -->
     */

    public String hostname = "localhost";
    public int port = 11111;
    public Player PlayerOne = new Player();
    public Player PlayerTwo = new Player();
    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;
    public Boolean connected = false;



    public PingPongClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;

        try {
            System.out.println("[INFO] Client: " + hostname + ":" + port);

            this.socket = new Socket(this.hostname, this.port);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            connected = true;
        } catch (Exception e) { }

        if(connected) {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

            while (true) {
                try {
                    printWriter.write("Hello World!");
                    printWriter.flush();
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
