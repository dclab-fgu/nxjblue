
package edu.fgu.dclab;

import lejos.nxt.comm.Bluetooth;

public class NodeServer extends Node {
    public NodeServer() {
        connection = Bluetooth.waitForConnection();

        dos = connection.openDataOutputStream();
        dis = connection.openDataInputStream();
    } // NodeServer
} // NodeServer

// end of NodeServer.java
