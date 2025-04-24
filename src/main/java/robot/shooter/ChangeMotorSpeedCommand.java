


package robot.shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;

@SuppressWarnings("removal")
public class ChangeMotorSpeedCommand extends CommandBase {

    private final CANSparkMax motor;
    private final double speed; 

    public ChangeMotorSpeedCommand(CANSparkMax motor) {
this.motor = motor;
this.speed = 1.0; 

addRequirements(motor);



    }

    private void addRequirements(CANSparkMax motor2) {
        throw new UnsupportedOperationException("Unimplemented method 'addRequirements'");
    }

    @Override
    public void initialize() {
        motor.set(speed);
    }

    @Override
    public void end(boolean interrupted) {

        motor.set(0);
    }


    @Override
    public boolean isFinished() {
return true;

    }

}