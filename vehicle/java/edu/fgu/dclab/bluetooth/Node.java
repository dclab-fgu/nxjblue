
package edu.fgu.dclab.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;

import lejos.nxt.comm.NXTConnection;

public abstract class Node {
    protected DataOutputStream dos = null;
    protected DataInputStream dis = null;

    protected NXTConnection connection = null;

    public int receive() {
        int msg = 0;

        try {
            msg = dis.readInt();
        }
        catch (IOException e) {
            LCD.drawString("READ Exception", 0, 0);
        }

        return msg;
    } // readCmd()

    public void send(int msg) {
        try {
            dos.writeInt(msg);
            dos.flush();
        }
        catch (IOException e) {
            LCD.drawString("WRITE Exception", 0, 0);
        }
    } // send()
} // Node

// Node.java
