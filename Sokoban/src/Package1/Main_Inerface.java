package Package1;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;

import java.util.Stack;
import javafx.scene.media.AudioClip;
import jdk.internal.util.xml.impl.Input;

import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Pattern;


public class Main_Inerface {
	public static void main(String[] args) throws MalformedURLException {
		new Main_Interface();
	}
}

class Main_Interface extends JFrame implements ActionListener, ItemListener {
	public static JLabel lblStep;
	private JLabel lblStepTitel, lblTitle;
	private JButton btnStopMusic, btnReturn, btnExit, btnRestart, btnLastLevel, btnNextLevel, btnSelect, btnFirstLevel;

	Sound sound;
	Panel1 panel;
	JComboBox jcb;
	JButton jtbExit;
	JMenuBar menuBar;
	JTextField tfInput;
	JMenu menuFile, menuEdit, menuHelp;
	JMenuItem menuRestart, menuLastLevel, menuNextLevel, menuSelectLevel, menuReturn, menuExit, menuAbout, menuCopy, menuCut, menuPaste;

	String[] s = { "1", "2", "3", "4", "5", "6"};
	
	public Main_Interface() throws MalformedURLException{
		super("Sokoban Game");	
		setSize(800, 800);
		setVisible(true);  // Can see the form
		setResizable(false);  // Can not change form size
		setLocation(300, 20);  // Form begin Location
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Click the window's close button
		
		Container cont = getContentPane();
		cont.setLayout(null);
		cont.setBackground(Color.orange);

		panel = new Panel1();
		lblStep = new JLabel("0");
		jcb = new JComboBox(s);
		lblTitle = new JLabel("Sokoban Game");
		lblStepTitel = new JLabel("Steps: ");
		tfInput = new JTextField();
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuHelp = new JMenu("Help");
		menuRestart = new JMenuItem("ReStart");
		menuLastLevel = new JMenuItem("Last Level");
		menuNextLevel = new JMenuItem("Next Level");
		menuSelectLevel = new JMenuItem("Select Level");
		menuReturn = new JMenuItem("Return Steps");
		menuExit = new JMenuItem("Exit Application");
		menuAbout = new JMenuItem("About Sokoban Game");
		menuCopy = new JMenuItem("Copy");
		menuCut = new JMenuItem("Cut");
		menuPaste = new JMenuItem("Paste");
		btnRestart = new JButton("Restart");
		btnReturn = new JButton("Return");
		btnLastLevel = new JButton("Last Level");
		btnNextLevel = new JButton("Next Level");
		btnSelect = new JButton("Select Level");
		btnFirstLevel = new JButton("First Level");
		btnStopMusic = new JButton("Close Music");
		btnExit = new JButton("Exit");
		
		setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
		menuFile.add(menuRestart);
		menuFile.add(menuLastLevel);
		menuFile.add(menuNextLevel);
		menuFile.add(menuSelectLevel);
		menuFile.add(menuReturn);
		menuFile.addSeparator();
		menuFile.add(menuExit);
		menuEdit.add(menuCopy);
		menuEdit.add(menuCut);
		menuEdit.add(menuPaste);
		menuHelp.add(menuAbout);
		
		menuRestart.addActionListener(this);
		menuLastLevel.addActionListener(this);
		menuNextLevel.addActionListener(this);
		menuSelectLevel.addActionListener(this);
		menuExit.addActionListener(this);
		menuReturn.addActionListener(this);
		menuCopy.addActionListener(this);
		menuCut.addActionListener(this);
		menuPaste.addActionListener(this);
		menuAbout.addActionListener(this);
		jcb.addItemListener(this);
		btnRestart.addActionListener(this);
		btnReturn.addActionListener(this);
		btnFirstLevel.addActionListener(this);
		btnLastLevel.addActionListener(this);
		btnNextLevel.addActionListener(this);
		btnSelect.addActionListener(this);
		btnStopMusic.addActionListener(this);
		btnExit.addActionListener(this);
	
		
		// X, Y, L, W;
		jcb.setBounds(130, 40, 50, 30);
	    lblTitle.setBounds(250  , 2, 200, 40);
	    lblStepTitel.setBounds(455, 35, 70, 42);
		lblStep.setBounds(525, 35, 50, 45);
		tfInput.setBounds(190, 40, 70, 30);
		btnExit.setBounds(385, 40, 60, 30);
		btnRestart.setBounds(650, 100, 105, 30);
		btnReturn.setBounds(650, 150, 105, 30);
		btnFirstLevel.setBounds(650, 200, 105, 30);
		btnNextLevel.setBounds(650, 300, 105, 30);
		btnLastLevel.setBounds(650, 250, 105, 30);
		btnStopMusic.setBounds(270, 40, 105, 30);
		btnSelect.setBounds(15, 40, 105, 30);
		lblTitle.setFont(new Font("TIMES NEW ROME", Font.BOLD, 22));
		lblStepTitel.setFont(new Font("TIMES NEW ROME", Font.BOLD, 20));
		lblStep.setFont(new Font("TIMES NEW ROME", Font.BOLD, 20));
		lblStepTitel.setForeground(Color.black);
		lblStep.setForeground(Color.black);

		add(panel, BorderLayout.CENTER);
		add(jcb);
		add(lblTitle);
		add(lblStepTitel);
		add(lblStep);
		add(tfInput);
		add(btnRestart);
		add(btnLastLevel);
		add(btnNextLevel);
		add(btnSelect);
		add(btnFirstLevel);
		add(btnStopMusic);
		add(btnReturn);
		add(btnExit);
		
		sound = new Sound();
		sound.loadSound();
		
		panel.Sokoban(panel.level);
		panel.requestFocus(); // Get focus
		validate(); // Ensure that components have a valid layout
		repaint(); // Clear and refresh
		// Change the level
		SelectLevel();
		}
		
	// Change the level - Pop the windows to select level
	public void SelectLevel() {
		// // if have error, loop
		do {
	    String lel = new String();
		lel = JOptionPane.showInputDialog(this, "Select the game level: 1 ~ 6", "Option",JOptionPane.WARNING_MESSAGE);
		while (!lel.matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "Error£¡Can not be empty and only number!", "Warning", JOptionPane.ERROR_MESSAGE);
			lel = JOptionPane.showInputDialog(this, "Select the game level: 1 ~ 6", "Option",JOptionPane.WARNING_MESSAGE);
		}
		panel.level = Integer.parseInt(lel);
		panel.remove();
		if (panel.level > panel.maxlevel() || panel.level < 1) {
			JOptionPane.showMessageDialog(this, "Error£¡ Retry!", "Warning", JOptionPane.ERROR_MESSAGE);
			panel.requestFocus();
		} 
		else {
			panel.Sokoban(panel.level); // Read the level of map
			panel.requestFocus();
			panel.step = 0;
		    }
		}while(panel.requestFocus(panel.level > panel.maxlevel() || panel.level < 1));
	}
	
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource() == btnRestart || e.getSource() == menuRestart) {
			panel.Sokoban(panel.level);
			panel.requestFocus();
			panel.remove();
			panel.step = 0;
		} 
		
		else if (e.getSource() == btnLastLevel || e.getSource() == menuLastLevel){
			panel.level--;
			if (panel.level < 1) {
				panel.level++;
				JOptionPane.showMessageDialog(this, "This is the first level !");
				panel.requestFocus();
			} 
			else {
				panel.Sokoban(panel.level);
				panel.requestFocus();
			}
			panel.remove();
			panel.step = 0;
		} 
		
		else if (e.getSource() == btnNextLevel || e.getSource() == menuNextLevel){
			panel.level++;
			if (panel.level > panel.maxlevel()) {
				panel.level--;
				JOptionPane.showMessageDialog(this, "This is the final level !");
				panel.requestFocus();
			} 
			else {
				panel.Sokoban(panel.level);
				panel.requestFocus();
			}
			panel.remove();
			panel.step = 0;
		} 
		
		else if (e.getSource() == btnExit || e.getSource() == menuExit) {
			System.exit(0);
		}
					
		else if (e.getSource() == menuAbout) {
			JOptionPane.showMessageDialog(this, "Sokoban Game\nDeveloper: Liu Shangyuan\nEmail: 729461836@qq.com\nQQ: 729461836 "
					+ "\nThis game is operated and controlled with the up, down, left and right arrow keys.");
		} 
		
		else if (e.getSource() == btnSelect || e.getSource() == menuSelectLevel) {
			SelectLevel();
		}

		else if (e.getSource() == btnFirstLevel) {
			panel.level = 1;
			panel.Sokoban(panel.level);
			panel.requestFocus();
			panel.remove();
			panel.step = 0;
		} 	
		
		// Open or close the music
		else if (e.getSource() == btnStopMusic) {
			if (sound.isplay()) {  //Play the music
				sound.mystop();
				btnStopMusic.setLabel("Open Music");
			} 
			else {  //Close the music
				sound.loadSound();
				btnStopMusic.setLabel("Close Music");
			}
			panel.requestFocus();
		}
		
		else if (e.getSource() == btnReturn || e.getSource() == menuReturn){
			if (panel.isMystackEmpty()) {
				JOptionPane.showMessageDialog(this, "Do not move!");
			}
			else {
				switch (panel.back()) {
				case 10:
					panel.backup(10);
					
					break;
				case 11:
					panel.backup(11);
					break;
				case 20:
					panel.backdown(20);
					break;
				case 21:
					panel.backdown(21);
					break;
				case 30:
					panel.backleft(30);
					break;
				case 31:
					panel.backleft(31);
					break;
				case 40:
					panel.backright(40);
					break;
				case 41:
					panel.backright(41);					
					break;
				}
			}
			panel.requestFocus();
		}
	}
		
	public void insertUpdate(Document e) {
		System.out.println(tfInput.getText());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}
