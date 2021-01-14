package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * A factory for creating Craft objects.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class CraftFactory {
	
	/**
	 * Creates a new Craft object.
	 *
	 * @param type the type
	 * @param orientation the orientation
	 * @return the craft
	 */
	public static Craft createCraft(String type, Orientation orientation) {
		Craft nave = null;
		Constructor<?> c = null;
		Class<?> clase = null;
		
		try {
			clase = Class.forName("model." + type);
		}
		catch (ClassNotFoundException e) {}
			
		if(clase != null) {
			Class<?> [] arg = {Orientation.class};
			
			try {
				c = clase.getDeclaredConstructor(arg);
				Object [] parametros = {orientation};
				try {
					nave = (Craft) c.newInstance(parametros);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
