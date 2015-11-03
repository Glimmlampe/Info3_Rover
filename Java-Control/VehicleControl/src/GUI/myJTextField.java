package GUI;
import javax.swing.JTextField;

import GUI.myTable;
import java.lang.StringBuilder;


public class myJTextField {
	
	private JTextField nameField;
	private JTextField firstValField;
	private JTextField secondValField;
	
	private myTable Table;
	
	
	
	
	public myJTextField(myTable t){
		

		this.nameField = new JTextField(15);
		this.firstValField = new JTextField(15);
		this.secondValField = new JTextField(15);
		this.Table = t;

	}
	

	public JTextField getNameField() {
		return nameField;
	}


	public JTextField getFirstValField() {
		return firstValField;
	}


	public JTextField getSecondValField() {
		return secondValField;
	}






	public myTable getTable() {
		return Table;
	}





	public void setTable(myTable table) {
		Table = table;
	}





	/**readInvalues liest die daten aus der Tabelle in die Textfields ein sodass texfields und Tabelle übereinstimmen
	 * (sollte nach jeder selectiion-änderung der Tabelle aufgrufen werden)
	 * (sollten sich die Command typen (prototypen) ändern muss hier ebenfalls etwas geändert werden)
	 */
	public void readInValues(int z){
		
		
		int x = z;

		if (x ==-1){}
		
		else{
			
		String type = new String();
		String readVal = new String();
		
		type = (String) Table.getModel().getValueAt(x, 1);
		readVal = (String) Table.getModel().getValueAt(x, 2);
		
		//System.out.println(type);
		//System.out.println(readVal);
		
		// lese spezielle werte ein
		if(type.equalsIgnoreCase("Gear")){
			readVal = (String) Table.getModel().getValueAt(x, 2);
			this.nameField.setText(readVal);
			
			readVal = (String) Table.getModel().getValueAt(x, 3);
			
			StringBuilder msb = new StringBuilder(readVal);
			String subread = new String();
			int b = msb.indexOf(" ");
			subread = msb.substring(0, b);
			this.firstValField.setText(subread);
			
			b = msb.lastIndexOf("|");
			subread = msb.substring(b+2 , msb.lastIndexOf(" "));
			this.secondValField.setText(subread);
			
			
		}
		else if(type.equalsIgnoreCase("Pause")){
			readVal = (String) Table.getModel().getValueAt(x, 2);
			this.nameField.setText(readVal);
			
			readVal = (String) Table.getModel().getValueAt(x, 3);
			
			StringBuilder msb = new StringBuilder(readVal);
			String subread = new String();
			subread =msb.substring(0 , msb.indexOf(" "));
			this.firstValField.setText(subread);
			
			
		}
		else if (type.equalsIgnoreCase("Direction")){
			readVal = (String) Table.getModel().getValueAt(x, 2);
			this.nameField.setText(readVal);
			
			readVal = (String) Table.getModel().getValueAt(x, 3);
			
			StringBuilder msb = new StringBuilder(readVal);
			String subread = new String();
			subread =msb.substring(0 , msb.indexOf(" "));
			this.firstValField.setText(subread);
			
			
		}
		else if (type.equalsIgnoreCase("Repetition")){
			readVal = (String) Table.getModel().getValueAt(x, 2);
			this.nameField.setText(readVal);
			
			readVal = (String) Table.getModel().getValueAt(x, 3);
			
			StringBuilder msb = new StringBuilder(readVal);
			String subread = new String();
			subread =msb.substring(0 , msb.indexOf(" "));
			this.firstValField.setText(subread);
			
			subread = msb.substring((msb.indexOf("|")+2) , msb.lastIndexOf(" "));
			this.secondValField.setText(subread);
			
			
		}
		else {}
		//System.out.println("wurde aufgerufen");
		}

	
		
		
	}
	

}
