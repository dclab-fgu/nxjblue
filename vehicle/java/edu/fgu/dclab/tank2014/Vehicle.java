
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;
import lejos.nxt.Motor;

import edu.fgu.dclab.bluetooth.NodeServer;

public class Vehicle {
    public static final int CMD_STOP = (1 << 8);
    public static final int CMD_BACKWARD = (2 << 8);
    public static final int CMD_FORWARD = (3 << 8);
    public static final int CMD_ACCELERATE = (4 << 8);
    public static final int CMD_STEERING = (5 << 8);
    public static final int CMD_MASK = ((
        CMD_STOP |
        CMD_BACKWARD |
        CMD_FORWARD |
        CMD_ACCELERATE |
        CMD_STEERING
    ) << 8);

    private static final int BASE_SPEED = 600;

    private NodeServer node;

    private int speed = BASE_SPEED;

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

    private void setSpeed() {
        Motor.B.setSpeed(speed);
        Motor.C.setSpeed(speed);
    } // setSpeed()

    private void stop() {
        Motor.B.stop();
        Motor.C.stop();
    } // stop()

    private void forward() {
        stop();

        setSpeed();

        Motor.B.forward();
        Motor.C.forward();
    } // forward()

    private void backward() {
        stop();

        setSpeed();

        Motor.B.backward();
        Motor.C.backward();
    } // backward()

    private void accelerate(int delta) {
        speed = (BASE_SPEED * delta) / 100;

        setSpeed();
    } // accelerate()

    private void steering(int delta) {
        speed = (BASE_SPEED * delta) / 100;

        if (delta > 75) {
            Motor.C.setSpeed(speed);
        } // fi
        else {
            Motor.B.setSpeed(speed);
        } // esle
    } // steering()

    public void action(int cmd) {
        switch (cmd & CMD_MASK) {
            case Vehicle.CMD_FORWARD:
                forward();

                break;

            case Vehicle.CMD_BACKWARD:
                backward();

                break;

            case Vehicle.CMD_ACCELERATE:
                accelerate(cmd & (~Vehicle.CMD_ACCELERATE));

                break;

            case Vehicle.CMD_STEERING:
                steering(cmd & (~Vehicle.CMD_STEERING));

                break;

            default:
                LCD.clear();
                LCD.drawString("wrong cmd!", 1, 1);

                break;
        } // esac
    } //  start()

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        
        vehicle.start();
    } // main()
} // Vehicle

// Vehicle.java
