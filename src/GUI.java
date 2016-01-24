import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = -8128013606004312201L;
	
	private JFrame frame = new JFrame();
	private JPanel mainPanel = new JPanel();
	private JTextField varGame[];
	private JTextField JTnbrPose;
	private JButton[] button;
	
	private String pathGame;
	private int nbrPoses;

	private PosesManager posesManager;
	private GameRobot gameRobot;

	GUI(PosesManager posesManager, GameRobot gameRobot) {
		this.posesManager = posesManager;
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
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void top() {
		JPanel topPanel = new JPanel();
		frame.add(topPanel, BorderLayout.NORTH);
		JTnbrPose = new JTextField("Nombre Pose");
		topPanel.add(JTnbrPose);

		JTnbrPose.addActionListener(e -> {
			nbrPoses = Integer.valueOf(JTnbrPose.getText());
			System.out.println(nbrPoses);
			mid();
		});
	}

	public void mid() {
		button = new JButton[nbrPoses];
		JFrame framePose = new JFrame("Pose");

		varGame = new JTextField[nbrPoses];
		framePose.setSize(1000, 600);
		framePose.setLocationRelativeTo(null);
		framePose.setVisible(true);
		JPanel midPanel = new JPanel();
		framePose.add(midPanel);
		GridLayout midGridLayout = new GridLayout(nbrPoses,2);
		midPanel.setLayout(midGridLayout);
		midPanel.setPreferredSize(new Dimension(100, 50));

		for(int i = 0; i < nbrPoses; i++) {
			varGame[i] = new JTextField();
			button[i] = new JButton("Pose " + String.valueOf(i));
			midPanel.add(varGame[i]);
			midPanel.add(button[i]);
			button[i].addActionListener(e -> {
				for(int j = 0; j < nbrPoses; j++)
				{
					if(e.getSource() == button[j])
					{
						posesManager.prepareNewPose(varGame[j].getText().charAt(0));
					}
				}
			});
		}
	}

	public void bot() {
		JPanel botPanel = new JPanel();
		JButton jouer = new JButton("Jouer");
		JButton choixJeu = new JButton("Choix jeu");
		frame.add(mainPanel);
		mainPanel.add(botPanel, BorderLayout.SOUTH);
		botPanel.add(choixJeu);
		botPanel.add(jouer);
		jouer.addActionListener(e -> {
			try {
				gameRobot.launch(pathGame);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		choixJeu.addActionListener(e -> {
			try {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				pathGame = file.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
}
