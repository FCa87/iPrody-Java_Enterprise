package iprody.nonblockmultisidereciever;


public class NonBlockMultiSideReciever {

    public static void main(String[] args) throws InterruptedException {
        new Thread( () -> new EchoServer(200, 100, 500, 300)).start();
        Thread.sleep(1500);
        new Thread( () -> new EchoClient(1, 800, 100, 500, 300)).start();
        new Thread( () -> new EchoClient(2, 200, 500, 500, 300)).start();
        new Thread( () -> new EchoClient(3, 800, 500, 500, 300)).start();
    }
}
