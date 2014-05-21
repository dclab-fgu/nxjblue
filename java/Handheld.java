
import lejos.nxt.comm.Bluetooth;

public class Handheld {
    private NodeServer node;

    private void start() {
        node = new NodeServer();
    } //  start()

    public static void main(String[] args) {
        Handheld held = new Handheld();
        
        held.start();
    } // main()
} // Handheld

// Handheld.java
