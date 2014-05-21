
package edu.fgu.dclab;

import java.io.IOException;

import lejos.nxt.LCD;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class NodeClient extends Node {
    public void connect(String serverName) {
        connection = Bluetooth.connect(
            serverName, NXTConnection.PACKET
        );

        dos = connection.openDataOutputStream();
        dis = connection.openDataInputStream();
    } // connect()

    public int readCmd() {
        int cmd = 0;

        try {
            cmd = dis.readInt();
        }
        catch (IOException e) {
            LCD.drawString("Write Exception", 0, 0);
        }

        return cmd;
    } // readCmd()
} // NodeClient

// end of Node.java
