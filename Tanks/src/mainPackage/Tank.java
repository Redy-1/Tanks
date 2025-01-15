package mainPackage;

public class Tank {
	
	private double2 pos;
	private double2 size;
	private int fuel;
		
	public Tank() {
		setFuel(defines.MAX_FUEL);
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

	public Cannonball shoot(double angle, double force) {
		angle=Math.PI/180*angle;
		force=force*defines.MAX_FORCE/100;
		Cannonball ball=new Cannonball(pos,defines.BALL_MASS,new double2(force/3*Math.cos(angle),-1*force/3*Math.sin(angle)),new double2(0,defines.G),defines.BALL_R);
		return ball;
	}
	
	public boolean checkCollision(Cannonball ball) {
		return (pos.x + size.x >= ball.getPos().x - ball.getRadius() &&     // r1 right edge past r2 left
				  pos.x <= ball.getPos().x + ball.getRadius() &&       // r1 left edge past r2 right
				  pos.y + size.y >= ball.getPos().y - ball.getRadius() &&       // r1 top edge past r2 bottom
				  pos.y <= ball.getPos().y + ball.getRadius());        // r1 bottom edge past r2 top
	}

	public void move(int dir) {
		int step=defines.TANK_STEP;
		
		pos.x+=step*dir;
		fuel--;
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
