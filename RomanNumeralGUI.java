import javax.swing.*;
import java.awt.*;

/**
 * Inherits JFrame and makes a constructor to set the title, height, width of the GUI and lastly creates a menuBar where you can choose to open a file or input.   
 * @author Jason Cheng
 * @version 5/4/2023
 */
public class RomanNumeralGUI extends JFrame {

	/**
	 * Constructor to set the title, height, and width of the GUI.
	 */
	public RomanNumeralGUI(String title, int height, int width) {
		setTitle(title); //Sets the string title into setTitle so I can set the title for the GUI in the Project4 file.
		setSize(height, width); //Sets the int height and weight into setSize so I can set the height and width for the GUI in the Project4 file.
		FileConversionMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
/**
 * Creates a GUI menu and adds button File and Open, includes a drop down menu, allows an action to happen after clicking on a button.
 */
	public void FileConversionMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");//Sets the title which is called File. When clicked, a drop down menu will appear. 
		JMenuItem item;//This creates the drop down menu where I can open a file and close the GUI.
		FileMenuHandler fmh = new FileMenuHandler(this);
		item = new JMenuItem("Open");
		item.addActionListener(fmh);//This makes it so when we click on the button, an action will actually happen.
		File.add(item);//Adds the button Open so when you click on File, the button Open will appear below.
		File.addSeparator();//Creates a line between the button Open and Quit
		item = new JMenuItem("Quit");
		item.addActionListener(fmh);
		File.add(item);
		menuBar.add(File);//Puts the File into the menuBar.
		setJMenuBar(menuBar);//Puts the menuBar in the JFrame.
		JMenu Conversion = new JMenu("Conversion");
		ConversionHandler cmh = new ConversionHandler(this);
		item = new JMenuItem("Convert Roman Numeral to Arabic");
		item.addActionListener(cmh);
		Conversion.add(item);
		menuBar.add(Conversion);//Puts Conversion next to File in the menuBar.
	}
}