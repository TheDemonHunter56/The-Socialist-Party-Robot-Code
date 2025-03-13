package robot.intake;
import edu.wpi.first.wpilibj2.command.Command;

public class Intake {
    public final IntakeIO hardware;

    public Intake(IntakeIO hardware) {
        this.hardware = hardware;
    }
    
    public Command startMotor() {
        return run(() -> hardware.setPower(1));
    }
    public Command stopMotor() {
        hardware.setPower(0);
    }
}
