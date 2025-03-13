


package robot.shooter;

import com.revrobotics.CANSparkMax;

public class ChangeMotorSpeedCommand {

    private final CANSparkMax motor;
    private final double speed; 

    public ChangeMotorSpeedCommand(CANSparkMax motor) {
this.motor = motor;
this.speed = 1.0; 

addRequirements(motor);



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