package controller;

import model.Enums.Layout;
import model.Model;
import view.View;
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
	
	public static void initController(String[] args) {
		setModel(Layout.NORMAL);
		setView();
		View.launchGUI(args);
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
	public static void setModel(Layout layout) throws NullPointerException {
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
	public static void setView() throws NullPointerException {
		view = new View();
	}

}
