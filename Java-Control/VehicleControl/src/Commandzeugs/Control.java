package Commandzeugs;
/**
 * Klasse Control (extends Command)
 * 
 * Beschreibung:
 * Klasse f�r Kontroll-Befehle (erbt von Command)
 * 
 * keine eigenen Parameter!
 * enth�lt darum auch keine equals!
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

	/**Konstruktor f�r superclass (command)
	 * da keine eigenen Parameter in Control
	 * -> nur Konstruktor f�r Superclass
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
	 * gibt einen String zur�ck in dem der Klassentyp steht
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
