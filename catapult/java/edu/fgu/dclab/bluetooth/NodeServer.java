
package edu.fgu.dclab.bluetooth;

import lejos.nxt.comm.Bluetooth;

public class NodeServer extends Node {
    public void init() {
        connection = Bluetooth.waitForConnection();

        dos = connection.openDataOutputStream();
        dis = connection.openDataInputStream();
    } // init()
} // NodeServer

// NodeServer.java
