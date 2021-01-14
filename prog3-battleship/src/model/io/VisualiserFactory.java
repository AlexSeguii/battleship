package model.io;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.Craft;
import model.Game;
import model.Orientation;


/**
 * A factory for creating Visualiser objects.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class VisualiserFactory {
	
	/**
	 * Creates a new Visualiser object.
	 *
	 * @param n the n
	 * @param g the g
	 * @return the i visualiser
	 */
	public static IVisualiser createVisualiser(String n, Game g) {
		IVisualiser nave = null;
		Constructor<?> c = null;
		Class<?> clase = null;
		
		try {
			clase = Class.forName("model.io.Visualiser" + n);
		}
		catch (ClassNotFoundException e) {}
			
		if(clase != null) {
			Class<?> [] arg = {Game.class};
			
			try {
				c = clase.getDeclaredConstructor(arg);
				Object [] parametros = {g};
				try {
					nave = (IVisualiser) c.newInstance(parametros);
				}
				catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			} 
			catch (NoSuchMethodException e) {
			} 
			catch (SecurityException e) {
			}
		}
		return nave;
	}
}
