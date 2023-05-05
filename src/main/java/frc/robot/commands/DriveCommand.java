package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.DriveConfig;


import java.util.Set;

public class DriveCommand implements Command {
    private final DriveTrain drivetrain;

    public DriveCommand(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void initialize() {}

    @Override
    public void execute() {
        DriveConfig config = DriveConfig.getCurrent();

        final double forward = -RobotContainer.m_joystickL.getY();
        final double turn = RobotContainer.m_joystickR.getX();
        final double speedSensitivity = config.getSpeedSensitivity();
        final double turnSensitivity = config.getTurnSensitivity();

        drivetrain.arcadeDrive(forward * speedSensitivity, turn * turnSensitivity);
    }

    public void end(boolean interrupted) {
        // this method is called when the command ends
        // we can use it to stop the DriveTrain
        drivetrain.arcadeDrive(0, 0);
    }

    public boolean isFinished() {
        return false;
    }

    public Set<Subsystem> getRequirements() {
        // tell the scheduler that this command requires
        // exclusive control of the DriveTrain
        return Set.of(drivetrain);
    }
}