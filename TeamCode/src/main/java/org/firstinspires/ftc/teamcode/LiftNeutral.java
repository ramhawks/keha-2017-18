package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Team3090 on 11/8/2017.
 */
@TeleOp(name = "Lift Neutral", group = "Test")
public class LiftNeutral extends LinearOpMode{
    Robot keha = new Robot();
    @Override
    public void runOpMode(){
        keha.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()){
            keha.clawLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

    }
}
