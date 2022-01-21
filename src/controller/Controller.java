package controller;

import javax.swing.SwingUtilities;

import model.Model;
import view.ChessGUI;

@SuppressWarnings("unused")
public class Controller {

	private static ChessGUI view;
	private static Model model;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initialise();
			}
		});
	}

	public static void initialise() {
		view = new ChessGUI();
		model = new Model();
	}
}
