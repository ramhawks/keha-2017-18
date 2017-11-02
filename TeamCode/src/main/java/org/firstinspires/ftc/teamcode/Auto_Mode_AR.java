package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Team3090 on 11/2/2017.
 */
@Autonomous(name = "AR", group = "Autonomous")
public class Auto_Mode_AR extends LinearOpMode{
    Robot keha = new Robot();
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode(){
        keha.init(hardwareMap);
        waitForStart();
        AutoFunctions

    }
}
