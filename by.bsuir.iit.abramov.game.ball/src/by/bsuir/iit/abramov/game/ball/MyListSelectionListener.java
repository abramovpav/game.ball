package by.bsuir.iit.abramov.game.ball;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import by.bsuir.iit.abramov.game.ball.view.Desktop;

public class MyListSelectionListener implements ListSelectionListener {

	private final Desktop	desktop;

	public MyListSelectionListener(final Desktop desktop) {

		this.desktop = desktop;
	}

	@Override
	public void valueChanged(final ListSelectionEvent e) {

		desktop.setSelectedID(e.getFirstIndex());

	}

}
