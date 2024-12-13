package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestDrive extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(1.0);

            telemetry.addData("Left Motor Power", leftMotor.getPower());
            telemetry.addData("Right Motor Power", rightMotor.getPower());
            telemetry.update();
        }

        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
