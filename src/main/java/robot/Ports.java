package robot;

// Note: the Ports aren't final. Subject to change based on the hardware.

public final class Ports {
  public static final class IO {
    public static final int OPERATOR = 0;
    public static final int DRIVER = 1;
    public static final int Upbutton = 12;
    public static final int Downbutton = 13;
  }

  public static final class Drive {
    public static final int RIGHT_LEADER = 2;
    public static final int RIGHT_FOLLOWER = 3;
    public static final int LEFT_LEADER = 4;
    public static final int LEFT_FOLLOWER = 5;
    
  }

  public static final class Intake {
    public static final int ROLL = 6;
  }

  public static final class Arms {
    public static final int Leftarm = 7;
    public static final int Rightarm = 8;
  
  }

  public static final class Shooter {
    public static final int rightShooter = 9;
    public static final int leftShooter = 10;
    public static int RightShooter;
  }

  public static final class Conveyor {
    public static final int CONVEYOR_LEADER = 9;
    public static final int CONVEYOR_FOLLOWER = 10;
  }

}
