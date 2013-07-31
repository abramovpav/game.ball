package by.bsuir.iit.abramov.game.ball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import by.bsuir.iit.abramov.game.ball.Observable;
import by.bsuir.iit.abramov.game.ball.controller.Controller;

public class Desktop extends JPanel implements Observable{
	public Window window;
	private Controller observer;
	
	public Desktop(Window window) {
		this.window = window;
		initialize();
	}
	
	
	private void initialize() {
		
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		Graphics2D g2d = (Graphics2D)gr;
		
		g2d.drawLine(0, 0, 100, 100);
	}
	
	@Override
	public void addObserver(Observer observer) {

		if (Controller.class == observer.getClass()) {
			observer = (Controller)observer;
		}
	}

	@Override
	public void removeObserver() {

		observer = null;
		
	}

}
