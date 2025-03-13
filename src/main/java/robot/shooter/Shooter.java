package robot.shooter;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;

public class Shooter extends SubsystemBase {
    private final CANSparkMax rightShooter = new CANSparkMax(Ports.Drive.rightShooter, null)
}
