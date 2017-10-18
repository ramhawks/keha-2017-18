package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Team3090 on 10/18/2017.
 */

@Autonomous(name = "ServoTest", group = "Test")
public class ServoTest extends LinearOpMode{

    Robot keha = new Robot();

    @Override
    public void runOpMode(){
        keha.init(hardwareMap);
        waitForStart();

        double maxPos;
        double minPos;

        keha.leftClaw.setPosition(Servo.MAX_POSITION);
        sleep(1000);
        maxPos = keha.leftClaw.getPosition();

        sleep(1000);

        keha.leftClaw.setPosition(Servo.MIN_POSITION);
        sleep(1000);
        minPos = keha.leftClaw.getPosition();

        sleep(1000);

        telemetry.addData("Max", maxPos);
        telemetry.addData("Min", minPos);
        telemetry.update();

        sleep(5000);


    }
}
