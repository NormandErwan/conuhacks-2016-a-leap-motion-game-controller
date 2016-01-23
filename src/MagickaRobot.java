import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class MagickaRobot {
	
	public MagickaRobot() throws AWTException, IOException {
		Runtime.getRuntime().exec("C:/Program Files (x86)/Steam/steamapps/common/Magicka/Magicka.exe");
		
		this.robot = new Robot();
	}
	
	public void sendKey(int keyEvent) {
		System.out.println("Send " + keyEvent);
		robot.keyPress(keyEvent);
        robot.delay(100);
        robot.keyRelease(keyEvent);
	}
	
	private Robot robot;
}
