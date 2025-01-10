package mainPackage;

public class Tank {
	
	private double2 pos;
	private double2 size;
	
	public Tank() {
		setPos(new double2(0,0));
		setSize(new double2(100,100));

	}
	public Tank(double2 pos,double2 size) {
		this.setPos(pos);
		this.setSize(size);
	}

	
	public double2 getPos() {
		return pos;
	}
	public void setPos(double2 pos) {
		this.pos = pos;
	}


	public double2 getSize() {
		return size;
	}
	public void setSize(double2 size) {
		this.size = size;
	}

	public void shoot(double angle, double force) {
		
	}
	
	public boolean checkCollision(Cannonball ball) {
		return (pos.x < ball.pos.x+ball.radiusRectB.Right && RectA.Right > RectB.Left &&
			     RectA.Top > RectB.Bottom && RectA.Bottom < RectB.Top ) ;
	}

	
	
	
}
