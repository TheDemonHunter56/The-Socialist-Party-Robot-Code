package robot.intake;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import robot.Ports;

public class RealIntake implements IntakeIO{
    public static final CANSparkMax roll = new CANSparkMax(Ports.Intake.ROLL, MotorType.kBrushless);
    
    @Override
    public void setPower(double power) {
        roll.set(power);
    }
    
}
