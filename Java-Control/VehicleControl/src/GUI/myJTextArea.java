package GUI;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;


/**erzeugt mir ein Jtextarea und konfiguriert deses. es Soll in der Gui als  Ausgabefenster fungieren.
 * 
 * @author Markus Friedrich
 *
 */
@SuppressWarnings("serial")
public class myJTextArea extends JTextArea {

	private JTextArea tA;
	private JScrollPane tAScroll;

	
	public myJTextArea(){
		// neues textarea mit " " als text und 4 reihen und 1 spalte
		this.tA = new JTextArea(); //(5,80);
		//this.tA.setBackground(Color.BLACK);
		//this.tA.setForeground(Color.CYAN);
		this.tA.setEditable(false);
		this.tA.setLineWrap(true);
		this.tA.setWrapStyleWord(true);
		this.tA.setSelectedTextColor(Color.GREEN);
		this.tA.setSelectionColor(Color.BLACK);
		this.tA.setTabSize(5);
		
		// sorgt dafür dass immer aktuellste zeile gezeigt wird 
		DefaultCaret caret = (DefaultCaret)this.tA.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		
		// adde das flowlayout in eine Scrollbar
		this.tAScroll = new JScrollPane(tA, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//this.tAScroll.setBackground(Color.BLACK);
		this.tAScroll.setBorder(null);
		this.tA.setLineWrap(true);
		
		
/*	
		JViewport body = new JViewport();
		body.setView(tA);
		body.setViewSize(new Dimension(3000, 3000));

		this.tAScroll.setViewportView(body);
*/		
	}
	
	
	

	
	public JTextArea getMyTextArea(){
		return this.tA;
	}
	
	public JScrollPane getMyScrollText(){
		return this.tAScroll;
	}
	
	
	/**Diese Methode ist eine synchronisierte Version von Jtextarea.append(), ergo sie sollte immer dann (statt dem normalen append() ) genutzt werden,
	 * wenn sie von parrallel laufenden threads aufgerufen wird, oder man sich nicht sicher ist ob noch ein anderer Prozess (thread) zugriff auf ein Textarea hat
	 * Parameter String s ist in dem Fall das was mittels append() angehängt werden soll!
	 * 
	 * @param s
	 * 
	 * @author Markus Friedrich
	 */
	public  synchronized void syncAppend(String s){
	
		this.tA.append(s);
		
	}
	
	
	
	
	
}
