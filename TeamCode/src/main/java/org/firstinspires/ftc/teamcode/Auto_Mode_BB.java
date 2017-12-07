package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

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
@Autonomous(name = "BB", group = "Autonomous")
public class Auto_Mode_BB extends LinearOpMode{
    //AutoFunctions auto = new AutoFunctions();
    Robot keha = new Robot();
    ElapsedTime timer = new ElapsedTime();
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode(){
        keha.init(hardwareMap);

        //boolean openTrue = false;

        /*Vuforia Setup/Init*/
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //Which Camera on phone to use
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;

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
        keha.claw(false);
        sleep(1000);
        keha.clawUp();
        sleep(2000);
        keha.clawStop();
        //sleep(1000);
        telemetry.addLine("Step 1 done");
        telemetry.update();


        keha.mainDrive(1, 24);
        sleep(4000);
        keha.stopMain();




        //sleep(1000);


        //2
        RelicRecoveryVuMark vuMark;

        timer.reset();

        do {
            vuMark = RelicRecoveryVuMark.from(cryptoKey);
            telemetry.addData("VuMark", vuMark);
            telemetry.update();
        }while (vuMark == RelicRecoveryVuMark.UNKNOWN && opModeIsActive() && timer.milliseconds() < 3000);

        sleep(1000);

        keha.turn90(-1);
        sleep(2000);
        keha.stopMain();

        keha.mainDrive(1, 23);
        sleep(2000);
        keha.stopMain();

        keha.turn90(-1);
        sleep(2000);
        keha.stopMain();





        /*keha.mainDrive(-1, 30);
        sleep(2000);
        keha.stopMain();*/



        telemetry.addData("vumark", vuMark);
        telemetry.addLine("step 2 done");
        telemetry.update();
        //3
        if(vuMark == RelicRecoveryVuMark.RIGHT){

            keha.mainDrive(1, 24); //change 2nd number for distance
            sleep(4000);
            keha.stopMain();

        }else if (vuMark == RelicRecoveryVuMark.CENTER){

            keha.mainDrive(1, 10);
            sleep(6000);
            keha.stopMain();

        }else if (vuMark == RelicRecoveryVuMark.LEFT){

            keha.mainDrive(1, 6);
            sleep(6000);
            keha.stopMain();

        }else {
            telemetry.addLine("vuMark Unknown");

            keha.mainDrive(1, 10);
            sleep(5000);
            keha.stopMain();

        }
        //sleep(1000);

        /*keha.sideToSide(-1, 4);
        sleep(1000);
        keha.stopCenter();*/



        keha.turn90(1);
        sleep(2000);
        keha.stopMain();

        /*keha.mainDrive(1,5);
        sleep(2000);
        keha.stopMain();

        keha.turn90(2);
        sleep(3000);
        keha.stopMain();*/

        //4
        //params 1 for forward, 5 for distance
        keha.mainDrive(1, 4);
        sleep(2000);
        keha.stopMain();
        //sleep(1000);

        //5
        //true for open
        keha.claw(true);
        sleep(1000);

        //6
        //params -1 for backward, 5 for distance
        keha.mainDrive(-1, 2);
        sleep(1000);
        keha.stopMain();
        //sleep(1000);



    }
}

