package robot.drive;

import static edu.wpi.first.units.Units.Seconds;
import static robot.drive.DriveConstants.POSITION_FACTOR;
import static robot.drive.DriveConstants.VELOCITY_FACTOR;

import java.util.List;
import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Annotations.Log;
import robot.Constants;
import robot.Ports;
import robot.Robot;
import robot.drive.DriveConstants.FF;
import robot.drive.DriveConstants.PID;

public class Drive extends SubsystemBase {
  // hardware
  private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
  private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
  private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
  private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);
  // Encoders
  private final RelativeEncoder leftEncoder = leftLeader.getEncoder();
  private final RelativeEncoder rightEncoder = rightLeader.getEncoder();
  // Gryoscope
  private final AnalogGyro gyro = new AnalogGyro(Ports.Drive.GYRO_CHANNEL);
  // Odemetry
  private final DifferentialDriveOdometry odometry =
      new DifferentialDriveOdometry(new Rotation2d(), 0, 0, new Pose2d());
  // Control Theory
  private final PIDController leftPidController = new PIDController(PID.kP, PID.kI, PID.kD);
  private final PIDController rightPidController = new PIDController(PID.kP, PID.kI, PID.kD);
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(FF.kS, FF.kV);
  //Simulation
  private final DifferentialDrivetrainSim driveSim;

  @Log.NT 
  private final Field2d field2d = new Field2d(); 

  public Drive() {
    for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
      spark.restoreFactoryDefaults();
      spark.setIdleMode(IdleMode.kBrake);
     }

    rightFollower.follow(rightLeader);
    leftFollower.follow(leftLeader);
    leftLeader.setInverted(true);

    //Encoder specifics 
    rightEncoder.setPositionConversionFactor(POSITION_FACTOR);
    leftEncoder.setVelocityConversionFactor(VELOCITY_FACTOR);
    rightEncoder.setVelocityConversionFactor(VELOCITY_FACTOR);
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    //gyro
    gyro.reset();
    SmartDashboard.putData("Field", field2d); //Foced adding feild2d
    
    driveSim =
        new DifferentialDrivetrainSim(
            DCMotor.getMiniCIM(2),
            DriveConstants.GEARING,
            DriveConstants.MOI,
            DriveConstants.DRIVE_MASS,
            DriveConstants.WHEEL_RADIUS,
            DriveConstants.TRACK_WIDTH,
            DriveConstants.STD_DEVS);

  }

  // Applies voltage to the motors, uses percentages with range -1 < X < 1
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
    driveSim.setInputs(leftVoltage, rightVoltage);
  }



  private void updateOdometry(Rotation2d rotation) {
    odometry.update(rotation, leftEncoder.getPosition(), rightEncoder.getPosition());
  }

  private void resetOdemetry() {
    odometry.resetPosition(new Rotation2d(), 0, 0, new Pose2d());
  }

  @Override
  public void periodic() {
    updateOdometry(Robot.isReal() ? gyro.getRotation2d() : driveSim.getHeading());
    rightEncoder.getPosition();
    leftEncoder.getPosition();
    field2d.setRobotPose(pose());
  }
  @Override
  public void simulationPeriodic() {
    driveSim.update(Constants.PERIOD.in(Seconds));
    leftEncoder.setPosition(driveSim.getLeftPositionMeters());
    rightEncoder.setPosition(driveSim.getRightPositionMeters());
  }

  public Pose2d pose() {
    return odometry.getPoseMeters();
  }

  // actual method that will return the command
  public Command drive(DoubleSupplier vLeft, DoubleSupplier vRight) {
    return run(() -> {
      drive(vLeft.getAsDouble(), vRight.getAsDouble());
    });
  }
}
