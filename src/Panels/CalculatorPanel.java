/*
 * Author : Bryan Spahr
 */

/*
 * Panel that represents a calculator and displayed over the HomeFrame
 */

package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import buttons.ButtonCalculator;
import photo.Photo;

public class CalculatorPanel extends JPanel implements ActionListener {

	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel buttonsContainer = new JPanel();

	// Wallpaper
	private Photo wallpaper = new Photo("./src/Pictures/wallpaper.jpg");

	// Result
	private JTextField result;

	// Buttons
	private ButtonCalculator buttons[] = new ButtonCalculator[16];
	private String stringButtons[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "/", "*", "=", "C" };
	private String pathButtons[] = new String[16];

	// Strings on screen
	private String mainScreen = "";
	private String screen1 = "", screen2 = "", OpsOnScreen = "";

	// Booleans
	private boolean empty = true, switcher = true;

	// Min values
	private double R = Integer.MIN_VALUE, L = Integer.MIN_VALUE;

	// Dimensions
	private Dimension dimensionTextField = new Dimension(420, 110);
	private Dimension dimensionmainPanel = new Dimension(420, 0);

	// Font
	private Font fontTextField = new Font("Arial", Font.BOLD, 47);

	// Constructor
	public CalculatorPanel() {

		/*
		 * Fill the array tab with path of the pictures for the buttons
		 */
		pathButtons = fillPath();

		// Settings for the result JTextfield (size, font, color, etc)
		result = new JTextField();
		result.setEditable(false);
		result.setBackground(Color.BLUE);
		result.setForeground(Color.WHITE);
		result.setPreferredSize(dimensionTextField);
		result.setFont(fontTextField);
		result.setBorder(new EmptyBorder(20, 0, 20, 0));

		/*
		 * Add buttons to buttonsContainer (in GridLayout) and add them
		 * ActionListener
		 */
		buttonsContainer.setLayout(new GridLayout(4, 4, 10, 10));
		for (int i = 0; i < 16; i++) {
			buttons[i] = new ButtonCalculator(new Photo(pathButtons[i]));
			buttons[i].addActionListener(this);
			buttonsContainer.add(buttons[i]);
		}

		// Set the buttonsContainer and the mainPanel non-opaque
		buttonsContainer.setOpaque(false);
		mainPanel.setOpaque(false);

		// Add the buttonsContainer to mainPanel
		mainPanel.add(buttonsContainer);

		// Add the result JTextfield and the mainPanel to the panel
		add(result);
		add(mainPanel);

		/*
		 * Settings of the JPanel (background color (in case the image is
		 * missing for example) and the visibility
		 */
		setBackground(Color.BLACK);
		setVisible(true);

	}

	// Paint the background with the wallpaper
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = wallpaper.getImage();
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();

		double imageWidth = img.getWidth(this);
		double imageHeight = img.getHeight(this);

		g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) imageWidth,
				(int) imageHeight, this);

	}

	// Method that fills the String array with the path of the pictures
	String[] fillPath() {

		String temp[] = new String[16];
		File folder = new File("./src/PicturesCalculator");
		File pics[] = folder.listFiles();

		for (int i = 0; i < pics.length; i++)
			temp[i] = "./src/PicturesCalculator/" + pics[i].getName();

		return temp;
	}

	// What happens when a button is pressed (ActionListener)
	@Override
	public void actionPerformed(ActionEvent event) {

		// for the num 0 to 9
		for (int i = 0; i <= 9; i++) {
			if (event.getSource() == buttons[i]) {
				mainScreen += i;
				result.setText("");
				result.setText(mainScreen);
			}
		}

		// for the operators
		for (int i = 10; i <= 14; i++) {
			if (event.getSource() == buttons[i]) {
				if (result.getText().lastIndexOf(OpsOnScreen) != -1)

					result.setText(result.getText().substring(0, result.getText().lastIndexOf(OpsOnScreen))
							+ stringButtons[i]);
				else
					result.setText(result.getText() + stringButtons[i]);
				OpsOnScreen = stringButtons[i];

				if (switcher) {
					screen1 = stringButtons[i];
					switcher = false;
				} else {
					screen2 = stringButtons[i];
					switcher = true;
				}

				if (screen1 != screen2 && screen2 != "") {
					if (switcher) {
						calculate(event, screen1.charAt(0), screen2);
					} else {
						calculate(event, screen2.charAt(0), screen1);
					}
				}
				if (stringButtons[i] != "=")
					calculate(event, stringButtons[i].charAt(0), stringButtons[i]);
			}
		}

		/*
		 * in case the button pressed is the "clear" button, clear the screen
		 * and reinitialize the variables
		 */
		if (event.getSource() == buttons[15]) {
			mainScreen = "";
			screen1 = "";
			screen2 = "";
			switcher = true;
			empty = true;
			result.setText("");
		}

	}

	// Method that verifies the inputs/variables of the operations/calculs
	public void calculate(ActionEvent event, char OpType, String Operator) {

		if (Operator == "=")
			Operator = "";

		if (empty && mainScreen == "") {
			return;
		}

		else if (empty && mainScreen != "") {
			R = Integer.parseInt(mainScreen);
			result.setText(mainScreen + Operator);
			mainScreen = "";
			empty = false;

		} else if (!empty && mainScreen != "") {
			L = Integer.parseInt(mainScreen);
			R = operation(R, L, OpType); // does the operation/calcul
			mainScreen = "";
			result.setText("");
			result.setText(R + Operator);
		}
	}

	// Method that actually does the calcul/operation and return the result
	public static double operation(double R, double L, char op) {
		switch (op) {
		case '+':
			return R + L;
		case '-':
			return R - L;
		case '*':
			return R * L;
		case '/':
			return R / L;
		}
		return 0;
	}

}
