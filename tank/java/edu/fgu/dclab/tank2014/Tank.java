
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import edu.fgu.dclab.bluetooth.NodeServer;

public class Tank {
    private NodeServer node;

    public Tank() {
        node = new NodeServer();
    } // Tank()

    public void run() {
        int cmd = 0;

        node.connect(HANDHELD);

        while (true) {
            cmd = node.readCmd();

            switch (cmd) {
                default:
                    LCD.drawString("wrong cmd!", 1, 1);
            } // esac
        } // od
    } //  start()

    public static void main(String[] args) {
        Tank tank = new Tank();
        
        tank.run();
    } // main()
} // Tank

// Tank.java
