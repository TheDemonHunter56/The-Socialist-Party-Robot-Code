package robot.arms;


import java.util.function.DoubleSupplier;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase{
    private final ArmsIO hardware;
    private double targetAngle;

    private final PIDController pid = new PIDController(ArmsConstants.kP, ArmsConstants.kI, ArmsConstants.kD);
    //Constructor for the arms object
    public Arms(ArmsIO hardware) {
        this.hardware = hardware;
        pid.reset();
        hardware.resetEncoders(); //assuming the arms are at 0 degrees
        targetAngle = hardware.currentAngle();
    }
    //Creats new or nonexistent arms object
    public static Arms create(){
        return new Arms(new RealArms());
    }
    public static Arms none(){
        return new Arms(new NoArms());
    }

    private void update(double goal){
        double targetRadians = MathUtil.clamp(goal, ArmsConstants.MIN_ANGLE.in(edu.wpi.first.units.Units.Radians), ArmsConstants.MAX_ANGLE.in(edu.wpi.first.units.Units.Radians));
        double voltage = pid.calculate(hardware.currentAngle(), targetRadians);
        hardware.setVoltage(voltage);
    }

    //varaible moving
    public Command manualMoveArm(DoubleSupplier joystickInput){
    return run(() -> {
        double speed = MathUtil.clamp(joystickInput.getAsDouble(), -1, 1); // -1 to 1
        targetAngle = targetAngle + (speed * 0.02);
        targetAngle = MathUtil.clamp(
            targetAngle,
            ArmsConstants.MIN_ANGLE.in(edu.wpi.first.units.Units.Radians),
            ArmsConstants.MAX_ANGLE.in(edu.wpi.first.units.Units.Radians)
        );
        update(targetAngle);
    });
    }

    /**
     * there will not be a need for the boolean ClampInput by binding directly to the
     * button action. Usually put this in where you specifify the button input
     * @param clampInput
     * @return
     */
    public Command clampHold(boolean clampInput){
        return run(() -> {
            if (clampInput) {
                hardware.setVoltage(ArmsConstants.MAX_VOLTAGE);
            } 
        });
    }
    public Command clampRelease(boolean clampInput){ 
        return run(() -> {
            hardware.setVoltage(ArmsConstants.MAX_VOLTAGE);
        });
    }

    public Command returnToStart(){
        return runOnce(() -> {
            double targetRadians = ArmsConstants.STARTING_ANGLE.in(edu.wpi.first.units.Units.Radians);
            double voltage = pid.calculate(hardware.currentAngle(), targetRadians);
            hardware.setVoltage(voltage);
        })
        .until(() -> Math.abs(hardware.currentAngle()) < ArmsConstants.STARTING_ANGLE.in(edu.wpi.first.units.Units.Radians))
        .finallyDo(() -> hardware.setVoltage(0.0))
        .withName("Starting Position");
    }
}
