package SerialSignal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import CommandFrames.USBFrame;
import GUI.MainGUI;

@SuppressWarnings("unused")
public class SerialClassData {
	public static BufferedReader input;
	public static OutputStream output;
	private List<String> Commands = new ArrayList<String>();
	private List<Integer> duration = new ArrayList<Integer>();
	private List<String> pV = new ArrayList<String>();
	
			// zeug von Markus Friedrich	
	//flags für threads (Static)
	public static Boolean isPaused = false;
	public static Boolean forceEnd = false;

	
	private static synchronized boolean checkIsPaused(){
		return isPaused;
	}
	private static synchronized boolean checkForceEnd(){
		return forceEnd;
	}
	public static synchronized void setIsPaused( boolean s){
		isPaused = s;
	}
	public static synchronized void setForceEnd(boolean s){
		forceEnd = s;
	}
	
	public static synchronized void writeData(String data) {
		//System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
		} catch (Exception e) {
			//System.out.println("could not write to port");
			MainGUI.getInstance().x.getMyTArea().syncAppend("\nERROR: could not write to Port! (check choosen COM-port)");
		}
	}

	public void addDuration(int newtime) {
		duration.add(newtime);
	}

	public void InitSerialConnection(List<String> Progablauf,
			List<Integer> Duration, String PORT_NAMES[],List<String> print ) {
		Commands = Progablauf;
		duration = Duration;
		pV = print;
		
		if (Commands == null) {
			return;
		}

		SerialClass main = new SerialClass();
		main.initialize();

		input = SerialClass.input;
		output = SerialClass.output;
		InputStreamReader Ir = new InputStreamReader(System.in);
		BufferedReader Br = new BufferedReader(Ir);

		Thread t = new Thread() {
			@Override
			public void run() {
				
				//System.out.println(forceEnd);
				//System.out.println(isPaused);
				
				//setze die flags zurück!
				setForceEnd(false);
				setIsPaused(false);
				
				// initialisiert das Fahrzeug! Zeit muss noch angepasst werden
				USBFrame.getInstance().getTA().syncAppend("Initialisiere Fahrzeug ...");
				writeData("test");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				if (!checkForceEnd()){
					USBFrame.getInstance().getTA().syncAppend("\nDone\n");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}	
				int j = 0;
				while ( j < Commands.size()) {
					if(checkIsPaused()){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						//check ob beendet werden soll
						if(checkForceEnd()){
							break;
						}
					}
					else{
						if(checkForceEnd()){
							break;
						}
						// printe das aktuelle Command
						USBFrame.getInstance().getTA().syncAppend("\n"+(j+1)+": "+ pV.get(j));
						writeData(Commands.get(j));
						try {
							Thread.sleep(duration.get(j));
						} catch (InterruptedException e) {
						}
						j++;
						
					}
					
				}
				
				if(!checkForceEnd()){
					// sage dass thread fertig ist (nur wenn nicht abbgebrochen wurde)
					USBFrame.getInstance().getTA().syncAppend("\n finished! \n");
				}
				
				// setze flags zurück
				setForceEnd(false);
				setIsPaused(false);
				
			}

		};
		
		
		t.setDaemon(true);
		t.start();
		//System.out.println("Started");
		
	}
}
