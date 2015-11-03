package myButtons;

import javax.swing.JButton;

import GUI.*;
import Proto_ZV.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**Klasse die mir den jeweiligen Button erstellt (KLassenname = buttonname) und eine Actionlistener in einer ineere Klasse dafür bereit-stellt
 * 
 * @author Markus Friedrich
 *
 */

@SuppressWarnings("serial")
public class AddButton extends JButton {
	
	private JButton button = new JButton (" Add ");
	private myJList myJList;
	private myTable myTable;

	public class AddControl implements ActionListener{

		
		/**wenn der AddButton gedrückt wurde soll er den Markierten Prototyp aus der Jlist
		 * entnehemen und aus der Klasse Prototyp die createInstance -methode aufrufen
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// nehme selceted value aus der Jlist (steht in help) und erzeuge instanz davon
			//sage der Table sie soll sich aktualisieren 
			// mach das alles nur sofern in der Jlist eine auswahlh erfolt ist sonst mach nichts mit dem button
			if(myJList.getselPrototyp()!=null){
			//System.out.println(myJList.getselPrototyp());
			Zentralverwaltung.getInstance().addStep(myJList.getselPrototyp());
			
			//sage tabelle update dich
			myTable.getModel().fireTableDataChanged();
			
			//setze focus auf neues elemnt in der tabelle
			myTable.setRowSel(myTable.getModel().getRowCount()-1);
			
			//System.out.println(Zentralverwaltung.getInstance().getControl().toString());
			}
		}
		
	}

	public AddButton(myJList m, myTable t){
		this.button.addActionListener(new AddControl());
		this.myJList = m;
		this.myTable = t;
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
		
	}
	
	
	public JButton getAddButton(){
		return this.button;	
	}

}
