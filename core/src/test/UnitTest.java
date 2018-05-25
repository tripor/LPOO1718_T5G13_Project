package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logic.Map;
import logic.entities.FactoryL;

class UnitTest {
	
	@Test
	void initMap() {	
		assertEquals(80, (new Map(80, 60)).getMapWidth());
		assertEquals(60, (new Map(80, 60)).getMapHeight());
		
		assertEquals(40, (new Map(40, 70)).getMapWidth());
		assertEquals(70, (new Map(40, 70)).getMapHeight());
	}
	
	@Test
	void tryFactory() {
		Map map = new Map(800, 600);
		FactoryL fac = new FactoryL(40, 40, 3);
		assertEquals(0, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
		
		map.addMap(fac);
		assertEquals(1, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
		assertEquals(fac, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).get(0));

		map.removeMap(fac);
		assertEquals(0, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
	}

}
