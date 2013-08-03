package by.bsuir.iit.abramov.game.ball.model;

public class Ball {

	private final double	mass;
	private int				x;
	private int				y;
	private Vector			speedVector;
	private Vector			accelerationVector;

	public Ball(final double mass) {

		this.mass = mass;
		x = y = 0;
		speedVector = new Vector(0, 0);
		accelerationVector = new Vector(0, 0);
	}

	private double calculateSpeedAngle(final double oldSpeed, final double newSpeed,
			final double acceleration, final double accAngle) {

		if (newSpeed == 0) {
			return 0;
		}
		if (oldSpeed == 0) {
			return accAngle;
		}
		return speedVector.getAngle()
				- Math.acos((Math.pow(oldSpeed, 2) + Math.pow(newSpeed, 2) - Math.pow(
						acceleration, 2)) / (2 * oldSpeed * newSpeed));
	}

	public final Vector getAccelerationVector() {

		return accelerationVector;
	}

	public double getMass() {

		return mass;
	}

	public final Vector getSpeedVector() {

		return speedVector;
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}

	public void refreshSpeedVector(final double dt, final Vector userPower,
			final Vector powerOfFriction) {

		double newX, newY;
		final double oldX = speedVector.getProjectionX(), oldY = speedVector
				.getProjectionY();
		final double acX = accelerationVector.getProjectionX();
		newX = oldX + acX * dt;
		if (Math.abs(userPower.getProjectionX()) < Math.abs(powerOfFriction
				.getProjectionX())) {
			if (oldX * newX < 0) {
				newX = 0;
			}
		}
		final double acY = accelerationVector.getProjectionY();
		newY = oldY + acY * dt;
		if (Math.abs(userPower.getProjectionY()) < Math.abs(powerOfFriction
				.getProjectionY())) {
			if (oldY * newY < 0) {
				newY = 0;
			}
		}
		/*
		 * double speed = speedVector.getProjectionX() +
		 * accelerationVector.getProjectionX() * dt;
		 * speedVector.setProjections(speed, speedVector.getProjectionY() +
		 * accelerationVector.getProjectionY() * dt);
		 * //speedVector.setAngle(calculateSpeedAngle(oldSpeed,
		 * speedVector.getLength(), accelerationVector.getLength(),
		 * accelerationVector.getAngle()));
		 */
		x = (int) (x + oldX * dt + (acX * Math.pow(dt, 2) / 2));
		y = (int) (y + oldY * dt + (acY * Math.pow(dt, 2) / 2));
		speedVector.setProjections(newX, newY);

	}

	public void setAccelerationVector(final Vector accelerationVector) {

		this.accelerationVector = accelerationVector;
	}

	public void setSpeedVector(final Vector speedVector) {

		this.speedVector = speedVector;
	}

	public void setX(final int x) {

		this.x = x;
	}

	public void setY(final int y) {

		this.y = y;
	}
}
