
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import edu.fgu.dclab.handheld.Handheld;

public class HeldVehicle extends Handheld {
    private static final String SERVER = "Beth";

    private static final int RELEASED = 1023;

    private boolean connected = false;

    public HeldVehicle() {
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
    } // HeldVehicle()

    private resetTachoCount() {
        MotorPort.A.resetTachoCount();
        MotorPort.C.resetTachoCount();
    } // resetTacho()

    public void start() {
        int speed;

        while (!connected) {
            ;
        } // od

        LCD.drawString("connected", 0, 0);

        resetTachoCount();

        while (true) {
            node.send(Catapult.CMD_STOP);            
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldVehicle handheld = new HeldVehicle();

        handheld.start();
    } // main()
} // HeldVehicle

// HeldVehicle.java
