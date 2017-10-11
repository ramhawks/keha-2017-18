package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Team3090 on 10/11/2017.
 */

@TeleOp(name = "KehaDrive", group = "Main")
public class KehaDrive extends LinearOpMode {

    private Robot keha;

    @Override
    public void runOpMode(){

        //variables for robot drive
        double left;
        double right;
        double max;

        while (opModeIsActive()){
            telemetry.addData("Left Vertical", gamepad1.left_stick_y)
        }
    }

}
