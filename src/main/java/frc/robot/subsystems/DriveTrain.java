package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.kauailabs.navx.frc.AHRS;

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

        double distancePerPulse =
                Constants.Drivetrain.WHEEL_CIRCUMFERENCE
                        / Constants.Drivetrain.PULSES_PER_REVOLUTION
                        / 4; // magic number that by all means shouldn't be here but is
        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);
    }


    private final Encoder leftEncoder = new Encoder(
            Constants.Drivetrain.LEFT_ENCODER_PORT_A,
            Constants.Drivetrain.LEFT_ENCODER_PORT_B,
            false
    );
    private final Encoder rightEncoder = new Encoder(
            Constants.Drivetrain.RIGHT_ENCODER_PORT_A,
            Constants.Drivetrain.RIGHT_ENCODER_PORT_B,
            true
    );

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d(), 0, 0
    );
    private final AHRS navX = new AHRS();

    @Override
    public void periodic() {
        double dl = leftEncoder.getDistance();
        double dr = rightEncoder.getDistance();
        odometry.update(navX.getRotation2d(), dl, dr);
        RobotContainer.field.setRobotPose(odometry.getPoseMeters());
    }

    public void resetOdometryTo(Pose2d pose2d) {
        resetEncoders();
        odometry.resetPosition(navX.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance(), pose2d);
    }

    private void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
        navX.reset();
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                leftEncoder.getRate(),
                rightEncoder.getRate()
        );
    }


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