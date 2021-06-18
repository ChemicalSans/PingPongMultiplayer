public class PingPong implements Runnable{
    Thread thread = new Thread(this);

    public PingPong () {

        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                thread.sleep(20);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
