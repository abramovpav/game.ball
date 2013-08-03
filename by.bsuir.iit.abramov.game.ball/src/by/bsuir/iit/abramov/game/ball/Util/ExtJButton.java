package by.bsuir.iit.abramov.game.ball.Util;

import javax.swing.JButton;

public class ExtJButton extends JButton {
	private final Object	container;

	public ExtJButton(final String name, final Object container) {

		super(name);
		this.container = container;
	}

	public final Object getContainer() {

		return container;
	}
}
