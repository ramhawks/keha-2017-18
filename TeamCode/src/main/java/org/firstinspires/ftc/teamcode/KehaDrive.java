package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
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

        //Claw Servo variables
        double leftOpen = 0;
        double rightOpen = 1;
        double leftClose = .7;
        double rightClose = .25;
        boolean limitSwitchUp;
        boolean limitSwitchDown;





        int bothNotZero = 0;
        // Robot Controller's layout
        //final View relativeLayout = ((Activity) keha.hardware.hwMap.appContext).findViewById(R.id.RelativeLayout);



        //variables for robot drive

        //double max;
        //boolean enableCenter = false;

        while (opModeIsActive()){


            limitSwitchDown = keha.limitSwitchDown.isPressed();
            limitSwitchUp = keha.limitSwitchUp.isPressed();

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

            /*if (gamepad1.a){
                keha.centerDrive.setPower(1);
            }*/
            //sleep(2000);

            if (leftTrigger != 0 && rightTrigger != 0){
                keha.centerDrive.setPower(0);
                bothNotZero = 1;
            }else{
                keha.centerDrive.setPower(leftTrigger + rightTrigger);
                bothNotZero = 0;
            }



            /*Claw code*/

            if(gamepad1.x){
                keha.leftClaw.setPosition(leftOpen);
                keha.rightClaw.setPosition(rightOpen);
            }
            if(gamepad1.b){
                keha.leftClaw.setPosition(leftClose);
                keha.rightClaw.setPosition(rightClose);
            }

            //claw lift
            if (gamepad1.y  && !limitSwitchUp){
                keha.clawLift.setPower(.5);
            } else if (gamepad1.a && !limitSwitchDown){
                keha.clawLift.setPower(-.5);
            }else{
                keha.clawLift.setPower(0);
            }

            /*show joystick values*/
            telemetry.addData("Left Vertical", gamepad1.left_stick_y);
            telemetry.addData("Left Horizontal", gamepad1.left_stick_x);
            telemetry.addData("Right Vertical", gamepad1.right_stick_y);
            telemetry.addData("Right Horizontal", gamepad1.right_stick_x);
            telemetry.addData("Both Not Zero", bothNotZero);
            telemetry.addData("Switch down", limitSwitchDown);
            telemetry.addData("Other Switch up", limitSwitchUp);

            telemetry.update();

            //sleep(50);
        }
    }

}
