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
public class DownButton extends JButton {
		
	private myTable myTable;
	private JButton button = new JButton (" Down ");
		
	public class DownControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(myTable.getSelRow()!=-1){
				//System.out.println(myTable.getSelRow());
				// mache incOrder vom markierten commandObj aus der Tabelle
				int x = myTable.getSelRow();
				Zentralverwaltung.getInstance().decOrder(x);
				// sage Model er soll updaten
				myTable.getModel().fireTableDataChanged();
				
				//setz neue Markierung in die Tabelle 
				//System.out.println(myTable.getModel().getRowCount()-1);
				if(x<myTable.getModel().getRowCount()-1)
					myTable.setRowSel(x+1);
				else
					myTable.setRowSel(x);
			}
		}
				
	}
	
	public DownButton(myTable m){
		this.button.addActionListener(new DownControl());
		this.myTable = m;
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
		
	}
		
	public JButton getMyButton(){
		return this.button;	
	}

}