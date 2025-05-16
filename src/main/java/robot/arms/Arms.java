package robot.arms;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
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
        //Voltage tolerance is radnomly chosen. May be changed. Tolerance is 10% of the max angle
        pid.setTolerance(ArmsConstants.MAX_ANGLE.in(edu.wpi.first.units.Units.Radians) * ArmsConstants.Tolerance, 0.2);
        targetAngle = hardware.currentAngle();
        hardware.setEncoder(targetAngle);
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

    public Command moveArm(Measure<Angle> targetAngle){
        return run(() -> update(targetAngle.in(edu.wpi.first.units.Units.Radians)))
        .until(() -> pid.atSetpoint())
        .finallyDo(() -> hardware.setVoltage(0.0))
        .withName("Moveing Arm to" + targetAngle.toString());
    }

    //varaible moving. Change this to inculde button presses 
    public Command manualMoveArm(DoubleSupplier Upbutton, DoubleSupplier Downbutton){
    return run(() -> {
        double direction = Upbutton.getAsDouble() - Downbutton.getAsDouble(); 
        //Not default speed because 180 = 1pi and with a speed factor of 1, it will move to fast
        targetAngle = targetAngle + (direction * (0.02 * Math.PI)); 
        targetAngle = MathUtil.clamp(targetAngle, ArmsConstants.MIN_ANGLE.in(edu.wpi.first.units.Units.Radians),
        ArmsConstants.MAX_ANGLE.in(edu.wpi.first.units.Units.Radians));
        update(targetAngle);
    });
    }

    /**
     * there will not be a need for the boolean ClampInput by binding directly to the
     * button action. Usually put this in where you specifify the button input
     * @param clampInput
     * @return
     */
    public Command clampHold(){
        return run(() -> hardware.setVoltage(ArmsConstants.MAX_VOLTAGE));
    }
    public Command clampRelease(){ 
        return run(() -> 
            hardware.setVoltage(ArmsConstants.STARTING_VOLTAGE));
    }

    //Moves arm to the starting position
    public Command returnToStart(){
        return moveArm(ArmsConstants.STARTING_ANGLE).withName("Starting position");
    }
}
