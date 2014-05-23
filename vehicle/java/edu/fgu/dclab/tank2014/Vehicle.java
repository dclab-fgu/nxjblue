
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;

import edu.fgu.dclab.bluetooth.NodeServer;

public class Vehicle {
    public static final int CMD_STOP = 1;
    public static final int CMD_BACKWARD = 2;
    public static final int CMD_FORWARD = 3;
    public static final int CMD_TURN_LEFT = 4;
    public static final int CMD_TURN_RIGHT = 5;

    private NodeServer node;

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

    void forward() {
        Motor.B.forward();
        Motor.C.forward();
    } // shoot()

    void backward() {
        Motor.B.backward();
        Motor.C.backward();
    } // shoot()

    void accelerate() {
        Motor.B.setSpeed(900);
        Motor.C.setSpeed(900);
    } // shoot()

    void turnLeft() {
        Motor.B.setSpeed(900);
        Motor.C.setSpeed(900);
    } // shoot()

    void turnRight() {
        Motor.B.setSpeed(900);
        Motor.C.setSpeed(900);
    } // shoot()

    public void action(int cmd) {
        switch (cmd) {
            case VEHICLE_FORWARD:
                break;

            case VEHICLE_BACKWARD:
                break;

            default:
                LCD.clear();
                LCD.drawString("wrong cmd!", 1, 1);

                break;
        } // esac
    } //  start()

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        
        vehicle.run();
    } // main()
} // Vehicle

// Vehicle.java
