package GUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**myTable erzeugt mit einem objekt von myTableModel
 * eine JTable und fügt diese in ein JscrollPane ein um eine Darstellung mit Scrollbar zu erhalten 
 * 
 * @author Markus Friedrich
 *
 */

@SuppressWarnings("serial")
public class myTable extends JTable implements MouseListener {

	private myTableModel model = new myTableModel();
	private JTable tabelle ;
	private JScrollPane bar;
	private int oldSel = -2;

	
	
	public myTable(){

		this.tabelle = new JTable(model);
		this.tabelle.setBackground(Color.WHITE);
		this.tabelle.setForeground(Color.BLACK);
		this.model.fireTableDataChanged();
		this.tabelle.setSelectionBackground(Color.BLACK);
		this.tabelle.setSelectionForeground(Color.GREEN);
		
		//dem Model noch ein Listener hinzufügen	
		this.tabelle.getModel().addTableModelListener(new myTableModel());
		
		this.bar = new JScrollPane(tabelle, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.bar.setBackground(Color.DARK_GRAY);
		this.bar.setBorder(null);
		
		this.oldSel = this.tabelle.getSelectedRow();
		this.tabelle.addMouseListener(this);
		this.tabelle.getTableHeader().setBackground(Color.DARK_GRAY);
		this.tabelle.getTableHeader().setForeground(Color.WHITE);
		
	}
	
	
	
	public JScrollPane getMyTable(){
		return this.bar;
	}
	
	@Override
	public myTableModel getModel(){
		return this.model;
	}
	
	public int getSelRow(){	
		//System.out.println(this.tabelle.getSelectedRow());
		return this.tabelle.getSelectedRow();

	}
	
	public void setRowSel(int x){
		this.tabelle.setRowSelectionInterval(x, x);
		
		//sage dem CommandSettingspanel es soll was anzeigen oder so
		MainGUI.getInstance().x.reSetTextFields();

	}
	
	public boolean hasSelChanged(){
		if(this.tabelle.getSelectedRow()==this.oldSel)
			return false;
		else if(this.tabelle.getSelectedRow()!=this.oldSel)
			return true;
		else
			return true;
		
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//sage dem CommandSettingspanel es soll was anzeigen oder so
		MainGUI.getInstance().x.reSetTextFields();
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//sage dem CommandSettingspanel es soll was anzeigen oder so
		//MainGUI.getInstance().x.reSetTextFields();
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		// ignore
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// ignore
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// ignore
		//MainGUI.getInstance().x.reSetTextFields();
	}



	
	
}
