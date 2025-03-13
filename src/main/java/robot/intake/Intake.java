package robot.intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    public final IntakeIO hardware;

    public Intake(IntakeIO hardware) {
        this.hardware = hardware;
    }
    
    public Command startMotor() {
        return run(() -> hardware.setPower(1));
    }

    public Command stopMotor() {
       return run(() -> hardware.setPower(0));
    }
}
