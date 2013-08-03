package by.bsuir.iit.abramov.game.ball.Util;

import java.util.TimerTask;

import by.bsuir.iit.abramov.game.ball.view.Desktop;

public class MyTimerTask extends TimerTask {
	private final Desktop	desktop;

	public MyTimerTask(final Desktop desktop) {

		this.desktop = desktop;
	}

	@Override
	public void run() {

		desktop.getObserver().run();

	}

}
