package CommandFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.MainGUI;

public class BeendenButton {
	
	private JButton end = new JButton("Beenden");
	private Boolean ThreadFlag = false;									// flag ob ein Worker thread existiert

	public BeendenButton(){
		end.addActionListener(new BeendenSteuerung());
		
		
	}
	
	public JButton getMyButton(){
		return end;
	}
	
	public class BeendenSteuerung implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// unlocke den Startbutton dr maingui
			MainGUI.getInstance().x.mysb.unlock();
			
			
			// kille nun den Backgroundthread wenn er existend is
			if(ThreadFlag){
				BackgroundThread.getInstance().killThread();
			}
			
			// beenden des command Frames
			USBFrame.getInstance().getMyFrame().setVisible(false);
			USBFrame.getInstance().getMyFrame().dispose();
			USBFrame.getInstance().destroyFrame();
			
	
			
			//kille nun den GUI thread
			myThread.getInstance().killThread();
			
		}
		
		
		
	}
	
	public void lock(){
		this.end.setEnabled(false);
	}
	
	public void unlock(){
		this.end.setEnabled(true);
	}
	
	public void setThreadFlag(Boolean b){
		this.ThreadFlag = b;	
	}
}
