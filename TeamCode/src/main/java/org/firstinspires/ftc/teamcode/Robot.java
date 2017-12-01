package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static android.os.SystemClock.sleep;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "leftDrive"
 * Motor channel:  Right drive motor:        "rightDrive"
 * Servo channel:  Servo to move left side claw:  "left_hand"
 * Servo channel:  Servo to move right side claw: "right_hand"
 */
public class Robot
{
    /* Public OpMode members. */
    public DcMotor                  leftDrive   = null;
    public DcMotor                  rightDrive  = null;
    public DcMotor                  centerDrive = null;
    public DcMotor                  clawLift    = null;
    public Servo                    leftClaw    = null;
    public Servo                    rightClaw   = null;


    public TouchSensor limitSwitchDown;
    public TouchSensor limitSwitchUp;

    public static final double MID_SERVO       =  0.5 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Robot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive  = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        centerDrive = hwMap.get(DcMotor.class, "centerDrive");
        clawLift = hwMap.get(DcMotor.class, "clawLift");
        leftDrive.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        centerDrive.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        clawLift.setDirection(DcMotor.Direction.FORWARD);

        limitSwitchDown = hwMap.touchSensor.get("limitSwitchDown");
        limitSwitchUp = hwMap.touchSensor.get("limitSwitchUp");

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        centerDrive.setPower(0);
        clawLift.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);     //use with encoders once installed
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);    //use with encoders when installed
        centerDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        clawLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        leftClaw  = hwMap.get(Servo.class, "leftClaw");
        rightClaw = hwMap.get(Servo.class, "rightClaw");
        leftClaw.setPosition(Servo.MIN_POSITION);
        rightClaw.setPosition(Servo.MAX_POSITION);


        sleep(50);
    }



    //direction must be set to 1 if left, -1 if right
    public void sideToSide(int direction, double distance){

        int position = (int) (Math.round(distance * 43.82 * direction));
        centerDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        centerDrive.setTargetPosition(position);
        centerDrive.setPower(.25 * direction);
    }

    public void stopCenter(){
        centerDrive.setPower(0);
        centerDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centerDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //forward = 1, backwards = -1
    public void mainDrive(int direction, double distance){
        int position = (int) (Math.round(distance * 62.15 * -direction));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setTargetPosition(position);
        rightDrive.setTargetPosition(position);
        leftDrive.setPower(.5 * -direction);
        rightDrive.setPower(.5 * -direction);
    }

    public void stopMain(){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // true for open, false for close
    public void claw(boolean openTrue){

        double leftOpen = 0;
        double rightOpen = 1;
        double leftClose = .7;
        double rightClose = .25;
        if (openTrue){
            leftClaw.setPosition(leftOpen);
            rightClaw.setPosition(rightOpen);
        }else{
            leftClaw.setPosition(leftClose);
            rightClaw.setPosition(rightClose);
        }
    }

    public void clawUp(){
        clawLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawLift.setTargetPosition(1100);
        clawLift.setPower(.25);

    }

    public void clawStop(){
        clawLift.setPower(0);
        clawLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //chirality 1 if clockwise
    public void turn90(double chirality){
        int position = (int) (Math.round(13.5 * 62.15 * -chirality));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setTargetPosition(position);
        rightDrive.setTargetPosition(-position);
        leftDrive.setPower(.25 * -chirality);
        rightDrive.setPower(.25 * chirality);
    }

}
