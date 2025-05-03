package robot.arms;

public class NoArms implements ArmsIO {

    @Override
    public double currentAngle() {;
        // No arms, so always return 0
        return 0.0;
    }

    public void stopClamp() {
        // No clamp to stop
    }

    public void moveTargetDegrees(double targetDegrees) {
        // No target degrees to move to
    }

    @Override
    public void setVoltage(double Voltage) {
    }

    @Override
    public void getVelocity() {
    }
}