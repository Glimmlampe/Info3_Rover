package Commandzeugs;

/**
 * Klasse Pause (extends Control)
 * 
 * Beschriebung:
 * objekt enthält als parameter eine zeitangabe
 * objekte von pasue sind controlcommands...
 * 
 * Parameterliest:
 * private double duration
 * 
 * @author Markus Friedrich
 *
 */
public class Pause extends Control {

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Pause(this.getName(), this.getID(), this.getStepID(), this.getDuration());
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

	private double duration;

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(duration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * equals für pause
	 * für beschreibung siehe equals von command 
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
		Pause other = (Pause) obj;
		if (Double.doubleToLongBits(duration) != Double
				.doubleToLongBits(other.duration))
			return false;
		return true;
	}

	/**
	 * gibt einen string zurück mit:
	 * (Klassentyp)+ Klassenattribut mit zugeörigem Inhalt
	 * 
	 */
	@Override
	public String toString() {
		return super.toString()+"Pause [duration=" + duration + "]";
	}

	/**
	 * Konstriktor für Pause
	 * initialisiert:
	 * private double duration;
	 * + Alle elemente der Superclasses
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 * @param duration
	 */
	public Pause(String name, int iD, int stepID, double duration) {
		super(name, iD, stepID);
		this.duration = duration;
	}

	@Override
	public String getClassType(){
		return "Pause";
	}
	
	@Override
	public String printOwnValues(){
		return (this.duration+ " s");
		
	}
	
	
}
