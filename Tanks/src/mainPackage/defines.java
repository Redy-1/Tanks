package mainPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

public class defines {
	public static final double G=9.80665;
	public static final double AIR_DENSITY=1.204;
	public static final int GROUND_LEVEL=200;
	public static final int SELECT_WIDTH=300;
	public static final int MAX_FUEL=5;
	public static final int TANK_STEP=10;
	public static final int FPS=60;
	public static final int MAX_FORCE=100*FPS;

	
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREEN_WIDTH=screenSize.getWidth();
	public static final double SCREEN_HEIGHT=screenSize.getHeight();
	public static final double BALL_MASS = 1;
	public static final double BALL_R = 10;
	public static final double TANK_W = 50;
	public static final double TANK_H = 25;
}	  
