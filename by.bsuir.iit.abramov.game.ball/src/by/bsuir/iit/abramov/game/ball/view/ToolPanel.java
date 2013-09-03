package by.bsuir.iit.abramov.game.ball.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import by.bsuir.iit.abramov.game.ball.MyListSelectionListener;
import by.bsuir.iit.abramov.game.ball.Factory.ButtonListenerFactory;
import by.bsuir.iit.abramov.game.ball.Util.ExtJButton;
import by.bsuir.iit.abramov.game.ball.Util.ToolButtons;
import by.bsuir.iit.abramov.game.ball.model.Ball;

public class ToolPanel extends JToolBar {
	private final Map<String, JButton>	buttons;
	private final ContentPanel			parent;
	private final JList					list;
	private final DefaultListModel		listModel;

	public ToolPanel(final ContentPanel parent) {

		this.parent = parent;
		buttons = new HashMap<String, JButton>();
		initialize();
		setOrientation(SwingConstants.VERTICAL);
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.addListSelectionListener(new MyListSelectionListener(parent.getDesktop()));

		this.add(list);

	}

	private void addBall(final Ball ball) {

		listModel.addElement("Ball: ID = " + ball);
	}

	private void createButtons() {

		ExtJButton button;
		final ButtonListenerFactory factory = new ButtonListenerFactory();
		for (final ToolButtons tButton : ToolButtons.values()) {
			if (tButton.isEnabled()) {
				button = new ExtJButton(tButton.getName(), parent.getDesktop());
				this.add(button);
				buttons.put(tButton.getName(), button);
				try {
					button.addActionListener(factory.createButtonListener(tButton));
				} catch (final IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void initialize() {

		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		// setLayout(new BorderLayout(0, 0));
		createButtons();
	}

}
