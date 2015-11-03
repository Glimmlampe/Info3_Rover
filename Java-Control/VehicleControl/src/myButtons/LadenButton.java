package myButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LadenButton extends JButton {
	
	private JButton button = new JButton(" Laden ");
	
	public class LadenControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public LadenButton(){
		this.button.addActionListener(new LadenControl());
	}
	
}
