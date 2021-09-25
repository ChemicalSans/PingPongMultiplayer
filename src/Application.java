import Server.Server;

public class Application {
    public static void main(String[] args) {
        if(args[0].equals("-server")) {
            new Server(6969);
        }
    }
}
