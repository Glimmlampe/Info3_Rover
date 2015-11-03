package GUI;

import javax.swing.JLabel;

/** myJLabel erstellt einfach ein Jlabel dass so konfiguriert werden knn wie man möchte (im Konstruktor dieser Klasse)
 * 
 * @author Markus friedrich
 *
 */
@SuppressWarnings("serial")
public class myJLabel extends JLabel {
	
	private JLabel label;
	
	public myJLabel(){
		
		this.label =  new JLabel(" ");
		//this.label.setForeground(Color.GREEN);
		//this.label.setBackground(Color.BLACK);
		this.label.setVisible(true);
		
	}
	
	
	public JLabel getmyLabel(){
		return this.label;
	}

}
