package myButtons;

import Proto_ZV.*;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Commandzeugs.*;
import GUI.myJTextArea;
import GUI.myJTextField;
import GUI.myTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Klasse die mir den jeweiligen Button erstellt (KLassenname = buttonname) und eine Actionlistener in einer ineere Klasse daf¸r bereit-stellt
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings("serial")
public class SaveButton extends JButton {
		
	private JButton button = new JButton (" Save ");
	private myTable myTable;
	private myJTextField tF;
	private JTextArea myArea;

		
	public class SaveControl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(myTable.getSelRow()!=-1){
				// x = markierte reihe aus tabelle
				int x = myTable.getSelRow();
			
				// was ist f¸r ein command - Obj an stelle x im command Vector
				Command help = Zentralverwaltung.getInstance().getControl().elementAt(x);
				if (help.getClassType().equalsIgnoreCase("Pause")) 
					isPauseCommand(x, help);
				if (help.getClassType().equalsIgnoreCase("Gear"))
					isGearCommand(x, help);
				if (help.getClassType().equalsIgnoreCase("Direction"))	
					isDirectionCommand(x, help);
				if (help.getClassType().equalsIgnoreCase("Repetition"))
					isRepetitionCommand(x, help);
			

			
			}
			
			
				
		}


				
	}
	public void clearTextF(){
		this.tF.getNameField().setText("");
		this.tF.getFirstValField().setText("");
		this.tF.getSecondValField().setText("");
		
		
	}
	
	
		
	public JButton getMyButton(){
		return this.button;	
	}

	public SaveButton(myTable t, myJTextField field, myJTextArea l){
		this.button.addActionListener(new SaveControl());
		this.myTable = t;
		this.tF = field;
		this.myArea = l.getMyTextArea();
		
		//this.button.setForeground(Color.RED);
		//this.button.setBackground(Color.BLACK);
	}
	
	
	
	
	public void isPauseCommand(int p, Command h) {
		
		Command help = h;
		int x = p;
		// was tun wenn welches command obj vorliegt
		String readpName;
		String readpDuration;
		double readDur;
		String errormsg1 = "" ;
		String errormsg2 = "" ;
		// ist ein Pause OBJ
		// lieﬂ relevante daten aus JtextFields eins
		if (tF.getNameField().getText().isEmpty()){
			readpName = "---";
			errormsg1 = ("Name: => Es wurde kein Namen eingegeben! Name wurde auf '---' gesetzt");
		}
		else
			readpName = (tF.getNameField().getText());
		
		
		if (tF.getFirstValField().getText().isEmpty())readpDuration = "0.0";
		else
			readpDuration = tF.getFirstValField().getText();
		// mache aus duration string ein double
		try {
			readDur = Double.parseDouble(readpDuration);
		} catch (NumberFormatException exc) {
			errormsg2 = ("Duration: " + readpDuration + " => Kann nicht in Double geparsed werden. defaultwert 0.0 wurde gesetzt!");
			readDur = 0.0;		
		}

		//gebe die error msg aus falls vorhanden
		if(!errormsg1.equalsIgnoreCase("") || !errormsg2.equalsIgnoreCase("")){
			myArea.append(("\n"+ errormsg1 +" \n" + errormsg2));
		}
		// erzeuge neues Pausen-Command und schriebe es Anschlieﬂend in den
		// Vektor
		help = new Pause(readpName, 0, 0, readDur);
		Zentralverwaltung.getInstance().getControl().setElementAt(help, x);

		// sage nun der Tabelle sie Kann sich wieder updaten
		myTable.getModel().fireTableDataChanged();

		// setze Marker auf das gerade Konfigurierte Element
		myTable.setRowSel(x);
	}

	
	private void isGearCommand(int p, Command h) {
	
		Command help = h;
		int x = p;
		// was tun wenn welches command obj vorliegt
		String readgName;
		String readgDuration;
		String readSpeed;
		String errormsg1 = "" ;
		String errormsg2 = "" ;
		String errormsg3 = "" ;
		double readDur;
		double readSp;
		// ist ein Pause OBJ
		// lieﬂ relevante daten aus JtextFields eins
		if (tF.getNameField().getText().isEmpty()){
			readgName = "---";
			errormsg3 = ("Name: => Es wurde kein Namen eingegeben! Name wurde auf '---' gesetzt");
		}
		else
			readgName = (tF.getNameField().getText());
		
		
		if (tF.getSecondValField().getText().isEmpty())readgDuration = "0.0";
		else
			readgDuration = tF.getSecondValField().getText();
		// mache aus duration string ein double
		try {
			readDur = Double.parseDouble(readgDuration);
		} catch (NumberFormatException exc) {
			errormsg2 = ("Duration: "+ readgDuration + " => Kann nicht in Double geparsed werden. defaultwert 0.0 wurde gesetzt!");
			readDur = 0.0;		
		}
		
		if (tF.getFirstValField().getText().isEmpty())readSpeed = "0.0";
		else
			readSpeed = tF.getFirstValField().getText();
		// mache aus speed string ein int
		try {
			readSp = Double.parseDouble(readSpeed);
		} catch (NumberFormatException exc) {
			errormsg1 = ("Speed: " + readSpeed + " => Kann nicht in Double geparsed werden. defaultwert 0.0 wurde gesetzt!");
			readSp = 0.0;		
		}
		
		//gebe die error msg aus falls vorhanden
		if(!errormsg1.equalsIgnoreCase("") || !errormsg2.equalsIgnoreCase("") || !errormsg3.equalsIgnoreCase("")){
			myArea.append(("\n"+errormsg3 +"\n" +errormsg1 +" \n" + errormsg2));
		}

		// erzeuge neues Gear-Command und schriebe es Anschlieﬂend in den
		// Vektor
		help = new Gear(readgName, 0, 0, 0, readSp, readDur);
		Zentralverwaltung.getInstance().getControl().setElementAt(help, x);

		// sage nun der Tabelle sie Kann sich wieder updaten
		myTable.getModel().fireTableDataChanged();

		// setze Marker auf das gerade Konfigurierte Element
		myTable.setRowSel(x);		
		
	}
	
	
	public void isDirectionCommand(int p, Command h) {
		
		Command help = h;
		int x = p;
		// was tun wenn welches command obj vorliegt
		String readdName;
		String readDirection;
		int readDir;
		String errormsg1 = "" ;
		String errormsg2 = "" ;
		// ist ein Pause OBJ
		// lieﬂ relevante daten aus JtextFields eins
		if (tF.getNameField().getText().isEmpty()){
			readdName = "---";
			errormsg1 = ("Name: => Es wurde kein Namen eingegeben! Name wurde auf '---' gesetzt");
		}
		else
			readdName = (tF.getNameField().getText());
		
		
		if (tF.getFirstValField().getText().isEmpty())readDirection = "0";
		else
			readDirection = tF.getFirstValField().getText();
		// mache aus duration string ein double
		try {
			readDir = Integer.parseInt(readDirection);
		} catch (NumberFormatException exc) {
			errormsg2 = ("Degree: "+ readDirection + " => Kann nicht in Int geparsed werden. defaultwert 0 wurde gesetzt!");
			readDir =0;		
		}


		//gebe die error msg aus falls vorhanden
		if(!errormsg1.equalsIgnoreCase("") || !errormsg2.equalsIgnoreCase("")){
			myArea.append(("\n"+errormsg1 +" \n" + errormsg2));
		}
		// erzeuge neues Pausen-Command und schriebe es Anschlieﬂend in den
		// Vektor
		help = new Direction(readdName, 0, 0, 0, readDir);
		Zentralverwaltung.getInstance().getControl().setElementAt(help, x);

		// sage nun der Tabelle sie Kann sich wieder updaten
		myTable.getModel().fireTableDataChanged();

		// setze Marker auf das gerade Konfigurierte Element
		myTable.setRowSel(x);
	}
	
	public void isRepetitionCommand(int p, Command h) {
		
		Command help = h;
		int x = p;
		// was tun wenn welches command obj vorliegt
		String readrName;
		String errormsg1 = "" ;
		String errormsg2 = "" ;
		String errormsg3 = "" ;
		String readRep;
		String readNum;
		int readR, readN;
		// ist ein repitition OBJ
		// lieﬂ relevante daten aus JtextFields eins
		if (tF.getNameField().getText().isEmpty()){
			readrName = "---";
			errormsg3 = ("Name: => Es wurde kein Namen eingegeben! Name wurde auf '---' gesetzt");
		}
		else
			readrName = (tF.getNameField().getText());
		
		
		if (tF.getFirstValField().getText().isEmpty()) readRep = "0";
		else
			readRep = tF.getFirstValField().getText();
		
		try {
			readR = Integer.parseInt(readRep);
		} catch (NumberFormatException exc) {
			errormsg1 = ("HW-Address: " + readRep + " => Kann nicht in Int geparsed werden. defaultwert 0 wurde gesetzt!");
			readR =0;		
		}
		
		if (tF.getSecondValField().getText().isEmpty())readNum = "0";
		else
			readNum = tF.getSecondValField().getText();
		
		try {
			readN = Integer.parseInt(readNum);
		} catch (NumberFormatException exc) {
			errormsg2 =("Count: "+ readNum + " => Kann nicht in Int geparsed werden. defaultwert 0 wurde gesetzt!");
			readN =0;		
		}

		// erzeuge neues Repetition-Command und schreibe es Anschlieﬂend in den
		// Vektor
		help = new Repetition(readrName, 0, 0, readR, readN);
		Zentralverwaltung.getInstance().getControl().setElementAt(help, x);
		
		//gebe die error msg aus falls vorhanden
		if(!errormsg1.equalsIgnoreCase("") || !errormsg2.equalsIgnoreCase("") || !errormsg3.equalsIgnoreCase("")){
			myArea.append(("\n"+errormsg3 +"\n" +errormsg1 +" \n" + errormsg2));
		}
		// sage nun der Tabelle sie Kann sich wieder updaten
		myTable.getModel().fireTableDataChanged();

		// setze Marker auf das gerade Konfigurierte Element
		myTable.setRowSel(x);
	}

	
}