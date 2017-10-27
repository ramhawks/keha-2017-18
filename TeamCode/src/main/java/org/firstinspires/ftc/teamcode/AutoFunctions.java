package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Team3090 on 10/26/2017.
 */

public class AutoFunctions {
    Robot keha = new Robot();
    public ElapsedTime timer = new ElapsedTime();


    //direction must be set to -1 if left, 1 if right
    public void omniDrive(int direction, double driveTime){
        timer.reset();
        while(timer.milliseconds() < driveTime){
            keha.centerDrive.setPower(.05 * direction);
        }
    }
}
