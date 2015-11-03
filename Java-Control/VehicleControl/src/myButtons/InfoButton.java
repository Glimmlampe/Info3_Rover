package myButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InfoButton extends JButton {
	
	private JButton button = new JButton(" Info ");
	
	public class InfoControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public InfoButton(){
		this.button.addActionListener(new InfoControl());
	}
	
}
