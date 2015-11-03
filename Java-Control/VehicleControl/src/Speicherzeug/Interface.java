package Speicherzeug;

import java.util.Properties;

public interface Interface {
	public Properties laden(String verzeichnis);
	public void speichern(Properties daten, String verzeichnis);
}
