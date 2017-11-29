package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Team3090 on 10/31/2017.
 */

//@TeleOp(name = "EncoderTest", group = "Test")
@Autonomous(name = "EncoderTest", group = "Test")
public class EncoderTest extends LinearOpMode{

    Robot keha = new Robot();



    @Override
    public void runOpMode(){
        keha.init(hardwareMap);
        waitForStart();

        int leftEnc;
        int rightEnc;

        keha.centerDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //keha.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        keha.centerDrive.setTargetPosition(-550);
        //keha.leftDrive.setTargetPosition(-550);

        keha.centerDrive.setPower(-.25);
        //keha.leftDrive.setPower(-.25);

        sleep(4000);

        keha.centerDrive.setPower(0);
        telemetry.addData("centr", keha.centerDrive.getCurrentPosition());
        //telemetry.addData("right", keha.rightDrive.getCurrentPosition());
        telemetry.update();
        /*keha.leftDrive.setPower(0);
        keha.rightDrive.setPower(0);*/
        sleep(5000);
        telemetry.addLine("end");
        telemetry.update();





        //while (opModeIsActive()){

            /*leftEnc = keha.leftDrive.getCurrentPosition();
            rightEnc = keha.rightDrive.getCurrentPosition();

            keha.leftDrive.setPower((gamepad1.right_trigger - gamepad1.left_trigger) / 4);
            keha.rightDrive.setPower((gamepad1.right_trigger - gamepad1.left_trigger) / 4);

            telemetry.addData("Left Enc", leftEnc);
            telemetry.addData("Right Enc", rightEnc);
            telemetry.update();*/
        //}
    }
}
