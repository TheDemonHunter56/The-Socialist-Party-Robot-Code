package robot.arms;

public class NoArms implements ArmsIO {

    @Override
    public void currentAngle() {;
    }

    @Override
    public void setClampPower(double power) {
    }

    public void stopClamp() {
        // No clamp to stop
    }

    public void moveTargetDegrees(double targetDegrees) {
        // No target degrees to move to
    }
}