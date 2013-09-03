package by.bsuir.iit.abramov.game.ball.model;

public class Ball {

	public final double		DEFAULT_MASS	= 10;
	private final double	mass;
	private int				x;
	private int				y;
	private Vector			speedVector;
	private Vector			accelerationVector;

	public Ball() {

		mass = DEFAULT_MASS;
		initialize();
	}

	public Ball(final Ball ball) {

		mass = ball.getMass();
		x = ball.getX();
		y = ball.getY();
		speedVector = new Vector(ball.getSpeedVector());
		accelerationVector = new Vector(ball.getAccelerationVector());

	}

	public Ball(final double mass) {

		this.mass = mass;
		initialize();
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

	private void initialize() {

		x = y = 0;
		speedVector = new Vector();
		accelerationVector = new Vector();
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
		x = (int) (x + oldX * dt + (acX * Math.pow(dt, 2) / 2));
		y = (int) (y + oldY * dt + (acY * Math.pow(dt, 2) / 2));
		speedVector.setProjections(newX, newY);

	}

	public void reset() {

		x = y = 0;
		speedVector.setProjections(0, 0);
		accelerationVector.setProjections(0, 0);
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
