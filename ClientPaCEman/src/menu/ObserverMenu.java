package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ObserverMenu extends JFrame implements ActionListener {
	
	public JFrame mFrame;
    public JPanel mPanel;
    private JLabel oBackground;
    private JButton obsPlayer1, obsPlayer2, backBtn;
    
    
    private static ObserverMenu instance = null;
    
    public ObserverMenu() {

        mFrame = new JFrame();
        mFrame.setResizable(false);
        mFrame.setBounds(100, 100, 600, 580);
        mFrame.setTitle("PaCEman Observer Menu");
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.getContentPane().setLayout(null);

        

        mPanel = new JPanel();
        mPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mPanel.setBounds(0, 0, 584, 541);
		mPanel.setLayout(new BorderLayout(0, 0));
		mFrame.getContentPane().add(mPanel);

        

        obsPlayer1 = new JButton();
        obsPlayer2 = new JButton();
        backBtn = new JButton();

        obsPlayer1.setBounds(50, 200, 200, 100);
        ImageIcon btnurl1 = new ImageIcon(getClass().getResource("/Resources/Player1Button.png"));
        obsPlayer1.setIcon(btnurl1);
        obsPlayer1.addActionListener(this);
        mPanel.add(obsPlayer1);

        obsPlayer2.setBounds(350, 200, 200, 100);
        ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Resources/Player2Button.png"));
        obsPlayer2.setIcon(btnurl2);
        obsPlayer2.addActionListener(this);
        mPanel.add(obsPlayer2);

        backBtn.setBounds(0, 490, 100, 50);
        ImageIcon btnurl3 = new ImageIcon(getClass().getResource("/Resources/BackButton.png"));
        backBtn.setIcon(btnurl3);
        backBtn.addActionListener(this);
        mPanel.add(backBtn);

        
        oBackground = new JLabel();
        oBackground.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/Menu.png"));
        oBackground.setIcon(bgurl);
        validate();
        mPanel.add(oBackground);
        mFrame.setVisible(true);


    }
    
    public static ObserverMenu getInstance(){
        if(instance == null){
            instance = new ObserverMenu();
        }
        return instance;
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/*if (e.getSource() == obsPlayer1) {
            new ObserverGame(1);
            mFrame.setVisible(false);
        }
        if (e.getSource() == obsPlayer2) {
            new ObserverGame(2);
            mFrame.setVisible(false);
        }*/
        if (e.getSource() == backBtn) {
            MainMenu.getInstance();
            mFrame.setVisible(false);
            instance = null;
        }
		
	}

}
