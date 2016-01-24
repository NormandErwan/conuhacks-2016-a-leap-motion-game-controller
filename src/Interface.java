import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
    private JTextField varGame;
    private JTextField JTnbrGesture;
    private PoseManager poseManager;
	
    Interface(PoseManager poseManager) {
		this.poseManager = poseManager;
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
	char[] alphabet = new char[]{'q','w','e','r','a','s','d','f'};
	JFrame frameGesture = new JFrame("Gesture");
	frameGesture.setSize(1000, 600);
	frameGesture.setLocationRelativeTo(null);
	frameGesture.setVisible(true);
	JPanel midPanel = new JPanel();
	frameGesture.add(midPanel);
	GridLayout midGridLayout = new GridLayout(nbrGestures,2);
	midPanel.setLayout(midGridLayout);
	midPanel.setPreferredSize(new Dimension(100, 50));
	for(int i=0; i<nbrGestures; i++){
	    	varGame = new JTextField(alphabet[i]);
		JButton button = new JButton("Gesture");
		midPanel.add(varGame);
		midPanel.add(button);
		varGame.addActionListener(new listenerChoiceVarGame());
	}
	
	
	//varGame.addActionListener(new listenerChoiceVarGame());

    }
    public class listenerChoiceVarGame implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	  
	    System.out.println(varGame.getText().charAt(0));
	    //tabKey[0]= varGame.getText().charAt(0);
	    
	}
	
    }
    
    public void bot(){
		JPanel botPanel = new JPanel();
		JButton jouer = new JButton("Jouer");
		JButton choixJeu =new JButton("Choix jeu");
		frame.add(mainPanel);
		mainPanel.add(botPanel, BorderLayout.SOUTH);
		botPanel.add(choixJeu);
		botPanel.add(jouer);
	
		choixJeu.addActionListener(new listenerChoiceOfTheGame());
    }

    public class listenerChoiceOfTheGame implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JFrame fcheminJeu = new JFrame();
	    try {
	    JFileChooser chooser = new JFileChooser();
	    chooser.showOpenDialog(null);
	    File file = chooser.getSelectedFile();
		pathGame = file.getCanonicalPath();

	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    

	}

    }

}
