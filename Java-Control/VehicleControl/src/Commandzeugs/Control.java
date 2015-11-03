package Commandzeugs;
/**
 * Klasse Control (extends Command)
 * 
 * Beschreibung:
 * Klasse für Kontroll-Befehle (erbt von Command)
 * 
 * keine eigenen Parameter!
 * enthält darum auch keine equals!
 * 
 * @author Markus friedrich
 *
 */
public abstract class Control extends Command {

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**Konstruktor für superclass (command)
	 * da keine eigenen Parameter in Control
	 * -> nur Konstruktor für Superclass
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 */
	public Control(String name, int iD, int stepID) {
		super(name, iD, stepID);
		// TODO Auto-generated constructor stub
	}

	/**
	 * toString 
	 * 
	 * gibt einen String zurück in dem der Klassentyp steht
	 */
	@Override
	public String toString() {
		return super.toString()+"Control []";
	}

	@Override
	public String getClassType(){
		return "Control";
	}
	
	@Override
	public String printOwnValues(){
		return super.printOwnValues();
		
	}
	
	
	
	
}
