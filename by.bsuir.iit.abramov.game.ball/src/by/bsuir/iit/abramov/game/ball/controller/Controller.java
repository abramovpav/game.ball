package by.bsuir.iit.abramov.game.ball.controller;

import java.awt.Point;
import java.util.Map;
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

	public Vector getAccelerationVector(final int ID) {

		return model.getAccelerationVector(ID);
	}

	public int getBallX(final int ID) {

		return model.getBallX(ID);
	}

	public int getBallY(final int ID) {

		return model.getBallY(ID);
	}

	public int getHeight() {

		return desktop.getHeight();
	}

	public Vector getPowerOfFriction(final int ID) {

		return model.getFrictionVector(ID);
	}

	public Vector getSpeedVector(final int ID) {

		return model.getSpeedVector(ID);
	}

	public Vector getUserPower(final int ID) {

		return desktop.getUserPower(ID);
	}

	public int getWidth() {

		return desktop.getWidth();
	}

	public void newUnit(final int ID) {

		model.newUnit(ID);
	}

	public void reset() {

		model.reset();
	}

	public void run() {

		final Map<Integer, Point> coordinates = model.getCoordinates();
		desktop.setCoordinates(coordinates);
	}

	@Override
	public void update(final Observable o, final Object arg) {

		// TODO Auto-generated method stub

	}
}
