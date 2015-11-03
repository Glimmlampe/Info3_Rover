package Speicherzeug;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;


public class Serialisieren implements Interface{

	ObjectOutputStream out;
	ObjectInputStream in;
	Properties daten;
	
	@SuppressWarnings("finally")
	@Override
	public Properties laden(String verzeichnis) {
		try {
			if (verzeichnis==null)
				verzeichnis="objects.ser";
			in=new ObjectInputStream(new FileInputStream(verzeichnis));
			daten=(Properties) in.readObject();
			//System.out.println("Erfolgreich Serialisierte Datei geladen.");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			return daten;
		}
	}

	@Override
	public void speichern(Properties daten, String verzeichnis) {
		try {
			if (verzeichnis==null)
				verzeichnis="objects.ser";
			open(verzeichnis);
			out.writeObject(daten);
			//System.out.println("Daten wurden erfolgreich Serialisiert.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
				
	}

	public void open(String verzeichnis) {
		try {
			this.out=new ObjectOutputStream(new FileOutputStream(verzeichnis));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void close() {
		try{
			out.close();
		}
		catch (Exception e){
			System.out.println("Fehler beim schlie?en. "+e);
		}
		
	}

}
