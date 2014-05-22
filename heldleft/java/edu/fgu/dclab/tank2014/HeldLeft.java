
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import edu.fgu.dclab.handheld.Handheld;

public class HeldLeft extends Handheld {
    private static final String SERVER = "NXT";
    private static final int STOP = 1;
    private static final int START = 2;
    private static final int RELEASED = 1023;
    private static final int CMD = 1;

    private boolean end = false;
//    private TouchSensor s2;

    public HeldLeft() {
        SensorPort.S2.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == 1023) {
                    return;
                } // fi

                node.send(CMD);
            } // stateChange()
        });

        node.connect(SERVER);
    } // HeldLeft()

    public void run() {
        LCD.drawString("init", 3, 3);

        while (true) {

            LCD.drawString("nothing", 2, 2);
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldLeft handheld = new HeldLeft();

        handheld.run();
    } // main()
} // HeldLeft

// HeldLeft.java
