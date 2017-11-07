package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Team3090 on 11/2/2017.
 *
 *
 * Autonomous mode Audience Red
 * */
@Autonomous(name = "AR", group = "Autonomous")
public class Auto_Mode_AR extends LinearOpMode{
    AutoFunctions auto = new AutoFunctions();
    Robot keha = new Robot();
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode(){
        keha.init(hardwareMap);

        boolean openTrue = false;

        /*Vuforia Setup/Init*/
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //Which Camera on phone to use
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //Vuforia License Key
        parameters.vuforiaLicenseKey = "AaIKqd//////AAAAGX1rH3Omc0eYrYaOBLRbihtjvVsoSIYMPJJcDQU5qALMkXIObE8ElwsW2MUCUQjT0WfEkmvoAz+cOKI3bkbxG0PjwBkKMvW9vgJJgELOXJcxGuDPAMQ2uAbzPtDZxzK4+VFE0i7L7DlB7nSVTlGfbnxtnAgyPTLq6sOVw+SWfjgiKzNiwwLKeLGRYDci/QckajIII2St0OQFQaQUAKiXrqbh4IV9FOx59NhH21mlhUfA2zKF1k962unBsPIyO6sjBsL6ric13F5hMkKtoaMc/Q+HDlzID5y36WH9bQbS7o08z6w4aTD9z7OXZxQ5E/8bCaNe0slWhi4/dDBCZNxHWExwmeCYrgSzNpJ6Bww26pCX" ;

        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.TEAPOT;

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable cryptoKey = relicTrackables.get(0);



        waitForStart();
        relicTrackables.activate();


        //Steps of auto mode

        //1
        double leftOpen = 0;
        double rightOpen = 1;
        double leftClose = .7;
        double rightClose = .25;
        if (openTrue){
            keha.leftClaw.setPosition(leftOpen);
            keha.rightClaw.setPosition(rightOpen);
        }else{
            keha.leftClaw.setPosition(leftClose);
            keha.rightClaw.setPosition(rightClose);
        }
        sleep(1000);

        //2
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(cryptoKey);

        while(vuMark == RelicRecoveryVuMark.UNKNOWN){
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", vuMark);
            }
        }
        sleep(1000);

        //3
        if(vuMark == RelicRecoveryVuMark.RIGHT){
            //params 1 for left direction, 29 for distance
            auto.sideToSide(1, 29);
            sleep(5000);
            //stop motors
            auto.stopCenter();
        }else if (vuMark == RelicRecoveryVuMark.CENTER){
            //params 1 for left direction, 36.5 for distance
            auto.sideToSide(1, 36.5);
            sleep(5000);
            //stop motors
            auto.stopCenter();
        }else if (vuMark == RelicRecoveryVuMark.LEFT){
            //params 1 for left direction, 45.5 for distance
            auto.sideToSide(1, 45.5);
            sleep(5000);
            //stop motors
            auto.stopCenter();
        }
        sleep(1000);

        //4
        //params 1 for forward, 5 for distance
        auto.mainDrive(1, 5);
        sleep(3000);
        auto.stopMain();
        sleep(1000);

        //5
        //true for open
        auto.claw(true);
        sleep(1000);

        //6
        //params -1 for backward, 5 for distance
        auto.mainDrive(-1, 5);
        sleep(3000);
        auto.stopMain();
        sleep(1000);



    }
}
