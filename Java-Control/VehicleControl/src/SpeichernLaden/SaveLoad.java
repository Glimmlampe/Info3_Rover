package SpeichernLaden;

import java.io.File;
import Proto_ZV.Zentralverwaltung;


/**Interface dass die Speichern und lAden Methoden zur Verfügung stellt.
 * Gespeichert werden Serialisierte Objekte. in diesem Fall Vektoren
 * 
 * @author Markus Friedrich
 *
 */
public interface SaveLoad {
	  public boolean load( File f, Zentralverwaltung Z );
	  public boolean save( File f, Zentralverwaltung Z );
}
