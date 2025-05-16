package robot.arms;

public class NoArms implements ArmsIO {
    @Override
    public double currentAngle() {
        // No arms, so always return 0
        return 0.0;
    }
    @Override
    public void setVoltage(double voltage) {
        // No arms, so do nothing
    }
    @Override
    public double getVelocity() {
        // No arms, so do nothing
        return 0.0;
    }
    @Override
    public void resetEncoders() {
        // No arms, so do nothing
    }
    @Override
    public void setEncoder(double value) {
        // No arms, so do nothing
    }
}