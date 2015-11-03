package SerialSignal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Commandzeugs.*;
import Proto_ZV.*;

/**
 * Geschwindigkeit;Winkel;Dauer
 * 
 * Geschwindigkeit: -100 bis 100 Winkel: -360 bis 360 Dauer 0 bis 99 Sekunden
 * (nur ganze Werte)
 * 
 * 
 * @author Brendle / Ostertag / 
 *
 */
public class USBCommunication implements Runnable {
	private List<String> commands = new ArrayList<String>();
	private List<Integer> duration = new ArrayList<Integer>();
	private Vector<Command> programmablauf = new Vector<Command>();
	private static String PORT_NAMES[] = null;
	private String fullCommand;
	private SerialClassData sCD = new SerialClassData();
	
	
	// von MArkus
	private List<String> printVersion = new ArrayList<String>();
	

	

	public Vector<Command> getProgrammablauf() {
		return programmablauf;
	}

	public void setProgrammablauf(Vector<Command> programmablauf) {
		this.programmablauf = programmablauf;
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	public List<Integer> getDuration() {
		return duration;
	}

	public void setDuration(List<Integer> duration) {
		this.duration = duration;
	}

	public static String[] getPORT_NAMES() {
		return PORT_NAMES;
	}

	public static void setPORT_NAMES(String[] pORT_NAMES) {
		PORT_NAMES = pORT_NAMES;
	}

	public void serialConnect() {
		int _duration = 2000;
		if (programmablauf == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Fehler", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int j = 0; j < programmablauf.size(); j++) {
			
			
				if (programmablauf.elementAt(j).getClass() == Direction.class) {
					//System.out.println("Direction");
					printVersion.add(programmablauf.elementAt(j).getName()+"	"+ programmablauf.elementAt(j).printOwnValues());
					Direction d = (Direction) programmablauf.elementAt(j);
					_duration = 2000;
					duration.add(_duration);
					fullCommand = "0;" + d.getDegree() + ";0";
					commands.add(fullCommand);
				
				}
				else if (programmablauf.elementAt(j).getClass() == Gear.class)
				{
					//System.out.println("Gear");
					printVersion.add(programmablauf.elementAt(j).getName()+"	"+ programmablauf.elementAt(j).printOwnValues());
					Gear g = (Gear) programmablauf.elementAt(j);
					_duration = (int) (2000 + g.getDuration() * 1000);
					duration.add(_duration);
					fullCommand = g.getSpeed() + ";0;" + g.getDuration();
					commands.add(fullCommand);
				
				}
				else if(programmablauf.elementAt(j).getClass()==Pause.class)
				{
					printVersion.add(programmablauf.elementAt(j).getName()+"	"+ programmablauf.elementAt(j).printOwnValues());
					//System.out.println("Pause");
					Pause p =(Pause) programmablauf.elementAt(j);
					_duration = (int) (p.getDuration()*1000);
				}
				else if(programmablauf.elementAt(j).getClass()==Repetition.class)
				{
					Repetition r = (Repetition) programmablauf.elementAt(j);
					//System.out.println("Repetition"+r.getNrRepetitions());
					if(r.getNrRepetitions()>=0)
					{
						//System.out.println("R");
						j=r.getJumpAdress()-1;
						r.setNrRepetitions(r.getNrRepetitions()-1);
						
					}
				}
				else
				{
					System.out.println("was anderes");
				}
				
			}	
			
		sCD.InitSerialConnection(commands, duration, PORT_NAMES, printVersion);

	}


	@Override
	public void run() {
		Vector<Command> tempAblauf = new Vector<Command>();
		for (Iterator<Command> iterator = Zentralverwaltung.getInstance().getControl().iterator(); iterator.hasNext();) {
			Command command;
			try {
				command = (Command)iterator.next().clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				command = new Pause("CloneFailed", 0, 0, 0);
			}
			tempAblauf.add(command);
		}
		setProgrammablauf(tempAblauf);
		
		/*
		for (Iterator iterator = tempAblauf.iterator(); iterator.hasNext();) {
			Command command = (Command) iterator.next();
			System.out.println(command);
		}
		*/

		serialConnect();
		
	}
	
	
	
}
