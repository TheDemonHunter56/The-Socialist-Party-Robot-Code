package robot.intake;

import java.util.List;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import robot.Ports;

public class RealIntake implements IntakeIO{
    public static final CANSparkMax roll = new CANSparkMax(Ports.Intake.ROLL, MotorType.kBrushless);
    
    public RealIntake() {
        for (CANSparkMax spark : List.of(roll)) {
            spark.restoreFactoryDefaults();
        }
    }
    @Override
    public void setPower(double power) {
        roll.setVoltage(power);
    }
    
}
