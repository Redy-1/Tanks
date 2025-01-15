package mainPackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	public Tank Player1;
	public Tank Player2;
	public Cannonball ball;
	public boolean damaged_ground[]=new boolean [(int)defines.SCREEN_WIDTH-defines.SELECT_WIDTH];
	public DrawPanel() {
		setBackground(Color.BLUE);
		setLayout(null);
		Player1=new Tank();
		Player2=new Tank();
		ball=new Cannonball();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect((int)Player1.getPos().x, (int)Player1.getPos().y, (int)Player1.getSize().x, (int)Player1.getSize().y);
		g.fillRect((int)Player2.getPos().x, (int)Player2.getPos().y, (int)Player2.getSize().x, (int)Player2.getSize().y);
		g.setColor(Color.red);
		g.fillOval((int)(ball.getPos().x-ball.getRadius()),(int) (ball.getPos().y-ball.getRadius()), (int)ball.getRadius()*2,(int)ball.getRadius()*2);
		for(int i=0;i<defines.SCREEN_WIDTH-defines.SELECT_WIDTH;i++) {
			if(damaged_ground[i]) {
				g.fillRect(i, (int)defines.SCREEN_HEIGHT-defines.GROUND_LEVEL-10, 10, 10);
			}
		}
	}
}
