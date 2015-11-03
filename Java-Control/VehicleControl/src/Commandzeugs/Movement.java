package Commandzeugs;

/**
 * Klasse Movement (erbt von Command)
 * 
 * Parameter: private int HwAdress
 * 
 * @author markus friedrich
 * 
 */
public abstract class Movement extends Command {

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

	private int HwAdress;

	public int getHwAdress() {
		return HwAdress;
	}

	public void setHwAdress(int hwAdress) {
		HwAdress = hwAdress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + HwAdress;
		return result;
	}

	/**
	 * equals für KLasse Movement equals beschreibung siehe: equals von Command
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
		Movement other = (Movement) obj;
		if (HwAdress != other.HwAdress)
			return false;
		return true;
	}

	/**
	 * Konstruktor erfordert auch die intialisierung der superklassen-Attribute
	 * 
	 * @param name
	 * @param iD
	 * @param stepID
	 * @param hwAdress
	 */
	public Movement(String name, int iD, int stepID, int hwAdress) {
		super(name, iD, stepID);
		HwAdress = hwAdress;
	}

	/**
	 * toString für Movement gibt einen string zurück in der der Klassentyp
	 * steht und dahinter die klassenvariablen
	 */
	@Override
	public String toString() {
		return super.toString() + "Movement [HwAdress=" + HwAdress + "]";
	}

	
	@Override
	public String getClassType(){
		return "Movement";
	}
	
	@Override
	public String printOwnValues(){
		return (this.HwAdress + " HwAddr.");
		
	}
	
	
}
