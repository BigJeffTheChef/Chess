package controller;

import javax.swing.SwingUtilities;

import model.Enums.Layout;
import model.Enums.Team;
import model.Model;

@SuppressWarnings("unused")
public class Controller {

	//private static ChessGUI view;
	private static Model model;

	public static void main(String[] args) {
			model = new Model(Layout.NORMAL);
	}
}
