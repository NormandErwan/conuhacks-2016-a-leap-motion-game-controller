import java.util.List;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Matrix;
import com.leapmotion.leap.Vector;

public class Pose {

	public Pose(HandList hands) {
		// Get finger relative position and directions
		Hand rightHand = hands.rightmost(); // TODO : need to choose which one

	    Vector handXBasis =  rightHand.palmNormal().cross(rightHand.direction()).normalized();
	    Vector handYBasis = rightHand.palmNormal().opposite();
	    Vector handZBasis = rightHand.direction().opposite();
	    Vector handOrigin =  rightHand.palmPosition();
	    Matrix handTransform = new Matrix(handXBasis, handYBasis, handZBasis, handOrigin);
	    handTransform = handTransform.rigidInverse();

	    for (Finger finger : rightHand.fingers()) {
	        Vector transformedPosition = handTransform.transformPoint(finger.tipPosition());
	        Vector transformedDirection = handTransform.transformDirection(finger.direction());
	        
	        fingerVectors.add(transformedPosition);
	        fingerVectors.add(transformedDirection);
	    }
	}
	
	public float match(Pose pose) {
		float minMatch = -1;
		
		for (int i = 0, s = fingerVectors.size(); i < s; i++) {
			float matchi = fingerVectors.get(i).normalized().dot(pose.getFingerVectors().get(i).normalized());
			minMatch = Math.min(matchi, minMatch);
		}
		
		return minMatch;
	}
	
	public List<Vector> getFingerVectors() {
		return fingerVectors;
	}
	
	private List<Vector> fingerVectors;
}
