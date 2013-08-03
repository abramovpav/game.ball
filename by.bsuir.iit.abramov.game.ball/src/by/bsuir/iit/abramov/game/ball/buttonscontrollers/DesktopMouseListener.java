package by.bsuir.iit.abramov.game.ball.buttonscontrollers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import by.bsuir.iit.abramov.game.ball.view.Desktop;

public class DesktopMouseListener implements MouseMotionListener {

	@Override
	public void mouseDragged(final MouseEvent e) {

		Desktop desktop;
		final Object object = e.getSource();
		if (!Desktop.class.equals(object.getClass())) {
			System.out.println(DesktopMouseListener.class
					+ ": source isn't Desktop class");
			return;
		}
		desktop = (Desktop) object;
		desktop.mouseMoved(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(final MouseEvent e) {

		// TODO Auto-generated method stub

	}

}
