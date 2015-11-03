package myButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SpeicherButton extends JButton {
	
	private JButton button = new JButton(" Speichern ");
	
	public class SpeicherControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public SpeicherButton(){
		this.button.addActionListener(new SpeicherControl());
	}
	
}
