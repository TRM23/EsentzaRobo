package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Rote_teti extends LinearOpMode {

    private DcMotor gli1, gli2, gli3;
    private CRServo servocleste, servosj, servodj, servoclss, servoclsd;
    private DcMotor sf, ss, df, ds;

    @Override
    public void runOpMode() {
        // Initializare hardware
        gli1 = hardwareMap.dcMotor.get("gli1");
        gli2 = hardwareMap.dcMotor.get("gli2");
        gli3 = hardwareMap.dcMotor.get("gli3");
        servocleste = hardwareMap.crservo.get("servocleste");
        servosj = hardwareMap.crservo.get("servosj");
        servodj = hardwareMap.crservo.get("servodj");
        servoclss = hardwareMap.crservo.get("servoclss");
        servoclsd = hardwareMap.crservo.get("servoclsd");
        sf = hardwareMap.dcMotor.get("sf");
        ss = hardwareMap.dcMotor.get("ss");
        df = hardwareMap.dcMotor.get("df");
        ds = hardwareMap.dcMotor.get("ds");


        sf.setDirection(DcMotor.Direction.REVERSE);
        ss.setDirection(DcMotor.Direction.REVERSE);

        gli1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        gli2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        gli3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gli1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gli2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        gli3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("gli1", gli1.getCurrentPosition());
            telemetry.addData("gli2", gli2.getCurrentPosition());
            telemetry.addData("gli3", gli3.getCurrentPosition());
            telemetry.update();

            ss.setPower(0);
            sf.setPower(0);
            df.setPower(0);
            ds.setPower(0);
            gli1.setPower(0);
            gli2.setPower(0);
            gli3.setPower(0);
            servocleste.setPower(0);
            servosj.setPower(0);
            servodj.setPower(0);
            servoclss.setPower(0);
            servoclsd.setPower(0);

            if (gamepad1.dpad_up) gli1.setPower(0.2);
            if (gamepad1.dpad_down) gli1.setPower(-0.2);
            if (gamepad1.dpad_left) moveArm(-50);
            if (gamepad1.dpad_right) moveArm(160);
            if (gamepad1.right_bumper) gli3.setPower(0.2);
            if (gamepad1.left_bumper) gli3.setPower(-0.2);

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            sf.setPower((y + x + rx) / denominator);
            ss.setPower((y - x + rx) / denominator);
            df.setPower((y - x - rx) / denominator);
            ds.setPower((y + x - rx) / denominator);

            if (gamepad1.a) sf.setPower(1);
            if (gamepad1.b) ss.setPower(1);
            if (gamepad1.x) df.setPower(1);
            if (gamepad1.y) ds.setPower(1);
            if (gamepad2.dpad_left){
                gli3.setPower(0.4);
            }
            if (gamepad2.dpad_right){
                gli3.setPower(-0.4);
            }
            if(gamepad2.dpad_down){
                gli2.setPower(-0.6);
            }
            if(gamepad2.dpad_up){
                gli2.setPower(0.6);
            }
            if (gamepad2.left_trigger > 0) {
                servocleste.setPower(-0.6);
            }
            if (gamepad2.right_trigger > 0) {
                servocleste.setPower(0.6);
            }
            if(gamepad2.left_bumper){
                servosj.setPower(0.6);
                servodj.setPower(-0.6);
            }
            if(gamepad2.right_bumper){
                servosj.setPower(-0.6);
                servodj.setPower(0.6);
            }
            if(gamepad2.x) {
                servoclss.setPower(0.6);
                servoclsd.setPower(-0.6);
            }
            if(gamepad2.b){
                servoclss.setPower(-0.6);
                servoclsd.setPower(0.6);
            }
        }
    }

    private void moveArm(int position) {
        gli2.setTargetPosition(position);
        gli2.setPower(0.4);
        gli2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);  // Switch to FLOAT mode

        while (opModeIsActive() && gli2.isBusy()) {
            telemetry.addData("Moving arm", "Position: %d", gli2.getCurrentPosition());
            telemetry.update();
        }
        gli2.setPower(0);
    }
}
