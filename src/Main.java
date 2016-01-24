import java.awt.AWTException;
import java.io.IOException;
import com.leapmotion.leap.*;
import com.sun.glass.events.KeyEvent;

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
		System.out.println("Prepare Arcane (S)");
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		        		poseManager.prepareNewPose(KeyEvent.VK_S);
		        		System.out.println("Arcane (S) acquired");
		        		System.out.println("Prepare Rock (D)");
		        		
		        		new java.util.Timer().schedule( 
		        		        new java.util.TimerTask() {
		        		            @Override
		        		            public void run() {
		        		        		poseManager.prepareNewPose(KeyEvent.VK_D);
		        		        		System.out.println("Rock (D) acquired");
		        		        		System.out.println("Press Enter to quit...");
		        		            }
		        		        }, 
		        		        2000 
		        		);
		            }
		        }, 
		        2000 
		);

		
		
		try {
			int inChar = System.in.read();
			System.out.println(inChar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("quit");

		// Remove the sample listener when done
		controller.removeListener(leapMotionListener);
	}
}
