package robot.arms;

public interface ArmsIO {
    //*Returns the angle of the arm in degrees */
    public double currentAngle();

    /**
     * starts clamping the coral*/
    public void setClampPower(double power);
     /**
     * Sets the voltage of the motors
    */
    public void setVoltage(double Voltage);
}
