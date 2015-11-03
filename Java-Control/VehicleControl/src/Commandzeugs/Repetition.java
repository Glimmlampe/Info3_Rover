package Commandzeugs;

/**
 * Klasse Repetition (extends Control)
 * 
 * Beschreibung:
 * ???
 * 
 * Parameter:
 * private int JumpAdress;
 * private int NrRepetitions;
 * 
 * @author Markus friedrich
 *
 */
public class Repetition extends Control {

	@Override
	public Object clone() throws CloneNotSupportedException {
		//System.out.println("Repetition clone");
		return new Repetition(this.getName(), this.getID(), this.getStepID(), this.getJumpAdress(), this.getNrRepetitions());//super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	private int jumpAdress;
	private int NrRepetitions;




		@Override
		public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + NrRepetitions;
		result = prime * result + jumpAdress;
		return result;
	}
		
	/**
	 * equals für Repetition
	 * Beschriebeung: siehe Equals von command
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repetition other = (Repetition) obj;
		if (NrRepetitions != other.NrRepetitions)
			return false;
		if (jumpAdress != other.jumpAdress)
			return false;
		return true;
	}

	/**
	 * toString von repetition
	 * returned einen String mit Inhalt:
	 * (Klassentyp)+ Klassenattribut mit zugeörigem Inhalt
	 * 
	 */
	@Override
	public String toString() {
		return super.toString()+"Repetition [jumpAdress=" + jumpAdress + ", NrRepetitions="
				+ NrRepetitions + "]";
	}



	/**
	 *  Konstruktor für Gear
	 * 
	 * intialisiert: int jumpAdress; intNrRepetitions;
	 * + alle KlassenAttribute der Superclasses
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 * @param jumpAdress
	 * @param nrRepetitions
	 */
	public Repetition(String name, int iD, int stepID, int jumpAdress,
			int nrRepetitions) {
		super(name, iD, stepID);
		this.jumpAdress = jumpAdress;
		NrRepetitions = nrRepetitions;
	}




	
	@Override
	public String getClassType(){
		return "Repetition";
	}
	
	
	@Override
	public String printOwnValues(){
		return (this.jumpAdress + " jAddr | " + this.NrRepetitions + " x");
		
	}

	public int getNrRepetitions() {
		// TODO Auto-generated method stub
		return this.NrRepetitions;
	}

	public void setNrRepetitions(int i) {
		this.NrRepetitions = i;
		
	}

	public int getJumpAdress() {
		// TODO Auto-generated method stub
		return this.jumpAdress;
	}
	
	
	
}
