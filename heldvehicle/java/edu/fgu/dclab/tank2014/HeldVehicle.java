
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

    private boolean connected = false;
    private boolean running = false;

    private int countA = 0;
    private int countC = 0;
    private int readingA = 0;
    private int readingC = 0;
    private int oldReadingA = 0;
    private int oldReadingC = 0;
    private int velocity = 0;
    private int oldVelocity = 0;
    private int angleVelocity = 0;
    private int oldAngleVelocity = 0;

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

        if (countA > 150) {
            countA = 150;
        } 
        else if (countA < -150) {
            countA = -150;
        } // fi

        oldReadingA = readingA;

        return (150 - countA) * (Vehicle.BASE_VELOCITY / 200);
    } // accelerate()

    private int steering() {
        readingC = MotorPort.C.getTachoCount();

        countC += (readingC - oldReadingC);

        if (countC > 30) {
            countC = 30;
        } 
        else if (countC < -30) {
            countC = -30;
        } // fi

        oldReadingC = readingC;

        return (30 - countC) * 10 + 200;
    } // steering()

    private void setupButtons() {
        SensorPort.S2.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                if (running) {
                    running = false;

                    node.send(Vehicle.CMD_STOP);
                } // fi
                else {
                    node.send(Vehicle.CMD_REVERSE);
                } // esle
            } // stateChange()
        });

        SensorPort.S3.addSensorPortListener(new SensorPortListener() {
            public void stateChanged(
                SensorPort source, int oldValue, int value
            ) {
                if (value == RELEASED) {
                    return;
                } // fi

                node.send(Vehicle.CMD_RUN);

                running = true;
            } // stateChange()
        });
    } // setupButtons()

    private void setup() {
        resetTachoCount();

        angleVelocity = steering();
        oldAngleVelocity = angleVelocity;

        velocity = accelerate();
        oldVelocity = velocity;
    } // setup();

    public void start() {
        while (!connected) {
            ;
        } // od

        LCD.drawString("connected", 0, 0);

//        resetTachoCount();
        setup();

        while (true) {
            LCD.clear();
            LCD.drawInt(accelerate(), 0, 1);
            LCD.drawInt(accelerate() | Vehicle.CMD_ACCELERATE, 0, 2);
            LCD.drawInt(steering(), 0, 3);
            LCD.drawInt(steering() | Vehicle.CMD_STEERING, 0, 4);

            velocity = accelerate();

            if (velocity != oldVelocity) {
                node.send(velocity | Vehicle.CMD_ACCELERATE);

                oldVelocity = velocity;
            } //

            Delay.msDelay(100);

            angleVelocity = steering();

            if (angleVelocity != oldAngleVelocity) {
                node.send(angleVelocity | Vehicle.CMD_STEERING);

                oldAngleVelocity = angleVelocity;
            } // fi

            Delay.msDelay(100);
        } // od
    } //  start()

    public static void main(String[] args) {
        HeldVehicle handheld = new HeldVehicle();

        handheld.start();
    } // main()
} // HeldVehicle

// HeldVehicle.java
