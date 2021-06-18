package TreePackage.TreeNetwork;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TreeConnection implements Runnable{
    Thread thread = new Thread(this);
    ServerSocket serverSocket;
    Socket socketA = new Socket();
    Socket socketB = new Socket();

    public TreeConnection(String hostname, int hostPort, int localPort) throws IOException {
        serverSocket = new ServerSocket(localPort);
        socketA = new Socket(hostname,hostPort);
        socketB = serverSocket.accept();

        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socketA.getInputStream());
                dataInputStream.readUTF();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendString(String s) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socketA.getOutputStream());
        dataOutputStream.writeUTF(s);
        dataOutputStream.close();
    }


}
