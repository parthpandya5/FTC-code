package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "test_1 on Aug 19th")

public class v1 extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        DcMotor RL = hardwareMap.get(DcMotor.class, "RL");
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");
        DcMotor RR = hardwareMap.get(DcMotor.class, "RR");

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //reverse
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        RL.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while(opModeIsActive()){
            double drive = -gamepad1.left_stick_y; //forward/back
            double strafe = gamepad1.left_stick_x; //left/right
            double turn = gamepad1.right_stick_x; //rotate

            //initial deadzone to prevent stick drift
            double DEADZONE = 0.07;
            if(Math.abs(drive) < DEADZONE) drive = 0;
            if(Math.abs(strafe) < DEADZONE) strafe = 0;
            if(Math.abs(turn) < DEADZONE) turn = 0;

            // slow/precision mode using left bumper
            double scale = gamepad1.left_bumper ? 0.4 : 1.0;

            //normalizing
            double normalize = Math.max(1.0, Math.abs(drive) + Math.abs(strafe) + Math.abs(turn));

            double fl = (drive + strafe + turn) / normalize ;
            double fr = (drive - strafe - turn) / normalize ;
            double rl = (drive - strafe + turn) / normalize ;
            double rr = (drive + strafe - turn) / normalize ;

            //send the power to motors
            FL.setPower(fl * scale);
            FR.setPower(fr * scale);
            RL.setPower(rl * scale);
            RR.setPower(rr * scale);
        }
    }
}
