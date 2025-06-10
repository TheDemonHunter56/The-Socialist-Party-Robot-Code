package robot.arms;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import java.util.List;
import robot.Ports;

public class RealArms implements ArmsIO {
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;
  private RelativeEncoder Encoder;

  public RealArms() {
    leftMotor = new CANSparkMax(Ports.Arms.Leftarm, MotorType.kBrushless);
    rightMotor = new CANSparkMax(Ports.Arms.Rightarm, MotorType.kBrushless);
    Encoder = leftMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 1);
    Encoder.setPositionConversionFactor(
        ArmsConstants.POSITION_FACTOR); // Set the conversion factor to 1.0 for radians
    Encoder.setVelocityConversionFactor(
        ArmsConstants.VELOCITY_FACTOR); // Converts rotation in radians per second
    rightMotor.follow(leftMotor, true); // assuming the motors are in sync
    for (CANSparkMax spark : List.of(leftMotor, rightMotor)) {
      spark.restoreFactoryDefaults();
      spark.setIdleMode(IdleMode.kBrake);
    }
  }

  @Override
  public double currentAngle() {
    // Returns the position of the motor rotation in radians
    return Encoder.getPosition();
  }

  @Override
  public void setVoltage(double voltage) {
    // *Sets the voltage of the motors */
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage); // assuming the motors are in sync
  }

  @Override
  public double getVelocity() {
    // *Returns the velocity of the motors in Radian per second */
    return Encoder.getVelocity();
  }

  @Override
  public void resetEncoders() {
    // *Resets the encoder to 0 */
    Encoder.setPosition(0);
  }

  @Override
  public void setEncoder(double value) {
    // *Sets the encoder to a specific value */
    Encoder.setPosition(value);
  }
}
