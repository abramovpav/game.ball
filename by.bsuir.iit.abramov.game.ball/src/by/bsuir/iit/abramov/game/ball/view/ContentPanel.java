package by.bsuir.iit.abramov.game.ball.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	private final Window	parent;
	private final Desktop	desktop;
	private final ToolPanel	toolPanel;

	public ContentPanel(final Window parent) {

		super();
		desktop = new Desktop(parent);
		toolPanel = new ToolPanel(this);
		setLayout(new BorderLayout(0, 0));
		this.add(desktop, BorderLayout.CENTER);
		this.add(toolPanel, BorderLayout.EAST);
		this.parent = parent;
		initialize();
	}

	public final Desktop getDesktop() {

		return desktop;
	}

	private void initialize() {

	}

}
