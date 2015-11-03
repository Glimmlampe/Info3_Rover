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
public class RemoveButton extends JButton {
	
	private myTable myTable;	
	private JButton button = new JButton (" Remove ");
		
	public class RemoveControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(myTable.getSelRow()!=-1){
				//System.out.println(myTable.getSelRow());
				int x = myTable.getSelRow();
				Zentralverwaltung.getInstance().removeStep(x);
				
				//sage Tabelle update dich
				myTable.getModel().fireTableDataChanged();	
				
				//setz neue Markierung in die Tabelle aber nur wenn min 1 Eintrag vorhanden ist
				//System.out.println(myTable.getModel().getRowCount()-1);
				if(myTable.getModel().getRowCount()>=1){
					if(x<=0)
						myTable.setRowSel(x);
					else if (x>=myTable.getModel().getRowCount())
						myTable.setRowSel(x-1);
					else
						myTable.setRowSel(x);	
				}
	
			}		
		}
				
	}
		
	public JButton getMyButton(){
		return this.button;	
	}

	
	public RemoveButton(myTable m){
		this.button.addActionListener(new RemoveControl());
		this.myTable = m;
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
	}
	
	
}
	

