package robot.conveyor;

import java.util.List;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;

public class Conveyor extends SubsystemBase {
    private final CANSparkMax Leader = new CANSparkMax(Ports.Conveyor.CONVEYOR_LEADER, MotorType.kBrushless);
    private final CANSparkMax Follower = new CANSparkMax(Ports.Conveyor.CONVEYOR_FOLLOWER, MotorType.kBrushless);
    
    public Conveyor() {
        for (CANSparkMax spark : List.of(Leader, Follower)) {
            spark.restoreFactoryDefaults();q
        }
        Follower.follow(Leader);
        Follower.setInverted(true);
    }

    private void conveyor (double speed) {
        Leader.set(speed);
    }

    public Command conveyor(DoubleSupplier vSpeed) {
    return run(() -> conveyor(vSpeed.getAsDouble()));
  }

}
