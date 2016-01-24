import java.util.HashMap;
import java.util.Map;

public class PosesManager {
	
	public static Double matchThreshold = 0.70; 
	
	public void setLeapMotionListener(LeapMotionListener leapMotionListener) {
		this.newPoseKey = null;
		this.leapMotionListener = leapMotionListener;
		this.keyPoses = new HashMap<Integer, Pose>();
	}
	
	public void prepareNewPose(int key) {
		newPoseKey = key;
		leapMotionListener.setTrueCapturePose();
	}
	
	public void createNewPose(Pose pose) {
		keyPoses.put(newPoseKey, pose);
		newPoseKey = null;
	}
	
	public Integer poseDetection(Pose pose) {
		Integer keyPoseDetected = null;
		for (Map.Entry<Integer, Pose> entry : keyPoses.entrySet()) {
			Double matchValue = entry.getValue().match(pose);
			if (matchValue >= matchThreshold) {
				keyPoseDetected = entry.getKey();
				break;
			}
		}
		return keyPoseDetected;
	}
	
	private Integer newPoseKey; // Remember when asked by the interface
	private LeapMotionListener leapMotionListener;
	private Map<Integer, Pose> keyPoses;
}
