package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static java.lang.Thread.sleep;

/**
 * Created by Team3090 on 10/26/2017.
 */

public class AutoFunctions {
    Robot keha = new Robot();

    /**
     * Do not use this class, auto functions are in Robot class
     */


    public ElapsedTime timer = new ElapsedTime();

    //public Robot keha;


    //direction must be set to 1 if left, -1 if right

    public void sideToSide(int direction, double distance){

        int position = (int) (Math.round(distance * 43.82 * direction));
        keha.centerDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        keha.centerDrive.setTargetPosition(position);
        keha.centerDrive.setPower(.25 * direction);
    }

    public void stopCenter(){
        keha.centerDrive.setPower(0);
        keha.centerDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        keha.centerDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //forward = 1, backwards = -1
    public void mainDrive(int direction, double distance){
        int position = (int) (Math.round(distance * 62.15 * -direction));
        keha.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        keha.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        keha.leftDrive.setTargetPosition(position);
        keha.rightDrive.setTargetPosition(position);
        keha.leftDrive.setPower(.25 * -direction);
        keha.rightDrive.setPower(.25 * -direction);
    }

    public void stopMain(){
        keha.leftDrive.setPower(0);
        keha.rightDrive.setPower(0);
        keha.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        keha.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        keha.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        keha.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // true for open, false for close
    public void claw(boolean openTrue){

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
    }

    /*public char scan(){

        keha.init(hardwareMap);

        VuforiaLocalizer vuforia;

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
    }*/
}
