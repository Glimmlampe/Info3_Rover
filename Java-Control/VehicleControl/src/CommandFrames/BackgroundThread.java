package CommandFrames;

import SerialSignal.SerialClassData;
import SerialSignal.USBCommunication;

/**Diese Threadklasse führt die eiegentliche Steuerung aus (und wird von der jeweiligen Command GUI via Start-Button aufgerunfen, bzw ausgeführt
 * 
 * 
 * @author Markus
 *
 */
public class BackgroundThread {

	
	private static BackgroundThread single = null;
	public Thread mBT;
	String name = "Worker";
	
	
	public static BackgroundThread getInstance(){
		if(single==null)
			single = new BackgroundThread();
		return single;	
	}
	
	private BackgroundThread(){
		
		this.mBT= new Thread(new USBCommunication() ,name);
	
	}
	
	
	public void killThread(){
		// setze den Singelton BackgroundThread zurück!
		BackgroundThread.single=null;
		
		//unterbricht(setze die pasue und kill flags in der Steuerung den BAckgroundThread und kill ihn ^^
		SerialClassData.setForceEnd(true);
		
		
		try {
			this.mBT.join();
		} catch (InterruptedException e) {
			
		}
		
	}
	
	public synchronized void forcePause(){
		//setzt das Pauseflag 
		SerialClassData.setIsPaused(true);
	}
	
	public synchronized void forceEndPause(){
		SerialClassData.setIsPaused(false);
	}
	
	
	
	
	
}
