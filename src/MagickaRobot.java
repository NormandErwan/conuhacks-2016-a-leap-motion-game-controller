import java.awt.AWTException;
import java.awt.Robot;

public class MagickaRobot {
	
	public MagickaRobot() throws AWTException {
		this.robot = new Robot();
	}
	
	public void sendKey(int keyEvent) {
		robot.keyPress(keyEvent);
        robot.delay(100);
        robot.keyRelease(keyEvent);
	}
	
	private Robot robot;
}
