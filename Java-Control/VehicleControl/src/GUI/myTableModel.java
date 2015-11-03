package GUI;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import Commandzeugs.Command;
import Proto_ZV.Zentralverwaltung;


/**class für das TabellenModell:
 * 
 * Baut ein Tabellen Modell nach dem AbstractTableModel auf und
 * beinhaltet zusätzlich einen TableModelListener 
 * 
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings("serial")
public class myTableModel extends AbstractTableModel implements TableModelListener{

	
	//final JLabel[] spaltenNamen= getSpaltenNamen();
	final String[] columnHeader= {"Index", "Command-Typ" , "Command-Name" , "Properties"};
	private Zentralverwaltung data = Zentralverwaltung.getInstance();
	
	@Override
	public String getColumnName(int column){
	  	return columnHeader[column];
  	}
	
/*	public JLabel getColumnNameL(int column){
		return spaltenNamen[column];
  	}
*/
	
	@Override
	public int getRowCount() {
		return data.getControl().size();
	}

	@Override
	public int getColumnCount() {
		return columnHeader.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Command help=data.getControl().elementAt(row);
		
		switch (column){
			case 0: return row;
			case 1: return help.getClassType();
			case 2: return help.getName();
			case 3: return help.printOwnValues();
			default: return "---";
		}
		
	}
	


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


	@Override
	public void tableChanged(TableModelEvent arg0) {
		
		this.fireTableDataChanged();
		//sage dem CommandSettingspanel es soll was anzeigen oder so
		MainGUI.getInstance().x.reSetTextFields();

	}

	public String printTabbleContent(){
		String Content = new String();
		String spalten = new String();
		// fülle Content
		for(int i =0 ; this.getRowCount()>i ; i++){
			spalten = spalten + i +"\t";
			for(int n =1; this.getColumnCount()>n ; n++){
				spalten= spalten + this.getValueAt(i, n).toString()+"    ";
			}
			spalten = spalten +"\n";
			Content = spalten;
		}
		
		
		return Content;
	}
	
	
	
	
}