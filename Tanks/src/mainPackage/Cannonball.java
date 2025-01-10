package mainPackage;

public class Cannonball {
	
	private double2 pos;
	private double radius;
	
	private double mass;
	private double2 vel;
	private double2 acc;
	
	public Cannonball() {
		setPos(new double2());
		setMass(1);
		setVel(new double2());
		setAcc(new double2());
		setRadius(50);
	}
	public Cannonball(double2 pos, double mass, double2 vel,
			double2 acc, double radius) {
		this.setPos(pos);
		this.setMass(mass);
		this.setVel(vel);
		this.setAcc(acc);
		this.setRadius(radius);
	}
	
	public double2 getPos() {
		return pos;
	}
	public void setPos(double2 pos) {
		this.pos = pos;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double2 getVel() {
		return vel;
	}
	public void setVel(double2 vel) {
		this.vel = vel;
	}
	public double2 getAcc() {
		return acc;
	}
	public void setAcc(double2 acc) {
		this.acc = acc;
	}
	public void updatePos() {
		//add time
		
		double vel_dis=vel.dis();
		double acc_dis=acc.dis();
		double crossSectionArea=Math.PI*radius*radius;
		double dragCoeficient=24/(defines.AIR_DENSITY * vel_dis * radius * 55555);
		double dragForce=vel_dis*vel_dis*defines.AIR_DENSITY*crossSectionArea/2*dragCoeficient;
		
		double2 dragForceVec=new double2((dragForce/acc_dis)*acc.x, 
									(dragForce/acc_dis)*acc.y);
		acc.add(dragForceVec.mul(new double2(-1/mass,-1/mass)));
		
		vel.add(acc);
		
		pos.add(vel);
	}
	
	public boolean hasLanded() {
		return (pos.y>defines.SCREEN_HEIGHT-defines.GROUND_LEVEL+radius);
	}
	

	
	
	
}
