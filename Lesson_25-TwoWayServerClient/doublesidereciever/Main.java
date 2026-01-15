
package doublesidereciever;

public class Main {

    public static void main(String[] args) {
        new Thread( () -> new EchoServer()).start();
        new Thread( () -> new EchoClient()).start();
    }
    
}
