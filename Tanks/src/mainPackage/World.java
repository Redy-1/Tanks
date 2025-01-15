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
import javax.swing.JProgressBar;

public class World extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	int turn=0;
	boolean runGame=true;

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
	
		boolean hasLanded[]=new boolean[(int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH+1];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int)defines.SCREEN_WIDTH, (int)defines.SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DrawPanel drawPanel = new DrawPanel();
		drawPanel.setBackground(new Color(0, 191, 255));
		drawPanel.setBounds(0, 0, (int)(defines.SCREEN_WIDTH-defines.SELECT_WIDTH), (int)(defines.SCREEN_HEIGHT-defines.GROUND_LEVEL));
		contentPane.add(drawPanel);
		
		Random r = new Random();
		drawPanel.Player1=new Tank(new double2(r.nextInt(100),defines.SCREEN_HEIGHT-defines.GROUND_LEVEL-defines.TANK_H), new double2(defines.TANK_W,defines.TANK_H),defines.MAX_FUEL);
		drawPanel.Player2=new Tank(new double2(defines.SCREEN_WIDTH-defines.SELECT_WIDTH-r.nextInt(100)-defines.TANK_W,defines.SCREEN_HEIGHT-defines.GROUND_LEVEL-defines.TANK_H), new double2(defines.TANK_W,defines.TANK_H),defines.MAX_FUEL);

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds((int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH, 0, defines.SELECT_WIDTH, (int)defines.SCREEN_HEIGHT);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Player 1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(75, 80, 75, 50);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Angle:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(75, 150, 75, 50);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		
		textField.setText("0");
		textField_1.setText("0");
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		progressBar.setBounds(58, 641, 176, 50);
		progressBar.setMaximum(defines.MAX_FUEL);
		progressBar.setValue(drawPanel.Player1.getFuel()+drawPanel.Player2.getFuel()-defines.MAX_FUEL);
		panel.add(progressBar);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nearest_valid=0;
				if(turn%2==0) {
					boolean flag=true;
					for(int i=(int)drawPanel.Player1.getPos().x-10;
							i>(int)drawPanel.Player1.getPos().x-defines.TANK_STEP-10;i--) {
						if(hasLanded[i]) {
							flag=false;
							nearest_valid=i+10;
						}
					}
					if(flag && drawPanel.Player1.checkFuel()) {
						drawPanel.Player1.move(-1);
					}
					if(!flag && drawPanel.Player1.checkFuel()) {
						drawPanel.Player1.setPos(new double2(nearest_valid,drawPanel.Player1.getPos().y));
						drawPanel.Player1.setFuel(drawPanel.Player1.getFuel()-1);
 
					}
					
					
				}else {
					boolean flag=true;
					for(int i=(int)drawPanel.Player2.getPos().x-10;
					i>(int)drawPanel.Player2.getPos().x-defines.TANK_STEP-10;i--)  {
						if(hasLanded[i]) {
							flag=false;
							nearest_valid=i+10;
						}
					}
					if(flag && drawPanel.Player2.checkFuel()) {
						drawPanel.Player2.move(-1);
					}
					if(!flag && drawPanel.Player2.checkFuel()) {
						drawPanel.Player2.setPos(new double2(nearest_valid,drawPanel.Player2.getPos().y));
						drawPanel.Player2.setFuel(drawPanel.Player2.getFuel()-1);
						
					}
				}
				progressBar.setValue(drawPanel.Player1.getFuel()+drawPanel.Player2.getFuel()-defines.MAX_FUEL);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnNewButton.setBounds(33, 335, 100, 100);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nearest_valid=0;
				if(turn%2==0) {
					boolean flag=true;
					
					for(int i=(int)drawPanel.Player1.getPos().x+(int)drawPanel.Player1.getSize().x;
							i<(int)drawPanel.Player1.getPos().x+(int)drawPanel.Player1.getSize().x+defines.TANK_STEP;i++) {
						if(hasLanded[i]) {
							flag=false;
							nearest_valid=i-(int)drawPanel.Player1.getSize().x;
						}
					}
					if(flag && drawPanel.Player1.checkFuel()) {
						drawPanel.Player1.move(1);
					}
					if(!flag && drawPanel.Player1.checkFuel()) {
						drawPanel.Player1.setPos(new double2(nearest_valid,drawPanel.Player1.getPos().y));
						drawPanel.Player1.setFuel(drawPanel.Player1.getFuel()-1);
						
					}
					
					
				}else {
					boolean flag=true;
					for(int i=(int)drawPanel.Player2.getPos().x+(int)drawPanel.Player2.getSize().x;
					i<(int)drawPanel.Player2.getPos().x+(int)drawPanel.Player2.getSize().x+defines.TANK_STEP;i++)  {
						if(hasLanded[i]) {
							flag=false;
							nearest_valid=i-(int)drawPanel.Player2.getSize().x;

						}
					}
					if(flag && drawPanel.Player2.checkFuel()) {
						drawPanel.Player2.move(1);
						
					}
					if(!flag && drawPanel.Player2.checkFuel()) {
						drawPanel.Player2.setPos(new double2(nearest_valid,drawPanel.Player2.getPos().y));
						drawPanel.Player2.setFuel(drawPanel.Player2.getFuel()-1);
					}
					
				}
				progressBar.setValue(drawPanel.Player1.getFuel()+drawPanel.Player2.getFuel()-defines.MAX_FUEL);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnNewButton_1.setBounds(166, 335, 100, 100);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Shoot");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(turn%2==0) {
					drawPanel.Player1.setPos(new double2(drawPanel.Player1.getPos().x+drawPanel.Player1.getSize().x,drawPanel.Player1.getPos().y));
					drawPanel.ball=drawPanel.Player1.shoot(Double.parseDouble(textField.getText()),Double.parseDouble(textField_1.getText()));
					drawPanel.Player1.setPos(new double2(drawPanel.Player1.getPos().x-drawPanel.Player1.getSize().x,drawPanel.Player1.getPos().y));
					
					turn++;
					drawPanel.Player1.setFuel(defines.MAX_FUEL);

					lblNewLabel_2.setText("Player 2");
					textField.setText("0");
					textField_1.setText("0");
				}else {
					drawPanel.ball=drawPanel.Player2.shoot(Double.parseDouble(textField.getText())*-1,Double.parseDouble(textField_1.getText())*-1);
					drawPanel.Player2.setPos(new double2(drawPanel.Player2.getPos().x,drawPanel.Player2.getPos().y));

					turn++;
					drawPanel.Player2.setFuel(defines.MAX_FUEL);

					lblNewLabel_2.setText("Player 1");
					textField.setText("0");
					textField_1.setText("0");
				}
				progressBar.setValue(drawPanel.Player1.getFuel()+drawPanel.Player2.getFuel()-defines.MAX_FUEL);
			}
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton_2.setBounds(33, 500, 233, 75);
		panel.add(btnNewButton_2);
		
		
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(0, (int)defines.SCREEN_HEIGHT-defines.GROUND_LEVEL, (int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH, defines.GROUND_LEVEL);
		contentPane.add(panel_1);
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				
				if(runGame) { 
					
					//System.out.println(drawPanel.ball.getPos().y);
					drawPanel.ball.updatePos();
					if(drawPanel.ball.hasLanded() && (int)drawPanel.ball.getPos().x>=0 &&
							(int)drawPanel.ball.getPos().x<=(int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH) {
						hasLanded[(int)drawPanel.ball.getPos().x]=true;
						drawPanel.damaged_ground[(int)drawPanel.ball.getPos().x]=true;
						drawPanel.ball=new Cannonball();
						
					}
					if(drawPanel.Player1.checkCollision(drawPanel.ball) && turn%2==0) {
						drawPanel.ball=new Cannonball();
						JFrame winscreen=new Winscreen(2);
						winscreen.setVisible(true);
						dispose();
					}
					if(drawPanel.Player2.checkCollision(drawPanel.ball) && turn%2!=0) {
						drawPanel.ball=new Cannonball();
						JFrame winscreen=new Winscreen(1);
						winscreen.setVisible(true);
						dispose();
					}
					

					drawPanel.repaint();
					
					
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000/defines.FPS);
		
		
	}
	/*
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect((int)Player1.getPos().x, (int)Player1.getPos().y, (int)Player1.getSize().x, (int)Player1.getSize().y);
		g.fillRect((int)Player2.getPos().x, (int)Player2.getPos().y, (int)Player2.getSize().x, (int)Player2.getSize().y);
		
	} 
	*/
	
	public void run() {
		
	}
}
