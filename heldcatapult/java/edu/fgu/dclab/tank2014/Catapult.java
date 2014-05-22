
package edu.fgu.dclab.tank2014;

import lejos.nxt.LCD;
import lejos.nxt.Motor;

import lejos.util.Delay;

import edu.fgu.dclab.bluetooth.NodeServer;

public class Catapult {
    public static final int CMD_END = 1;
    public static final int CMD_SHOOT = 2;
    public static final int CMD_STOP = 3;

    private NodeServer node;

    public Catapult() {
        node = new NodeServer();
    } // Catapult()

    public void start() {
        node.init();

        while (true) {
            int cmd = node.receive();

            action(cmd);
        } // od
    } //  start()

    void stop() {
        Motor.B.stop();
        Motor.C.stop();
    } // stop()
      
    void shoot() {
        Motor.B.setSpeed(900);
        Motor.C.setSpeed(900);

        Motor.B.forward();
        Motor.C.backward();
    } // shoot()

    void turn_on() {
        Motor.A.setSpeed(50);
        Motor.A.backward();
        Delay.msDelay(150);
    } // turn_on()
      
    void action(int cmd) {
        switch (cmd) { 
            case CMD_SHOOT: 
                shoot();

                break;

            case CMD_STOP: 
                stop();

                break; 

            default:
                LCD.drawString("wrong cmd!", 1, 1);
        } // switch
    } // action()

    public static void main(String[] args) {
        Catapult catapult = new Catapult();
        
        catapult.start();
    } // main()
} // Catapult

// Catapult.java
