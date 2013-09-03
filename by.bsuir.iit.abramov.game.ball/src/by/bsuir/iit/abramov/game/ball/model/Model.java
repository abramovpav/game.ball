package by.bsuir.iit.abramov.game.ball.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;

public class Model implements Observable {

	private static final double				kFriction	= 0.5;
	private static final int				g			= 10;
	private Controller						observer;
	private static final double				dt			= 1;

	private final Map<Integer, DefaultUnit>	units;

	public Model() {

		final Vector userPower = new Vector();
		final Vector powerOfFriction = new Vector();
		units = new HashMap<Integer, DefaultUnit>();
		/*
		 * DefaultUnit unit = new DefaultUnit(0);
		 * unit.setFrictionPower(powerOfFriction); List<Vector> powerVectors =
		 * new ArrayList<Vector>(); powerVectors.add(userPower);
		 * unit.setPowerVectors(powerVectors); units.put(0, unit); unit = new
		 * DefaultUnit(1, unit); units.put(1, unit);
		 */

	}

	@Override
	public void addObserver(final Observer observer) {

		if (Controller.class.equals(observer.getClass())) {
			this.observer = (Controller) observer;
		}
	}

	public DefaultUnit createUnit(final int ID) {

		final DefaultUnit result = new DefaultUnit(ID);
		result.setFrictionPower(new Vector());
		return result;
	}

	public Vector getAccelerationVector(final int ID) {

		Vector result;
		if (units.containsKey(ID)) {
			result = units.get(ID).getBall().getAccelerationVector();
		} else {
			result = null;
		}

		return result;
	}

	private final Ball getBall(final int ID) {

		if (!units.containsKey(ID)) {
			return null;
		} else {
			return units.get(ID).getBall();
		}
	}

	public Integer getBallX(final int ID) {

		Integer result;
		if (units.containsKey(ID)) {
			result = units.get(ID).getBall().getX();
		} else {
			result = null;
		}
		return result;
	}

	public int getBallY(final int ID) {

		Integer result;
		if (units.containsKey(ID)) {
			result = units.get(ID).getBall().getY();
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
		for (final DefaultUnit unit : units.values()) {

			ball = unit.getBall();
			final int ID = unit.getID();
			userPower = getUserPower(ID);
			powerOfFriction = unit.getFrictionPower();
			powerOfFriction.clear();
			refreshFrictionVector(unit);
			if (userPower == null || powerOfFriction == null || ball == null) {
				point = null;
				points.put(ID, point);
				continue;
			}
			secondNewtonLaw(unit);
			refreshSpeedVector(unit);
			point = new Point(ball.getX(), ball.getY());
			points.put(ID, point);
			System.out.println("******************");
			System.out.println("ID = " + ID);
			System.out.println("F = " + userPower.getLength() + " "
					+ userPower.getAngle());
			System.out.println("v = " + ball.getSpeedVector().getLength());
			System.out.println("a = " + ball.getAccelerationVector().getLength());
			System.out.println(point);
			System.out.println("******************");
		}
		return points;
	}

	public final Vector getFrictionVector(final int ID) {

		if (!units.containsKey(ID)) {
			return null;
		} else {
			return units.get(ID).getFrictionPower();
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

	public Vector getSpeedVector(final int ID) {

		Vector result;
		if (units.containsKey(ID)) {
			result = units.get(ID).getBall().getSpeedVector();
		} else {
			result = null;
		}

		return result;
	}

	private final Vector getUserPower(final int ID) {

		return observer.getUserPower(ID);
	}

	public void newUnit(final int ID) {

		units.put(ID, createUnit(ID));
	}

	private void refreshFrictionVector(final DefaultUnit unit) {

		double value;

		final Ball ball = unit.getBall();
		final Vector powerOfFriction = unit.getFrictionPower();
		final Vector userPower = getUserPower(unit.getID());
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

	private void refreshSpeedVector(final DefaultUnit unit) {

		final Ball ball = unit.getBall();
		final Vector powerOfFriction = unit.getFrictionPower();
		final Vector userPower = getUserPower(unit.getID());
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

		for (final DefaultUnit unit : units.values()) {
			final Ball ball = unit.getBall();
			final Vector powerOfFriction = unit.getFrictionPower();
			if (powerOfFriction != null) {
				powerOfFriction.clear();
			}
			if (ball != null) {
				ball.reset();
			}
		}
	}

	private void secondNewtonLaw(final DefaultUnit unit) {

		final Ball ball = unit.getBall();
		final Vector powerOfFriction = unit.getFrictionPower();
		final Vector userPower = getUserPower(unit.getID());
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
