package controller;

import model.Enums.Layout;
import model.Model;
import view.View;

/**
 * This static class is the Controller for the application, following MVC principles. The view and model are defined and stored here.
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Controller {

	private static Model model;
	private static View view;

	///////////////////////////////////////////////////
	// CONSTRUCTOR									//
	/////////////////////////////////////////////////

	public static void initController(String[] args, Layout layout) {
		try {
			setModel(layout);
			setView();
			View.launchGUI(args);
			System.out.println("MVC successfully initialised");
		} catch (InstantiationError e) {
			System.err.println("Program MVC initialisation not successful");
			System.err.println(e.getMessage());
		}
	}

	///////////////////////////////////////////////////
	// GETTERS N SETTERS							//
	/////////////////////////////////////////////////

	/**
	 * @return the m
	 */
	public static Model getModel() {
		return model;
	}

	/**
	 * @param m the m to set
	 */
	private static void setModel(Layout layout) {
		model = new Model(layout);
	}

	/**
	 * @return the v
	 */
	public static View getView() {
		return view;
	}

	/**
	 * @param v the v to set
	 */
	public static void setView() throws InstantiationError {
		view = new View();
	}



}
