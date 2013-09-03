package by.bsuir.iit.abramov.game.ball.model;

import java.util.ArrayList;
import java.util.List;

public class DefaultUnit {
	private final Integer	ID;
	private final Ball		ball;
	private Vector			frictionPower;
	private List<Vector>	powerVectors;

	public DefaultUnit(final int ID) {

		this.ID = ID;
		ball = new Ball();
		frictionPower = new Vector();
		powerVectors = new ArrayList<Vector>();
		final Vector userPower = new Vector();
		powerVectors.add(userPower);
	}

	public DefaultUnit(final int ID, final Ball ball) {

		this.ID = ID;
		this.ball = ball;
		frictionPower = new Vector();
		powerVectors = new ArrayList<Vector>();
		final Vector userPower = new Vector();
		powerVectors.add(userPower);
	}

	public DefaultUnit(final int ID, final DefaultUnit unit) {

		this.ID = ID;
		ball = new Ball(unit.getBall());
		frictionPower = new Vector(unit.getFrictionPower());
		powerVectors = new ArrayList<Vector>();
		for (final Vector vector : unit.getPowerVectors()) {
			final Vector userPower = new Vector(vector);
			powerVectors.add(userPower);
		}
	}

	public DefaultUnit(final int ID, final double mass) {

		this.ID = ID;
		ball = new Ball(mass);
		frictionPower = new Vector();
		powerVectors = new ArrayList<Vector>();
		final Vector userPower = new Vector();
		powerVectors.add(userPower);
	}

	public Ball getBall() {

		return ball;
	}

	public Vector getFrictionPower() {

		return frictionPower;
	}

	public Integer getID() {

		return ID;
	}

	public List<Vector> getPowerVectors() {

		return powerVectors;
	}

	public void setFrictionPower(final Vector frictionPower) {

		this.frictionPower = frictionPower;
	}

	public void setPowerVectors(final List<Vector> powerVectors) {

		this.powerVectors = powerVectors;
	}

}
