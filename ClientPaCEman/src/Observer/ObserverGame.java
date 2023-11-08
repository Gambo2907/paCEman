package Observer;

import Socket.Observer_Action;

import javax.swing.*;



public class ObserverGame extends JFrame
{
    private int high, breadth; //Variables para las dimensiones del JFrame
    private ObserverController game;

    
    public ObserverGame(int oPlayer)
    {
        high = 900;
        breadth = 915;
        setSize(breadth, high);
        setTitle("PaCEman Observer");
        setResizable(true);
        setFocusable(false);
        setLocationRelativeTo(null);
        game = ObserverController.getInstance();
        add(game);
        ObserverController.getInstance().setObserver(oPlayer);

        setVisible(true);//Hacemos visible el JFrame.
    }





}
