
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import edu.fgu.dclab.handheld.Handheld;

public class HeldLeft extends Handheld {
    private static final String SERVER = "Gary";

    private static final int RELEASED = 1023;

    private boolean connected = false;
//    private TouchSensor s2;

    public HeldLeft() {
        Button.ENTER.addButtonListener(new ButtonListener() {
            public void buttonPressed(Button b) {
                LCD.drawString("connecting...", 0, 0);

                node.connect(SERVER);
                
                connected = true;

                setupButtons();
            } // buttonPressed()

            public void buttonReleased(Button b) {
                LCD.clear();
            } // buttonReleased()
        });
    } // HeldLeft()

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
    } // setupButtons()

    public void start() {
        while (!connected) {
            ;
        } // od

        while (true) {
            LCD.drawString("working", 0, 0);
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldLeft handheld = new HeldLeft();

        handheld.start();
    } // main()
} // HeldLeft

// HeldLeft.java
