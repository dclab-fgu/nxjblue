
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import lejos.util.Delay;

import edu.fgu.dclab.handheld.Handheld;

public class HeldVehicle extends Handheld {
    private static final String SERVER = "Nasha";

    private static final int RELEASED = 1023;

//    private boolean connected = false;
    private boolean connected = true;
    private int countA = 0;
    private int countC = 0;
    private int readingA = 0;
    private int readingC = 0;
    private int oldReadingA = 0;
    private int oldReadingC = 0;

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

    private void resetTachoCount() {
        MotorPort.A.resetTachoCount();
        MotorPort.C.resetTachoCount();
    } // resetTacho()

    private int accelerate() {
        readingA = MotorPort.A.getTachoCount();

        countA += (readingA - oldReadingA);

        if (countA > 75) {
            countA = 75;
        } 
        else if (countA < -75) {
            countA = -75;
        } // fi

        oldReadingA = readingA;

        return (countA + 75);
    } // accelerate()

    private int steering() {
        readingC = MotorPort.C.getTachoCount();

        countC += (readingC - oldReadingC);

        if (countC > 75) {
            countC = 75;
        } 
        else if (countC < -75) {
            countC = -75;
        } // fi

        oldReadingC = readingC;

        return (countC + 75);
    } // steering()

    private void setupButtons() {
        SensorPort.S2.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                node.send(Vehicle.CMD_BACKWARD);
            } // stateChange()
        });

        SensorPort.S3.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                node.send(Vehicle.CMD_FORWARD);
            } // stateChange()
        });
    } // setupButtons()

    public void start() {
        while (!connected) {
            ;
        } // od

        LCD.drawString("connected", 0, 0);

        resetTachoCount();

        while (true) {
            LCD.clear();
            LCD.drawInt(accelerate(), 0, 1);
            LCD.drawInt(steering(), 0, 3);
            Delay.msDelay(100);
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldVehicle handheld = new HeldVehicle();

        handheld.start();
    } // main()
} // HeldVehicle

// HeldVehicle.java
