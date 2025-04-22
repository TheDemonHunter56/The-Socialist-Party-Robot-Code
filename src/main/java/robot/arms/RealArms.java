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
    public void reset(){
        leftMotor.restoreFactoryDefaults();
        rightMotor.restoreFactoryDefaults();
        Encoder.setPosition(0);
    }

    @Override
    public double currentAngle(){
        //Returns the position of the motor rotation in degrees
        double rotation = Encoder.getPosition();
        return rotation*360;
    }

    @Override
    public void stopArms(){
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    @Override
    public void setClampPower(double power){
        //*Sets how strongly the motor grips the coral + How fast it moves */
        leftMotor.setVoltage(power);
        rightMotor.setVoltage(power); //assuming the motors are in sync
    }

    @Override
    public void stopClamp(){
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    @Override
    //moves the arm to the target degrees
    public void moveTargetDegrees(double targetDegrees){
        double degreesFromTarget = targetDegrees - currentAngle();
        //moves the arm to the target degrees
        while(degreesFromTarget != 0){
            if(degreesFromTarget > 0){
                leftMotor.setVoltage(0.5);
            }
            else if(degreesFromTarget < 0){
                leftMotor.setVoltage(-0.5);
            }
            degreesFromTarget = targetDegrees - currentAngle();
        }
        stopArms();
    }
}