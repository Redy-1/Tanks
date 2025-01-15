package mainPackage;

public class double2 {
	public double x;
	public double y;
	
	public double2() {
		x=0;
		y=0;
	}
	public double2(double x,double y) {
		this.x=x;
		this.y=y;
	} 
	
	public void add(double2 second) {
		x=x+second.x;
		y=y+second.y;
	}
	public void mul(double2 second) {
		x=x*second.x;
		y=y*second.y;
	}
	
	public double dis() {
		return Math.sqrt(x*x + y*y);
	}
	
	
}
