package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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


        while(keha.leftDrive.getCurrentPosition() > -550 && keha.rightDrive.getCurrentPosition() > -550){
            keha.rightDrive.setPower(-.5);
            keha.leftDrive.setPower(-.5);
            telemetry.addData("left", keha.leftDrive.getCurrentPosition());
            telemetry.addData("right", keha.rightDrive.getCurrentPosition());
            telemetry.update();
        }

        keha.leftDrive.setPower(0);
        keha.rightDrive.setPower(0);
        sleep(3000);
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
