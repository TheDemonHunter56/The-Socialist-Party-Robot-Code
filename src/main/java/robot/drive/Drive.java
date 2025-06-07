package robot.drive;

import static robot.drive.DriveConstants.POSITION_FACTOR;
import static robot.drive.DriveConstants.VELOCITY_FACTOR;

import java.util.List;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;
import robot.drive.DriveConstants.FF;
import robot.drive.DriveConstants.PID;


public class Drive extends SubsystemBase{
    //hardware
    private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);
    //Encoders
    private final RelativeEncoder leftEncoder = leftLeader.getEncoder();
    private final RelativeEncoder rightEncoder = rightLeader.getEncoder();
    //Gryoscope
    private final AnalogGyro gyro =  new AnalogGyro(Ports.Drive.GYRO_CHANNEL);
    //Odemetry 
    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0, new Pose2d());
    //Control Theory
    private final PIDController leftPidController = new PIDController(PID.kP, PID.kI, PID.kD);
    private final PIDController rightPidController = new PIDController(PID.kP, PID.kI, PID.kD);
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(FF.kS, FF.kV);

    public Drive() {
        for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake); }
        rightFollower.follow(rightLeader);
        leftFollower.follow(leftLeader);
        leftLeader.setInverted(true); 

        rightEncoder.setPositionConversionFactor(POSITION_FACTOR);
        leftEncoder.setVelocityConversionFactor(VELOCITY_FACTOR);
        rightEncoder.setVelocityConversionFactor(VELOCITY_FACTOR);
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);

        gyro.reset();
  }
    //Applies voltage to the motors, uses percentages with range -1 < X < 1
    private void drive(double leftSpeed, double rightSpeed) {
        double actualLeftSpeed = leftSpeed * DriveConstants.MAX_SPEED;
        double actualRightSpeed = rightSpeed * DriveConstants.MAX_SPEED;

        final double leftFeedSpeed = feedforward.calculate(actualLeftSpeed);
        final double rightFeedSpeed = feedforward.calculate(actualRightSpeed);

        final double leftPID = leftPidController.calculate(leftEncoder.getVelocity(), actualLeftSpeed);
        final double rightPID = rightPidController.calculate(rightEncoder.getVelocity(), actualRightSpeed);
        
        double leftVoltage = leftPID + leftFeedSpeed;
        double rightVoltage = rightPID + rightFeedSpeed;

        leftLeader.setVoltage(leftVoltage);
        rightLeader.setVoltage(rightVoltage);

    }
    private void updateOdometry(Rotation2d rotation) {
        odometry.update(rotation, leftEncoder.getPosition(), rightEncoder.getPosition());
    }

    private void resetOdemetry(){
        odometry.resetPosition(new Rotation2d(), 0, 0, new Pose2d());
    }

    @Override
     public void periodic() {
        updateOdometry(gyro.getRotation2d());
    }
    public Pose2d pose() {
        return odometry.getPoseMeters();
    }
    //actual method that will return the command
    public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight){
        return run(() -> drive(vLeft.getAsDouble(), vRight.getAsDouble()));
    }

}