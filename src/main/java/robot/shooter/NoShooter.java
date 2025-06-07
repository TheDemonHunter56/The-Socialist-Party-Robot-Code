package robot.shooter;

public class NoShooter implements ShooterIO {
    @Override
    public void setMoterVoltage(double voltage) {
        // No operation for NoShooter
    }
    @Override
    public void resetEncoders() {
        // No operation for NoShooter
    }
    @Override
    public double getVelocity() {
        // No operation for NoShooter
        return 0.0;
    }
}
