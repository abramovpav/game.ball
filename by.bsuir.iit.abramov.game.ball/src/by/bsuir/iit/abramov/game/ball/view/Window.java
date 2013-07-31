package by.bsuir.iit.abramov.game.ball.view;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final String	VAR_TITLE	= "alpha";
	private static final String	CONST_TITLE	= "Ball v. ";
	Desktop desktop;
	
	public Window() {
		super(CONST_TITLE + VAR_TITLE);
		setBounds(0, 0, 800,500);
		desktop = new Desktop(this);
		setContentPane(desktop);
	}
	
	public Desktop getDesktop() {
		return desktop;
	}

}
