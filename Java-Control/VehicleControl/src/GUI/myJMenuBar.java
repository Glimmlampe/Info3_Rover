package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.Runtime;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import Proto_ZV.Zentralverwaltung;





@SuppressWarnings("serial")
public class myJMenuBar extends JMenuBar  {
	
	
	private JMenuBar myBar;
	private JMenu file;
	private JMenu info;
	private JMenuItem speichern;
	private JMenuItem laden;
	private JMenuItem infoItem;
	private JMenuItem manual;
	
	private JTextArea myArea;
	

	
	
	public myJMenuBar( myJTextArea a ){
		this.myArea = a.getMyTextArea();
		// uiManager setzt für das look&feel neue default werte in diesemf all für markierungen der menuitems und menus
		UIManager.put("MenuItem.selectionBackground", Color.BLACK);
		UIManager.put("MenuItem.selectionForeground", Color.GREEN);
		UIManager.put("Menu.selectionForeground", Color.GREEN);
		UIManager.put("Menu.selectionBackground", Color.BLACK);
		
		this.myBar =  new JMenuBar();
		//this.myBar.setBackground(Color.BLACK);
		//this.myBar.setForeground(Color.CYAN);
		this.myBar.setUI(null);
		
		// erzeuge die Menüs die in der menu Bar dargestellt werden sollen
		
		this.file =  new JMenu(" File ");
		//this.file.setBackground(Color.BLACK);
		this.file.setForeground(Color.WHITE);
		this.info = new JMenu(" Help ");
		//this.info.setBackground(Color.BLACK);
		this.info.setForeground(Color.WHITE);

		
		this.speichern = new JMenuItem("Save");
		this.laden = new JMenuItem("Load");
		this.infoItem = new JMenuItem("Status");
		this.manual = new JMenuItem("show Manual");
		
		

		
		
		// adde ddie items in die menus
		
		this.file.add(speichern);
		this.file.add(laden);
		this.info.add(infoItem);
		this.info.add(manual);
		

		
		
		// adde actionListener zu den Items
		this.speichern.addActionListener(new SpeichernControl());
		this.laden.addActionListener(new LadenControl());
		this.infoItem.addActionListener(new InfoControl());
		this.manual.addActionListener(new ManualControl());

		// adde das JMenu und das Jmenuitem info in die MenuBar
		this.myBar.add(file);
		this.myBar.add(info);
		// addie her das bereits fertig erstellte comPort menü
		this.myBar.add(COMport.getInstance().getUSBmenu());
		
	}
	
	public JMenuBar getMyBar (){
		return this.myBar;
	}

	

	public class SpeichernControl implements ActionListener{

		private File fi = new File("./Saves/");
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		    JFileChooser chooser = new JFileChooser(fi);

		    int returnVal = chooser.showSaveDialog(getParent());
		   if(returnVal == JFileChooser.APPROVE_OPTION) {

			   // setzt file auf das aktuelle verzeichn iss des JfileChoosers
			   this.fi = chooser.getCurrentDirectory(); 
			   // hole den namen des zu speichernden files aus dem chooser
			   String DateiNamen = chooser.getName(chooser.getSelectedFile());
			   //füge den path aus file fi und den namen des zu speichernden files in 1 string zusammen und rufe die Speichernmethode aus Zentralverwaltung auf
			   Zentralverwaltung.getInstance().speichern(Zentralverwaltung.getInstance().getP(), this.fi.getPath()+"/"+DateiNamen);
			   
			   myArea.append("Daten wurden gespeichert in:"+"\n"+fi.getPath()+DateiNamen);
		   }
		   
		   
		}
		
	}
		
		
	
	
	public class LadenControl implements ActionListener{
		
		private File fi = new File("./Saves/");
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		    JFileChooser chooser = new JFileChooser(fi);

		    int returnVal = chooser.showOpenDialog(getParent());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {

			    // hole dir das gewählte File vom chooser , wandle es in eine path string um und rufe von Zentralverwltung die laden methode auf mit diesem path string
				this.fi = chooser.getSelectedFile();
				Zentralverwaltung.getInstance().laden(this.fi.getPath());
		    	
				myArea.append("\nDaten wurden erfolgreich eingelesen aus:"+"\n"+this.fi.getPath());
		    } 
		    
		    // aktualisiere tabelle
		    MainGUI.getInstance().x.myTable.getModel().fireTableDataChanged();
		    
		    
		    
		}
		
	}
	
	public class InfoControl implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String infomessage = new String();
			
			infomessage = ("\n### Status ###"
							+"\nUSB-Port :    "
							+COMport.getInstance().getSelectedPort()
							+"\nExecution-status: "
							+MainGUI.getInstance().getExecutor()
							+"\n(für BedienungsInformationen oder sonstige Hinweise öffnen sie bitte die Bedienungsanleitung"
							+"\nunter Information -> show manual)"
							+"\n#############"
							);
			
			myArea.append(infomessage);
			
			/*myArea.append("\nDieses kleine Tool dient zur Erzeugung und zur Verwaltung von Steuercommands.\n"
							+"Der Linke Teil der Oberfläche stellt Prototypen für zu erzeugenede Commands bereit.\n"
							+"Der mittlere teil der Oberfläche stellt den Command Vector in einer Tabelle dar.\n"
							+"Der rechte Teil der Oberfläche stellt je nach markiertem ListenElement die benötigten Konfigurationsfenster bereit.\n"
							+"Falls Sie eine Benutzer-Anleitung suchen drücken Sie bitte in der Menüleiste auf Info und anschließend auf 'show manual'. \n" );
			*/
		}
		
	}

	
	public class ManualControl implements ActionListener{

		private File mpath = new File("./");		 //mpath enthält den pfad für das Manual.txt
		private String mpath2 = mpath.getAbsolutePath();
		private String mpath3 = mpath2.substring(0, mpath2.length()-1)+"Manual\\Manual.pdf";
		private String[] cmdstring =new String[] {"CMD.EXE" ,"/C" , "start " ,  mpath3  };

		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//erzeuge hier mir dem exec command einene neuen prozess der den editor mit Manual.txt öffnet
			//System.out.println(mpath3);
			try{
				Runtime runtime = Runtime.getRuntime();
				@SuppressWarnings("unused")
				Process process = runtime.exec(cmdstring);
			
			} catch (SecurityException err){
				System.out.println("security fail");
			}
			catch (IOException io){
				System.out.println("io-fail");
			}
			catch (NullPointerException ne){
				System.out.println("iwas war null");
			}
			catch (IllegalArgumentException ie){
				System.out.println("illegales command");
			}
		}
		
		
		
		
		
	}

}
