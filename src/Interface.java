import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;



public class Interface extends JFrame {
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel();

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

    public void bot(){
	JPanel botPanel = new JPanel();
	JButton jouer = new JButton("Jouer");
	JButton choixJeu =new JButton("Choix jeu");
	frame.add(mainPanel);
	mainPanel.add(botPanel, BorderLayout.SOUTH);
	botPanel.add(choixJeu);
	botPanel.add(jouer);

	choixJeu.addActionListener(new listenerChoixJeu());
    }

    public class listenerChoixJeu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JFrame fcheminJeu = new JFrame();
	    try {
	    JFileChooser chooser = new JFileChooser();
	    chooser.showOpenDialog(null);
	    File file = chooser.getSelectedFile();
		String fullPath = file.getCanonicalPath();
		System.out.println(fullPath);
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    

	}

    }


}
