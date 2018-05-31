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
		Map map = new Map(50);
		assertTrue(map==Map.singleton);
		assertEquals(50, map.getMapWidth());
		assertEquals(50, map.getMapHeight());
		assertEquals(0,map.getLista().size);
		assertEquals(0,map.getLista_material().size);
		assertEquals(0,map.getLista_material_toActor().size);
		assertEquals(0,map.getLista_person().size);
		assertEquals(0,map.getLista_person_toActor().size);
		assertEquals(5,map.getMap().size);
		assertEquals(5,map.getMap().get(0).size);
		assertEquals(0,map.getMap().get(0).get(0).size);
		assertEquals(5000,map.getMoney());
		assertEquals(0,map.getMoney_wasted());
		
	}
	@Test
	public void mapIsInsideMap() {	
		Map map = new Map(50);
		assertTrue(map.isInsideMap(0, 0));
		assertTrue(map.isInsideMap(4, 4));
		assertTrue(map.isInsideMap(2, 2));
		assertFalse(map.isInsideMap(-1, 0));
		assertFalse(map.isInsideMap(0, -1));
		assertFalse(map.isInsideMap(6, 2));
		assertFalse(map.isInsideMap(2, 6));
		assertFalse(map.isInsideMap(2, 5));
		assertFalse(map.isInsideMap(5, 2));
	}
	
	@Test
	public void mapSpawn() {	
		Map map = new Map(50);
		map.getLista_background().get(0).set(0,new BackGroundL(0,0,"land",0));
		map.getLista_background().get(0).set(1,new BackGroundL(0,0,"land",0));
		map.getLista_background().get(1).set(0,new BackGroundL(0,0,"land",0));
		map.getLista_background().get(1).set(1,new BackGroundL(0,0,"land",0));
		map.getLista_background().get(0).set(2,new BackGroundL(0,0,"land",0));
		map.getLista_background().get(2).set(0,new BackGroundL(0,0,"land",0));
		map.spawn(2, 0, 0, "iron_ore");
		assertEquals("iron_ore",map.getLista_background().get(0).get(0).getType());
		assertEquals("land_iron.png",map.getLista_background().get(0).get(0).getTypeLand());
		assertEquals(0*Map.division,map.getLista_background().get(0).get(0).getPosX());
		assertEquals(0*Map.division,map.getLista_background().get(0).get(0).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(0).get(1).getType());
		assertEquals("land_iron.png",map.getLista_background().get(0).get(1).getTypeLand());
		assertEquals(0*Map.division,map.getLista_background().get(0).get(1).getPosX());
		assertEquals(1*Map.division,map.getLista_background().get(0).get(1).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(1).get(0).getType());
		assertEquals("land_iron.png",map.getLista_background().get(1).get(0).getTypeLand());
		assertEquals(1*Map.division,map.getLista_background().get(1).get(0).getPosX());
		assertEquals(0*Map.division,map.getLista_background().get(1).get(0).getPosY());
		

		assertEquals("land",map.getLista_background().get(1).get(1).getType());
		assertEquals("land.png",map.getLista_background().get(1).get(1).getTypeLand());
		assertEquals("land",map.getLista_background().get(2).get(0).getType());
		assertEquals("land.png",map.getLista_background().get(2).get(0).getTypeLand());
		assertEquals("land",map.getLista_background().get(0).get(2).getType());
		assertEquals("land.png",map.getLista_background().get(0).get(2).getTypeLand());
		
	}
	@Test
	public void mapSpawn2() {	
		Map map = new Map(50);
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				map.getLista_background().get(i).set(j,new BackGroundL(0,0,"land",0));
			}
		}
		map.spawn(2, 2, 2, "iron_ore");
		assertEquals("iron_ore",map.getLista_background().get(2).get(2).getType());
		assertEquals("land_iron.png",map.getLista_background().get(2).get(2).getTypeLand());
		assertEquals(2*Map.division,map.getLista_background().get(2).get(2).getPosX());
		assertEquals(2*Map.division,map.getLista_background().get(2).get(2).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(1).get(2).getType());
		assertEquals("land_iron.png",map.getLista_background().get(1).get(2).getTypeLand());
		assertEquals(1*Map.division,map.getLista_background().get(1).get(2).getPosX());
		assertEquals(2*Map.division,map.getLista_background().get(1).get(2).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(3).get(2).getType());
		assertEquals("land_iron.png",map.getLista_background().get(3).get(2).getTypeLand());
		assertEquals(3*Map.division,map.getLista_background().get(3).get(2).getPosX());
		assertEquals(2*Map.division,map.getLista_background().get(3).get(2).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(2).get(1).getType());
		assertEquals("land_iron.png",map.getLista_background().get(2).get(1).getTypeLand());
		assertEquals(2*Map.division,map.getLista_background().get(2).get(1).getPosX());
		assertEquals(1*Map.division,map.getLista_background().get(2).get(1).getPosY());
		assertEquals("iron_ore",map.getLista_background().get(2).get(3).getType());
		assertEquals("land_iron.png",map.getLista_background().get(2).get(3).getTypeLand());
		assertEquals(2*Map.division,map.getLista_background().get(2).get(3).getPosX());
		assertEquals(3*Map.division,map.getLista_background().get(2).get(3).getPosY());
		
		assertEquals("land",map.getLista_background().get(1).get(1).getType());
		assertEquals("land.png",map.getLista_background().get(1).get(1).getTypeLand());
		assertEquals("land",map.getLista_background().get(2).get(0).getType());
		assertEquals("land.png",map.getLista_background().get(2).get(0).getTypeLand());
		assertEquals("land",map.getLista_background().get(0).get(2).getType());
		assertEquals("land.png",map.getLista_background().get(0).get(2).getTypeLand());
		assertEquals("land",map.getLista_background().get(1).get(3).getType());
		assertEquals("land.png",map.getLista_background().get(1).get(3).getTypeLand());
		assertEquals("land",map.getLista_background().get(2).get(4).getType());
		assertEquals("land.png",map.getLista_background().get(2).get(4).getTypeLand());
		assertEquals("land",map.getLista_background().get(3).get(3).getType());
		assertEquals("land.png",map.getLista_background().get(3).get(3).getTypeLand());
		assertEquals("land",map.getLista_background().get(4).get(2).getType());
		assertEquals("land.png",map.getLista_background().get(4).get(2).getTypeLand());
		assertEquals("land",map.getLista_background().get(3).get(1).getType());
		assertEquals("land.png",map.getLista_background().get(3).get(1).getTypeLand());
		
	}
	@Test
	public void mapCreateBackground() {	
		Map map=new Map(50);
		assertEquals(50/Map.division,map.getLista_background().size);
		assertEquals(50/Map.division,map.getLista_background().get(0).size);
		map.getLista_background().clear();
		map.createBackground();
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				assertEquals("land",map.getLista_background().get(i).get(j).getType());
				assertEquals(i*Map.division,map.getLista_background().get(i).get(j).getPosX());
			}
		}
	}
	
	@Test
	public void mapCreateBackground2() {	
		Map map=new Map(1000);
		int grass=0,iron=0,copper=0;
		for(int i=0;i<1000/Map.division;i++)
		{
			for(int j=0;j<1000/Map.division;j++)
			{
				if(map.getLista_background().get(i).get(j).getType().equals("iron_ore"))
				{
					iron++;
				}
				if(map.getLista_background().get(i).get(j).getType().equals("copper_ore"))
				{
					copper++;
				}
				if(map.getLista_background().get(i).get(j).getType().equals("grass"))
				{
					grass++;
				}
			}
		}
		assertTrue(grass!=0);
		assertTrue(iron!=0);
		assertTrue(copper!=0);
		
	}
	@Test
	public void mapRecreateMap() {	
		Map map=new Map(50);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		PersonL per=new PersonL(0,0);
		map.getLista_material().add(mat);
		map.getLista_material().add(mat);
		map.getLista_material().add(mat);
		map.getLista_material_toActor().add(mat);
		map.getLista_material_toActor().add(mat);
		assertEquals(2,map.getLista_material_toActor().size);
		map.getLista_person().add(per);
		map.getLista_person().add(per);
		map.getLista_person().add(per);
		map.getLista_person_toActor().add(per);
		map.getLista_person_toActor().add(per);
		assertEquals(2,map.getLista_person_toActor().size);
		map.recreateMap();
		assertEquals(50/Map.division,map.getMap().size);
		assertEquals(50/Map.division,map.getMap().get(0).size);
		assertEquals(3,map.getLista_material_toActor().size);
		assertEquals(3,map.getLista_material().size);
		assertEquals(3,map.getLista_person().size);
		assertEquals(3,map.getLista_person_toActor().size);
		
		
	}
	@Test
	public void mapEmptyConstructor() {	
		Map map=new Map();
		assertTrue(Map.singleton==map);
	}
	@Test
	public void mapTransform() {
		int pixel = 14;
		
		Map map = new Map(1);
		assertEquals((pixel / Map.division), map.transformToBlock(pixel));
	}
	@Test
	public void mapGetMapPixel() {	
		Map map=new Map(50);
		MineL mine=new MineL(0,0,1);
		map.addMap(mine);
		assertEquals(0,map.getMapPixel(30, 30).size);
		assertEquals(1,map.getMapPixel(0, 0).size);
	}
	@Test
	public void mapGetMapPercisionPixel() {	
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		assertTrue(map.addMap(conv));
		assertTrue(map.addMap(mat));
		assertEquals(2,map.getMapPixel(0, 0).size);
		assertEquals(2,map.getMapPercisionPixel(3, 4).size);
		assertEquals(2,map.getMapPercisionPixel(4, 3).size);
		assertEquals(2,map.getMapPercisionPixel(7, 4).size);
		assertEquals(2,map.getMapPercisionPixel(4, 7).size);
		assertEquals(1,map.getMapPercisionPixel(0, 0).size);
		assertEquals(0,map.getMapPercisionPixel(15, 15).size);
	}
	@Test
	public void mapGetMapBlock() {	
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		assertTrue(map.addMap(conv));
		assertTrue(map.addMap(mat));
		assertEquals(2,map.getMapBlock(0, 0).size);
	}
	@Test
	public void mapAddMap() {	
		Map map=new Map(1000);
		FactoryL fac=new FactoryL(0,0,1);
		FactoryL fac2=new FactoryL(44,44,1);
		assertTrue(map.addMap(fac));
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				assertEquals(1,map.getMapBlock(i, j).size);
			}
		}
		for(int i=4;i<8;i++)
		{
			for(int j=0;j<4;j++)
			{
				assertEquals(0,map.getMapBlock(i, j).size);
			}
		}
		for(int i=0;i<4;i++)
		{
			for(int j=4;j<8;j++)
			{
				assertEquals(0,map.getMapBlock(i, j).size);
			}
		}
		assertFalse(map.addMap(fac));
		assertTrue(map.addMap(fac2));
		for(int i=4;i<8;i++)
		{
			for(int j=4;j<8;j++)
			{
				assertEquals(1,map.getMapBlock(i, j).size);
			}
		}
	}
	@Test
	public void mapAddMap2() {	
		Map map=new Map(1000);
		map.setMoney(0);
		FactoryL fac=new FactoryL(0,0,1);
		assertFalse(map.addMap(fac));
	}
	@Test
	public void mapRemoveMap() {	
		Map map=new Map(1000);
		FactoryL fac=new FactoryL(0,0,1);
		FactoryL fac2=new FactoryL(40,0,1);
		FactoryL fac3=new FactoryL(0,40,1);
		assertTrue(map.addMap(fac));
		assertTrue(map.addMap(fac2));
		assertTrue(map.addMap(fac3));
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				assertEquals(1,map.getMapBlock(i, j).size);
			}
		}
		assertEquals(3,map.getLista().size);
		map.removeMap(fac);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				assertEquals(0,map.getMapBlock(i, j).size);
			}
		}
		for(int i=4;i<8;i++)
		{
			for(int j=0;j<4;j++)
			{
				assertEquals(1,map.getMapBlock(i, j).size);
			}
		}
		for(int i=0;i<4;i++)
		{
			for(int j=4;j<8;j++)
			{
				assertEquals(1,map.getMapBlock(i, j).size);
			}
		}
		assertEquals(2,map.getLista().size);
	}
	@Test
	public void mapCheckPositions() {	
		Map map=new Map(1000);
		assertFalse(map.checkPositions(1001, 0, 0, 0));
		assertTrue(map.checkPositions(1000, 0, 0, 0));
		assertFalse(map.checkPositions(0, 1001, 0, 0));
		assertTrue(map.checkPositions(0, 1000, 0, 0));
		assertFalse(map.checkPositions(1000, 0, 1, 0));
		assertTrue(map.checkPositions(1000, 0, 0, 1));
		assertFalse(map.checkPositions(0, 1000, 0, 1));
		assertTrue(map.checkPositions(0, 1000, 1, 0));
		
	}
	@Test
	public void mapPointIsOccupied() {	
		Map map=new Map(1000);
		ConveyorL conv=new ConveyorL(0,0,1);
		assertTrue(map.addMap(conv));
		assertFalse(map.pointIsOccupied(10, 10));
		assertTrue(map.pointIsOccupied(0, 0));
		
	}
	@Test
	public void mapGetBackgorund() {	
		Map map=new Map(50);
		map.getLista_background().get(0).set(0,new BackGroundL(0,0,"land",0));
		assertEquals("land",map.getBackGroundPoint(0, 0).getType());
		
	}
	
	@Test
	public void tryMapRandom() {
		Map map=new Map(50);
		int one=0,two=0;
		for(int i=0;i<100000;i++)
		{
			int numero=map.randomNumber(1, 2);
			if(numero==1)
				one++;
			else
				two++;
		}
		assertEquals(50,one*100/100000,3);
		assertEquals(50,two*100/100000,3);
	}

	@Test
	public void tryEntityConstructor() {
		Map map=new Map(50);
		InserterL insert= new InserterL(0,0,1);
		assertEquals((Map.division-InserterL.width)/2,insert.getPosX());
		assertEquals((Map.division-InserterL.height)/2,insert.getPosY());
		assertEquals(1,insert.getDirection());
		FactoryL fac=new FactoryL(10,10,1);
		assertEquals(10,fac.getPosX());
		assertEquals(10,fac.getPosY());
		assertEquals(1,fac.getDirection());
		MineL mine=new MineL(0,10,1);
		assertEquals(0,mine.getPosX());
		assertEquals(10,mine.getPosY());
		assertEquals(1,mine.getDirection());
		
	}

	@Test
	public void tryEntityReceive() {
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		MaterialL mat= new MaterialL(1,1,"iron_ore");
		assertEquals("iron_ore",mat.getType());
		assertTrue(conv.receiveMaterial(mat));
		assertEquals(1,map.getLista_material().size);
		assertEquals(0,mat.getId());
	}
	
	@Test
	public void tryEntityPickUp() {
		ConveyorL conv=new ConveyorL(0,0,1);
		assertNull(conv.pickUp("any"));
	}
	
	@Test
	public void tryAddEntity() {
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		assertEquals(5000,map.getMoney());
		assertEquals(0,map.getMoney_wasted());
		assertTrue(conv.addEntity());
		assertEquals(1,map.getLista().size);
		assertEquals(5000-ConveyorL.price,map.getMoney());
		assertEquals(ConveyorL.price,map.getMoney_wasted());
		map.setMoney(0);
		assertEquals(0,map.getMoney());
		assertFalse(conv.addEntity());
		assertEquals(0,map.getMoney());
		assertEquals(ConveyorL.price,map.getMoney_wasted());
		assertEquals(1,map.getLista().size);
	}

	@Test
	public void tryRemoveEntity() {
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		conv.addEntity();
		conv.removeEntity();
		assertEquals(0,map.getLista().size);
	}
	
	@Test
	public void tryLoadGame() {
		Map map=new Map(50);
		ConveyorL conv=new ConveyorL(0,0,1);
		ConveyorL conv2=new ConveyorL(30,30,1);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		assertTrue(map.addMap(conv));
		assertTrue(map.addMap(conv2));
		assertTrue(map.addMap(mat));
		SaveState save= new SaveState();
		save.saveGame("test", map);
		Map map2=save.loadGame("test");
		assertTrue(map.getLista().size==map2.getLista().size);
		assertTrue(map.getLista_material().size==map2.getLista_material().size);
		Map map3=save.loadGame("LLLLLLLLLLLLLLL");
		assertNull(map3);
	}
	
	@Test
	public void createPlace() {
		FactoryL fac=new FactoryL(0,100,2);
		assertEquals(0,fac.getPosX());
		assertEquals(100,fac.getPosY());
		assertEquals(FactoryL.width,fac.getWidth());
		assertEquals(FactoryL.height,fac.getHeight());
		assertEquals(2,fac.getDirection());
		assertEquals(2,fac.getDoorAtBorder());
		FactoryL fac2=new FactoryL();
	}
	@Test
	public void placeAddToStorage() {
		FactoryL fac=new FactoryL(0,100,2);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		mat.setId(0);
		fac.addToStorage(mat);
		assertEquals(1,mat.getId());
		assertEquals(1,fac.getInternalStorage().size());
	}
	@Test
	public void placePickUp() {
		Map map=new Map(50);
		FactoryL fac=new FactoryL(0,100,2);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		fac.addToStorage(mat);
		assertNull(fac.pickUp("any"));
		assertNull(fac.pickUp("a"));
		//TODO
		
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
		
		mat2.moveMaterial(moveX, moveY);
		assertEquals((mX + moveX), mat2.getPosX());
		assertEquals((mY + moveY), mat2.getPosY());

		/*assertEquals(mat, fac.removeMaterial(typeText));
		assertEquals(mat2, fac.removeMaterial("any"));
		assertNull(fac.removeMaterial("any"));*/
		
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
		//ist.handler(map);
		
		new InserterL();
	}
	
	@Test
	public void tryBudget() {
		Map map = new Map(700);
		
		for(int i=0; i<10; i+=50) {
			for(int j=0; j<10; j+=50) {
				//assertTrue(map.addEntityLista(new FactoryL(i, j, 2)));
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
		
		cvy.handler();
		assertFalse(map.getLista_background().first().first().getMaterial());
	}
	
	@Test
	public void tryMine() {
		int x = 4, y = 8, doorAtBorder = 3;
		
		Map map = new Map(60);
		
		MineL mine = new MineL(x, y, doorAtBorder);
		assertTrue(map.addMap(mine));
		
		int i; 
		
		/*for(i = 0; i < 99; i++) {
			assertEquals(0,(int)mine.handler());
		}
		assertEquals(1,(int)mine.handler());*/
	}
	
	@Test
	public void tryHouseAndPerson() {

		/*int from_x = 4, from_y = 8,
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
		}*/
	}

}
