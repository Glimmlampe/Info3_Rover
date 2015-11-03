package GUI;

import com.fazecast.jSerialComm.*;

//import gnu.io.CommPortIdentifier;
//import gnu.io.SerialPort;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.Enumeration;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;

import SerialSignal.SerialClass;


public class COMport {
	
	
	private static COMport single = null;
	
	private Vector<String> PortNamen;
	
	
	// die relveanten Jmenus
	private JMenu USBPort;
	private JMenu setUSBPort;
	private Vector<JCheckBoxMenuItem> itemArray;
	private SerialPort Portlist[];
	private boolean test = false;
	
	// mein actionlistener
	private CheckBoxControl cba;
	
	public static COMport getInstance(){
		if (single == null){
				single = new COMport();
			}
			return single;
	}
	
	
	
	private COMport (){
		// zu testzwecken die testinit verwenden in der verwendung dann die init() !!!!!
		init();
		//testinit();
		genMenu();
	}
	
	//liefert das fertige menu zurück welches dann in die Jmenubar geaddet werden kann
	public JMenu getUSBmenu(){
		return this.USBPort;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public void init(){
		
//		Enumeration Portenum;
//		Portenum = CommPortIdentifier.getPortIdentifiers();
//		
//		while(Portenum.hasMoreElements()){
//			CommPortIdentifier c = (CommPortIdentifier)Portenum.nextElement();
//			System.out.println(c.getName()+": "+c.getCurrentOwner());
//		}
//		
		
		
		this.Portlist = SerialPort.getCommPorts();
		
		for (int i = 0; i < Portlist.length; i++) {
			System.out.println(Portlist[i].getSystemPortName()+" : "+Portlist[i].getDescriptivePortName());
		}
		


	}
	
	
	/**diese Methode dient nur zu testzwecken sie liest nicht die vorhandenen COM-ports ein sondern erstellt "testports" (strings)
	 * 
	 */
	public void testinit(){
		this.PortNamen = new Vector<String>();
		int i =0;
		for (i=0; i<7; i++){
			this.PortNamen.addElement("COM"+i+" : ");
		}
		this.test = true;
	}
	
	
	
	// gibt den selectierten Port zurück (für status abfrage)
	public String getSelectedPort(){
		String erg =  new String();
		
		for(int i =0; i<= this.itemArray.size()-1; i++){
			if(itemArray.elementAt(i).isSelected()){
				erg = itemArray.elementAt(i).getText();
			}
		}
		
		if(erg.isEmpty()){
			erg = "None selected";
		}
		
		return erg;
	}
	
	
	
	
	
	
	
	
	
	/**Diese Methode erzeugt zu den COM-ports gehörende menüeinträge und legt die Hierarchie fest
	 * 
	 */
	public void genMenu(){
		
		this.cba = new CheckBoxControl();
		this.itemArray = new Vector<JCheckBoxMenuItem>();
		
		this.USBPort = new JMenu("Connection");
		this.USBPort.setForeground(Color.WHITE);
		this.setUSBPort = new JMenu("set USB-port");
		
		UIManager.put("CheckBoxMenuItem.selectionForeground", Color.GREEN);
		UIManager.put("CheckBoxMenuItem.selectionBackground", Color.BLACK);
		
		int i=0;
		
		if(test){
			while(i< this.PortNamen.size()-1){
				JCheckBoxMenuItem help = new JCheckBoxMenuItem(this.PortNamen.elementAt(i));
				//adde meine actionlistener auf help
				help.addActionListener(cba);
				this.itemArray.addElement(help);
				this.setUSBPort.add(itemArray.elementAt(i));
				i++;
			}
		}
		
		else{		
				for(int n=0; n<Portlist.length; n++){
				JCheckBoxMenuItem help = new JCheckBoxMenuItem(Portlist[n].getSystemPortName()+" : "+Portlist[n].getDescriptivePortName());
				//adde meine actionlistener auf help
				help.addActionListener(cba);
				this.itemArray.addElement(help);
				this.setUSBPort.add(itemArray.elementAt(i));
				i++;
			}
		}
		
		
		
		this.USBPort.add(setUSBPort);	
	}
	
	
	/**Checkbox control ist der actionlistener für die Checkboxes
	 * 
	 * @author Markus
	 *
	 */
	public class CheckBoxControl implements ActionListener {
		
		private int oldsel = -1;
		private int sel = -1;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// wenn noch nichts selected wurde setze die "erste selection" --> dies betrifft aller ersten aufruf
			if(oldsel == -1 && sel == -1){
				//hole das selcetierte checkboxitem
				for(int i=0; i<= itemArray.size()-1; i++){
					if(itemArray.elementAt(i).isSelected()){
						sel = oldsel =i;
					}
				}
			}
			
			// hole nun das aktuell selectierte checkboxitem, aber nur wenn es nicht das das alte selectierte ist
			for(int n =0; n<=itemArray.size()-1; n++){
				//wenn ein checkboxitem selectiert ist und es NICHT das alte ist
				if(itemArray.elementAt(n).isSelected() && n!=oldsel){
					// setze neue selction
					sel =n;
				}
			}
			
			// prüfe nun ob 2 checkboxitems selectiert sind wenn ja setze das oldsel item zurück
			if(sel != oldsel){
				
				itemArray.elementAt(oldsel).setSelected(false);
				// überschriebe nun die aktuelle selection auf oldsel und setze sel wieder auf -1
				oldsel = sel;
				//sel =-1;
			}
			
			// jetzt sollte nur noch 1 item selectiert sein jetzt hole den identifier von diesem item und setze ihn als Port
			String[] myport = {""}; 
			// lese bis zur ersten leerzeile => also nur COM4 (beispielsweise)
			myport[0] = itemArray.elementAt(sel).getText().substring(0, itemArray.elementAt(sel).getText().indexOf(" "));
			
			// setze nun den Selectierten Port!
			SerialClass.PORT_NAMES = myport;
			System.out.println(myport[0]);
			
			// gib noch aus welche Comport gesetzt wurde
			MainGUI.getInstance().x.getMyTArea().syncAppend("\n COM-port wurde gesetzt auf:"
															+"\n"+getSelectedPort());
			
			
		}
		
		
	}
	
	
	
	
	
	
}
