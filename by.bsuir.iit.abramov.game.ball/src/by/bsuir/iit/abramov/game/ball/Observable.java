package by.bsuir.iit.abramov.game.ball;

import java.util.Observer;

public interface Observable {
	public void addObserver(Observer observer);
	public void removeObserver();
	
}
