import java.awt.BorderLayout;

import javax.swing.*;

public class Interface extends JFrame {
    	private JFrame frame = new JFrame();
    	private JPanel mainPanel = new JPanel();
    	
 Interface(){
	Bas();

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
    
    public void Bas(){
	JPanel botPanel = new JPanel();
	JButton jouer = new JButton("Jouer");
	JButton choixJeu =new JButton("Choix jeu");
	frame.add(mainPanel);
	mainPanel.add(botPanel, BorderLayout.SOUTH);
	botPanel.add(jouer, BorderLayout.CENTER);
	botPanel.add(choixJeu, BorderLayout.WEST);
	
    }
    

}
