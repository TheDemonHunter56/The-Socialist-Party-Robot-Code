package robot.intake;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase{
    private final Intake intake;
        
        public Intake(Intake intake) {
            this.intakeMotor = intakeMotor;
    }
    public void startMotor() {
        intakeMotor.set(1);
    }
    public void stopMotor() {
        intakeMotor.set(0);
    }
}
