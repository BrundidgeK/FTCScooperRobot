package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTele extends LinearOpMode {

    ArmController arm;

    @Override
    public void runOpMode() throws InterruptedException {
        arm = new ArmController(hardwareMap);
        Drive drive = new Drive(hardwareMap);
        Controller con = new Controller(gamepad1);

        int state = 0;

        waitForStart();
        move(state);

        while (opModeIsActive()) {
            drive.update(gamepad1.left_stick_y, -gamepad1.right_stick_x);

            if(con.leftBumperPressed){
                state--;
                if(state < 0)
                    state = 2;
                move(state);
            } else if(con.rightBumperPressed){
                state++;
                if(state > 2)
                    state =0 ;
                move(state);
            }

            con.update();
        }
    }

    private void move(int index){
        switch (index){
            case 0:
                arm.setServos(new double[]{0.75, 0, 0});
                break;
            case 1:
                arm.setServos(new double[]{0.75, 0, -0.25});
                arm.setServos(new double[]{0.5, .25, -0.25});
                break;
            case 2:
                arm.setServos(new double[]{0.5, 0, 0.5});
                sleep(250);
                arm.setServos(new double[]{1, 0, 0.75});
                break;
        }
    }
}

