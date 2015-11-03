package Commandzeugs;

public class Gear extends Movement {

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Gear(this.getName(), this.getID(), this.getStepID(), this.getHwAdress(), this.getSpeed(), this.getDuration());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double speed;
	private double duration;
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
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
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
		
	/**
	 * equals für Gear
	 * für Equals beschreibung siehe Equals von Command
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
		Gear other = (Gear) obj;
		if (Double.doubleToLongBits(duration) != Double
				.doubleToLongBits(other.duration))
			return false;
		if (Double.doubleToLongBits(speed) != Double
				.doubleToLongBits(other.speed))
			return false;
		return true;
	}

	
	/**
	 * toString von Gear
	 * returned einen String mit Inhalt:
	 * (Klassentyp)+ Klassenattribut mit zugeörigem Inhalt
	 * 
	 */
	@Override
	public String toString() {
		return super.toString()+"Gear [speed=" + speed + ", duration=" + duration + "]";
	}

	

	/**
	 * Konstruktor für Gear
	 * 
	 * intialiesiert double Speed und double duration
	 * + alle KlassenAtribute der Superclasses
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 * @param hwAdress
	 * @param speed
	 * @param duration
	 */
	public Gear(String name, int iD, int stepID, int hwAdress, double speed,
			double duration) {
		super(name, iD, stepID, hwAdress);
		this.speed = speed;
		this.duration = duration;
	}

	@Override
	public String getClassType(){
		return "Gear";
	}
	
	@Override
	public String printOwnValues(){
		return (this.speed + " cm/s | " + this.duration + " s" );
		
	}
	
	
	
}
