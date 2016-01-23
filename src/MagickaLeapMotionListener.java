import java.awt.event.KeyEvent;

import com.leapmotion.leap.*;

public class MagickaLeapMotionListener extends Listener {
	
	public MagickaLeapMotionListener(MagickaRobot magickaRobot) {
		this.magickaRobot = magickaRobot;
	}
	
	public void onConnect(Controller controller) {
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onFrame(Controller controller) {
    	Frame frame = controller.frame();
    	
    	System.out.println(frame.gestures().count());
    	
    	for (Gesture gesture : frame.gestures()) {
    		switch (gesture.type()) {
            	case TYPE_SWIPE:
            		magickaRobot.sendKey(KeyEvent.VK_Q);
            		break;
            	default:
            		break;
    		}
    	}
    }
    
    private MagickaRobot magickaRobot;
}
