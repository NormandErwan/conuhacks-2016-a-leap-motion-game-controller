import java.awt.AWTException;
import java.io.IOException;
import com.leapmotion.leap.*;

public class Main {

	public static void main(String[] args) throws AWTException, IOException {
		PosesManager posesManager = new PosesManager();
		GameRobot gameRobot = new GameRobot();
		new GUI(posesManager, gameRobot);
		LeapMotionListener leapMotionListener = new LeapMotionListener(gameRobot, posesManager);
		
		posesManager.setLeapMotionListener(leapMotionListener);

		// Create the Leap Motion controller, which can run in background
		Controller controller = new Controller();
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		
		// Connect to the Leap Motion
		controller.addListener(leapMotionListener);

		// Keep this process running until Enter is pressed
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Disconnect from the Leap Motion
		controller.removeListener(leapMotionListener);
	}
}
