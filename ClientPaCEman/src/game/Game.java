package game;
import javax.swing.JFrame;



public class Game extends JFrame
{
    private int high, breadth; 
    private ViewController game;

    
    public Game()
    {
        high = 800;
        breadth = 915;
        setSize(breadth, high);
        setTitle("PaCEman");
        setResizable(false);
        setFocusable(false);
        setLocationRelativeTo(null);
        game = ViewController.getInstance();
        add(game);
        setVisible(true);//Hacemos visible el JFrame.
    }





}
