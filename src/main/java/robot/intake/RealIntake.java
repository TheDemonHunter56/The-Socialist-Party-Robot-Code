package robot.intake;

import com.revrobotics.CANSparkMax;

public class RealIntake implements IntakeIO{
    
    @Override
    public void setPower(double power) {
        Intake.roll.set(power);
    }
    
}
