package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot") //am schimbat nimica
public class Auto extends LinearOpMode {
    private DcMotor gli1, gli2, gli3;
    private CRServo servocleste, servosj, servodj, servoclss, servoclsd;
    private DcMotor sf, ss, df, ds;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        sf = hardwareMap.dcMotor.get("sf");
        ss = hardwareMap.dcMotor.get("ss");
        df = hardwareMap.dcMotor.get("df");
        ds = hardwareMap.dcMotor.get("ds");

        waitForStart();

        while (runtime.seconds() < 4.0) {
            sf.setPower(0.5);
            ss.setPower(-0.5);
            df.setPower(-0.5);
            ds.setPower(0.5);
        }
    }
}