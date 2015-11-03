package CommandFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



@SuppressWarnings("serial")
public class StartButton extends JButton {
	
	private JButton start = new JButton("Start");
	private BackgroundThread worker;
	public StartButton(){
		start.addActionListener(new StartSteuerung());
		
	}
	
	public JButton getMyButton(){
		return start;
	}
	
	public class StartSteuerung implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				
			//USBFrame.getInstance().getTA().syncAppend("\n Start  " );
			
			//aktiviere den PauseButton und setze threadflag im beendenButton
			USBFrame.getInstance().getThisBeenden().setThreadFlag(true);
			USBFrame.getInstance().getThisPause().unlock();
			
			// setze den Button auf disable (damit nicht mehrfach gestartet weden kann)
			start.setEnabled(false);
			
			// starte hier die Übertragung mittels des backgroundthreads
			worker = BackgroundThread.getInstance();
			worker.mBT.setDaemon(true);						// sorgt dafür dass der thread nach beenden der anwenung nicht mehr weiterläuft!
			worker.mBT.start();
			
		}
		
		
		
	}
	
	public void lock(){
		this.start.setEnabled(false);
	}
	
	public void unlock(){
		this.start.setEnabled(true);
	}
	
	
}
