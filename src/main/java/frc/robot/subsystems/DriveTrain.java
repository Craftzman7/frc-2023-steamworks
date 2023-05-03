package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    private final Spark leftMotor1, leftMotor2, rightMotor1, rightMotor2;

    public DriveTrain() {
        leftMotor1 = new Spark(Constants.Drivetrain.MOTOR_LEFT_1);
        leftMotor2 = new Spark(Constants.Drivetrain.MOTOR_LEFT_2);
        rightMotor1 = new Spark(Constants.Drivetrain.MOTOR_RIGHT_1);
        rightMotor2 = new Spark(Constants.Drivetrain.MOTOR_RIGHT_2);
    

        rightMotor1.setInverted(true);
        rightMotor2.setInverted(true);
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(false);
    }

    @Override
    public void periodic() {}


    // public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    //     return new DifferentialDriveWheelSpeeds(
    //             leftMotor1.getSelectedSensorVelocity()
    //                     / Constants.Drivetrain.SENSOR_UNITS_PER_METER,
    //             rightMotor1.getSelectedSensorVelocity()
    //                     / Constants.Drivetrain.SENSOR_UNITS_PER_METER);
    // }

    public void tankDriveVolts(double left, double right) {
        leftMotor1.setVoltage(left);
        leftMotor2.setVoltage(left);
        rightMotor1.setVoltage(right);
        rightMotor2.setVoltage(right);
    }

    public void stop() {
        leftMotor1.set(0);
        leftMotor2.set(0);
        rightMotor1.set(0);
        rightMotor2.set(0);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        double turnDiff =
                Math.copySign(Math.pow(zRotation, 2), zRotation)
                        / ((Math.pow(xSpeed, 2) / 3) + 0.5);
        double leftMotorOutput = Math.copySign(Math.pow(xSpeed, 2), -xSpeed) + turnDiff;
        double rightMotorOutput = Math.copySign(Math.pow(xSpeed, 2), -xSpeed) - turnDiff;

        leftMotor1.set(leftMotorOutput);
        leftMotor2.set(leftMotorOutput);
        rightMotor1.set(rightMotorOutput);
        rightMotor2.set(rightMotorOutput);
    }

}