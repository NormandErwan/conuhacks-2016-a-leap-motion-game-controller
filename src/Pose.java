import java.util.ArrayList;
import java.util.List;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Matrix;
import com.leapmotion.leap.Vector;

public class Pose {

	public Pose(Frame frame) {
		this.fingerVectors = new ArrayList<Vector>();
		
		// Get finger relative position and directions
		Hand rightHand = frame.hands().rightmost(); // TODO : need to choose which one

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
	
	public Double match(Pose pose) {
		Double minMatch = 1.0;
		
		// Normalize each vector, do the dot product, and keep the lowest result
		for (int i = 0, s = fingerVectors.size(); i < s; i++) {
			Double matchi = (double) fingerVectors.get(i).normalized().dot(pose.getFingerVectors().get(i).normalized());
			minMatch = Math.min(matchi, minMatch);
		}
		
		return minMatch;
	}
	
	public List<Vector> getFingerVectors() {
		return fingerVectors;
	}
	
	private List<Vector> fingerVectors;
}
