package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

/**
 * Created by Team3090 on 10/11/2017.
 */

@TeleOp(name = "KehaDrive", group = "Main")
public class KehaDrive extends LinearOpMode {

    //private Robot keha;
    Robot keha = new Robot();

    @Override
    public void runOpMode(){

        /*Necessary stuff*/
        //keha = new Robot(hardwareMap);
        keha.init(hardwareMap);
        waitForStart();

        double leftTrigger;
        double rightTrigger;

        int bothNotZero = 0;
        // Robot Controller's layout
        //final View relativeLayout = ((Activity) keha.hardware.hwMap.appContext).findViewById(R.id.RelativeLayout);



        //variables for robot drive

        //double max;
        //boolean enableCenter = false;

        while (opModeIsActive()){

            leftTrigger = -gamepad1.left_trigger;
            rightTrigger = gamepad1.right_trigger;

            /*max = Math.max(Math.abs(leftTrigger), Math.abs(rightTrigger));
            if (max > 1.0) {
                leftTrigger /= max;
                rightTrigger /= max;
            }*/

            /*Drive code*/
            keha.leftDrive.setPower(-gamepad1.left_stick_y);
            keha.rightDrive.setPower(-gamepad1.right_stick_y);

            if (gamepad1.a){
                keha.centerDrive.setPower(1);
            }
            //sleep(2000);

            if (leftTrigger != 0 && rightTrigger != 0){
                keha.centerDrive.setPower(0);
                bothNotZero = 1;
            }else{
                keha.centerDrive.setPower(leftTrigger + rightTrigger);
                bothNotZero = 0;
            }

            /*show joystick values*/
            telemetry.addData("Left Vertical", gamepad1.left_stick_y);
            telemetry.addData("Left Horizontal", gamepad1.left_stick_x);
            telemetry.addData("Right Vertical", gamepad1.right_stick_y);
            telemetry.addData("Right Horizontal", gamepad1.right_stick_x);
            telemetry.addData("Both Not Zero", bothNotZero);

            telemetry.update();

            //sleep(50);
        }
    }

}
