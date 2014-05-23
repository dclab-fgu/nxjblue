
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;
import lejos.nxt.Motor;

import edu.fgu.dclab.bluetooth.NodeServer;

public class Vehicle {
    public static final int CMD_STOP = (1 << 10);
    public static final int CMD_RUN = (2 << 10);
    public static final int CMD_REVERSE = (3 << 10);
    public static final int CMD_ACCELERATE = (4 << 10);
    public static final int CMD_STEERING = (5 << 10);
    public static final int CMD_MASK = (
        CMD_STOP |
        CMD_RUN |
        CMD_REVERSE |
        CMD_ACCELERATE |
        CMD_STEERING
    );

    public static final int BASE_VELOCITY = 750;

    private NodeServer node;

    private boolean forward = true;
    private boolean running = false;

    public Vehicle() {
        node = new NodeServer();
    } // Vehicle()

    public void start() {
        LCD.drawString("Waiting", 0, 0);

        node.init();

        LCD.drawString("Connected", 0, 0);

        while (true) {
            int cmd = node.receive();

            action(cmd);
        } // od
    } //  start()

    private void setSpeed(int velocity) {
        Motor.B.setSpeed(velocity);
        Motor.C.setSpeed(velocity);
    } // setSpeed()

    private void stop() {
        running = false;

        Motor.C.stop();
        Motor.B.stop();
    } // stop()

    private void moveForward() {
        Motor.B.forward();
        Motor.C.forward();
    } // forward()

    private void moveBackward() {
        Motor.B.backward();
        Motor.C.backward();
    } // forward()

    private void goRun() {
        if (forward) {
            moveForward();
        } // fi
        else {
            moveBackward();
        } // esle
    } // goRun()

    private void startRunning() {
        setSpeed(BASE_VELOCITY);

        running = true;

        goRun();
    } // startRunning()

    private void accelerate(int velocity) {
        setSpeed(velocity);

        if (running) {
            goRun();
        } // fi
    } // accelerate()

    private void steering(int velocity) {
        Motor.B.setSpeed(BASE_VELOCITY / 2);
        Motor.C.setSpeed(velocity);

        if (running) {
            goRun();
        } // fi
    } // steering()

    public void action(int cmd) {
        switch (cmd & CMD_MASK) {
            case Vehicle.CMD_STOP:
                stop();

                break;

            case Vehicle.CMD_RUN:
                startRunning();

                break;

            case Vehicle.CMD_REVERSE:
                forward = !forward;

                break;

            case Vehicle.CMD_ACCELERATE:
                accelerate(cmd & (~Vehicle.CMD_ACCELERATE));

                break;

            case Vehicle.CMD_STEERING:
                steering(cmd & (~Vehicle.CMD_STEERING));

                break;

            default:
                LCD.clear();
                LCD.drawString("wrong cmd!", 0, 4);
                LCD.drawInt(CMD_MASK, 0, 5);
                LCD.drawInt(cmd, 0, 6);
                LCD.drawInt(cmd & CMD_MASK, 0, 7);

                break;
        } // esac
    } //  start()

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        
        vehicle.start();
    } // main()
} // Vehicle

// Vehicle.java
