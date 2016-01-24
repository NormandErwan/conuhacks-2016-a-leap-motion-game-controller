import java.awt.BorderLayout;
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
    private JTextField varGame;
    Interface(){
	
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
    /*public void mid(){
	FlowLayout midPanel = new JPanel();
	varGame = new JTextField();
	
	mainPanel.add(midPanel, BorderLayout.CENTER);
	mid
	
	varGame.addActionListener(new listenerChoiceVarGame());

    }
    public class listenerChoiceVarGame implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	  
	    tabGesture[0]= new Gesture(varGame.getText().charAt(0));
	    
	}
	
    }*/
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
