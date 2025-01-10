package mainPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

public class defines {
	public static final double G=9.80665;
	public static final double AIR_DENSITY=1.204;
	public static final int GROUND_LEVEL=200;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCREEN_WIDTH=screenSize.getWidth();
	public static final double SCREEN_HEIGHT=screenSize.getHeight();
}	  
