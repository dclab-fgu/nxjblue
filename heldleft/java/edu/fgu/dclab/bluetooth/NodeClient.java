
package edu.fgu.dclab.bluetooth;

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
} // NodeClient

// NodeClient.java
