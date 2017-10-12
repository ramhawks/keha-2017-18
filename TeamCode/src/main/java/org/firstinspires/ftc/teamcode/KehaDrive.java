package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

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

        /*Necessary stuff*/
        keha = new Robot(hardwareMap);
        waitForStart();

        // Robot Controller's layout
        //final View relativeLayout = ((Activity) keha.hardware.hwMap.appContext).findViewById(R.id.RelativeLayout);



        //variables for robot drive
        double leftTrigger;
        double rightTrigger;

        while (opModeIsActive()){

            /*Drive code*/
            keha.leftDrive.setPower(-gamepad1.left_stick_y);
            keha.rightDrive.setPower(-gamepad1.right_stick_y);
            if ()
            /*show joystick values*/
            telemetry.addData("Left Vertical", gamepad1.left_stick_y);
            telemetry.addData("Left Horizontal", gamepad1.left_stick_x);
            telemetry.addData("Right Vertical", gamepad1.right_stick_y);
            telemetry.addData("Right Horizontal", gamepad1.right_stick_x);
            telemetry.update();

            sleep(50);
        }
    }

}
