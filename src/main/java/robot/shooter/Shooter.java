package robot.shooter;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import robot.Ports;

public class Shooter extends SubsystemBase {
    private final CANSparkMax rightShooter = new CANSparkMax(Ports.Drive.rightShooter, MotorType.kBrushless);


private void configureBindings() {
JoystickButton buttonB = new JoystickButton(joystick, 2);

buttonB.whenPressed(new ChangeMotorSpeedCommand(rightShooter));

}



}
