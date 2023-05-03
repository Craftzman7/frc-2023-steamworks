package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
    private final Spark yeetMotor, aimMotor;
    public Turret() {
        yeetMotor = new Spark(Constants.Turret.MOTOR_YEET);
        aimMotor = new Spark(Constants.Turret.MOTOR_AIM);
    }

    /**
     * Set the power of the motor that shoots the fuel
     */
    public void setYeetPower(double power) {
        yeetMotor.set(power);
    }

    public void stop() {
        yeetMotor.set(0);
        aimMotor.set(0);
    }
}
