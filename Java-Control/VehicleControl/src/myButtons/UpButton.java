package myButtons;

import javax.swing.JButton;

import GUI.myTable;
import Proto_ZV.Zentralverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Klasse die mir den jeweiligen Button erstellt (KLassenname = buttonname) und eine Actionlistener in einer ineere Klasse dafür bereit-stellt
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings("serial")
public class UpButton extends JButton {
	
	private myTable myTable; 
	private JButton button = new JButton (" Up ");
		
	public class UpControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(myTable.getSelRow()!=-1){
				//System.out.println(myTable.getSelRow());
				int x = myTable.getSelRow(); 
				
				// mache incOrder vom markierten commandObj aus der Tabelle
				Zentralverwaltung.getInstance().incOrder(x);
				// sage Model er soll updaten
				myTable.getModel().fireTableDataChanged();
			
				//setz neue Markierung in die Tabelle 
				//System.out.println(myTable.getModel().getRowCount()-1);
				if(x<=0)
					myTable.setRowSel(x);
				else
					myTable.setRowSel(x-1);
			}		
		}
				
	}
		
	public JButton getMyButton(){
		return this.button;	
	}

	public UpButton(myTable m){
		this.button.addActionListener(new UpControl());
		this.myTable = m;
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
	}
	
	
}