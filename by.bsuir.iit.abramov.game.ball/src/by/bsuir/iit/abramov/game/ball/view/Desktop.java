package by.bsuir.iit.abramov.game.ball.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JPanel;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;
import by.bsuir.iit.abramov.game.ball.model.Vector;

public class Desktop extends JPanel implements Observable {

	private static final Color			COLOR_DEFAULT			= Color.BLACK;
	private static final Color			COLOR_POWER_OF_FRICTION	= Color.GREEN;
	private static final Color			COLOR_SPEED				= Color.RED;
	private static final Color			COLOR_ACCELERATION		= Color.BLUE;
	private static final Color			COLOR_USER_POWER		= Desktop.COLOR_DEFAULT;
	private static final int			DEFAULT_USER_POWER		= 50;
	private static final int			BALL_IMAGE_RADIUS		= 10;
	public Window						window;
	private Controller					observer;
	private Map<Integer, Point>			ballCoordinates;
	private Timer						activeTimer;
	private int							selectedBallID;
	private final Map<Integer, Vector>	userPowerVectors;

	public Desktop(final Window window) {

		this.window = window;
		userPowerVectors = new HashMap<Integer, Vector>();
		initialize();
	}

	@Override
	public void addObserver(final Observer observer) {

		if (Controller.class.equals(observer.getClass())) {
			this.observer = (Controller) observer;
		}
	}

	public void cleanTimer() {

		if (activeTimer != null) {
			activeTimer.cancel();
		}
		activeTimer = null;
	}

	public void decUserPowerLength() {

		final Vector userPower = getUserPower(selectedBallID);
		final double angle = userPower.getAngle();
		final double length = userPower.getLength() - 1;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	private void drawPower(final Graphics2D g2d, final int centerBallX,
			final int centerBallY, final Vector vector, final Color color) {

		Double angle;
		angle = vector.getAngle();
		g2d.setColor(color);
		if (angle != null) {
			g2d.drawLine(centerBallX, centerBallY, centerBallX
					+ (int) (Math.cos(angle) * Desktop.DEFAULT_USER_POWER), centerBallY
					+ (int) (Math.sin(angle) * Desktop.DEFAULT_USER_POWER));
		}
	}

	public int getBallX(final int ID) {

		return observer.getBallX(ID);
	}

	public int getBallY(final int ID) {

		return observer.getBallY(ID);
	}

	public final Controller getObserver() {

		return observer;
	}

	public Vector getUserPower(final int ID) {

		if (userPowerVectors.containsKey(ID)) {
			return userPowerVectors.get(ID);
		} else {
			return null;
		}
	}

	public void incUserPowerLength() {

		final Vector userPower = getUserPower(selectedBallID);
		final double angle = userPower.getAngle();
		final double length = userPower.getLength() + 1;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	private void initialize() {

		final Vector userPower = new Vector(Desktop.DEFAULT_USER_POWER, 0);
		userPowerVectors.put(0, userPower);
		ballCoordinates = new HashMap<Integer, Point>();
		ballCoordinates.put(0, new Point(getWidth() / 2, getHeight() / 2));
	}

	public void mouseMoved(int x, int y) {

		final Vector userPower = getUserPower(selectedBallID);
		x = x - getBallX(selectedBallID);
		y = y - getBallY(selectedBallID);

		Double angle;
		if (x != 0) {
			if (y == 0 && x < 0) {
				angle = Math.PI;
			} else {
				angle = Math.atan((double) y / (double) x);
			}
		} else {
			if (y == 0) {
				angle = null;
				return;
			}
			angle = y > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
		}

		if (x < 0 && y < 0) {
			angle += Math.PI;
		} else if (x < 0 && y > 0) {
			angle += Math.PI;
		} else if (x > 0 && y < 0) {
			angle += 2 * Math.PI;
		}

		if (angle != null) {
			final double length = userPower.getLength();
			userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
		}
	}

	@Override
	public void paintComponent(final Graphics gr) {

		final Graphics2D g2d = (Graphics2D) gr;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		int x, y;

		for (final Integer ID : ballCoordinates.keySet()) {
			final Point point = ballCoordinates.get(ID);
			x = point.x;
			y = point.y;
			g2d.setColor(Desktop.COLOR_DEFAULT);
			g2d.drawOval(x, y, Desktop.BALL_IMAGE_RADIUS * 2,
					Desktop.BALL_IMAGE_RADIUS * 2);
			final int centerBallX = x + Desktop.BALL_IMAGE_RADIUS;
			final int centerBallY = y + Desktop.BALL_IMAGE_RADIUS;

			// draw a
			drawPower(g2d, centerBallX, centerBallY, observer.getAccelerationVector(ID),
					Desktop.COLOR_ACCELERATION);

			// draw v
			drawPower(g2d, centerBallX, centerBallY, observer.getSpeedVector(ID),
					Desktop.COLOR_SPEED);

			// draw frictionPower
			drawPower(g2d, centerBallX, centerBallY, observer.getPowerOfFriction(ID),
					Desktop.COLOR_POWER_OF_FRICTION);

			// draw F
			drawPower(g2d, centerBallX, centerBallY, getUserPower(ID),
					Desktop.COLOR_USER_POWER);

			// draw mark
			// int markX = x < 0 ? DEFAULT_USER_POWER : (x > getWidth() ?
			// getWidth()
			// - DEFAULT_USER_POWER : x);
			// int markY = y < 0 ? DEFAULT_USER_POWER : (y > getHeight() ?
			// getHeight() - DEFAULT_USER_POWER : y);
			// drawPower(g2d, markX, markY, observer.getSpeedVector(),
			// COLOR_SPEED);
			final String text = "F = "
					+ Double.toString(getUserPower(selectedBallID).getLength()) + ";v = "
					+ observer.getSpeedVector(selectedBallID).getLength();
			g2d.drawString(text, getWidth() - text.length() * 7 - 10, getHeight() - 2);
		}
	}

	public void recordActivetimer(final Timer timer) {

		activeTimer = timer;
	}

	@Override
	public void removeObserver() {

		observer = null;
	}

	public void reset() {

		observer.reset();
		cleanTimer();
		for (final Integer ID : ballCoordinates.keySet()) {
			final Point point = ballCoordinates.get(ID);
			point.x = observer.getBallX(ID);
			point.y = observer.getBallY(ID);
		}
		for (final Vector userPower : userPowerVectors.values()) {
			userPower.setProjections(Desktop.DEFAULT_USER_POWER, 0);
		}
		selectedBallID = 0;
		repaint();
	}

	public void setCoordinates(final Map<Integer, Point> points) {

		ballCoordinates.clear();
		ballCoordinates.putAll(points);
		repaint();
	}

	public void turnLeftUserPower() {

		final Vector userPower = getUserPower(selectedBallID);
		final double length = userPower.getLength();
		final double angle = userPower.getAngle() + Math.PI / 90;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	public void turnRightUserPower() {

		final Vector userPower = getUserPower(selectedBallID);
		final double length = userPower.getLength();
		final double angle = userPower.getAngle() - Math.PI / 90;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

}
