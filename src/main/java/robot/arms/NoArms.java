package robot.arms;

public class NoArms implements ArmsIO {

    public void ArmStart() {
        // No arms to start
    }

    public void reset() {
        // No arms to reset
    }

    public double currentAngle() {
        return 0; // No angle to return
    }

    public void stopArms() {
        // No arms to stop
    }

    public void setClampPower(double power) {
        // No clamp to set power for
    }

    public void stopClamp() {
        // No clamp to stop
    }

    public void moveTargetDegrees(double targetDegrees) {
        // No target degrees to move to
    }
}