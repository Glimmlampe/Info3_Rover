package Proto_ZV;
import Commandzeugs.*; //für die command klasse und alles darunter

import java.io.Serializable;  // um prototypen Speicherbar zu machen!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**Blatt-2-Aufg-1
 * Klasse Prototyp: Die klasse Protoyp enthält zwei String Attribute
 * mit deren Hilfe Passende CommandObj erzeugt werden
 * 
 * @author Markus Friedrich
 *
 */
public class Prototyp implements Serializable {

	/**
	 * Defauult Serial Version ID (zum Serialisieren, also Speichern notwending)
	 */
	private static final long serialVersionUID = 1L;
	private String nameCommand;
	private String className;
	
	/**Konstruktor fals Klassename und klassenart bereits übergeben werden
	 * 
	 * @param x = nameCommand
	 * @param y = className (KlassenArt)
	 */
	public Prototyp(String x, String y){
		this.setNameCommand(x);
		this.setClassName(y);
	}
	
	/**Std-Konstruktor
	 * 
	 * befüllt name Command und className über Konsoleneingabe
	 * wann was eingegeben werden soll wird per Sys.out.println angegeben!
	 * (benutzt helper-methode)
	 * 
	 */
	public Prototyp(){
		System.out.println("Bitte geben Sie einen Command-namen ein:");
		this.setNameCommand(helper());
		System.out.println("Bitte geben ein von Welcher Art Das Command sein soll :");
		System.out.println("(Beispiele: Gerade, Kurve, Pause, Repetition)");
		this.setClassName(helper());
		
	}
	
	
	/**Die Methode erzeugt verschiedene Command Objekte.
	 * differenzierung geschieht über einen switch-case über das Attribut ClassName(string)
	 * falls ein entsprechender case vorliegt wird das zugehörige commandObj erzeugt mit nameCommand als name
	 * falls Keine übereinstimmung gefunden wird wird kein CommandObj erzeugt sondern null zurückgegeben
	 * 
	 * @return referenz auf CommandObj (Bei Fehler: null +(ausgabe im default zweig!))
	 */
	public Command createInstance(){
		//todo
		switch(className){
		case "Gear": // erzeuge ein Gear Obj
		case "gear":	
			return new Gear(nameCommand, 0, 0, 0, 0, 0);
			//break;
		case "Direction":
		case "direction":	
			return new Direction(nameCommand, 0, 0, 0, 0);
			//break;
		case "Pause":
		case "pause":	
			return new Pause(nameCommand, 0, 0, 0);
			//break;
		case "Repetition":
		case "repetition":	
			return new Repetition(nameCommand, 0, 0, 0, 0);
			//break;
		default:// gibt dann null zurück
			System.out.println("error Keine erzeugung von Command möglich!");
			return null;
			//break;
		}
		
	}
	
	
	/**String Helper
	 * 
	 * ist eine hilfsmethode die vom Std-Konstruktor benutzt wird
	 * um eine Eingabe der Strings zu bewältigen.
	 * erzeugt einen Buffered reader und liest Zeilenweise ein und gibt diese als String zurück
	 * 
	 * ps: hat try - catch block beim readline (ohne gehts ned ^^)
	 * @throws IOException
	 */
	private String helper(){
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		String i="";
		try {
			i= bR.readLine();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IOException wurde geworfen!");
		}
		return i;
	}
	
	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getNameCommand() {
		return nameCommand;
	}

	public void setNameCommand(String nameCommand) {
		this.nameCommand = nameCommand;
	}

	@Override
	public String toString() {
		return ""+ nameCommand + "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((nameCommand == null) ? 0 : nameCommand.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prototyp other = (Prototyp) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (nameCommand == null) {
			if (other.nameCommand != null)
				return false;
		} else if (!nameCommand.equals(other.nameCommand))
			return false;
		return true;
	}

	
	
}
