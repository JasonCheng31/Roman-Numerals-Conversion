import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Jason Cheng
 * @version 5/4/2023
 */
public class FileMenuHandler implements ActionListener {
	JFrame jframe;
	TextArea SortedRoman;
	TextArea SortedArabic;

	/**
	 * Sets a textarea in the constructor so each time when opening a file, it won't make a new textarea.
	 * @param jf
	 */
	public FileMenuHandler(JFrame jf) {
		jframe = jf;
		Container myContentPane = jframe.getContentPane();
		jframe.setLayout(new GridLayout(0, 3)); //Set rows and columns
		SortedRoman = new TextArea(); //To put the roman numeral from a text file into the first column. I put the text area here so every time we open a file without stopping, it gets rid of the old columns and puts in a new column with text. 
		SortedArabic = new TextArea();
		myContentPane.add(SortedRoman);
		myContentPane.add(SortedArabic);
	}

	/**
	 * Method that when you press on a button that has the word Open or Quit, then a action will happen.
	 * @param event
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName = event.getActionCommand();
		if (menuName.equals("Open"))
			openFile(); //When you click on the menu item Open, the openFile method will be initiated.
		else if (menuName.equals("Quit"))
			System.exit(0);
	}

	/**
	 * Method that allows you to choose a file, then open and read the file with the method readSource.
	 */
	public void openFile() {
		int status;
		JFileChooser chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			readSource(chooser.getSelectedFile()); //When a file is chosen, the method readSource is initiated.
		} else
			JOptionPane.showMessageDialog(null, "Open File dialog is canceled");
	}

	/**
	 * Reads the chosen file and puts the Roman Numeral and Arabic into the GUI through a textarea in seperate columns, an exception will occur if there's an invalid Roman Numeral.
	 * @param chosenFile
	 */
	public void readSource(File chosenFile) {
		String chosenFileName = chosenFile.getAbsolutePath();
		TextFileInput inFile = new TextFileInput(chosenFileName);
		String Roman;
		SortedRoman.setText("Sorted Roman Numeral\n\n"); // Every time we open a new file, the first line is set to be Roman Numeral and then places in texts beneath the word Roman Numeral. This prevents us from having multiple text file in the GUI.
		SortedArabic.setText("Sorted Arabic \n\n");
		Roman = inFile.readLine(); //Reads each line.
		String temp = ""; //To hold each RomanNumeral one at a time and if it's invalid, this String is used to print to the console.
		TreeMap<RomanNumeral, Integer> treeMap = new TreeMap<RomanNumeral,Integer>(new RomanNumeral());//Create a TreeMap to sort the Roman Numeral and Arabic.
		while (Roman != null) {
			String[] linesplit = Roman.split(",");
			int[] Arabic = new int[linesplit.length];
			// Gets rid of the comma and separate the value into a new row. Stores the value into a linkedlist.
			for (int i = 0; i < linesplit.length; i++) {
				temp = linesplit[i];
				try {//Tries to put the int value into an Arabic array. Tries to put the Arabic and linesplit array into a treemap. If there's an invalid input, print out that invalid input into console.
					Arabic[i] = RomanNumeral.romantoint(linesplit[i]); //Stores each int into an Arabic array.
					treeMap.put(new RomanNumeral(linesplit[i]), Arabic[i]); //Puts the Arabic and linesplit array into a treemap.
				} catch (IllegalRNException e) {
					System.out.println(temp + " is invalid");
				}
			}

			Roman = inFile.readLine();
		}
		//For loop that iterates through each element in the treemap and append the sorted Roman Numeral and Arabic into a GUI with a Roman Numeral object called rn.
		for(RomanNumeral rn: treeMap.keySet()) { 
			SortedRoman.append(rn.getRoman() + "\n");
			SortedArabic.append(rn.getArabic() + "\n");
		}
		

		inFile.close();

		jframe.setVisible(true);

	}
}