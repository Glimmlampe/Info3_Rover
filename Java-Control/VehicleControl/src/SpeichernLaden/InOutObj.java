package SpeichernLaden;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;

import Commandzeugs.Command;
import Proto_ZV.Zentralverwaltung;



/**InOutObj serialisiert die ihr übergebenen Objekte um diese dann zu Speichern bzw. zu Laden
 * (mittels Interface SaveLoad).
 * Die zu Speichernden Objekte müssen das Interface Seriazible implementiert haben (Flag-Interface ohne Methoden)
 * 
 * @author Markus Friedrich
 *
 */
public class InOutObj implements SaveLoad{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean load(File f, Zentralverwaltung Z) {
		if ( f==null )
		      return false;
		          
		    try {
		      // FileInputStream repräsentiert einen strom von bytes. ObjectInputStream 
		      // deserialisiert die vorab mittels der klasse ObjectOutputStream geschriebenen 
		      // objekte.
		      ObjectInputStream oIS= new ObjectInputStream( new FileInputStream( f ) );
		      
		      Object obj= null;
		      // lese ein objekt nach dem anderen aus dem inputstream. das letzte 
		      // object, welches gelesen wird, ist null. dieses muss allerdings explizit
		      // geschrieben worden sein; andernfalls wird eine EOFException geworfen.
		      while ( (obj= oIS.readObject()) != null )
		        // da das geschriebene objekt ein Vektor-objekt war, wird dieses auf die 
		        // klasse Vektor gecastet und in den Command Vektor geschrieben.
		        Z.setControl((Vector<Command>) obj);

		      oIS.close();
		    } catch (Exception e) {
		      return false;
		    }
		    
		    return true;
	}

	@Override
	public boolean save(File f, Zentralverwaltung Z) {
	    if ( f==null )
	        return false; 
	            
	      try {
	        // FileOutputStream repräsentiert einen strom von bytes. ObjectOutputStream 
	        // serialisiert objekte und schreibt diese in den outputstream.
	        ObjectOutputStream oOS= new ObjectOutputStream( new FileOutputStream( f ) );
	        
	        // der zu schreibende Vektor wird aus Zentralverwaltung gelesen und 
	        // als Vektor<Command> obj in eine Datei geschrieben (Boxing).
	          oOS.writeObject( Z.getControl() );
	        
	        // als letztes objekt wird "null" geschrieben, damit beim einlesen das
	        // dateiende erkannt wird. 
	        oOS.writeObject( null );
	      
	        // schreibe eventuell noch gepufferte daten. 
	        oOS.flush();
	        oOS.close();
	      } catch (Exception e){
	        return false;
	      }
	      
	      return true;
	}

	
	
	
	
}
