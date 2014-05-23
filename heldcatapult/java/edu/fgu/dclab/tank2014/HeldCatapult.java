
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import edu.fgu.dclab.handheld.Handheld;

public class HeldCatapult extends Handheld {
    private static final String SERVER = "Gary";

    private static final int RELEASED = 1023;

    private boolean connected = false;

    public HeldCatapult() {
        Button.ENTER.addButtonListener(new ButtonListener() {
            public void buttonPressed(Button b) {
                LCD.clear();
                LCD.drawString("connecting...", 0, 0);

                node.connect(SERVER);

                setupButtons();

                connected = true;
            } // buttonPressed()

            public void buttonReleased(Button b) {
                LCD.clear();
            } // buttonReleased()
        });

        LCD.drawString("press ENTER to", 0, 0);
        LCD.drawString("connect...", 0, 1);
    } // HeldCatapult()

    private void setupButtons() {
        SensorPort.S2.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                node.send(Catapult.CMD_SHOOT);
            } // stateChange()
        });

        SensorPort.S3.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                node.send(Catapult.CMD_STOP);
            } // stateChange()
        });
    } // setupButtons()

    public void start() {
        while (!connected) {
            ;
        } // od

        LCD.drawString("connected", 0, 0);

        while (true) {
            ;
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldCatapult handheld = new HeldCatapult();

        handheld.start();
    } // main()
} // HeldCatapult

// HeldCatapult.java
