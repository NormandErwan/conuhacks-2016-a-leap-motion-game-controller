import java.awt.AWTException;
import java.io.IOException;
import com.leapmotion.leap.*;

public class Main {

	public static void main(String[] args) throws AWTException, IOException {
		PoseManager poseManager = new PoseManager();
		
		//Launch the GUI
		new Interface(poseManager);
		
		// Launch the robot and the game
		GameRobot gameRobot = new GameRobot();

		// Create a sample listener and controller
		LeapMotionListener leapMotionListener = new LeapMotionListener(gameRobot, poseManager);
		Controller controller = new Controller();
		
		poseManager.setLeapMotionListener(leapMotionListener);

		// Allow to get frame when the application is in background
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);

		// Have the sample listener receive events from the controller
		controller.addListener(leapMotionListener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(leapMotionListener);
	}
}
