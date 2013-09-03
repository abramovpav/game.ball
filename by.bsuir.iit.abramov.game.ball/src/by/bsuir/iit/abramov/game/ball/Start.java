package by.bsuir.iit.abramov.game.ball;

import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.controller.Controller;
import by.bsuir.iit.abramov.game.ball.model.Model;
import by.bsuir.iit.abramov.game.ball.view.Window;

public class Start {

	public static void main(final String[] args) {

		final Window window = new Window();

		final Model model = new Model();

		final Observer observer = new Controller(window.getDesktop(), model);
		window.getDesktop().addObserver(observer);
		model.addObserver(observer);
		window.setVisible(true);

	}

}
