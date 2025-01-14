package mainPackage;

public class Tank {
	
	private double2 pos;
	private double2 size;
	private int fuel;
	
	public Tank() {
		setFuel(0);
		setPos(new double2(0,0));
		setSize(new double2(100,100));

	}
	public Tank(double2 pos,double2 size,int fuel) {
		this.fuel=fuel;
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
		angle=Math.PI/180*angle;
		Cannonball ball=new Cannonball(pos,3,new double2(0,0),new double2(force/3*Math.cos(angle),force/3*Math.sin(angle)),50);
		while(!checkCollision(ball) || !ball.hasLanded()) {
			ball.updatePos();
		}
	}
	
	public boolean checkCollision(Cannonball ball) {
		return (pos.x + size.x >= ball.getPos().x - ball.getPos().y &&     // r1 right edge past r2 left
				  pos.x <= ball.getPos().x - ball.getPos().y + ball.getRadius() &&       // r1 left edge past r2 right
				  pos.y + size.y >= ball.getPos().y - ball.getPos().y &&       // r1 top edge past r2 bottom
				  pos.y <= ball.getPos().y - ball.getPos().y + ball.getRadius());        // r1 bottom edge past r2 top
	}

	public void move(int dir) {
		int step=10;
		pos.x+=step*dir;
	}
	
	public boolean checkFuel() {
		return fuel > 0;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	
	
	
}
