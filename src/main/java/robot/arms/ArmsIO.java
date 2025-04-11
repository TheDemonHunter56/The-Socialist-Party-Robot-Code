package robot.arms;

public interface ArmsIO {
    //*Returns the angle of the arm in degrees */
    public double currentAngle();
    /**
     * Stops the arms from moving 
    */
    public void stopArms();
    /** 
     * resets arm to starting poisiton */
    public void reset();

    /**
     * starts clamping the coral*/
    public void setClampPower(double power);
     /**
     * Moves the arm into position in degrees
    */
    public void moveTargetDegrees(double DegreesFromTarget);
    /**
     * stops clamping the coral
    */
    public void stopClamp();
}
