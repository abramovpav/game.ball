package by.bsuir.iit.abramov.game.ball.buttonscontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.game.ball.Util.ExtJButton;
import by.bsuir.iit.abramov.game.ball.view.Desktop;

public final class PlusButtonListener implements ActionListener {
	private static final int	sec	= 1000;

	@Override
	public void actionPerformed(final ActionEvent e) {

		ExtJButton button;
		final Object object = e.getSource();
		if (!ExtJButton.class.equals(object.getClass())) {
			System.out.println(PlusButtonListener.class
					+ ": source isn't ExtJButton class");
			return;
		}
		button = (ExtJButton) object;
		if (!Desktop.class.equals(button.getContainer().getClass())) {
			System.out.println(PlusButtonListener.class
					+ ": container isn't Desktop type");
			return;
		}

		final Desktop desktop = (Desktop) button.getContainer();
		desktop.incUserPowerLength();
	}

}
