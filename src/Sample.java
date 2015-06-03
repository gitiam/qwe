import java.io.IOException;
import com.leapmotion.leap.*;

class SampleListener extends Listener {

    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    public void onDisconnect(Controller controller){
    	System.out.println("Disconnected");
    }
    
    public void onExit(Controller controller){
    	System.out.println("Exit");
    }
    
    public void onFrame(Controller controller) {
    	//IF FRAME IS AVAILABLE PRINT AVAILABLE 
        System.out.println("Frame available");
        //FRAME DECLARATIION
        Frame frame = controller.frame();        
        //PRINT VALUES OF FRAME
        System.out.println(" Frame id: " + frame.id() 
        		+ ", timestamp: " + frame.timestamp() 
        		+ ", hands: " + frame.hands().count()
        		+ ", fingers: " + frame.fingers().count()
        		+ ", tools: " + frame.tools().count()
        		+ ", gestures: " + frame.gestures().count());
        //HANDS DATA
        for(Hand hand : frame.hands()){
        	//DETERMINE IF THE HAND IS LEFT OR RIGHT
        	String handType = hand.isLeft() ? "Left Hand" : "Right Hand";
        	//PRINT HANDTYPE, ID AND PALM POSITION
        	System.out.println("  " + handType + ", id: " + hand.id()
                    + ", palm position: " + hand.palmPosition());
        	//HAND NORMAL VECTOR AND DIRECTION
        	Vector normal = hand.palmNormal();
        	Vector direction = hand.direction();
        	System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
                     + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                     + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
        	//FINGERS
        	for (Finger finger : hand.fingers()) {
        		System.out.println("    " + finger.type() + ", id: " + finger.id()
                         + ", length: " + finger.length()
                         + "mm, width: " + finger.width() + "mm");

        		//BONES
        		for(Bone.Type boneType : Bone.Type.values()) {
        			Bone bone = finger.bone(boneType);
        			System.out.println("      " + bone.type()
                             + " bone, start: " + bone.prevJoint()
                             + ", end: " + bone.nextJoint()
                             + ", direction: " + bone.direction());
        		}//END 2ND FOR
        	}//END FIRST FOR         	
        }//END FOR OF HANDS 
    }//END OF ONFRAMES
}//END LISTENER

public class Sample {
	public static void main(String[] args){
		//CONTROLER AND LISTENER DECLARATION
		SampleListener listener = new SampleListener();
		Controller controller = new Controller();
		//ADD LISTENER TO THE CONTROLLER
		//EVERY TIME A CONTROLLER EVENT OCCURS A FUNCTION IN LISTENER WILL BE CALLED  
		controller.addListener(listener);		
		//KEEP PROCESS RUNNING UNTIL ENTER
		System.out.println("Press enter to quit...");
		try{
			System.in.read();
		}//TRY END 
		catch(IOException e){
			e.printStackTrace();
		}//CATCH END
		//REMOVE LISTENER WHEN DONE
		controller.removeListener(listener);		
	}//MAIN END
}//SAMPLE END