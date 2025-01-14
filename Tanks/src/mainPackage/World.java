package mainPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class World extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	int turn=0;
	boolean runGame=true;
	Tank Player1=new Tank();
	Tank Player2=new Tank();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					World frame = new World();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public World() {
		Random r = new Random();
		Player1=new Tank(new double2(r.nextInt(100),defines.SCREEN_HEIGHT-defines.GROUND_LEVEL-42), new double2(150,75),5);
		Player2=new Tank(new double2(defines.SCREEN_WIDTH-defines.SELECT_WIDTH-r.nextInt(100)-150,defines.SCREEN_HEIGHT-defines.GROUND_LEVEL-42), new double2(150,75),5);


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int)defines.SCREEN_WIDTH, (int)defines.SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds((int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH, 0, defines.SELECT_WIDTH, (int)defines.SCREEN_HEIGHT);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Turn 1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(75, 80, 75, 50);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Angle:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(75, 150, 75, 50);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(140, 150, 75, 50);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Power: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(75, 220, 75, 50);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(140, 220, 75, 50);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(turn%2==0) {
					Player1.move(-1);
				}else {
					Player2.move(-1);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnNewButton.setBounds(33, 335, 100, 100);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(turn%2==0) {
					Player1.move(1);
				}else {
					Player2.move(1);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnNewButton_1.setBounds(166, 335, 100, 100);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Shoot");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(turn%2==0) {
					Player1.shoot(Double.parseDouble(textField.getText()),Double.parseDouble(textField_1.getText()));
				}else {
					Player2.shoot(Double.parseDouble(textField.getText()),Double.parseDouble(textField_1.getText()));
				}
			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_2.setBounds(33, 500, 233, 75);
		panel.add(btnNewButton_2);
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(0, (int)defines.SCREEN_HEIGHT-defines.GROUND_LEVEL, (int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH, defines.GROUND_LEVEL);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_2.setBounds(0, 0, (int)(defines.SCREEN_WIDTH-defines.SELECT_WIDTH), (int)(defines.SCREEN_HEIGHT-defines.GROUND_LEVEL));
		contentPane.add(panel_2);
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				
				if(runGame) {
					
					panel_2.repaint();
					System.out.println(Player1.getPos().x);
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000/60);
		
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect((int)Player1.getPos().x, (int)Player1.getPos().y, (int)Player1.getSize().x, (int)Player1.getSize().y);
		g.fillRect((int)Player2.getPos().x, (int)Player2.getPos().y, (int)Player2.getSize().x, (int)Player2.getSize().y);
		
	} 
	
	public void run() {
		
	}
}
