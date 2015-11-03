package CommandFrames;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Commandzeugs.Command;
import Commandzeugs.Repetition;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import GUI.MainGUI;
import GUI.myJTextArea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Vector;
import Proto_ZV.Zentralverwaltung;


public class USBFrame implements Runnable {
	
	private static USBFrame single = null;
	private Vector<Command> workload;
	
	private JFrame usbFrame;
	private JLabel conInfo;
	private JLabel conStatus;
	private JLabel commandSum;
	private JLabel commandSumNum;
	private myJTextArea output;
	private StartButton start = new StartButton();
	private PauseButton pause = new PauseButton();
	private BeendenButton beenden = new BeendenButton();
	
	
	private JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel line1a = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel line2a = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel ButtonLine = new JPanel(new GridLayout(1,5));
	
	
	public static USBFrame getInstance(){
		if(single == null){
			single = new USBFrame();
			return single;
		}
		else
			return single;	
	}

	
	
	private USBFrame(){
		// kopiere den aktuellen CommandVektor

		
		// erstellt und initialisiert das Singelton
		
		this.usbFrame = new JFrame("Commandwindow");
		
		this.conInfo = new JLabel("USB-Connection-Status :");
		this.line1.add(conInfo);
		this.conStatus = new JLabel("Connected");
		this.conStatus.setForeground(Color.GREEN);
		this.line1a.add(conStatus);
		this.commandSum = new JLabel("Number of total Commands to execute :");
		this.line2.add(commandSum);
		this.commandSumNum = new JLabel(totalCommandAmaount());
		this.line2a.add(commandSumNum);
		
		this.output = new myJTextArea();
		
	}
	
	



	@Override
	public void run() {
		
		usbFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 			// soll via Button beendet werden
		//usbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usbFrame.setMinimumSize(new Dimension(480 , 240));
		usbFrame.setLayout(new GridBagLayout());
		usbFrame.getContentPane().setBackground(Color.GRAY);
		
		
		//adde buttons in die Buttonline
		this.ButtonLine.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.ButtonLine.add(this.start.getMyButton(),0);
		this.ButtonLine.add(new JLabel(""),1);
		this.ButtonLine.add(this.pause.getMyButton(),2);
		this.ButtonLine.add(new JLabel(""),3);
		this.ButtonLine.add(this.beenden.getMyButton(),4);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		
	    c.gridx= 0;           
	    c.gridy= 0;           
	    c.gridwidth= 2;   
	    c.gridheight= 1;
	    c.weightx= 1.0;
	    c.weighty= 0.0;
		this.usbFrame.add(line1, c);
		
	    c.gridx= 3;           
	    c.gridy= 0;           
	    c.gridwidth= 1;   
	    c.gridheight= 1;
	    c.weightx= 1.0;
	    c.weighty= 0.0;
		this.usbFrame.add(line1a, c);
		
	    c.gridx= 0;           
	    c.gridy= 1;           
	    c.gridwidth= 2;   
	    c.gridheight= 1;
	    c.weightx= 1.0;
	    c.weighty= 0.0;
		this.usbFrame.add(line2, c);
		
	    c.gridx= 3;           
	    c.gridy= 1;           
	    c.gridwidth= 1;   
	    c.gridheight= 1;
	    c.weightx= 1.0;
	    c.weighty= 0.0;
		this.usbFrame.add(line2a, c);
		
	    c.gridx= 0;           
	    c.gridy= 2;           
	    c.gridwidth= 4;   
	    c.gridheight= 3;
	    c.weightx= 1.0;
	    c.weighty= 1.0;
		this.usbFrame.add(output.getMyScrollText(), c);
		
	    c.gridx= 0;           
	    c.gridy= 6;           
	    c.gridwidth= 4;   
	    c.gridheight= 1;
	    c.weightx= 1.0;
	    c.weighty= 0.0;
	    this.ButtonLine.setBackground(Color.GRAY);
		this.usbFrame.add(ButtonLine, c);
		

		this.usbFrame.pack();
		this.usbFrame.setResizable(false);
		this.usbFrame.setVisible(true);
		
		
	}
	
	
	public myJTextArea getTA(){
		return this.output;
	}
	
	public JFrame getMyFrame(){
		return this.usbFrame;
	}
	
	public JLabel getStatusLabel(){
		return this.conStatus;
	}
	
	public void redraw(){
		this.usbFrame.revalidate();
		this.usbFrame.repaint();
	}
	
	public void destroyFrame(){
		single = null;
		workload = null;	// lösche auch den vektor
		// setze in der Miangui den executor flag zurück
		MainGUI.getInstance().setExecutor(false);
	}
	
	public Vector<Command> getWorkLoad(){
		return workload;
	}
	
	public StartButton getThisStart(){
		return this.start;
	}
	
	public PauseButton getThisPause(){
		return this.pause;
	}
	
	public BeendenButton getThisBeenden(){
		return this.beenden;
	}
	
	// berechnet die anzahl der gesamt auszuführenden Commands
	@SuppressWarnings("unchecked")
	public String totalCommandAmaount(){
		int x = 0 ;
		int wieder = 0;
		int jmp= 0;
		this.workload = null;
		this.workload = (Vector<Command>) Zentralverwaltung.getInstance().getControl().clone();
		
		for(int i=0; x <= workload.capacity()-1; i++){
		//	System.out.println(i);
			if(workload.elementAt(i).getClassType().equalsIgnoreCase("Gear")){
				x++;
			}
			else if(workload.elementAt(i).getClassType().equalsIgnoreCase("Direction")){
				x++;
			}
			else if (workload.elementAt(i).getClassType().equalsIgnoreCase("Pause")){
				x++;
			}
			else if(workload.elementAt(i).getClassType().equalsIgnoreCase("Repetition")){
				Repetition r = (Repetition) workload.elementAt(i);
				
				wieder = r.getNrRepetitions();
				jmp = r.getJumpAdress();
				x = x + wieder * (i-jmp);

			}
			else{}

		}
	
		return ""+x;
	}
	
	
}
