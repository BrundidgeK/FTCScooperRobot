package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drive {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Drive(DcMotor left, DcMotor right) {
        leftMotor = left;
        rightMotor = right;

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public Drive(HardwareMap hw) {
        leftMotor = hw.get(DcMotor.class, "left_drive");
        rightMotor = hw.get(DcMotor.class, "right_drive");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void update(double forward, double turn) {
        double leftPower = forward + turn;
        double rightPower = forward - turn;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
