package GUI;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MainGUI {
	
	private static MainGUI single = null;
	public myPanels x;
	public JFrame f;
	private boolean execution = false;
	
	
	public static MainGUI getInstance(){
		if(single==null)
			single = new MainGUI();
		return single;	
	}

	private MainGUI(){
		this.x = new myPanels();
		this.f = new JFrame("Track-Control");

	}
	
	
	public static void main(String[] args) {

		MainGUI.getInstance().run2();
	}

	public void run2(){
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(1200, 720));
		f.getContentPane().setBackground(Color.GRAY);
		
		//setzt grund-layout für ddas frame
		f.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill= GridBagConstraints.BOTH;		// für horizontal und vertikal 
		// für abstand zwischen den gui elementen
		gc.insets = new Insets(2,4,2,4);
		//System.out.println(f.getBackground());
		
		
		// adde die menubar
	    gc.gridx= 0;           
	    gc.gridy= 0;           
	    gc.gridwidth=GridBagConstraints.REMAINDER;   
	    gc.gridheight= 1;
	    gc.weightx= 0.0;
	    gc.weighty= 0.0;
	    x.b.setMinimumSize(new Dimension(1000,25));
	    x.b.setPreferredSize(new Dimension(1000,25));
		f.add(x.b, gc);
		
		
		
		
	    gc.gridx= 0;           
	    gc.gridy= 1;           
	    gc.gridwidth= 2;   
	    gc.gridheight= 4;
	    gc.weightx= 0.5;
	    gc.weighty= 0.7;
	    x.getProbpanel().setPreferredSize(new Dimension(180,500));
		f.add(x.getProbpanel(), gc);
		
		
		gc.gridx = 2;
		gc.gridy = 1;
		gc.gridwidth = 5;
		gc.gridheight= 4;
	    gc.weightx= 0.5;
	    gc.weighty= 0.7;
	    x.getCommandpanel().setPreferredSize(new Dimension(500,500));
		f.add(x.getCommandpanel(), gc);
		

		gc.gridx = 7;
		gc.gridy = 1;
		gc.gridwidth = 3;
		gc.gridheight= 4;
	    gc.weightx= 0.5;
	    gc.weighty= 0.7;
	    x.getCommandsettings().setPreferredSize(new Dimension(340,500));
		f.add(x.getCommandsettings(), gc);
	    
		gc.gridx= 0;
		gc.gridy = 6;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.gridheight = GridBagConstraints.REMAINDER;
		gc.ipady = 40;
	    gc.weightx= 1.0;
	    gc.weighty= 0.4;
	    x.getConsolepanel().setPreferredSize(new Dimension(1000,100));
		f.add(x.getConsolepanel(), gc);

			
		f.pack();
		f.setResizable(true);
		f.setVisible(true);
		

	}

	
	public void run(){
		// TODO Auto-generated method stub
		//myPanels x = new myPanels();
		//JFrame f = new JFrame("Control-Developer");
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(1024, 640));
		f.getContentPane().setBackground(Color.GRAY);
		
		//setzt grund-layout für ddas frame
		f.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill= GridBagConstraints.BOTH;		// für horizontal und vertikal resize
		// für abstand zwischen den gui elementen
		gc.insets = new Insets(2,4,2,4);
		//System.out.println(f.getBackground());
		
		
		// adde die menubar
	    gc.gridx= 0;           
	    gc.gridy= 0;           
	    gc.gridwidth=12;   
	    gc.gridheight= 1;
	    gc.weightx= 0.0;
	    gc.weighty= 0.0;
		f.add(x.b, gc);
		
		
		
		
	    gc.gridx= 0;           
	    gc.gridy= 1;           
	    gc.gridwidth= 2;   
	    gc.gridheight= 4;
	    gc.weightx= 1;
	    gc.weighty= 1;
	    x.getProbpanel().setMinimumSize(new Dimension(140,365));
		f.add(x.getProbpanel(), gc);
		
		
		gc.gridx = 2;
		gc.gridy = 1;
		gc.gridwidth = 5;
		gc.gridheight= 4;
	    gc.weightx= 1;
	    gc.weighty= 1;
	    x.getCommandpanel().setMinimumSize(new Dimension(500,365));
		f.add(x.getCommandpanel(), gc);
		
		gc.gridx = 7;
		gc.gridy = 1;
		gc.gridwidth = 3;
		gc.gridheight= 4;
	    gc.weightx= 1;
	    gc.weighty= 1;
	    x.getCommandsettings().setMinimumSize(new Dimension(340,365));
		f.add(x.getCommandsettings(), gc);
	    
		gc.gridx= 0;
		gc.gridy = 12;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.gridheight = GridBagConstraints.REMAINDER;
	    gc.weightx= 1.0;
	    gc.weighty= 0.6;
	    x.getConsolepanel().setMinimumSize(new Dimension(1000,160));
		f.add(x.getConsolepanel(), gc);

			
		f.pack();
		f.setResizable(true);
		f.setVisible(true);
	}

	public void setExecutor(boolean b){
		this.execution = b;
	}
	public String getExecutor(){
		if(this.execution){
			return "running";
		}
		else
			return "not running";
	}
	

	
}