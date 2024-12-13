package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmController {
    private Servo[] armJoints = new Servo[3];
    private final double[] lengths = new double[]{7.5, 7.5}; // Lengths of bicep and forearm in arbitrary units (e.g., cm)
    private final double radianRange = Math.PI; // Joint angle range from -π to π (for shoulder and elbow)
    private final double maxDistance = Math.hypot(lengths[0], lengths[1]); // Maximum reachable distance

    public ArmController(HardwareMap hw) {
        // Initialize the arm joints (assuming they are named "arm0", "arm1", "arm2" in the hardware map)
        for (int i = 0; i < armJoints.length; i++) {
            armJoints[i] = hw.get(Servo.class, "arm" + i);
        }
    }

    public void inverseKinematics(int x, int y, Telemetry t) {
        // Calculate the distance to the target point (x, y) from the shoulder
        double distance = Math.hypot(x, y);

        // Check if the point is within reach, and scale accordingly
        if (distance > maxDistance) {
            double scale = maxDistance / distance;
            x *= scale;
            y *= scale;
            distance = maxDistance;
        }

        // Calculate the shoulder angle (θ1) using the law of cosines
        double shoulderAngle = Math.acos((distance * distance + lengths[0] * lengths[0] - lengths[1] * lengths[1]) /
                (2 * lengths[0] * distance));

        // Calculate the elbow angle (θ2) using the law of cosines
        double foreAngle = Math.acos((lengths[1] * lengths[1] + lengths[0] * lengths[0] - distance * distance) /
                (2 * lengths[0] * lengths[1]));

        // Determine the sign of the shoulder angle depending on the y-coordinate (up or down)
        if (y < 0) {
            shoulderAngle = -shoulderAngle;
        }

        // Update telemetry for debugging
        t.addData("Shoulder Angle (deg)", Math.toDegrees(shoulderAngle));
        t.addData("Elbow Angle (deg)", Math.toDegrees(foreAngle));

        // Map shoulder and elbow angles to servo positions (0 to 1)
        armJoints[0].setPosition(mapAngleToServoPosition(shoulderAngle));
        armJoints[1].setPosition(mapAngleToServoPosition(foreAngle));
    }

    public double mapAngleToServoPosition(double angle) {
        // Normalize the angle (assumes -π to π, map to 0 to 1)
        double normalizedAngle = (angle + radianRange) / (2 * radianRange);
        // Clip to ensure that values stay within 0 to 1 range
        return Range.clip(normalizedAngle, 0, 1);
    }

    public void setPosition(int index, double position){
        armJoints[index].setPosition(position);
    }

    public void setServos(double[] positions){
        for(int i = 0; i < positions.length; i++){
            armJoints[i].setPosition(positions[i]);
        }
    }

    public void flipWrist() {
        // Toggle wrist position between 0 and 1
        armJoints[2].setPosition(armJoints[2].getPosition() == 0 ? 1 : 0);
    }
}
