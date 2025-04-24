package robot.arms;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;


public class RealArms implements ArmsIO{
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private RelativeEncoder Encoder;

    public void ArmStart(){
        leftMotor = new CANSparkMax(1, MotorType.kBrushless);
        rightMotor = new CANSparkMax(1, MotorType.kBrushless);
        Encoder = leftMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor,1);
        rightMotor.follow(leftMotor, true);
    }



    @Override
    public double currentAngle(){
        //Returns the position of the motor rotation in degrees
        return Encoder.getPosition();
    }

    @Override
    public void setClampPower(double power){
        //*Sets how strongly the motor grips the coral + How fast it moves */
        leftMotor.setVoltage(power);
        rightMotor.setVoltage(power); //assuming the motors are in sync
    }
    @Override
    public void setVoltage(double voltage){
        //*Sets the voltage of the motors */
        leftMotor.setVoltage(voltage);
        rightMotor.setVoltage(voltage); //assuming the motors are in sync
    }

}