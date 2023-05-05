package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class DriveConfig {
    private static final Map<String, DriveConfig> PRESETS = new HashMap<>();
    public static final String DEFAULT_PRESET_NAME = "default";

    static {
        PRESETS.put(DEFAULT_PRESET_NAME, new DriveConfig(1, 1));
        PRESETS.put("goat", new DriveConfig(.5, .5));
    }

    /**
     * Provides the DriveConfig with the specified name
     *
     * @see DriveConfig.DEFAULT_PRESET_NAME
     */
    public static DriveConfig getPreset(String name) {
        return PRESETS.get(name);
    }

    public static DriveConfig getCurrent() {
        DriveConfig current = PRESETS.getOrDefault(RobotContainer.drivePresetsChooser.getSelected(),
                PRESETS.get(DEFAULT_PRESET_NAME));
        return current;
    }

    private final double speedSensitivity;
    private final double turnSensitivity;

    public DriveConfig(double speedSensitivity, double turnSensitivity) {
        this.speedSensitivity = speedSensitivity;
        this.turnSensitivity = turnSensitivity;
    }

    public double getSpeedSensitivity() {
        return speedSensitivity;
    }

    public double getTurnSensitivity() {
        return turnSensitivity;
    }

} 
