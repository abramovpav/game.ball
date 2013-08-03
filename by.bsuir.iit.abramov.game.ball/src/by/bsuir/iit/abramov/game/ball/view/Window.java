package by.bsuir.iit.abramov.game.ball.view;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final String	VAR_TITLE	= "alpha";
	private static final String	CONST_TITLE	= "Ball v. ";

	private final ContentPanel	contentPanel;

	public Window() {

		super(Window.CONST_TITLE + Window.VAR_TITLE);
		setBounds(0, 0, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel = new ContentPanel(this);
		setContentPane(contentPanel);
	}

	public final Desktop getDesktop() {

		return contentPanel.getDesktop();
	}

}
