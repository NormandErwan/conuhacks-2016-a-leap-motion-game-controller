import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Interface extends JFrame {
	
	private JFrame frame = new JFrame();
	private JPanel mainPanel = new JPanel();
	private String pathGame;
	private char[] tabKey;
	private int nbrGestures;
	private JTextField varGame[];
	private JTextField JTnbrGesture;
	private JButton[] button;

	private PoseManager poseManager;
	private GameRobot gameRobot;

	Interface(PoseManager poseManager, GameRobot gameRobot) {
		this.poseManager = poseManager;
		this.gameRobot = gameRobot;

		top();
		bot();
		initUI();
	}

	/**
	 * Initializes the main properties of the game window
	 */
	public void initUI() {
		frame.setTitle("Réglage du LeapMotion pour le jeu");
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void top(){
		JPanel topPanel = new JPanel();
		frame.add(topPanel, BorderLayout.NORTH);
		JTnbrGesture = new JTextField("Nombre Gesture");
		topPanel.add(JTnbrGesture);

		JTnbrGesture.addActionListener(new listenerNbrGesture());
	}

	public class listenerNbrGesture implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nbrGestures = JTnbrGesture.getText().charAt(0)-48; //ascii
			System.out.println(nbrGestures);
			mid();
			//tabKey[0]= varGame.getText().charAt(0);
		}
	}

	public void mid(){
		button = new JButton[nbrGestures];
		JFrame frameGesture = new JFrame("Gesture");

		varGame = new JTextField[nbrGestures];
		frameGesture.setSize(1000, 600);
		frameGesture.setLocationRelativeTo(null);
		frameGesture.setVisible(true);
		JPanel midPanel = new JPanel();
		frameGesture.add(midPanel);
		GridLayout midGridLayout = new GridLayout(nbrGestures,2);
		midPanel.setLayout(midGridLayout);
		midPanel.setPreferredSize(new Dimension(100, 50));

		for(Integer i=0; i<nbrGestures; i++){
			varGame[i] = new JTextField();
			button[i] = new JButton("Gesture "+ i.toString());
			midPanel.add(varGame[i]);
			midPanel.add(button[i]);
			button[i].addActionListener(new listenerChoiceGesture());
		}
	}

	public class listenerChoiceGesture implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int g = 0; g<nbrGestures;g++)
			{
				if(e.getSource() == button[g])
				{
					poseManager.prepareNewPose(varGame[g].getText().charAt(0));
				}
			}
		}

	}

	public void bot(){
		JPanel botPanel = new JPanel();
		JButton jouer = new JButton("Jouer");
		JButton choixJeu = new JButton("Choix jeu");
		frame.add(mainPanel);
		mainPanel.add(botPanel, BorderLayout.SOUTH);
		botPanel.add(choixJeu);
		botPanel.add(jouer);
		jouer.addActionListener(new listenerJouer());
		choixJeu.addActionListener(new listenerChoiceOfTheGame());
	}

	public class listenerChoiceOfTheGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				pathGame = file.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public class listenerJouer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				gameRobot.launch(pathGame);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
