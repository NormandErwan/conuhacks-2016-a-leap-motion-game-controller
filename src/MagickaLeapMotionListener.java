import java.awt.event.KeyEvent;

import com.leapmotion.leap.*;

public class MagickaLeapMotionListener extends Listener {
	
	public MagickaLeapMotionListener(MagickaRobot magickaRobot, PoseManager poseManager) {
		this.magickaRobot = magickaRobot;
		this.poseManager = poseManager;
	}
	
	public void onConnect(Controller controller) {
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	// Capture new pose
    	if (capturePose) {
    		capturePose = false;
    		
    		// Get the new pose
    		Pose pose = new Pose();
    		
    		poseManager.createFuturePose(pose);
    	}
    	else { // Or Match poses
	    	for (Gesture gesture : frame.gestures()) { // TODO : remove
	    		switch (gesture.type()) {
	            	case TYPE_SWIPE:
	            		magickaRobot.sendKey(KeyEvent.VK_Q);
	            		break;
	            	default:
	            		break;
	    		}
	    	}
    	}
    }
    
    public void setTrueCapturePose() {
    	capturePose = true;
    }
    
    private MagickaRobot magickaRobot;
    private boolean capturePose;
    private PoseManager poseManager;
}
