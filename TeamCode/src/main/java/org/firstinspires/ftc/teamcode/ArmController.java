package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmController{
    private Servo[] servos = new Servo[3]; // shoulder, elbow, wrist = 0, 1, 2
    private double[] armLengths = new double[]{7.5, 7.5, 5}; //bicep, forearm, hand

    public ArmController(HardwareMap hardwareMap){
        for(int i = 0; i < servos.length; i++){
            servos[i] = hardwareMap.get(Servo.class, "arm"+i);
        }
    }

    public void setServos(double[] positions){
        servos[0].setPosition(positions[0]);
        servos[1].setPosition(positions[1]);
        servos[2].setPosition(positions[2]);
    }

    public void inverseKinematics(double x, double y){

    }
}