package by.bsuir.iit.abramov.game.ball.model;

public class Vector {

	private double angle;
	private double length;
	public Vector(double angle, double length) {

		super();
		this.angle = angle;
		this.length = length;
	}
	public double getAngle() {
	
		return angle;
	}
	public double getLength() {
	
		return length;
	}
	public void setAngle(double angle) {
	
		this.angle = angle;
	}
	public void setLength(double length) {
	
		this.length = length;
	}
	
	

}
