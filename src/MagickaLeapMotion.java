import java.awt.AWTException;
import java.io.IOException;
import com.leapmotion.leap.*;

public class MagickaLeapMotion {
	
	public static void main(String[] args) throws AWTException {
        // Create a sample listener and controller
        Listener listener = new MagickaLeapMotionListener(new MagickaRobot());
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}
