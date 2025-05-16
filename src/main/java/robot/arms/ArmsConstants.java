//Please note that this file is manually improted from the Arms branch not the current arms branch. The contribution for this file goes to the original author: xenonerias

package robot.arms;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.trajectory.ExponentialProfile.Constraints;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Mass;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Mult;
import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Constants;
import edu.wpi.first.units.Units;

@SuppressWarnings("unused")
public class ArmsConstants extends SubsystemBase{

  public static final double THROUGHBORE_GEARING = 16.0 / 54.0;
  public static final double POSITION_FACTOR = Units.Radian.of(THROUGHBORE_GEARING * 2 * Math.PI).in(Units.Radian); //what is this? (Made some changes used for the encoder. See RealArms.java)
  public static final double VELOCITY_FACTOR = Units.RadiansPerSecond.of((THROUGHBORE_GEARING * 2 * Math.PI) /60).in(Units.RadiansPerSecond);

    public static final Measure<Angle> STARTING_ANGLE = Units.Degrees.of(0);

  public static final Measure<Angle> MIN_ANGLE = STARTING_ANGLE;//redundant?
  public static final Measure<Angle> MAX_ANGLE = Units.Degrees.of(180);

public static final double DEFAULT_SPEED = 1;
public static final double STILL_SPEED = 0;
public static final double MAX_SPEED = 2;
public static final double DEFAULT_TORQUE = 0;
public static final double MAX_TORQUE = 1;

public static final double STARTING_VOLTAGE = 0;
public static final double MAX_VOLTAGE = 12;

//values for the PID controller are randomly chosen. Need to be tuned
public static final double kP = 0.1;
public static final double kI = 0.1;
public static final double kD = 0.0;
public static final double Tolerance = 0.05;

//*based off of NEO nominal voltage*/



}
