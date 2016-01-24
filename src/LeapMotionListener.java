import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class LeapMotionListener extends Listener {
	
	public LeapMotionListener(GameRobot gameRobot, PoseManager poseManager) {
		this.gameRobot = gameRobot;
		this.poseManager = poseManager;
	}
	
    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	// Capture new pose
    	if (capturePose == true) {
    		capturePose = false;
    		
    		// Get the new pose
    		Pose pose = new Pose(frame);
    		poseManager.createNewPose(pose);
    	}
    	else { // Or Match poses
    		Pose pose = new Pose(frame);
    		Integer poseKey = poseManager.poseDetection(pose);
    		if (poseKey != null) {
    			gameRobot.sendKey(poseKey);
    		}
    	}
    }
    
    public void setTrueCapturePose() {
    	capturePose = true;
    }
    
    private GameRobot gameRobot;
    private Boolean capturePose;
    private PoseManager poseManager;
}
