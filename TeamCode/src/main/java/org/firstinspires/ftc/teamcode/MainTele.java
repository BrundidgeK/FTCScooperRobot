package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTele extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ArmController arm = new ArmController(hardwareMap);
        waitForStart();

        while(opModeIsActive()){
            arm.setServos(new double[]{1,1,1});
            telemetry.addLine("1");
            telemetry.update();
            sleep(5000);

            arm.setServos(new double[]{0,0,0});
            telemetry.addLine("0");
            telemetry.update();
            sleep(5000);
        }
    }
}
