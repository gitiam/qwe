import java.io.IOException;
import com.leapmotion.leap.*;

public class Sample {
	public static void main(String[] args){
		
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
        System.out.println("Frame available");
        Frame frame = controller.frame();
        //PRINT VALUES OF FRAME
        System.out.println(" Frame id: " + frame.id() 
        		+ ", timestamp: " + frame.timestamp() 
        		+ ", hands: " + frame.hands().count()
        		+ ", fingers: " + frame.fingers().count()
        		+ ", tools: " + frame.tools().count()
        		+ ", gestures: " + frame.gestures().count());
    }
}