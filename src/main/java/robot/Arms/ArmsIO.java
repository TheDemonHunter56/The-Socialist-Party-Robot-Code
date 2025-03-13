package robot.Arms;

public interface ArmsIO {
    /**
     * @return the position of the arm in degrees from starting angle. 
     */
    public double position();
    /**
     * Changes the limit of the motor 
     * (Ie. changes how fast the motor can possibly move)
     */
    public void setVoltage();
     /**
     * Moves the arm into position in degrees
    */
    public void movearm();
    /**
     * Moves the arm into position in degrees
    */
    public void setArms();
}
