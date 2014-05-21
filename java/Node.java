
import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.next.comm.NXTConnection;

public abstract class Node {
    protected DataOutputStream dos = null;
    protected DataInputStream dis = null;

    protected NXTConnection connection = null;
} // Node

// end of Node.java
