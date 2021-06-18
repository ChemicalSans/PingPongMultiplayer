package TreePackage.TreeTools;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;

public class DataTransferOverNetwork {
    public static String hostname = "localhost";
    public static int port = 11111;

    public DataTransferOverNetwork() throws MalformedURLException {
    }

    public static void main(String[] args) {
        try {
            DataTestOne dataTestOne = new DataTestOne();
            DataTestTwo dataTestTwo = new DataTestTwo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class DataTestOne {
    public DataTestOne() throws IOException {
        String hostname = DataTransferOverNetwork.hostname;
        int port = DataTransferOverNetwork.port;

        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        System.out.println("new Client conected: " + socket.getLocalAddress());

        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = reader.readLine();

    }
}

class DataTestTwo {
    public DataTestTwo() throws IOException {
        String hostname = DataTransferOverNetwork.hostname;
        int port = DataTransferOverNetwork.port;

        Socket socket = new Socket(hostname,port);


        OutputStream out = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write("Hello World!");
        writer.flush();
    }

}

