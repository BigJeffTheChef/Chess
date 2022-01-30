package controller;

import model.Enums.Layout;
import model.Model;
import view.View;

public class Controller {

	private static Model model;
	private static View view;
	
	public static void main(String[] args) {
		model = new Model(Layout.NORMAL);
		view = new View(args);
	}
}
