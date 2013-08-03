package by.bsuir.iit.abramov.game.ball.model;

import java.awt.Point;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;

public class Model implements Observable {

	private static final double	kFriction	= 0.5;
	private static final int	g			= 10;
	private Controller			observer;
	private Vector				userPower;
	private final Vector		powerOfFriction;
	private static final double	dt			= 1;

	private final Ball			ball;

	public Model() {

		ball = new Ball(10);
		userPower = new Vector(0, 0);
		powerOfFriction = new Vector(0, 0);
	}

	@Override
	public void addObserver(final Observer observer) {

		if (Controller.class.equals(observer.getClass())) {
			this.observer = (Controller) observer;
		}
	}

	public Vector getAccelerationVector() {

		return ball.getAccelerationVector();
	}

	public int getBallX() {

		return ball.getX();
	}

	public int getBallY() {

		return ball.getY();
	}

	public Point getCoordinates() {

		final Point point = new Point(0, 0);

		userPower = observer.getUserPower();
		powerOfFriction.clear();
		refreshFrictionVector();
		secondNewtonLaw();
		refreshSpeedVector();

		point.x = ball.getX();
		point.y = ball.getY();
		System.out.println("******************");
		System.out.println("F = " + userPower.getLength() + " " + userPower.getAngle());
		System.out.println("v = " + ball.getSpeedVector().getLength());
		System.out.println("a = " + ball.getAccelerationVector().getLength());
		System.out.println(point);
		System.out.println("******************");
		return point;
	}

	private Double getInverseAngle(final Double angle, final double length) {

		Double inversion = angle;
		if (length == 0) {
			return new Double(0);
		}
		if (angle != null) {
			inversion -= Math.PI;

			if (inversion < 0) {
				inversion += 2 * Math.PI;
			}
		} else {
			inversion = null;
		}

		return inversion;
	}

	public Vector getPowerOfFriction() {

		return powerOfFriction;
	}

	public Vector getSpeedVector() {

		return ball.getSpeedVector();
	}

	private void refreshFrictionVector() {

		double value;

		value = Model.kFriction * ball.getMass() * Model.g;
		final Double speedAngle = getInverseAngle(ball.getSpeedVector().getAngle(), 1);
		if (speedAngle == null) {
			powerOfFriction.clear();
			if (userPower.getLength() != 0) {
				double frX = value
						* Math.cos(getInverseAngle(userPower.getAngle(),
								userPower.getLength()));
				double frY = value
						* Math.sin(getInverseAngle(userPower.getAngle(),
								userPower.getLength()));

				if (Math.abs(frX) > Math.abs(-1 * userPower.getProjectionX())) {
					frX = -1 * userPower.getProjectionX();
				}
				if (Math.abs(frY) > Math.abs(-1 * userPower.getProjectionY())) {
					frY = -1 * userPower.getProjectionY();
				}
				powerOfFriction.setProjections(frX, frY);
			}
			return;
		}
		final double cos = Math.cos(speedAngle);
		final double sin = Math.sin(speedAngle);
		powerOfFriction.setProjections(value * cos, value * sin);
		/*
		 * if (ball.getSpeedVector().getLength() == 0 ) {//&&
		 * powerOfFriction.getLength() > userPower.getLength()) {
		 * 
		 * }
		 */
	}

	private void refreshSpeedVector() {

		ball.refreshSpeedVector(Model.dt, userPower, powerOfFriction);
	}

	@Override
	public void removeObserver() {

		observer = null;

	}

	private void secondNewtonLaw() {

		double accelerationX, accelerationY;
		final double uPX = userPower.getProjectionX(), uPY = userPower.getProjectionY();
		final double fPX = powerOfFriction.getProjectionX(), fPY = powerOfFriction
				.getProjectionY();
		final double mass = ball.getMass();

		// X:

		accelerationX = (uPX + fPX) / mass;

		// Y:

		accelerationY = (uPY + fPY) / mass;

		ball.getAccelerationVector().setProjections(accelerationX, accelerationY);
	}

}
