package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import logic.*;
import logic.entities.*;

public class UnitTest {

	/*@Test
	public void initMap() {	
		int mapWidth = (Map.division * 2) + 2,
			mapHeight = (Map.division * 2) + 2;
		
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
		
		cvy.moveMaterials(map);
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
	/*}
	
	@Test
	public void tryPerson() {

		// Note: Since A* applies MAX_BLOCKS, if origin and destination too far, the test result may incorrect.
		
		int from_x = 4, from_y = 8,
			to_x = 30, to_y = 20;
		
		Map map = new Map(60);
		
		PersonL ps = new PersonL(from_x, from_y, map);
		assertTrue(map.addMap(ps));
		assertEquals((from_x + ps.getWidth() / 2), ps.getPosX());
		assertEquals(ps.toString(), ps.toString());
		
		map.removeMap(ps);
		
		//	List<Node> path = ps.getPath(to_y, to_x);
		//	assertEquals(path.size(), ps.getPath(to_y, to_x).size());
		//	
		//	Node n = ps.popPath();
		//	assertEquals(path.get(0), n);
		//	
		//	n.calculateHeuristic(new Node(to_y, to_x));
		//	assertEquals((Math.abs(to_y - n.getRow()) + Math.abs(to_x - n.getCol())), n.getH());
		//	
		//	assertEquals(n.checkBetterPath(new Node(from_x, from_y), 10), n.checkBetterPath(new Node(from_x, from_y), 10));
	}
	
	@Test
	public void popPathWithMoreBlocks() {

		Map map = new Map(200);
		
		InserterL ist = new InserterL(5, 5, 1);
		map.addEntityLista(ist);
		
	//	PersonL ps = new PersonL(0, 0, map);
	//	ps.getPath(200, 200);
	//	
	//	for(int i=0; i<100; i++) {
	//		ps.popPath();
	//	}
	}
	
	@Test
	public void tryLoadGame() {
		String name = "test";
		int width = 60, height = 80;
		
		SaveState s = new SaveState();
		Map map = new Map(width);
		
		s.saveGame(name, map);
		
		//	Map m = s.loadGame(name);
		//	assertEquals(width, m.getMapWidth());
		//	assertEquals(height, m.getMapHeight());
	}*/

}
