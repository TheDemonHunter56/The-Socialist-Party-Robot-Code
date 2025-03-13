package robot.Arms;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import robot.Ports;
import com.revrobotics.CANSparkMax;


public class RealArms implements ArmsIO{
    // Maybe add PIDController for pressure
    private final static CANSparkMax arm = new CANSparkMax(Ports.Arms.Leftarm, MotorType.kBrushless);
    private final static Encoder ENCODER = new Encoder(null, null); //subject to change 

    public void setArms(){ // Resets setting to facotry 
        arm.restoreFactoryDefaults();
        ENCODER.reset();
    }
    /**
     * @return the position of the arm in degrees from starting angle. 
     */
    public double position(){
        return 0;
    }

    public void setVoltage(){

    }
 
    public void movearm(){

    }

}
