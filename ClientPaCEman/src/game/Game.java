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
        setResizable(true);
        setFocusable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = ViewController.getInstance();
        add(game);
        setVisible(true);//Hacemos visible el JFrame.
    }
    
   




}
