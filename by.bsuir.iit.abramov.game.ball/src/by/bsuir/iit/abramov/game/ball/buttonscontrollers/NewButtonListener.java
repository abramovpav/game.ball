package by.bsuir.iit.abramov.game.ball.buttonscontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.game.ball.Util.ExtJButton;
import by.bsuir.iit.abramov.game.ball.view.Desktop;

public final class NewButtonListener implements ActionListener {
	private static final int	sec	= 1000;

	@Override
	public void actionPerformed(final ActionEvent e) {

		ExtJButton button;
		final Object object = e.getSource();
		if (!ExtJButton.class.equals(object.getClass())) {
			System.out.println(NewButtonListener.class
					+ ": source isn't ExtJButton class");
			return;
		}
		button = (ExtJButton) object;
		if (!Desktop.class.equals(button.getContainer().getClass())) {
			System.out
					.println(NewButtonListener.class + ": container isn't Desktop type");
			return;
		}

		final Desktop desktop = (Desktop) button.getContainer();

		// NewUnitDialog dialog = new NewUnitDialog();
		// dialog.setVisible(true);
		final String newID = JOptionPane.showInputDialog("New ID");
		final int ID = Integer.parseInt(newID);
		desktop.newUnit(ID);

	}

}
