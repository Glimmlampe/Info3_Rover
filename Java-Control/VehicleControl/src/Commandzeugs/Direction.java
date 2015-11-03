package Commandzeugs;



/**
 * Klasse Direction (extends Movement)
 * 
 * KLasse für Richtungen in Grad erbt von Movement
 * 
 * Parameter:
 * Private int Degree;
 * 
 * @author Markus friedrich
 *
 */
public class Direction extends Movement{

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Direction(this.getName(), this.getID(), this.getStepID(), this.getHwAdress(), this.getDegree());
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

	private int degree;

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + degree;
		return result;
	}
	/**
	 * equals für Direction
	 * für euals beschreiung siehe: equals von Command
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direction other = (Direction) obj;
		if (degree != other.degree)
			return false;
		return true;
	}

	/**
	 * toString von Direction
	 * gibt einen String zurück mit:
	 * (Klassentyp)+ Klassenattribut mit zugeörigem Inhalt
	 * 
	 */
	@Override
	public String toString() {
		return super.toString()+"Direction [degree=" + degree + "]";
	}

	/**
	 * Konstruktor für Direction
	 * intitalsiert degree und alle Attribute der Superclasses von direction 
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 * @param hwAdress
	 * @param degree
	 */
	public Direction(String name, int iD, int stepID, int hwAdress, int degree) {
		super(name, iD, stepID, hwAdress);
		this.degree = degree;
	}

	@Override
	public String getClassType(){
		return "Direction";
	}
	
	@Override
	public String printOwnValues(){
		return (this.degree + " °");
		
	}
	
	
	
	
}
