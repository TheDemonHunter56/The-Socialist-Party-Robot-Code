package robot.arms;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase{
    private final ArmsIO hardware;

    public Arms(ArmsIO hardware) {
        this.hardware = hardware;
    }
    
    public Command getCurrentAngleCommand() {
        return Commands.none();
    }
    public Command clamp(){
        return Commands.none();
        /**Move the arm 2pi raidans and clamp the coral */
    }
    public Command returnToStart(){
        return Commands.none();
        /**Move the arm to the start position */
    }
}
