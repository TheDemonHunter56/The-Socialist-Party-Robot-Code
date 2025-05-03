//Please note that this file is manually improted from the Arms branch not the current arms branch. The contribution for this file goes to the original author: xenonerias

package robot.arms;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.Units;

public class ArmsConstants extends SubsystemBase{

  public static final double THROUGHBORE_GEARING = 16.0 / 54.0;
  public static final Measure<Angle> POSITION_FACTOR = Units.Rotations.of(THROUGHBORE_GEARING);
    public static final Measure<Velocity<Angle>> VELOCITY_FACTOR = POSITION_FACTOR.per(Units.Minute);

public static final Measure<Angle> STARTING_ANGLE = Units.Degrees.of(10);

  public static final Measure<Angle> MIN_ANGLE = Units.Degrees.of(0);
  public static final Measure<Angle> MAX_ANGLE = Units.Degrees.of(180);

  

public static final double MAX_SPEED = 2;


public static final double STARTING_VOLTAGE = 0;
public static final double MAX_VOLTAGE = 12;
//*based off of NEO nominal voltage*/




}
