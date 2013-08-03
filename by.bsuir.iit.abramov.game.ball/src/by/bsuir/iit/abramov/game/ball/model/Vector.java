package by.bsuir.iit.abramov.game.ball.model;

public class Vector {

	private Double	angle;
	private double	projectionX;
	private double	projectionY;
	private double	length;

	public Vector(final double projectionX, final double projectionY) {

		super();

		this.projectionX = projectionX;
		this.projectionY = projectionY;
		if (projectionX != 0) {
			angle = Math.atan(projectionY / projectionX);
		} else {
			if (projectionY == 0) {
				angle = null;
				length = 0;
				return;
			}
			angle = projectionY > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
		}
		if (angle == 0 || angle == Math.PI * 2) {
			length = projectionX;
			return;
		}
		if (angle == Math.PI / 2 || angle == 3 * Math.PI / 2) {
			length = projectionY;
			return;
		}
		length = Math.sqrt(Math.pow(projectionX, 2) + Math.pow(projectionY, 2));
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

	public void setProjections(final double projectionX, final double projectionY) {

		this.projectionX = checkProjection(projectionX);
		this.projectionY = checkProjection(projectionY);
		if (this.projectionX != 0) {
			if (this.projectionY == 0 && this.projectionX < 0) {
				angle = Math.PI;
			} else {
				angle = Math.atan(this.projectionY / this.projectionX);
			}
		} else {
			if (this.projectionY == 0) {
				angle = null;
				length = 0;
				return;
			}
			angle = this.projectionY > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
		}

		if (this.projectionX < 0 && this.projectionY < 0) {
			angle += Math.PI;
		} else if (this.projectionX < 0 && this.projectionY > 0) {
			// angle *= -1;
			angle += Math.PI;
		} else if (this.projectionX > 0 && this.projectionY < 0) {
			angle += 2 * Math.PI;
		}
		if (angle == 0 || angle == Math.PI * 2) {
			length = Math.abs(this.projectionX);
			return;
		}
		if (angle == Math.PI / 2 || angle == 3 * Math.PI / 2) {
			length = Math.abs(this.projectionY);
			return;
		}
		length = Math.sqrt(Math.pow(this.projectionX, 2) + Math.pow(this.projectionY, 2));
	}

}
