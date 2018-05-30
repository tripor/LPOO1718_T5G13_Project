package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import graphic.Console;
import logic.*;
import logic.entities.*;

public class UnitTest {

	@Test
	public void initMap() {	
		int mapWidth = (Map.division * 200) + 2,
			mapHeight = (Map.division * 200) + 2;
		
		Map map = new Map(mapWidth);
		assertEquals(mapWidth, map.getMapWidth());
		assertEquals(mapHeight, map.getMapHeight());
	}
	
	@Test
	public void tryRecreateMap() {
		
		int width = 60, height = 60;

		Map map = new Map(width);
		assertTrue(map.addMap(new FactoryL(4,4,3)));
		assertFalse(map.addMap(new FactoryL(4,4,3)));
		
		PersonL p = new PersonL();
		map.addEntityLista(p);
		map.removeMap(p);
		
		map.addEntityLista(new MaterialL());
		assertEquals(((width/Map.division) * (height/Map.division)), map.recreateMap());
	}
	
	@Test
	public void convertPixelToBlock() {
		int pixel = 14;
		
		Map map = new Map(1);
		assertEquals((pixel / Map.division), map.transformToBlock(pixel));
	}
	
	@Test
	public void tryFactory() {
		int x = 4, y = 8, doorAtBorder = 3;
		int x2 = 2, y2 = 5;
		
		Map map = new Map(60);
		
		FactoryL fac = new FactoryL(x, y, doorAtBorder);
		assertEquals(x, fac.getPosX());
		assertEquals(y, fac.getPosY());
		assertEquals((x + fac.getWidth()), fac.getRight());
		assertEquals((y - fac.getHeight()), fac.getTop());
		assertEquals(doorAtBorder, fac.getDoorAtBorder());
		
		fac.setPosition(x2, y2);
		assertEquals(x2, fac.getPosX());
		assertEquals(y2, fac.getPosY());
		assertEquals((x2 + fac.getWidth()), fac.getRight());
		assertEquals((y2 - fac.getHeight()), fac.getTop());
		
		fac.setPosition(x, y);		
		assertEquals(0, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);

		assertTrue(map.addMap(fac));
		assertEquals(1, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
		assertTrue(map.pointIsOccupied(fac.getPosX(), fac.getPosY()));
		assertEquals(fac, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).get(0));

		FactoryL fac2 = new FactoryL(x, y, doorAtBorder);
		assertFalse(map.addMap(fac2));
		
		map.removeMap(fac);
		assertEquals(0, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
	}
	
	@Test
	public void tryMaterial() {
		String typeText = "testing";
		int moveX = -1, moveY = 1;
		
		FactoryL fac = new FactoryL(4, 8, 3);
		
		Map map = new Map(60);
		assertTrue(map.addMap(fac));
		
		MaterialL mat = new MaterialL();
		
		mat.setType(typeText);
		assertEquals(typeText, mat.getType());

		fac.addToStorage(mat);

		MaterialL mat2 = new MaterialL();
		mat2.setType(typeText);
		fac.addToStorage(mat2);
		
		int mX = mat2.getPosX(), mY = mat.getPosY();
		
		mat2.moveMaterial(moveX, moveY, map);
		assertEquals((mX + moveX), mat2.getPosX());
		assertEquals((mY + moveY), mat2.getPosY());

		assertEquals(mat, fac.removeMaterial(typeText));
		assertEquals(mat2, fac.removeMaterial("any"));
		assertNull(fac.removeMaterial("any"));
		
		fac.addToStorage(mat);
		fac.addToStorage(mat2);
		
		map.recreateMap();
	}
	
	@Test
	public void tryInserter() {
		
		int x = 3, y = 4, direction = 1;
		// direction = 1|2|3|4
		
		FactoryL fac = new FactoryL(4, 8, 3);
		
		Map map = new Map(60);
		assertTrue(map.addMap(fac));
		
		InserterL ist = new InserterL(x, y, direction);
		ist.handler(map);
		
		new InserterL();
	}
	
	@Test
	public void tryBudget() {
		Map map = new Map(700);
		
		for(int i=0; i<10; i+=50) {
			for(int j=0; j<10; j+=50) {
				assertTrue(map.addEntityLista(new FactoryL(i, j, 2)));
			}
		}
	}
	
	@Test
	public void tryConveyor() {
		
		int x = 3, y = 4;
		
		FactoryL fac = new FactoryL(4, 8, 3);
		
		Map map = new Map(60);
		assertTrue(map.addMap(fac));
		
		int direction;
		
		direction = 1;
		ConveyorL cvy = new ConveyorL(x, y, direction);
		assertEquals(direction, cvy.getDirection());
		assertEquals(1, cvy.getMovementY());
		assertEquals(0, cvy.getMovementX());
		
		direction = 2;
		cvy = new ConveyorL(x, y, direction);
		assertEquals(0, cvy.getMovementY());
		assertEquals(1, cvy.getMovementX());
		
		direction = 3;
		cvy = new ConveyorL(x, y, direction);
		assertEquals(-1, cvy.getMovementY());
		assertEquals(0, cvy.getMovementX());
		
		direction = 4;
		cvy = new ConveyorL(x, y, direction);
		assertEquals(0, cvy.getMovementY());
		assertEquals(-1, cvy.getMovementX());
		
		map.addMap(new MaterialL());
		
		cvy.moveMaterials(map);
		assertFalse(map.lista_background.first().first().getMaterial());
	}
	
	@Test
	public void tryMine() {
		/*int x = 4, y = 8, doorAtBorder = 3;
		
		Map map = new Map(60, 60);
		
		MineL mine = new MineL(x, y, doorAtBorder);
		assertTrue(map.addMap(mine));
		
		int i; 
		
		for(i = 0; i < 99; i++) {
			assertFalse(mine.handler());
		}
		assertTrue(mine.handler());*/
	}
	
	@Test
	public void tryHouseAndPerson() {

		int from_x = 4, from_y = 8,
			to_x = 30, to_y = 20;
		
		Map map = new Map(80);
		
		HouseL h = new HouseL(from_x, from_y, 2);
		FactoryL f = new FactoryL(to_x, to_y, 3);
		
		map.addMap(h);
		map.addMap(f);
		
		h.handler(map);
		// including add person
		
		for(int i=0; i<100; i++) {
			map.lista_person.first().getPath();
		}
	}
	
	
	@Test
	public void tryLoadGame() {
		String name = "test";
		int size = 60;
		
		SaveState s = new SaveState();
		Map map = new Map(size);
		
		s.saveGame(name, map);
		
		Map m = s.loadGame(name);
		assertEquals(size, m.getMapWidth());
		assertEquals(size, m.getMapHeight());
	}

}
