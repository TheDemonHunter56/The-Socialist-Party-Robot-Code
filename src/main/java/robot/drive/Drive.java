package robot.drive;

import java.util.List;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;
import math;

public class Drive extends SubsystemBase{
    private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);

    //reseting motors to factory standards + canceling out joystick noise
    public Drive() {
        for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake);
            spark.setDeadband(0.1); //sets a threshold below which input values are treated as 0 
        }
        rightFollower.follow(rightLeader);
        leftFollower.follow(leftLeader);
        leftLeader.setInverted(true); 
  }
    //Applies voltage to the motors, uses percentages with range -1 < X < 1
    private void arcadeDrive(XboxController driverController) {
        double forwardPower = driverController.getY(Hand.kLeft);
        double turnPower = driverController.getX(Hand.kRight);
        
        if (driverController.getBumper(Hand.kLeft)) turnPower *= 0.5;

        
    }

    //stops the robot from moving
    public void stop() {
        leftLeader.set(0);
        rightLeader.set(0);
    }

    //returns (leftSpeed, rightSpeed)
    public double getSpeed() {
        return (leftLeader.get(), rightLeader.get())
    }

    //actual method that will return the command
    public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight){
        return run(() -> drive(vLeft.getAsDouble(), vRight.getAsDouble()));
    }

}
