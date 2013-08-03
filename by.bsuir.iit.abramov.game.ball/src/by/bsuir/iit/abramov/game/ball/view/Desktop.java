package by.bsuir.iit.abramov.game.ball.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.MinusButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.StartButtonListener;
import by.bsuir.iit.abramov.game.ball.controller.Controller;
import by.bsuir.iit.abramov.game.ball.model.Vector;

public class Desktop extends JPanel implements Observable {

	private static final int	BALL_RADIUS	= 10;
	public Window				window;
	private Controller			observer;
	private int					x, y;
	private Vector				userPower;
	private Timer				activeTimer;

	public Desktop(final Window window) {

		this.window = window;
		// this.setLayout(new BorderLayout(0, 0));
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

		final double angle = userPower.getAngle();
		final double length = userPower.getLength() - 1;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	public int getBallX() {

		return observer.getBallX();
	}

	public int getBallY() {

		return observer.getBallY();
	}

	public final Controller getObserver() {

		return observer;
	}

	public Vector getUserPower() {

		return userPower;
	}

	public void incUserPowerLength() {

		final double angle = userPower.getAngle();
		final double length = userPower.getLength() + 1;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	private void initialize() {

		userPower = new Vector(50, 0);
		JButton button = new JButton("Start");
		button.setLocation(400, 400);
		this.add(button);
		button.addActionListener(new StartButtonListener());
		button = new JButton("-");
		button.addActionListener(new MinusButtonListener());
		this.add(button);
		x = getWidth() / 2;
		y = getHeight() / 2;
	}

	public void mouseMoved(int x, int y) {

		x = x - getBallX();
		y = y - getBallY();

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
			// angle *= -1;
			angle += Math.PI;
		} else if (x > 0 && y < 0) {
			angle += 2 * Math.PI;
		}
		// System.out.println("angle = " + angle);
		if (angle != null) {
			final double length = userPower.getLength();
			userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
		}
	}

	@Override
	public void paintComponent(final Graphics gr) {

		final Graphics2D g2d = (Graphics2D) gr;
		g2d.clearRect(0, 0, getWidth(), getHeight());

		g2d.drawOval(x, y, Desktop.BALL_RADIUS * 2, Desktop.BALL_RADIUS * 2);
		g2d.drawLine(x + Desktop.BALL_RADIUS, y + Desktop.BALL_RADIUS, x
				+ Desktop.BALL_RADIUS + (int) userPower.getProjectionX(), y
				+ Desktop.BALL_RADIUS + (int) userPower.getProjectionY());
		final int centerX = getWidth() / 2, centerY = getHeight() / 2;
		Double angle;

		// draw F
		g2d.setColor(Color.BLACK);
		angle = userPower.getAngle();
		g2d.drawLine(centerX + Desktop.BALL_RADIUS, centerY + Desktop.BALL_RADIUS,
				centerX + Desktop.BALL_RADIUS + (int) (Math.cos(angle) * 50), centerY
						+ Desktop.BALL_RADIUS + (int) (Math.sin(angle) * 50));
		// g2d.drawLine(centerX + BALL_RADIUS, centerY + BALL_RADIUS, centerX +
		// BALL_RADIUS + (int)userPower.getProjectionX(), centerY + BALL_RADIUS
		// + (int)userPower.getProjectionY());

		// draw a
		final Vector acceleration = observer.getAccelerationVector();
		angle = acceleration.getAngle();
		if (angle != null) {
			g2d.setColor(Color.BLUE);
			g2d.drawLine(centerX + Desktop.BALL_RADIUS, centerY + Desktop.BALL_RADIUS,
					centerX + Desktop.BALL_RADIUS + (int) (Math.cos(angle) * 50), centerY
							+ Desktop.BALL_RADIUS + (int) (Math.sin(angle) * 50));
			// g2d.drawLine(centerX + BALL_RADIUS, centerY + BALL_RADIUS,
			// centerX + BALL_RADIUS + (int)acceleration.getProjectionX(),
			// centerY + BALL_RADIUS + (int)acceleration.getProjectionY());
		}

		// draw v
		final Vector speed = observer.getSpeedVector();
		angle = speed.getAngle();
		if (angle != null) {
			g2d.setColor(Color.RED);
			g2d.drawLine(centerX + Desktop.BALL_RADIUS, centerY + Desktop.BALL_RADIUS,
					centerX + Desktop.BALL_RADIUS + (int) (Math.cos(angle) * 50), centerY
							+ Desktop.BALL_RADIUS + (int) (Math.sin(angle) * 50));
			// g2d.drawLine(centerX + BALL_RADIUS, centerY + BALL_RADIUS,
			// centerX + BALL_RADIUS + (int)speed.getProjectionX(), centerY +
			// BALL_RADIUS + (int)speed.getProjectionY());
		}

		// draw frictionPower
		final Vector friction = observer.getPowerOfFriction();
		angle = friction.getAngle();
		if (angle != null) {
			g2d.setColor(Color.YELLOW);
			g2d.drawLine(centerX + Desktop.BALL_RADIUS, centerY + Desktop.BALL_RADIUS,
					centerX + Desktop.BALL_RADIUS + (int) (Math.cos(angle) * 50), centerY
							+ Desktop.BALL_RADIUS + (int) (Math.sin(angle) * 50));
		}
	}

	public void recordActivetimer(final Timer timer) {

		activeTimer = timer;
	}

	@Override
	public void removeObserver() {

		observer = null;

	}

	public void setCoordinates(final int x, final int y) {

		this.x = x;
		this.y = y;
	}

	public void setCoordinates(final Point point) {

		x = point.x;
		y = point.y;
		repaint();
	}

	public void turnLeftUserPower() {

		final double length = userPower.getLength();
		final double angle = userPower.getAngle() + Math.PI / 90;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

	public void turnRightUserPower() {

		final double length = userPower.getLength();
		final double angle = userPower.getAngle() - Math.PI / 90;
		userPower.setProjections(length * Math.cos(angle), length * Math.sin(angle));
	}

}
