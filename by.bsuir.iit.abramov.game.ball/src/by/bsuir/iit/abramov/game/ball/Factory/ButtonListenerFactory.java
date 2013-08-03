package by.bsuir.iit.abramov.game.ball.Factory;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.game.ball.Util.ToolButtons;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.LeftButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.MinusButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.PlusButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.ResetButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.RightButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.StartButtonListener;
import by.bsuir.iit.abramov.game.ball.buttonscontrollers.StopButtonListener;

public class ButtonListenerFactory {
	public final ActionListener createButtonListener(final ToolButtons toolButton)
			throws IllegalArgumentException {

		switch (toolButton) {
			case MINUS:
				return new MinusButtonListener();
			case PLUS:
				return new PlusButtonListener();
			case RESET:
				return new ResetButtonListener();
			case START:
				return new StartButtonListener();
			case STOP:
				return new StopButtonListener();
			case Left:
				return new LeftButtonListener();
			case Right:
				return new RightButtonListener();
			default:
				throw new IllegalArgumentException();
		}
	}
}
