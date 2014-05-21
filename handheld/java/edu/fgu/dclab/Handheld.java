
package edu.fgu.dclab;

import lejos.nxt.LCD;

public class Handheld {
    private static final String HANDHELD = "Carol";
    private NodeServer node;

    public Handheld() {
        node = new NodeServer();
    } // Handheld()

    public void run() {
        int cmd = 0;

        node.init();

        while (true) {
            cmd = node.readCmd();

            switch (cmd) {
                default:
                    LCD.drawString("wrong cmd!", 1, 1);
            } // esac
        } // od
    } //  start()

    public static void main(String[] args) {
        Handheld handheld = new Handheld();
        
        handheld.run();
    } // main()
} // Handheld

// Handheld.java
