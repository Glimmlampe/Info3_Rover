package CommandFrames;

/**Diese Klasse erzeugt mir einen Thread in dem der zweite GUI-teil laufen soll
 * (USB oder BT frame) ebenso beinhaltet diese klasse methoden um den gui thread zu killen
 * 
 * @author Markus
 *
 */

public class myThread{
	
	
	private static myThread single = null;
	public Thread mT;
	String name = "USB";
	
	
	public static myThread getInstance(){
		if(single==null)
			single = new myThread();
		return single;	
	}
	
	private myThread(){
		
		this.mT= new Thread(USBFrame.getInstance() ,name);
		
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void killThread(){
		// setze den Singelton myThread zurück!
		myThread.single = null;
		
		//unterbricht den gui Thread
		this.mT.interrupt();
		this.mT.stop();
		try {
			this.mT.join();
		} catch (InterruptedException e) {

		}

		
	}
	

}
