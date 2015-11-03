package GUI;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import myButtons.*;

@SuppressWarnings("serial")
public class myJToolBar extends JToolBar {
	private JToolBar myBar;
	
	
	public myJToolBar(){
		this.myBar =  new JToolBar();
		
		this.myBar.add(new LadenButton());
		this.myBar.add(new SpeicherButton());
		this.myBar.add(new InfoButton());

		//this.myBar.setBackground(Color.DARK_GRAY);
		//this.myBar.setForeground(Color.BLACK);
		
		this.myBar.setFloatable(false);
		this.myBar.addSeparator();
		
	}
	
	public JToolBar getMyBar (){
		return this.myBar;
	}
	

}
