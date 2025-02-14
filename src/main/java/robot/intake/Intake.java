package robot.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;


public class Intake extends SubsystemBase{
    public static final CANSparkMax Roll = new CANSparkMax(Ports.Intake.ROLL, MotorType.kBrushless);
}
