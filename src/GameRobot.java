import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameRobot {
	
	public GameRobot() throws AWTException, IOException {
		String magickaPathError = "Magicka.exe was not found ! Please copy the path to the Magicka.exe program in the file 'magicka_path.txt'";
		
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get("magicka_path.txt"));
		} catch (IOException e1) {
			throw new IOException(magickaPathError);
		}
		
		if (lines.isEmpty()) {
			throw new IOException(magickaPathError);
		} else {
			try {
				Runtime.getRuntime().exec(lines.get(0));
			} catch (IOException e) {
				throw new IOException(magickaPathError);
			}
		}
		
		this.robot = new Robot();
	}
	
	public void sendKey(int key) {
		robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
	}
	
	private Robot robot;
}
