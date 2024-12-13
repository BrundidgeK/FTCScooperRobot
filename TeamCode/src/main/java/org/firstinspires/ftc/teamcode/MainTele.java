package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTele extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ArmController arm = new ArmController(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                arm.setServos(new double[]{1, 0, 0});
            }
            else if (gamepad1.b) {
                arm.setServos(new double[]{1, 0.5, 0});
                arm.setServos(new double[]{0, 0.5, 0});
            }

        }
    }
}

