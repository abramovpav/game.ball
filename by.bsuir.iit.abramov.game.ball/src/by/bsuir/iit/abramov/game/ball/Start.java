package by.bsuir.iit.abramov.game.ball;
import java.util.Observer;

import by.bsuir.iit.abramov.game.ball.controller.Controller;
import by.bsuir.iit.abramov.game.ball.model.Model;
import by.bsuir.iit.abramov.game.ball.view.Window;


public class Start {
	
	public static void main(String[] args) {

		Window window = new Window();
		window.setVisible(true);
		
		Model model = new Model();
		
		Observer observer = new Controller(window.getDesktop(), model);
		window.getDesktop().addObserver(observer);
		model.addObserver(observer);

	}

}
