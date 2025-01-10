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
	
	public double2 add(double2 second) {
		double2 sum=new double2();
		sum.x=x+second.x;
		sum.y=y+second.y;
		return sum;
	}
	public double2 mul(double2 second) {
		double2 sum=new double2();
		sum.x=x*second.x;
		sum.y=y*second.y;
		return sum;
	}
	
	public double dis() {
		return Math.sqrt(x*x + y*y);
	}
	
	
}
