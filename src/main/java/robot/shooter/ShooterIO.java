package robot.shooter;

public interface ShooterIO {
    public void setMoterVoltage(double voltage); // sets the voltage for the shooter motors 
    public void resetEncoders(); // resets the shooter encoders to 0
    public double getVelocity(); // gets the velocity of the shooter motors in RPM
}
