package by.bsuir.iit.abramov.game.ball.controller;

import java.util.Observable;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.model.Model;
import by.bsuir.iit.abramov.game.ball.view.Desktop;

public class Controller implements Observer {

	private final Desktop desktop;
	private final Model model;
	public Controller(Desktop desktop, Model model) {

		super();
		this.desktop = desktop;
		this.model = model;
	}
	@Override
	public void update(Observable o, Object arg) {

		// TODO Auto-generated method stub
		
	}
	
	public int getWidth() {
		return desktop.getWidth();
	}
	
	public int getHeight() {
		return desktop.getHeight();
	}
}
