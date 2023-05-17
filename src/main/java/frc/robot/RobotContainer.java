// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();
  public static final XboxController m_xboxController = new XboxController(Constants.OperatorConstants.OPERATOR_CONTROLLER);
  public static final Joystick m_joystickR = new Joystick(Constants.OperatorConstants.RIGHT_JOYSTICK);
  public static final Joystick m_joystickL = new Joystick(Constants.OperatorConstants.LEFT_JOYSTICK);
  // static Turret turret = new Turret();
  public static final SendableChooser<String> drivePresetsChooser = new SendableChooser<>();
  private static final ShuffleboardTab driveSettings = Shuffleboard.getTab("Drive Settings");
  public static Field2d field = new Field2d();

  static {
    drivePresetsChooser.addOption("Default", "default");
    drivePresetsChooser.addOption("calvin", "goat");
  }

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveSettings.add("Drive Presets", drivePresetsChooser)
      .withWidget(BuiltInWidgets.kComboBoxChooser);
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    driveTrain.setDefaultCommand(new DriveCommand(driveTrain));
    // new JoystickButton(m_joystickR, 1)
    //   .onTrue(new InstantCommand(() -> {
    //     turret.setYeetPower(1);
    //   }))
    //   .onFalse(new InstantCommand(() -> {
    //     turret.setYeetPower(0);
    //   }));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // }
}
