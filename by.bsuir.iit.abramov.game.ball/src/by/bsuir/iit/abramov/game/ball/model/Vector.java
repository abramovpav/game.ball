package by.bsuir.iit.abramov.game.ball.model;

public class Vector {

	private final double	DEFAULT_PROJECTION	= 0;
	private Double			angle;
	private double			projectionX;
	private double			projectionY;
	private double			length;

	public Vector() {

		super();

		projectionX = DEFAULT_PROJECTION;
		projectionY = DEFAULT_PROJECTION;
		angle = length = 0;
	}

	public Vector(final double projectionX, final double projectionY) {

		super();

		this.projectionX = checkProjection(projectionX);
		this.projectionY = checkProjection(projectionY);
		calculateAngleAndLength(this.projectionX, this.projectionY);
	}

	public Vector(final Vector vector) {

		super();

		projectionX = checkProjection(vector.getProjectionX());
		projectionY = checkProjection(vector.getProjectionY());
		calculateAngleAndLength(projectionX, projectionY);
	}

	private void calculateAngleAndLength(final double projectionX,
			final double projectionY) {

		calculateAngleFromProjections(this.projectionX, this.projectionY);
		if (angle == null) {
			return;
		}
		if (isAngleOnX()) {
			length = Math.abs(projectionX);
			return;
		}
		if (isAngleOnY()) {
			length = Math.abs(projectionY);
			return;
		}
		length = Math.sqrt(Math.pow(this.projectionX, 2) + Math.pow(this.projectionY, 2));
	}

	private void calculateAngleFromProjections(final double projectionX,
			final double projectionY) {

		if (projectionX != 0) {
			if (projectionY == 0 && projectionX < 0) {
				angle = Math.PI;
			} else {
				angle = Math.atan(projectionY / projectionX);
			}
		} else {
			if (projectionY == 0) {
				angle = null;
				length = 0;
				return;
			}
			angle = projectionY > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
		}

		if (projectionX < 0 && projectionY < 0) {
			angle += Math.PI;
		} else if (projectionX < 0 && projectionY > 0) {
			angle += Math.PI;
		} else if (projectionX > 0 && projectionY < 0) {
			angle += 2 * Math.PI;
		}
	}

	private double checkProjection(final double projectionX) {

		return Math.abs(projectionX) > 0.000001 ? projectionX : 0;
	}

	public void clear() {

		setProjections(0, 0);
	}

	public Double getAngle() {

		if (length == 0) {
			return null;
		}
		return angle;
	}

	public double getLength() {

		return length;
	}

	public double getProjectionX() {

		return projectionX;
	}

	public double getProjectionY() {

		return projectionY;
	}

	private boolean isAngleOnX() {

		return angle == 0 || angle == Math.PI * 2 || angle == Math.PI;
	}

	private boolean isAngleOnY() {

		return angle == Math.PI / 2 || angle == 3 * Math.PI / 2;
	}

	public void setProjections(final double projectionX, final double projectionY) {

		this.projectionX = checkProjection(projectionX);
		this.projectionY = checkProjection(projectionY);
		calculateAngleAndLength(this.projectionX, this.projectionY);
	}

}
