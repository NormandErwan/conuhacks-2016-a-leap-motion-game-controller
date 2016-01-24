import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class GameRobot {
	
	public GameRobot() throws AWTException, IOException {
		this.robot = new Robot();
	}
	
	public void launch(String pathGame) throws IOException {
		Runtime.getRuntime().exec(pathGame);
	}
	
	public void sendKey(int key) {
		robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
	}
	
	private Robot robot;
}
