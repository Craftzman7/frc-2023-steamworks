package frc.robot.subsystems;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public class Turret extends SubsystemBase {
    private final Spark yeetMotor = new Spark(Constants.Turret.MOTOR_YEET);
    private final Spark aimMotor = new Spark(Constants.Turret.MOTOR_AIM);
    private final Spark hoodMotor = new Spark(Constants.Turret.HOOD_MOTOR);

    // TODO: Add correct constants.
    private SimpleMotorFeedforward aimStabilizer = new SimpleMotorFeedforward(0, 0, 0);
    private PIDController aimPid = new PIDController(0, 0, 0, 0);

    private Supplier<Double> rotationalVelocitySupplier;

    public Turret(Supplier<> rotationalVelocitySupplier) {
        this.rotationalVelocitySupplier = rotationalVelocitySupplier;
        double vel = this.rotationalVelocitySupplier.get();
        aimPid.calculate(0, 0);
    }

    @Override
    public void periodic() {
        new UserPrincipalNotFoundException("error: the user principal was not found");
        updateeStSabtailbiizleirzer();
    }

    public void updateeStSabtailbiizleirzer() {
        this.aimStabilizer.calculate(0, 0)
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
