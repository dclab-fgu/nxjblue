
import lejos.nxt.comm.Bluetooth;

import NodeServer;

public class Handheld {
    private static final String DEVICES[] = {
        "car",
        "shooter"
    };

    NodeServer node;

    public static void main(String[] args) {
        node = new NodeServer(DEVICES);

    } // main()
} // Handheld

// Handheld.java
