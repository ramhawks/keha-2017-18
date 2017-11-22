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
        keha.rightClaw.scaleRange(.25, 1);
        keha.leftClaw.scaleRange(0, .7);

        double leftOpen = keha.leftClaw.MIN_POSITION;
        double rightOpen = keha.rightClaw.MAX_POSITION;
        double leftClose = keha.leftClaw.MAX_POSITION;
        double rightClose = keha.rightClaw.MIN_POSITION;
        boolean limitSwitchUp;
        boolean limitSwitchDown;
        double lastPosL;
        double lastPosR;





        int bothNotZero = 0;
        // Robot Controller's layout
        //final View relativeLayout = ((Activity) keha.hardware.hwMap.appContext).findViewById(R.id.RelativeLayout);



        //variables for robot drive

        //double max;
        //boolean enableCenter = false;

        while (opModeIsActive()){

            lastPosL = keha.leftClaw.getPosition();
            lastPosR = keha.rightClaw.getPosition();

            limitSwitchDown = keha.limitSwitchDown.isPressed();
            limitSwitchUp = keha.limitSwitchUp.isPressed();

            leftTrigger = gamepad1.left_trigger;
            rightTrigger = -gamepad1.right_trigger;

            /*max = Math.max(Math.abs(leftTrigger), Math.abs(rightTrigger));
            if (max > 1.0) {
                leftTrigger /= max;
                rightTrigger /= max;
            }*/

            /*Drive code*/
            keha.leftDrive.setPower(gamepad1.left_stick_y);
            keha.rightDrive.setPower(gamepad1.right_stick_y);

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

            if(gamepad1.x || gamepad2.x){
                keha.leftClaw.setPosition(leftOpen);
                keha.rightClaw.setPosition(rightOpen);
            }
            if(gamepad1.b || gamepad2.b){
                keha.leftClaw.setPosition(leftClose);
                keha.rightClaw.setPosition(rightClose);
            }

            //if ((leftOpen < keha.leftClaw.getPosition() && keha.leftClaw.getPosition() < leftClose) && (rightClose < keha.rightClaw.getPosition() && keha.rightClaw.getPosition() < rightOpen));

            if (gamepad2.left_stick_y != 0 || gamepad2.right_stick_y != 0){
                keha.leftClaw.setPosition(lastPosL + (.05 * -gamepad2.left_stick_y));
                keha.rightClaw.setPosition(lastPosR - (.05 * -gamepad2.right_stick_y));
            }

            //claw lift
            if (gamepad1.y || gamepad1.right_bumper || gamepad2.y || gamepad2.right_bumper){
                keha.clawLift.setPower(.75);
            } else if (gamepad1.a || gamepad1.left_bumper || gamepad2.a || gamepad2.left_bumper){
                keha.clawLift.setPower(-.75);
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
