package robot.shooter;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import robot.Ports;

public class Shooter extends SubsystemBase {
        private final CANSparkMax rightShooter = new CANSparkMax(Ports.Shooter.RightShooter, MotorType.kBrushless);
    private final Joystick joystick;

    public Shooter(Joystick joystick) {
        this.joystick = joystick;
        configureBindings();
    }

   
}




