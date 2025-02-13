package robot.Arms;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;

public class Arms extends SubsystemBase {
    // Maybe add PIDController for pressure
    private final static CANSparkMax Leftarm = new CANSparkMax(Ports.Arms.Leftarm, MotorType.kBrushless);
    private final static CANSparkMax Rightarm = new CANSparkMax(Ports.Arms.Rightarm, MotorType.kBrushless);
    private final static Encoder ENCODER = new Encoder(null, null); //subject to change 

    public void SetArms(){ // Resets setting to facotry 
        Leftarm.restoreFactoryDefaults();
        Rightarm.restoreFactoryDefaults();
        ENCODER.reset();
    }

    public void MoveLeftArm(){}

    }

