package by.bsuir.iit.abramov.game.ball.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;

public class Model implements Observable {

	private static final double	kFriction	= 0.5;
	private static final int	g	= 10;
	private Controller observer;
	private Vector userPower;
	private Vector powerOfFriction;
	private Vector speedVector;
	private double speed;
	private Ball ball;
	private double acceleration;
	private int x;
	private int y;
	
	public Model() {
		ball = new Ball(10);
		userPower = new Vector(0, 0);
		acceleration  = 0;
		speed = 0;
		speedVector = new Vector(0,0);
		powerOfFriction = new Vector(0, 0);
		x = observer.getWidth() / 2;
		y = observer.getHeight() / 2;
	}
	
	private double getFrictionValue() {
		double value;
		
		value = kFriction * g;
		double angle = Math.abs(userPower.getAngle() - powerOfFriction.getAngle());
		double userPowerProjectionOnFriction = userPower.getLength() * Math.cos(angle);
		if (userPower.getLength() != 0) {
			value = Math.min(userPowerProjectionOnFriction, value);
		}
		
		return value;
	}
	
	private void refreshFrictionVector() {
		powerOfFriction.setLength(getFrictionValue());
		powerOfFriction.setAngle(getInverseAngle(speedVector.getAngle()));
	}
	
	
	private double getInverseAngle(final double angle) {
		double inversion = angle;
		
		inversion -= Math.PI;
		
		if (inversion < 0) {
			inversion += 2 * Math.PI;
		}
		
		return inversion;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	@Override
	public void addObserver(Observer observer) {

		if (Controller.class == observer.getClass()) {
			this.observer = (Controller)observer;
		}
	}

	@Override
	public void removeObserver() {
			observer = null;
		
	}

	

}
