
/**
 * @author Jason Cheng
 * @version 5/4/2023
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConversionHandler implements ActionListener {
	JFrame jframe;

	public ConversionHandler(JFrame jf) {
		jframe = jf;
	}

	/**
	 * Method that when a user clicks on the menu item, an action will happen.
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName = event.getActionCommand();
		if (menuName.equals("Convert Roman Numeral to Arabic"))
			inputRomanNumeral(); //If you click on the menu item Convert Roman Numeral to Arabic, then the inputRomanNumeral method will be initiated. 
		else if (menuName.equals("Quit"))
			System.exit(0);
	}

	/**
	 * Lets the user input a RomanNumeral and convert it to Arabic. If RomanNumeral is invalid, will print the invalid input into console and dialog box.
	 */
	public void inputRomanNumeral() {
		String input = "";
		try {
			input = JOptionPane.showInputDialog("Enter a Roman Numeral(Must be uppercase): "); //Dialog box pops up letting the user input a Roman Numeral.
			int RomanNum = RomanNumeral.romantoint(input); //Converts the roman numeral into arabic and stores it into an integer.
			RomanNumeral input1 = new RomanNumeral(input); //Checks to see if the input is valid.
			JOptionPane.showMessageDialog(null, input + ": " + RomanNum);
		} catch (IllegalRNException e) {
			System.out.println(input + " is not valid. Please try again!"); //If input is invalid, prints out the invalid roman numeral to the console.
			JOptionPane.showMessageDialog(null, input + " is invalid."); //Prints out the invalid roman numeral to the dialog box.
		}

	}
}
