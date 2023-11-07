package game;

import javax.swing.JFrame;

public class Game extends JFrame{
	
	private int high, width; //Variables para las dimensiones del JFrame
    
    public Game()
    {
        high = 700;
        width = 915;
        setSize(width, high);
        setTitle("PaCEman");
        setResizable(false);
        setFocusable(false);
        setLocationRelativeTo(null);
        setVisible(true);//Hacemos visible el JFrame.
    }

}
