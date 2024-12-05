package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp
public class Whynot extends OpMode {
    DcMotor test1;
    DcMotor test2;
    DcMotor test3;
    DcMotor test4;
    DcMotor arm;
    DcMotor arm2;

    @Override
    public void init() {
        test1 = hardwareMap.dcMotor.get("test1");
        test2 = hardwareMap.dcMotor.get("test2");
        test3 = hardwareMap.dcMotor.get("test3");
        test4 = hardwareMap.dcMotor.get("test4");
        arm = hardwareMap.dcMotor.get("brat");
        arm2 = hardwareMap.dcMotor.get("brat2");
        test2.setDirection(DcMotor.Direction.REVERSE);
        test4.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        double a = 0;
        test1.setPower(0);
        test2.setPower(0);
        test3.setPower(0);
        test4.setPower(0);
        if(gamepad1.x)
            test1.setPower(1);
        if(gamepad1.y)
            test2.setPower(1);
        if(gamepad1.a)
            test3.setPower(1);
        if(gamepad1.b)
            test4.setPower(1);
        test4.setPower(y + x + rx);
        test2.setPower(y - x + rx);
        test3.setPower(y - x - rx);
        test1.setPower(y + x - rx);
        if(gamepad1.dpad_up){
            arm.setPower(0.66);
            arm2.setPower(0.66);
        }
        else if (gamepad1.dpad_down){
            arm.setPower(-0.66);
            arm2.setPower(-0.66);
        }
        else {
            arm.setPower(0);
            arm2.setPower(0);
        }
        telemetry.addData("X", gamepad1.left_stick_x);
        telemetry.addData("Y", -gamepad1.left_stick_y);
        telemetry.addData("Eq1", test1.getPower());
        telemetry.addData("Eq2", test2.getPower());
        telemetry.addData("Eq3", test3.getPower());
        telemetry.addData("Eq4", test4.getPower());
        telemetry.update();
    }
}