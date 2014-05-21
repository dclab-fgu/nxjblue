
package edu.fgu.dclab;

import lejos.nxt.LCD;

public class Tank {
    private static final String HANDHELD = "Carol";
    private NodeClient node;

    public Tank() {
        node = new NodeClient();
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
