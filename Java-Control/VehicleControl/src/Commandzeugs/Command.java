package Commandzeugs;

import java.io.Serializable;


/**
 * Abstract class Command
 * 
 * Beschreibung:
 * Command ist in der Vererbungshirarchie die oberste KLasse
 * von ihr erben: Movement und Control
 * 
 * ParameterListe:
 * private String name;
 * private int ID;
 * private int stepID;
 * 
 * 
 * @author Markus Friedrich
 *
 */
public abstract class Command implements Serializable{
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		//System.out.println("Command clone");
		return super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int ID;
	private int stepID;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getStepID() {
		return stepID;
	}
	public void setStepID(int stepID) {
		this.stepID = stepID;
	}
	
	/**
	 * Auto generated: wird hat mit equals erstellt
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + stepID;
		return result;
	}
	
	/**
	 * Auto generated:
	 * equals prüft ob ein übergebens objekt gleich ist (true) oder nicht (false)
	 * 
	 * überprüft anhand der referenzen auf beide objekte (this und die übergebene auf object)
	 * (auch ob es das identische Objekt ist also wenn dass aufrufende übergeben wird ^^)
	 * fragt dann von welcher klasse die jeweiligen objekte sind -> gleich oder nicht 
	 * falls ja: überprüft die Klassen Attribute auf Gleichheit
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stepID != other.stepID)
			return false;
		return true;
	}
	
	/**
	 * auto generated:
	 * gibt einen String zurück, mit dem Inhalt:
	 * (klassentyp) KLassenattribute+(Inhalt der jeweiligen Variable)
	 */
	@Override
	public String toString() {
		return "Command [name=" + name + ", ID=" + ID + ", stepID=" + stepID
				+ "]";
	}
	
	
	/**
	 * Konstruktor der Klasse Command
	 * 	
	 * @param name
	 * @param iD
	 * @param stepID
	 */
	public Command(String name, int iD, int stepID) {

		this.name = name;
		ID = iD;
		this.stepID = stepID;
	}
	
	public String getClassType(){
		return "Command";
	}
	
	public String printOwnValues(){
		return (this.name);
		
	}
	
	
	
}
