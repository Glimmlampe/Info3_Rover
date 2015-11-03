package myButtons;

import javax.swing.JButton;
import javax.swing.JTextArea;

import GUI.COMport;
import GUI.MainGUI;
import GUI.myJTextArea;
import GUI.myTable;
import CommandFrames.myThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Klasse die mir den jeweiligen Button erstellt (KLassenname = buttonname) und eine Actionlistener in einer ineere Klasse dafür bereit-stellt
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings("serial")
public class StartButton extends JButton {
		
	@SuppressWarnings("unused")
	private myTable myTable;
	private myJTextArea myArea;
	private JButton button = new JButton (" Start ");
	public myThread Ablauf;
		
	public class StartControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(COMport.getInstance().getSelectedPort().equalsIgnoreCase("None selected")){
				myArea.syncAppend("\nERROR : Bitte einen COM-port auswählen ! (Connection -> set USB-port)");
			}
			else{
				myArea.syncAppend("\n");
				myArea.syncAppend("CommandWindow wird gestartet! "+"\n");
			
				// starte neuen thread
				Ablauf = myThread.getInstance();
				Ablauf.mT.setDaemon(true);           		// macht daraus ein DeamonThread, so wird er auf jedenfall beednet nach beenden des Programms
				Ablauf.mT.start();
			
			
				// lock den button 
				lock();
			
				// setze execution flag für statusausgabe
				MainGUI.getInstance().setExecutor(true);
			}
		}
				
	}
		
	public JButton getMyButton(){
		return this.button;	
		
	}

	public StartButton(myTable m, myJTextArea l){
		this.button.addActionListener(new StartControl());
		this.myTable = m;
		this.myArea = l;
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
		
		
	}
	
	public void lock(){
		this.button.setEnabled(false);
	}
	
	public void unlock(){
		this.button.setEnabled(true);
	}
	
	
	
	
	
}