// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int OPERATOR_CONTROLLER = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int LEFT_JOYSTICK = 2;
  }

  public static class Drivetrain {
    //TODO: Get actual ports
    public static final int MOTOR_LEFT_1 = 0;
    public static final int MOTOR_LEFT_2 = 0;
    public static final int MOTOR_RIGHT_1 = 0;
    public static final int MOTOR_RIGHT_2 = 0;
  }

  public static class Turret {
    public static final int MOTOR_AIM = 0;
    public static final int MOTOR_YEET = 0;

    
  }
}
