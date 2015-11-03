package Proto_ZV;
import Commandzeugs.*;
import Speicherzeug.*;

import java.util.Properties;
import java.util.Vector;



/**Zentralverwaltung ist eine klasse zur verwaltung zweier Vektoren.
 * von Zentralverwaltung kann man nur 1 Objekt Bilden (singelton pattern)
 * Vektor(Prototyp) und Vektor(controlProcess)
 * Zusätzlich ist es möglich die beiden Vektorlisten zu speichern und zu Laden
 * (gespeichert werden die objekte im aktuellen Verzeichniss!)
 * 
 * 
 * @author Markus Friedrich
 *
 */
public class Zentralverwaltung implements Interface {
	
	private static Zentralverwaltung single=null;		// kennt sich selbst ^^ muss sein wegen singelton
	private Vector<Prototyp> proto;						// vektor für prototypen
	private Vector<Command> controlProcess;				// vektor für Controlprocesses
	private Properties p;								// nur wegem speichern
	private String path="./Saves/Test.txt";								// speicherpfad
	

	/**zugriff von außen durch diese Methode
	 * (erzeugung durch klassenname.getInstance())
	 * falls unique == null wird das Obj erzeugt falls schon vorhanden wird referenz darauf zurückgegeben!
	 * 
	 * @return liefert referenz auf das ZentralverwaltungsObjekt zurück
	 */
	public static Zentralverwaltung getInstance(){
		if(single==null)
			single = new Zentralverwaltung();			// nutzt den private Konstruktor hier
		return single;
		
	}
	
	/**befüllt den vektor proto mit allen möglichen Prototyp-Objekten
	 * 
	 */
	public void createPrototypen(){
		//sorge dafür dass er leer ist
		proto.clear();
		//füge alle möglichen Prototypen hinzu die es gibt!
		proto.add(new Prototyp("Gerade aus","Gear"));
		proto.add(new Prototyp("Kurve","Direction"));
		proto.add(new Prototyp("Pause","Pause"));
		proto.add(new Prototyp("Repetition","Repetition"));
		
	}
	
	/**erstellt aus einem Prototyp das dazu Passende command obj 
	 * und fügt es hinten an den Vector Control an.
	 * falls erzeugung nicht Möglich war macht diese Methode nur eine System.out.prinln
	 * 
	 * @param p = prototypObj
	 */
	public void addStep(Prototyp p){
		//erzeuge aus p ein Command und füge es hinten an dden Command Vector an
		Command help = p.createInstance();
		// mach nur weiter wenn help != null ist
		if(help!=null){
			//setze das commandObj in den Vector ein
			controlProcess.add(help);
			
		}
		else
			System.out.println("Konnte aus dem Prototyp kein Command erzeugen!");
		
	}
	
	/**löscht ein Element aus dem Vektor controlProcess.
	 * 
	 * falls id zu groß löscht er letztes Element.
	 * falls id zu klein löscht er erstes Element.
	 * 
	 * @param iD	= löscht elemnt an dieser Position
	 */
	public void removeStep(int iD){
		int x = iD;
		//prüfe ob iD elemente überhaupt in vorhanden sind (falls nein setze iD = size() und lösche letztes Element)
		if(x>=controlProcess.size()-1)
			x=controlProcess.size()-1;
		// prüfe ob iD <= 0 falls ja lösche erstes Element!
		if(x<=0)
			x=0;
		//lösche nun das Elemt aus dm Vektor
		controlProcess.removeElementAt(x);
	
	}
	
	/**verschiebt ein Element im vector controlProcess um 1 Position nach Vorne
	 * falls iD <= 0 ist wird nichts gemacht.
	 * falls iD >= maxElements ist setze verschiebe das letze Element
	 * 
	 * @param iD = index, welches objekt im vektor nach vorne soll
	 */
	public void incOrder(int iD){
		int x = iD;
		//prüfe ob bereits erstes element falls ja mach nix sonst verschieben
		if(x<=0);
		// wenn id größer als vektor ist setze id auf maxElements und schiebe um 1 nach vorne
		else if(x>=controlProcess.size()-1){
			x=controlProcess.size()-1;
			Command help=controlProcess.elementAt(x);
			controlProcess.setElementAt(controlProcess.elementAt(x-1), x);
			controlProcess.setElementAt(help, x-1);
		}	
		// schiebe den Befehl um 1 nach Vorne	
		else{
			Command help=controlProcess.elementAt(x);
			controlProcess.setElementAt(controlProcess.elementAt(x-1), x);
			controlProcess.setElementAt(help, x-1);
		}
		
	}
	
	/**verschiebt ein Element im vector controlProcess um 1 Position nach Vorne
	 * falls iD <= 0 ist wird erstes Element nach hinten geschoben.
	 * falls iD >= maxElements ist wird nichts gemacht.
	 * 
	 * @param iD = index, welches objekt im vektor nach vorne soll
	 */
	public void decOrder(int iD){
		int x = iD;
		//prüfe ob iD <= 0 falls ja schiebe erstes Element nach hinten
		if(x<=0){
			x = 0;
			Command help=controlProcess.elementAt(x);
			controlProcess.setElementAt(controlProcess.elementAt(x+1), x);
			controlProcess.setElementAt(help, x+1);
		}
		//prüfe ob id>= letzes element falls ja mach nix
		else if(x>=controlProcess.size()-1){}
		else{
			Command help=controlProcess.elementAt(x);
			controlProcess.setElementAt(controlProcess.elementAt(x+1), x);
			controlProcess.setElementAt(help, x+1);
		}
	}
	
	
	
	
	
	
	/**
	 * Konstruktor muss private sein wegen Singelton!
	 * somit keine Klassenbildung nach außen möglich
	 * setzt die beiden leeren vektoren!
	 */
	private Zentralverwaltung(){
		this.setControl(new Vector<Command>(0));
		this.setProto(new Vector<Prototyp>(0));
		this.p = new Properties();
		this.createPrototypen();
	}
	
	

	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public Vector<Prototyp> getProto() {
		return proto;
	}


	public void setProto(Vector<Prototyp> proto) {
		this.proto = proto;
	}


	public Vector<Command> getControl() {
		return controlProcess;
	}


	public void setControl(Vector<Command> control) {
		this.controlProcess = control;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((controlProcess == null) ? 0 : controlProcess.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((proto == null) ? 0 : proto.hashCode());
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
		Zentralverwaltung other = (Zentralverwaltung) obj;
		if (controlProcess == null) {
			if (other.controlProcess != null)
				return false;
		} else if (!controlProcess.equals(other.controlProcess))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (proto == null) {
			if (other.proto != null)
				return false;
		} else if (!proto.equals(other.proto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Zentralverwaltung [proto=" + proto + ", controlProcess="
				+ controlProcess + "]";
	}

	@SuppressWarnings("unchecked")	//wegen warnung kp was das heißt
	@Override
	public Properties laden(String verzeichnis) {
		Properties daten = new Properties();
		Serialisieren ser = new Serialisieren();
		daten = ser.laden(verzeichnis);
		controlProcess = (Vector<Command>) daten.get("controlProcess");
		this.createPrototypen();
		return null;
	}

	@Override
	public void speichern(Properties daten, String verzeichnis) {
		daten = new Properties();
		daten.put("controlProcess", controlProcess);
		Serialisieren ser= new Serialisieren();
		ser.speichern(daten, verzeichnis);
				
	}
	
	public String printCommandV(){
		return controlProcess.toString();
	}

	public  String printCurrentCommand(int x){
		String s;
		s = getControl().elementAt(x).getName() + getControl().elementAt(x).printOwnValues();
		return s;
	}
	
}
