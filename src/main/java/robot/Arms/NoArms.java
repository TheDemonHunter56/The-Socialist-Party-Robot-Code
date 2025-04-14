package robot.Arms;

//*for when the arm is not useable*/
public class NoArms implements ArmsIO {

public double posiition() {
    return 0;
}

public double velocity() {
    return 0;
}

public void setVoltage() {}
}