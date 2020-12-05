package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;


public class CraftFactoryPreTest {

	/* Comprueba la correcta creación de todos los Craft para todas las orientaciones */
	@Test
	public void testCreateCraftOk() {
		Craft craft;
		String [] crafts = {"Destroyer", "Cruiser", "Carrier", "Battleship", "Fighter", "Transport", "Bomber"};

		for(int i = 0; i < crafts.length; i++) {
			String s = crafts[i];
			for (Orientation orient : Orientation.values()) {
				craft = CraftFactory.createCraft(s, orient);
				try {
					Class<?> c;
					if( i < 4) {
						c = Class.forName("model.ship." + s);
					}
					else {
						c = Class.forName("model.aircraft." + s);
					}
					assertEquals(craft.getClass(), c);
					assertEquals(craft.getOrientation(), orient);
					
				} 
				catch (ClassNotFoundException e) {
					fail("error clase no encontrada " + e.getMessage());
				}
			}
		}
	}
	@Test
	public void testCreateCraftWrong() {
		assertNull(CraftFactory.createCraft("grande", Orientation.EAST));
	}
}
