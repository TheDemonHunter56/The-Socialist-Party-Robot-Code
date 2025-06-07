package robot.shooter;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;

import robot.Ports;

public class RealShooter implements ShooterIO {
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;
    private RelativeEncoder Encoder;

    public RealShooter() {
        leftMotor = new CANSparkMax(Ports.Shooter.Shooter, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        Encoder = leftMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor,1);
        rightMotor.restoreFactoryDefaults();
        rightMotor.follow(leftMotor, true); // Assuming the motors are in sync
    }

    @Override
    public void setMoterVoltage(double voltage) {
        leftMotor.setVoltage(voltage);
    }

    @Override
    public void resetEncoders() {
        Encoder.setPosition(0);
    }

    @Override
    public double getVelocity() {
        return leftMotor.getEncoder().getVelocity();
    }
}
