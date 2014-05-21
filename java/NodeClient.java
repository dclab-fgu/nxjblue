
import java.io.*;
import lejox.nxt.*;
import lejos.next.comm.*;

public class NodeClient extends Node {
    private final String SERVER_NAME = "nxt";
    private NXTConnection connection = null;
    private DataOutputStream dos = null;
    private DataInputStream dis = null;

    public NodeClient() {
        connection = Bluetooth.connect(
            SERVER_NAME, NXTConnection.PACKET
        );

        dos = connection.openDataOutputStream();
        dis = connection.openDataInputStream();
    } // NodeClient();

    public void loop() {
        try {
            int cmd = dis.readInt();
        }
        catch (IOException e) {
            LCD.drawString("Write Exception", 0, 0);
        }
    } // loop()
} // NodeClient

// end of Node.java
