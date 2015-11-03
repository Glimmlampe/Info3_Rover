package CommandFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PauseButton {
	
	private boolean isfortsetzen = false;
	
	private JButton pause = new JButton("Pause");

	public PauseButton(){
		pause.addActionListener(new PausenSteuerung());
		lock();
	}
	
	public JButton getMyButton(){
		return pause;
	}
	
	public class PausenSteuerung implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				
			//wenn isfortsetzen false ist pausiere den backgroundthread und rename den button auf fortsetzen und setze flag
			if(!isfortsetzen){
				USBFrame.getInstance().getTA().syncAppend("\n    ### pause ### ");
				//ändere das Buttonlabel
				pause.setText("Fortsetzen");
				isfortsetzen = true;
				
				//setze conectionStatus auf Paussiert
				USBFrame.getInstance().getStatusLabel().setText("Paused");
				USBFrame.getInstance().getStatusLabel().setForeground(Color.ORANGE);
				USBFrame.getInstance().redraw();
				
				// todo den Backgroundthread auf wait setzen!
				
				//BackgroundThread.getInstance().mBT.suspend();
					//wenn beim testen suspend und resume nicht funktionieren dann muss die 
					// Steuerungs-schleife mit eine bool flag erweitert werden dass nach jedem kommand abgefagt wird
					// um den thread dann zu unterbrechen muss er interuppted werden flag soll auf true oder so gesetzt werden
					// um ihn weiter laufen zu lassen einfach dann das flag zurücksetzen 
					// die methoden um den thread zu interrupten müssen dann noch implementiert werden
				
				BackgroundThread.getInstance().forcePause();
				
			}
			else if(isfortsetzen){
				USBFrame.getInstance().getTA().syncAppend("\n    ### fortsetzen ### ");
				//ändere das Buttonlabel
				pause.setText("Pause");
				isfortsetzen = false;
				
				//setze conectionStatus auf Paussiert
				USBFrame.getInstance().getStatusLabel().setText("Connected");
				USBFrame.getInstance().getStatusLabel().setForeground(Color.GREEN);
				USBFrame.getInstance().redraw();
				
				// todo den Backgroundthread aufwecken!
				BackgroundThread.getInstance().forceEndPause();
				
			}
			else{}
			
		}
		
		
		
	}
	
	public void lock(){
		this.pause.setEnabled(false);
	}
	
	public void unlock(){
		this.pause.setEnabled(true);
	}
	
	
	
}