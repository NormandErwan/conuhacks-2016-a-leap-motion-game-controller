import java.awt.event.KeyEvent;

public class PoseManager {
	
	public void setMagickaLeapMotionListener(LeapMotionListener magickaLeapMotionListener) {
		this.magickaLeapMotionListener = magickaLeapMotionListener;
	}
	
	public void newPoseFromInterface(KeyEvent keyEvent) {
		futureKeyPose = keyEvent;
		
	}
	
	public void createFuturePose(Pose pose) {
		// match futureKeyPose with pose, and push to the map, then save/serialize
	}
	
	private KeyEvent futureKeyPose; // remember when asked by the interface
	private LeapMotionListener magickaLeapMotionListener;
}
