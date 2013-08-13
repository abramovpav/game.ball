package by.bsuir.iit.abramov.game.ball.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;

public class Model implements Observable {

	private static final double			kFriction	= 0.5;
	private static final int			g			= 10;
	private Controller					observer;
	private final Map<Integer, Vector>	userPowerVectors;
	private final Map<Integer, Vector>	frictionVectors;
	private static final double			dt			= 1;

	private final Map<Integer, Ball>	balls;

	public Model() {

		final Ball ball = new Ball(0, 10);
		balls = new HashMap<Integer, Ball>();
		balls.put(0, ball);
		final Vector userPower = new Vector(0, 0);
		userPowerVectors = new HashMap<Integer, Vector>();
		userPowerVectors.put(0, userPower);
		final Vector powerOfFriction = new Vector(0, 0);
		frictionVectors = new HashMap<Integer, Vector>();
		frictionVectors.put(0, powerOfFriction);
	}

	@Override
	public void addObserver(final Observer observer) {

		if (Controller.class.equals(observer.getClass())) {
			this.observer = (Controller) observer;
		}
	}

	public Vector getAccelerationVector(final int ID) {

		Vector result;
		if (balls.containsKey(ID)) {
			result = balls.get(ID).getAccelerationVector();
		} else {
			result = null;
		}

		return result;
	}

	private final Ball getBall(final int ID) {

		if (!balls.containsKey(ID)) {
			return null;
		} else {
			return balls.get(ID);
		}
	}

	public Integer getBallX(final int ID) {

		Integer result;
		if (balls.containsKey(ID)) {
			result = balls.get(ID).getX();
		} else {
			result = null;
		}
		return result;
	}

	public int getBallY(final int ID) {

		Integer result;
		if (balls.containsKey(ID)) {
			result = balls.get(ID).getY();
		} else {
			result = null;
		}
		return result;
	}

	public final Map<Integer, Point> getCoordinates() {

		final Map<Integer, Point> points = new HashMap<Integer, Point>();
		Point point;
		Vector userPower, powerOfFriction;
		Ball ball;
		for (final Integer ID : balls.keySet()) {

			ball = getBall(ID);
			userPower = getUserPower(ID);
			powerOfFriction = getFrictionVector(ID);
			powerOfFriction.clear();
			refreshFrictionVector(ID);
			if (userPower == null || powerOfFriction == null || ball == null) {
				point = null;
				points.put(ID, point);
				continue;
			}
			secondNewtonLaw(ID);
			refreshSpeedVector(ID);
			point = new Point(ball.getX(), ball.getY());
			points.put(ID, point);
			System.out.println("******************");
			System.out.println("F = " + userPower.getLength() + " "
					+ userPower.getAngle());
			System.out.println("v = " + ball.getSpeedVector().getLength());
			System.out.println("a = " + ball.getAccelerationVector().getLength());
			System.out.println(point);
			System.out.println("******************");
		}
		return points;
	}

	private final Vector getFrictionVector(final int ID) {

		if (!frictionVectors.containsKey(ID)) {
			return null;
		} else {
			return frictionVectors.get(ID);
		}
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

	public Vector getPowerOfFriction(final int ID) {

		Vector result;

		if (frictionVectors.containsKey(ID)) {
			result = frictionVectors.get(ID);
		} else {
			result = null;
		}
		return result;
	}

	public Vector getSpeedVector(final int ID) {

		Vector result;
		if (balls.containsKey(ID)) {
			result = balls.get(ID).getSpeedVector();
		} else {
			result = null;
		}

		return result;
	}

	private final Vector getUserPower(final int ID) {

		return observer.getUserPower(ID);
	}

	private void refreshFrictionVector(final int ID) {

		double value;

		final Ball ball = getBall(ID);
		final Vector powerOfFriction = getFrictionVector(ID);
		final Vector userPower = getUserPower(ID);
		if (userPower == null || powerOfFriction == null || ball == null) {
			// processing of Exception
		}
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
	}

	private void refreshSpeedVector(final int ID) {

		final Ball ball = getBall(ID);
		final Vector powerOfFriction = getFrictionVector(ID);
		final Vector userPower = getUserPower(ID);
		if (userPower == null || powerOfFriction == null || ball == null) {
			// processing of Exception
		}
		ball.refreshSpeedVector(Model.dt, userPower, powerOfFriction);
	}

	@Override
	public void removeObserver() {

		observer = null;

	}

	public void reset() {

		for (final Integer ID : balls.keySet()) {
			final Ball ball = getBall(ID);
			final Vector powerOfFriction = getFrictionVector(ID);
			if (powerOfFriction != null) {
				powerOfFriction.clear();
			}
			if (ball != null) {
				ball.reset();
			}
		}
	}

	private void secondNewtonLaw(final int ID) {

		final Ball ball = getBall(ID);
		final Vector powerOfFriction = getFrictionVector(ID);
		final Vector userPower = getUserPower(ID);
		if (userPower == null || powerOfFriction == null || ball == null) {
			// processing of Exception
		}
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
