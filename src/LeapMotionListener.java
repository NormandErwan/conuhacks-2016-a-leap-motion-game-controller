import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class LeapMotionListener extends Listener {
	
	public static Integer poseDetectedDelay = 350; // milliseconds
	
	public LeapMotionListener(GameRobot gameRobot, PosesManager poseManager) {
		this.gameRobot = gameRobot;
		this.capturePose = false;
		this.poseManager = poseManager;
		this.lastFramePoseKey = null;
		this.poseDetectionStartTime = 0;
	}
	
    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	// Capture new pose
    	if (capturePose == true) {
    		capturePose = false;
    		Pose pose = new Pose(frame);
    		poseManager.createNewPose(pose);
    	}
    	else { // Or detect a pose
    		Pose pose = new Pose(frame);
    		Integer poseKey = poseManager.poseDetection(pose);
    		
    		// Send the command if the same pose is detected during the poseDetectedDelay
    		if (poseKey != null) {
    			if (lastFramePoseKey == poseKey) {
    				long delay = System.currentTimeMillis() - poseDetectionStartTime;
    				if (delay >= poseDetectedDelay) {
        				gameRobot.sendKey(poseKey);
        				poseDetectionStartTime = System.currentTimeMillis();
    				}
    			}
    			else {
    				poseDetectionStartTime = System.currentTimeMillis();
    			}
    		}
    		lastFramePoseKey = poseKey;
    	}
    }
    
    public void setTrueCapturePose() {
    	capturePose = true;
    }
    
    private GameRobot gameRobot;
    private Boolean capturePose; // Flag for next frame (onFrame method)
    private PosesManager poseManager;
    
    private Integer lastFramePoseKey;
    private long poseDetectionStartTime;
}
