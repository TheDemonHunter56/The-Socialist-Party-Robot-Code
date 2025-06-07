package robot.arms;

public interface ArmsIO {
    //*Returns the angle of the arm in degrees */
    public double currentAngle();
    /**
     * starts clamping the coral*/
    public void setVoltage(double Voltage);
    /**
     * Get volicity of the motors in RPM
     */
    public double getVelocity();

    //Resets the encoder to 0
    public void resetEncoders();
    /*
     * Sets the encoder to a specific value
     */
    public void setEncoder(double value);
}
