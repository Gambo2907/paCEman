package menu;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import game.Game;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private JLabel Background;
	private JButton btnplayer;
	private JButton btnobserver;
	private static MainMenu instance = null;
	

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("PaCEman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 580);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(0, 0, 584, 541);
		panel.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panel);
		
		
		btnplayer = new JButton();
		btnplayer.setBackground(new Color(0, 0, 0));
		
		btnplayer.setBounds(50, 200, 200, 100);
		ImageIcon play_url = new ImageIcon(getClass().getResource("/Resources/PlayButton.png"));
		btnplayer.setIcon(play_url);
		btnplayer.addActionListener(this);
        panel.add(btnplayer, BorderLayout.CENTER);
        
        btnobserver = new JButton();
        btnobserver.setBackground(new Color(0, 0, 0));
        btnobserver.setBounds(350, 200, 200, 100);
        ImageIcon observe_url = new ImageIcon(getClass().getResource("/Resources/ObserveButton.png"));
		btnobserver.setIcon(observe_url);
		btnobserver.addActionListener(this);
        panel.add(btnobserver, BorderLayout.CENTER);
		
		
		Background = new JLabel();
        Background.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/Menu.png"));
        Background.setIcon(bgurl);
        validate();
        panel.add(Background, BorderLayout.CENTER);
        
        

        frame.setVisible(true);

    }
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getSource() == btnplayer) {
            new Game();
            frame.setVisible(false);

        }
        if (e.getSource() == btnobserver) {
            ObserverMenu newObserver = ObserverMenu.getInstance();
            frame.setVisible(false);
            instance = null;
        }

		
	}
	
	public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }
}
