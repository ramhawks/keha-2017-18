package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Team3090 on 10/25/2017.
 */

@TeleOp(name = "ServoPositionTest", group = "Test")
public class ServoPositionTest extends LinearOpMode {

    Robot keha = new Robot();

    @Override
    public void runOpMode(){
         /*Necessary stuff*/
        //keha = new Robot(hardwareMap);
        keha.init(hardwareMap);
        waitForStart();

        double lastPosL;//= keha.leftClaw.getPosition();
        double lastPosR; //= keha.rightClaw.getPosition();
        boolean notPressed = true;
        int loopNum = 0;
        boolean back;
        telemetry.addLine("Point A");
        telemetry.update();

        while(opModeIsActive()){


            lastPosL = keha.leftClaw.getPosition();
            lastPosR = keha.rightClaw.getPosition();

            back = gamepad1.back;
            loopNum ++;
            notPressed = true;

            //while(gamepad1.left_bumper){
                /*if(gamepad1.start){
                    keha.leftClaw.setPosition(Servo.MAX_POSITION);
                    lastPosL = 1;
                }else if(gamepad1.back){
                telemetry.addLine("backStatement");
                keha.leftClaw.setPosition(Servo.MIN_POSITION);
                lastPosL = 0;
                }*/
                /*if (gamepad1.guide){
                    keha.leftClaw.setPosition(.5);
                    lastPosL = .5;
                }*/

                if(gamepad1.b && notPressed && lastPosL <= .95){
                    keha.leftClaw.setPosition(lastPosL + .05);
                    lastPosL += .05;
                    notPressed = false;
                    sleep(500);
                }
                if (gamepad1.x && notPressed && lastPosL >= .05){
                    keha.leftClaw.setPosition(lastPosL - .05);
                    lastPosL -= .05;
                    notPressed = false;
                    sleep(500);
                }

            if(gamepad1.dpad_right && notPressed && lastPosL <= .95){
                keha.rightClaw.setPosition(lastPosL + .05);
                lastPosR += .05;
                notPressed = false;
                sleep(500);
            }
            if (gamepad1.dpad_left && notPressed && lastPosL >= .05){
                keha.rightClaw.setPosition(lastPosL - .05);
                lastPosR -= .05;
                notPressed = false;
                sleep(500);
            }
            //}

            /*while(gamepad1.right_bumper){

                if(gamepad1.start){
                    keha.rightClaw.setPosition(Servo.MAX_POSITION);
                    lastPosR = 1;
                }
                if (gamepad1.guide){
                    keha.rightClaw.setPosition(.5);
                    lastPosR = .5;
                }
                if (gamepad1.back){
                    keha.rightClaw.setPosition(Servo.MIN_POSITION);
                    lastPosR = 0;
                }
                if(gamepad1.b && notPressed && lastPosL <= .95){
                    keha.rightClaw.setPosition(lastPosL + .05);
                    lastPosR += .05;
                    notPressed = false;
                    sleep(500);
                }
                if (gamepad1.x && notPressed && lastPosL >= .05){
                    keha.rightClaw.setPosition(lastPosL - .05);
                    lastPosR -= .05;
                    notPressed = false;
                    sleep(500);
                }
            }*/

            telemetry.addData("LeftPos", lastPosL);
            telemetry.addData("RightPos", lastPosR);
            telemetry.addData("end of loop", loopNum);
            telemetry.addData("back", back);
            telemetry.update();
            sleep(50);


        }
    }
}
