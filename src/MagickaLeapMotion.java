import java.awt.AWTException;
import java.io.IOException;
import com.leapmotion.leap.*;

public class MagickaLeapMotion {

	public static void main(String[] args) throws AWTException, IOException {
		PoseManager poseManager = new PoseManager();
		
		//Launch the GUI
		new Interface(poseManager);
		
		// Launch the robot and the game
		MagickaRobot magickaRobot = new MagickaRobot();

		// Create a sample listener and controller
		MagickaLeapMotionListener magickaLeapMotionListener = new MagickaLeapMotionListener(magickaRobot, poseManager);
		Controller controller = new Controller();
		
		poseManager.setMagickaLeapMotionListener(magickaLeapMotionListener);

		// Allow to get frame when the application is in background
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);

		// Have the sample listener receive events from the controller
		controller.addListener(magickaLeapMotionListener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(magickaLeapMotionListener);
	}
}
