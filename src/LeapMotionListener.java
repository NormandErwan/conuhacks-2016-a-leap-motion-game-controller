import java.awt.event.KeyEvent;

import com.leapmotion.leap.*;

public class LeapMotionListener extends Listener {
	
	public LeapMotionListener(GameRobot gameRobot, PoseManager poseManager) {
		this.gameRobot = gameRobot;
		this.poseManager = poseManager;
	}
	
    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	// Capture new pose
    	if (capturePose) {
    		capturePose = false;
    		
    		// Get the new pose
    		Pose pose = new Pose(frame.hands());
    		poseManager.createFuturePose(pose);
    	}
    	else { // Or Match poses

    	}
    }
    
    public void setTrueCapturePose() {
    	capturePose = true;
    }
    
    private GameRobot gameRobot;
    private boolean capturePose;
    private PoseManager poseManager;
}
