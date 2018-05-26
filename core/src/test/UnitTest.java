package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.*;
import logic.entities.*;

public class UnitTest {

	@Test
	public void initMap() {	
		int mapWidth = (Map.division * 2) + 2,
			mapHeight = (Map.division * 2) + 2;
		
		Map map = new Map(mapWidth, mapHeight);
		assertEquals(mapWidth, map.getMapWidth());
		assertEquals(mapHeight, map.getMapHeight());
		
		map.recreateMap();
		assertEquals(mapWidth, map.getMapWidth());
		assertEquals(mapHeight, map.getMapHeight());
	}
	
	@Test
	public void convertPixelToBlock() {
		int pixel = 14;
		
		Map map = new Map(1, 1);
		assertEquals((pixel / Map.division), map.transformToBlock(pixel));
	}
	
	@Test
	public void tryFactory() {
		int x = 4, y = 8, doorAtBorder = 3;
		int x2 = 2, y2 = 5;
		
		Map map = new Map(60, 60);
		
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

		map.addMap(fac);
		assertEquals(1, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
		// assertTrue(map.pointIsOccupied(fac.getPosX(), fac.getPosY()));
		assertEquals(fac, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).get(0));

		map.removeMap(fac);
		assertEquals(0, map.getMapPercisionPixel(fac.getPosX(), fac.getPosY()).size);
	}
	
	@Test
	public void tryMaterial() {
		String typeText = "testing";
		int moveX = -1, moveY = 1;
		
		FactoryL fac = new FactoryL(4, 8, 3);
		
		Map map = new Map(60, 60);
		map.addMap(fac);
		
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
		
		Map map = new Map(60, 60);
		map.addMap(fac);
		
		InserterL ist = new InserterL(x, y, direction);
		ist.handler(map);
	}
	
	@Test
	public void tryConveyor() {
		
		int x = 3, y = 4, direction = 1;
		// direction = 1|2|3|4
		
		FactoryL fac = new FactoryL(4, 8, 3);
		
		Map map = new Map(60, 60);
		map.addMap(fac);
		
		ConveyorL cvy = new ConveyorL(x, y, direction);
		assertEquals(direction, cvy.getDirection());
		
		cvy.moveMaterials(map);
	}
	
	@Test
	public void tryMine() {
		int x = 4, y = 8, doorAtBorder = 3;
		
		Map map = new Map(60, 60);
		
		MineL mine = new MineL(x, y, doorAtBorder);
		map.addMap(mine);
		mine.handler();
	}
	
	@Test
	public void tryPerson() {
		int from_x = 4, from_y = 8,
			to_x = 30, to_y = 20;
		
		Map map = new Map(60, 60);
		
		PersonL ps = new PersonL(from_x, from_y, map);
		map.addMap(ps);
		assertEquals((from_x + ps.getWidth() / 2), ps.getPosX());
		
		map.removeMap(ps);
		
		assertTrue(ps.getPath(to_y, to_x).size() > 0);
		
		Node n = ps.popPath();
		assertNotNull(n);
		
		n.calculateHeuristic(new Node(to_y, to_x));
		n.setNodeData(new Node(from_x, from_y), 5);
		assertNotNull(n.checkBetterPath(new Node(from_x, from_y), 5));
	}

}
