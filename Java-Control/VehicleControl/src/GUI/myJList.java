package GUI;

import javax.swing.JList;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;

import Proto_ZV.*;



/**erzeugt eine Jlist mit dem Vector<Prototypen> aus der Zentralverwaltung!
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class myJList extends JList implements ListSelectionModel {
	
	private JList<Prototyp> myProtos;
	private JScrollPane bar;
	private Prototyp help;
	
	// Konstruktor, liste wird erstellt und parametrisiert
	public myJList(){
		
		this.myProtos = new JList<Prototyp>(Zentralverwaltung.getInstance().getProto());
		this.myProtos.setSelectionMode(SINGLE_SELECTION);
		this.myProtos.setSelectionBackground(Color.BLACK);
		this.myProtos.setSelectionForeground(Color.GREEN);
		this.myProtos.setSelectedIndex(-1);
		
		
		//füge ListenListener hinzu
		this.myProtos.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// "speichert" den selected Prototyp in help
				int id =0;
				if(myProtos.getSelectedIndex()!=-1){
				id = myProtos.getSelectedIndex();
				}
				//System.out.println(id);
				help = Zentralverwaltung.getInstance().getProto().elementAt(id);
				
			}	
		});
		
		
		// erzeuge scrollbar und konfiguriere sie
		this.bar = new JScrollPane(this.myProtos, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JViewport head = new JViewport();
		JLabel text = new JLabel("Prototypen", SwingConstants.CENTER);
		text.setForeground(Color.WHITE);
		text.setBackground(Color.DARK_GRAY);
		head.setBackground(Color.DARK_GRAY);
		head.setView(text);
		
		this.bar.setColumnHeader(head);
		//this.bar.setBackground(Color.DARK_GRAY);
		

		
	}
	public Prototyp getselPrototyp(){
		return this.help;
	}
	
	public JList<Prototyp> getmyList(){
		return this.myProtos;
	}
	
	public JScrollPane getmyscrollList(){
		return this.bar;
	}
	
	
	@Override
	public void insertIndexInterval(int index, int length, boolean before) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeIndexInterval(int index0, int index1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setAnchorSelectionIndex(int index) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLeadSelectionIndex(int index) {
		// TODO Auto-generated method stub
		
	}
	



}
