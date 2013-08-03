package by.bsuir.iit.abramov.game.ball.controller;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.model.Model;
import by.bsuir.iit.abramov.game.ball.model.Vector;
import by.bsuir.iit.abramov.game.ball.view.Desktop;

public class Controller implements Observer {

	private final Desktop	desktop;
	private final Model		model;

	public Controller(final Desktop desktop, final Model model) {

		super();
		this.desktop = desktop;
		this.model = model;
	}

	public Vector getAccelerationVector() {

		return model.getAccelerationVector();
	}

	public int getBallX() {

		return model.getBallX();
	}

	public int getBallY() {

		return model.getBallY();
	}

	public int getHeight() {

		return desktop.getHeight();
	}

	public Vector getPowerOfFriction() {

		return model.getPowerOfFriction();
	}

	public Vector getSpeedVector() {

		return model.getSpeedVector();
	}

	public Vector getUserPower() {

		return desktop.getUserPower();
	}

	public int getWidth() {

		return desktop.getWidth();
	}

	public void run() {

		final Point coordinates = model.getCoordinates();
		desktop.setCoordinates(coordinates);
	}

	@Override
	public void update(final Observable o, final Object arg) {

		// TODO Auto-generated method stub

	}
}
