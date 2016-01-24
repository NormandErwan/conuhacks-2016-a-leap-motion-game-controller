import java.util.HashMap;
import java.util.Map;

public class PoseManager {
	
	public static Double matchThreshold = 0.65; 
	
	public void setLeapMotionListener(LeapMotionListener leapMotionListener) {
		this.newPoseKey = null;
		this.leapMotionListener = leapMotionListener;
		this.keyPoses = new HashMap<Integer, Pose>();
	}
	
	public void prepareNewPose(int key) {
		System.out.println(key);
		newPoseKey = key;
		leapMotionListener.setTrueCapturePose();
	}
	
	public void createNewPose(Pose pose) {
		keyPoses.put(newPoseKey, pose);
		newPoseKey = null;
	}
	
	public int poseDetection(Pose pose) {
		Integer keyPoseDetected = null;
		for (Map.Entry<Integer, Pose> entry : keyPoses.entrySet()) {
			Double matchValue = entry.getValue().match(pose);
			System.out.println(matchValue);
			if (matchValue >= matchThreshold) {
				keyPoseDetected = entry.getKey();
			}
		}
		return keyPoseDetected;
	}
	
	private Integer newPoseKey; // remember when asked by the interface
	private LeapMotionListener leapMotionListener;
	private Map<Integer, Pose> keyPoses;
}
