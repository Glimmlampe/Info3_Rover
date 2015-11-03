package GUI;
import myButtons.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import Proto_ZV.Zentralverwaltung;

import javax.swing.JPanel;

/**myPanels stellt eine Klasse dar die meherer JPanels enthält, welche auch hier vom aufbau her deklariert werden
 * Sie dient in erster Linie dazu die Sublayouts für das haupt-Jframe bereitzustellen. PLus die jeweiligen get-methoden für diese Panels
 * 
 * @author Markus Friedrich
 *
 */

public class myPanels {
	private JPanel probpanel;
	private JPanel Commandpanel;
	private JPanel Commandsettings;
	private JPanel Consolepanel;
	
	
	private myJList myJList = new myJList();
	public myTable myTable = new myTable();
	private myJTextField textF = new myJTextField(myTable);
	private myJTextArea ta = new myJTextArea();
	public JMenuBar b = new myJMenuBar(ta).getMyBar();
	public StartButton mysb = new StartButton(myTable, ta);
	
	
	//generiere mehrere subsublayouts im flowlayout
	private JPanel subCommandsettings;

	
	
	private JPanel subsub; 
	@SuppressWarnings("unused")
	private JPanel subsub1;
	@SuppressWarnings("unused")
	private JPanel subsub2; 
	@SuppressWarnings("unused")
	private JPanel subsub21;
	@SuppressWarnings("unused")
	private JPanel subsub22;
	@SuppressWarnings("unused")
	private JPanel subsub23;
	private JPanel subsub9; 
	
	private JLabel Lname =new JLabel(" Name : ");
	private JLabel LVal1 =new JLabel(" ");
	private JLabel LVal2 =new JLabel(" ");
	private JLabel Lplatz = new JLabel (" ");			// platzhalterlabel
	private JLabel Lplatz2 = new JLabel (" ");			// platzhalterlabel
	
	
	
	public myJTextArea getMyTArea(){
		return this.ta;
	}
	
	
	public void generateSubsubs(){
	//generiere mehrere subsublayouts im flowlayout
		this.subsub = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub1 = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub2 = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub21 = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub22 = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub23 = new JPanel (new FlowLayout(FlowLayout.CENTER));
		this.subsub9 = new JPanel (new FlowLayout(FlowLayout.RIGHT));
		
		GridBagConstraints cs = new GridBagConstraints();
		this.subCommandsettings = new JPanel(new GridBagLayout());
		
		cs.fill = GridBagConstraints.BOTH;
		
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
		
		subCommandsettings.add(Lname, cs);
		
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
	    
		subCommandsettings.add(textF.getNameField() , cs);
		
		// platzhalter einfügen
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 3;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
		
		subCommandsettings.add(Lplatz, cs);
		
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
	    
		subCommandsettings.add(LVal1 , cs);
		
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
	    
		subCommandsettings.add(textF.getFirstValField() , cs);
		
		// platzhalter einfügen
		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 3;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
		
		subCommandsettings.add(Lplatz2, cs);
		
		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 1;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
	    
		subCommandsettings.add(LVal2 , cs);
		
		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		cs.gridheight = 1;
	    cs.weightx= 0;
	    cs.weighty= 0;
	    
		subCommandsettings.add(textF.getSecondValField() , cs);
		
		// setze sie als default auf unsichtbar
		this.Lname.setVisible(false);
		this.textF.getNameField().setVisible(false);
		this.LVal1.setVisible(false);
		this.textF.getFirstValField().setVisible(false);
		this.LVal2.setVisible(false);
		this.textF.getSecondValField().setVisible(false);
		
		
	
	}
	
	
	public JPanel getProbpanel() {
		return probpanel;
	}

	public JPanel getCommandpanel() {
		return Commandpanel;
	}

	public JPanel getCommandsettings() {
		return Commandsettings;
	}

	public JPanel getConsolepanel() {
		return Consolepanel;
	}

	
	
	/**im Konstruktor werden die Panels definiert.
	 * somit kann im frame welches diese klasse verwendet einfach bereits definierte
	 * panels mittels den get methoden hinzugefügt werden.
	 * 
	 *
	 */
	public myPanels(){
		 
		this.createProbPanel();
		
		
		// gridbaglauout für commandpanel
		this.createCommandPanel();
		
		
		// gridbaglayout für commandsettings
		createCommandSettinges();
		
		
		// flowlayout für Consolpane
		this.createConsolPane();
		
		  
	  }
	
	
	private void createProbPanel(){
		
		
		// gridbaglayout für probpanel
		this.probpanel = new JPanel (new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		// passe dich bei größenänderung an horizontal und vertikal
		c.fill = GridBagConstraints.BOTH;
		c.weightx= 1.0;
		c.gridwidth = 2;
		c.gridheight = 2;
	    c.weightx= 1.0;
	    c.weighty= 1.0;
		this.probpanel.add(myJList.getmyscrollList(), c);
		
		//jetzt den button setzen, unter die liste rechts ohne
		c.gridx = 1;
		c.gridy = 2; 			
	    c.weightx= 0.0;
	    c.weighty= 0.0;
			    
	    //generiere hier ein subpanel im FlowLayout und füge dann in dieses LAyout den Button ein
	    JPanel subProbpanel = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
	    subProbpanel.add(new AddButton(myJList, myTable).getAddButton());
	    subProbpanel.setBackground(Color.GRAY);
	    // nun das Sub-panel dem gesamt panel hinzufügen
		this.probpanel.add(subProbpanel, c);
		
	}
	
	
	private void createCommandPanel(){
		this.Commandpanel = new JPanel (new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 3;
	    c.weightx= 1.0;
	    c.weighty= 1.0;
		this.Commandpanel.add(myTable.getMyTable(),c);
		
		
		//jetzt Positionierung für die SubPanesl setzen
		c.gridx = 1;
		c.gridy = 4; 
		c.gridwidth = 1;
	    c.weightx= 0.0;
	    c.weighty= 0.0;
		// generiere subpanel im flowlayout und adde remove Button
	    JPanel subCommandpanel = new JPanel( new FlowLayout(FlowLayout.LEFT) );
	    subCommandpanel.add(new RemoveButton(myTable).getMyButton());
	    subCommandpanel.setBackground(Color.GRAY);
	    //jetzt dem CommandPnale hinzufügen
	    this.Commandpanel.add(subCommandpanel, c);
	    
	    
		c.gridx = 1;
		c.gridy = 4; 
		c.gridwidth = 2;
	    c.weightx= 0.0;
	    c.weighty= 0.0;
		// generiere subpanel im flowlayout und adde start down und up Button
	    JPanel subCommandpanel2 = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
	    subCommandpanel2.add(new UpButton(myTable).getMyButton());
	    subCommandpanel2.add(new DownButton(myTable).getMyButton());
	    subCommandpanel2.add(mysb.getMyButton());
	    subCommandpanel2.setBackground(Color.GRAY);
	    //jetzt dem CommandPnale hinzufügen
	    this.Commandpanel.add(subCommandpanel2, c);
	    
	    
	}
	
	
	private void createCommandSettinges(){
		
		/*
		this.Commandsettings = new JPanel (new GridBagLayout());
		
		cc.fill = GridBagConstraints.BOTH;
		
		// Positionierung für das Gridlayout im gridbaglayout

	
		// generiere sublayout im Gridlayout
		this.subCommandsettings = new JPanel (new GridLayout(7,1));

		*/
		generateSubsubs();
		
		JLabel title = new JLabel("Command-Properties");
		title.setForeground(Color.WHITE);
		subsub.add(title);
		subsub.setBackground(Color.DARK_GRAY);
		subsub.setForeground(Color.GREEN);

		// sublayout für den SaveButton
		subsub9.add(new SaveButton(myTable, textF, ta).getMyButton());
		subsub9.setBackground(Color.GRAY);

		// ab hier mal als Borderlayout
		
		this.Commandsettings = new JPanel(new BorderLayout());
		
		Commandsettings.add(subsub, BorderLayout.NORTH);
		Commandsettings.add(subsub9, BorderLayout.SOUTH);
		
		
		Commandsettings.add(subCommandsettings, BorderLayout.CENTER);
		
		
	}
	
	private void createConsolPane(){
		
		
		this.Consolepanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx= 1.0;
	    c.weighty= 1.0;
		

		this.Consolepanel.add(ta.getMyScrollText(), c);
		this.Consolepanel.setBackground(Color.BLACK);
		
		
	}
	
	
	
	
	
	/**diese Allgorihtmus entscheided welche Jlabels und welche Jtextfields im rechten teil der GUI,
	 * also den Command-Properties angezeigt werden sollen und wleche nicht, in dem er einfach nur diejenigen visible setzt
	 * die verwendet werden sollen. Ist nichts ausgewählt in der Liste wird auch nichts visible, somit ist der rechte Fensterteil leer!
	 * 
	 * @author Markus Friedrich
	 */
	public void reSetTextFields(){

		if(myTable.hasSelChanged() && Zentralverwaltung.getInstance().getControl().size()>0 && myTable.getSelRow()<Zentralverwaltung.getInstance().getControl().size()  ){
	
			// setze alle auf invisible die benötigten werden dann sichtbar gemacht!
			this.Lname.setVisible(false);
			this.textF.getNameField().setVisible(false);
			this.LVal1.setVisible(false);
			this.textF.getFirstValField().setVisible(false);
			this.LVal2.setVisible(false);
			this.textF.getSecondValField().setVisible(false);
			
			
			
		if(Zentralverwaltung.getInstance().getControl().elementAt(myTable.getSelRow()).getClassType().equalsIgnoreCase("Gear")){
			
			this.LVal1.setText(" Geschw. : ");
			this.LVal2.setText(" Zeit : ");
			
			this.Lname.setVisible(true);
			this.textF.getNameField().setVisible(true);
			this.LVal1.setVisible(true);
			this.textF.getFirstValField().setVisible(true);
			this.LVal2.setVisible(true);
			this.textF.getSecondValField().setVisible(true);
			
			this.Commandsettings.repaint();

			

		}
		
		else if(Zentralverwaltung.getInstance().getControl().elementAt(myTable.getSelRow()).getClassType().equalsIgnoreCase("Direction")){

			this.LVal1.setText(" Richtung : ");
			
			this.Lname.setVisible(true);
			this.textF.getNameField().setVisible(true);
			this.LVal1.setVisible(true);
			this.textF.getFirstValField().setVisible(true);
			this.LVal2.setVisible(false);
			this.textF.getSecondValField().setVisible(false);
			
			this.Commandsettings.repaint();

			
		}
		
		else if(Zentralverwaltung.getInstance().getControl().elementAt(myTable.getSelRow()).getClassType().equalsIgnoreCase("Pause")){

			this.LVal1.setText(" Dauer : ");
			
			this.Lname.setVisible(true);
			this.textF.getNameField().setVisible(true);
			this.LVal1.setVisible(true);
			this.textF.getFirstValField().setVisible(true);
			this.LVal2.setVisible(false);
			this.textF.getSecondValField().setVisible(false);
			
			this.Commandsettings.repaint();
			
		}
		
		else if(Zentralverwaltung.getInstance().getControl().elementAt(myTable.getSelRow()).getClassType().equalsIgnoreCase("Repetition")){

			this.LVal1.setText(" Jmp-Addr. : ");
			this.LVal2.setText(" Wiederholungen : ");
			
			this.Lname.setVisible(true);
			this.textF.getNameField().setVisible(true);
			this.LVal1.setVisible(true);
			this.textF.getFirstValField().setVisible(true);
			this.LVal2.setVisible(true);
			this.textF.getSecondValField().setVisible(true);
			
			this.Commandsettings.repaint();
	
		}
		else{
			


		}
		
		
		
		}
		textF.readInValues(myTable.getSelRow());

		
		MainGUI.getInstance().x.Commandsettings.validate();
		MainGUI.getInstance().x.Commandsettings.repaint();
		
	}
	
}
