package robot.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase{
    private final CANSparkMax Roll = new CANSparkMax(Ports.Intake.ROLL, MotorType.kBrushless);
}
