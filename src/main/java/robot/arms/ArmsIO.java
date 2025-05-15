package robot.arms;

public interface ArmsIO {
    //*Returns the angle of the arm in degrees */
    public double currentAngle();
    /**
     * starts clamping the coral*/
    public void setVoltage(double Voltage);
    /**
     * Get volicity of the motors
     */
    public void getVelocity();
    public void resetEncoders();
}
