package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
		assertEquals(500,map.getMoney());
		assertEquals(0,map.getMoney_wasted());
		assertEquals(0,map.getLooking_for_worker().size);
		
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
				assertEquals(j*Map.division,map.getLista_background().get(i).get(j).getPosY());
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
		map.setMoney(10000);
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
		assertEquals(500,map.getMoney());
		assertEquals(0,map.getMoney_wasted());
		assertTrue(conv.addEntity());
		assertEquals(1,map.getLista().size);
		assertEquals(500-ConveyorL.price,map.getMoney());
		assertEquals(ConveyorL.price,map.getMoney_wasted());
		map.setMoney(0);
		assertEquals(0,map.getMoney());
		assertFalse(conv.addEntity());
		assertEquals(0,map.getMoney());
		assertEquals(ConveyorL.price,map.getMoney_wasted());
		assertEquals(1,map.getLista().size);
		map.setMoney(ConveyorL.price);
		assertTrue(conv.addEntity());
		assertEquals(2,map.getLista().size);
		assertEquals(0,map.getMoney());
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
	public void tryDoorPositionEntity() {
		FactoryL fac=new FactoryL(40,40,1);
		assertEquals(60,fac.doorXposition());
		assertEquals(80,fac.doorYposition());
		FactoryL fac2=new FactoryL(40,40,3);
		assertEquals(60,fac2.doorXposition());
		assertEquals(40-PersonL.height,fac2.doorYposition());
		FactoryL fac3=new FactoryL(40,40,2);
		assertEquals(80,fac3.doorXposition());
		assertEquals(60,fac3.doorYposition());
		FactoryL fac4=new FactoryL(40,40,4);
		assertEquals(40-PersonL.width,fac4.doorXposition());
		assertEquals(60,fac4.doorYposition());
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
	public void testRecipe() {
		Recipe receita=new Recipe();
		receita.selectRecipie(0);
		ArrayList<String> testar=receita.getSelectedRecipe();
		assertEquals("copper_plate",testar.get(0));
		assertEquals("copper_cable",testar.get(1));
		assertEquals(0,receita.selectedRecipie());
		assertEquals(receita.getRecipe().size(),receita.totalNumber());
		assertEquals("copper_plate = copper_cable",receita.getRecipeString(0));
		assertEquals("iron_plate + copper_cable + copper_cable = electronic_circuit",receita.getRecipeString(1));
		assertTrue(receita.canReceive("copper_plate"));
		assertFalse(receita.canReceive("n"));
		receita.selectRecipie(1);
		assertTrue(receita.canReceive("iron_plate"));
		assertTrue(receita.canReceive("copper_cable"));
		assertFalse(receita.canReceive("electronic_circuit"));
		testar=receita.getConsumir();
		assertEquals("iron_plate",testar.get(0));
		assertEquals("copper_cable",testar.get(1));
		assertEquals("copper_cable",testar.get(2));
		assertEquals(3,testar.size());
		assertEquals("electronic_circuit",receita.getProduct());
		receita.selectRecipie(0);
		assertEquals("copper_cable",receita.getProduct());
		assertEquals(15,receita.timeToBuild());
		receita.selectRecipie(1);
		assertEquals(25,receita.timeToBuild());
		
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
	}
	@Test
	public void placeAddToStorage() {
		FactoryL fac=new FactoryL(0,100,2);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		mat.setId(0);
		fac.addToStorage(mat);
		assertEquals(1,mat.getId());
		assertEquals(1,fac.getInternalStorage().size());
		mat.setId(0);
		fac.addToExternalStorage(mat);
		assertEquals(1,mat.getId());
		assertEquals(1,fac.getExternalStorage().size());
		fac.moveToExternal(mat);
		assertEquals(2,fac.getExternalStorage().size());
		
	}
	@Test
	public void placePickUp() {
		Map map=new Map(50);
		FactoryL fac=new FactoryL(0,100,2);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		assertNull(fac.pickUp("any"));
		assertNull(fac.pickUp("a"));
		fac.addToExternalStorage(mat);
		MaterialL mat2=new MaterialL(0,0,"iron_ore2");
		MaterialL mat3=new MaterialL(0,0,"iron_ore3");
		fac.addToExternalStorage(mat2);
		fac.addToExternalStorage(mat3);
		MaterialL devolvido=fac.pickUp("any");
		assertTrue(devolvido!=null);
		assertEquals(0,devolvido.getId());
		assertEquals(1,map.getLista_material_toActor().size);
		assertEquals(2,fac.getExternalStorage().size());
		devolvido=null;
		devolvido=fac.pickUp("iron_ore3");
		assertTrue(devolvido!=null);
		assertEquals(0,devolvido.getId());
		assertEquals(2,map.getLista_material_toActor().size);
	}
	@Test
	public void placeWorker() {
		Map map=new Map(50);
		FactoryL fac=new FactoryL(0,100,2);
		PersonL person=new PersonL(0,0);
		assertNull(fac.getWorker());
		fac.acceptWorker(person);
		assertTrue(fac.getWorker()==person);
		assertEquals(1,person.getId());
		assertEquals(0,map.getLooking_for_worker().size);
		fac.lookForWorker();
		assertEquals(1,map.getLooking_for_worker().size);
	}
	@Test
	public void backgroundType() {
		BackGroundL b=new BackGroundL(0,0,"grass",0);
		assertEquals("grass.png",b.getTypeLand());
		b=new BackGroundL(0,0,"copper_ore",0);
		assertEquals("land_copper.png",b.getTypeLand());
		b=new BackGroundL(0,0,"2",100);
		assertEquals("",b.getTypeLand());
		assertTrue(b.getMaterial());
		assertEquals(99,b.getQuantity());
		assertEquals(0,(int)b.handler());
		assertTrue(b.addEntity());
		assertEquals(0,b.getPrice());
		b.removeEntity();
	}
	
	@Test
	public void tryConveyor() {
		Map map = new Map(60);
		int direction = 1;
		ConveyorL cvy = new ConveyorL(0, 0, direction);
		assertEquals(direction, cvy.getDirection());
		assertEquals(1, cvy.getMovementY());
		assertEquals(0, cvy.getMovementX());
		
		direction = 3;
		cvy = new ConveyorL(0, 0, direction);
		assertEquals(-1, cvy.getMovementY());
		assertEquals(0, cvy.getMovementX());
		
		direction = 4;
		cvy = new ConveyorL(0, 0, direction);
		assertEquals(0, cvy.getMovementY());
		assertEquals(-1, cvy.getMovementX());
		
		direction = 2;
		cvy = new ConveyorL(0, 0, direction);
		assertEquals(0, cvy.getMovementY());
		assertEquals(1, cvy.getMovementX());
		
		MaterialL mat=new MaterialL(0,0,"iron");
		mat.setPosX(0);
		mat.setPosY(0);
		MaterialL mat2=new MaterialL(8,8,"iron");
		mat2.setPosX(8);
		mat2.setPosY(8);
		MaterialL mat3=new MaterialL(0,15,"iron");
		mat3.setPosX(0);
		mat3.setPosY(15);

		assertTrue(map.addMap(cvy));
		assertTrue(map.addMap(mat));
		assertTrue(map.addMap(mat2));
		assertTrue(map.addMap(mat3));
		assertEquals(0,mat.getPosX());
		assertEquals(0,mat.getPosY());
		cvy.handler();
		assertEquals(1,mat.getPosX());
		assertEquals(0,mat.getPosY());
		assertEquals(9,mat2.getPosX());
		assertEquals(8,mat2.getPosY());
		assertEquals(0,mat3.getPosX());
		assertEquals(15,mat3.getPosY());
		cvy.handler();
		cvy.handler();
		cvy.handler();
		cvy.handler();
		cvy.handler();
		cvy.handler();
		cvy.handler();
		cvy.handler();
		assertEquals(9,mat.getPosX());
		assertEquals(0,mat.getPosY());
	}
	@Test
	public void tryFactory() {
		
		Map map = new Map(60);
		FactoryL fac= new FactoryL(0,0,1);
		assertEquals(0,fac.getReceita().selectedRecipie());
		assertEquals(0,fac.getSelectedRecipe());
		MaterialL mat=new MaterialL(0,0,"copper_plate");
		fac.getInternalStorage().add(mat);
		assertEquals(1,fac.getInternalStorage().size());
		fac.selectRecipe(1);
		assertEquals(0,fac.getInternalStorage().size());
		assertEquals(1,fac.getReceita().selectedRecipie());
		MaterialL mat2=new MaterialL(0,0,"iron_ore");
		fac.getInternalStorage().add(mat2);
		fac.getInternalStorage().add(mat);
		MaterialL devolvido=fac.transform();
		assertNull(devolvido);
		assertEquals(2,fac.getInternalStorage().size());
		fac.selectRecipe(0);
		fac.getInternalStorage().add(mat2);
		fac.getInternalStorage().add(mat);
		devolvido=fac.transform();
		assertEquals("copper_cable",devolvido.getType());
		assertEquals(1,fac.getInternalStorage().size());
		assertEquals(0,fac.getTime());
		assertEquals(10,fac.getWork_time());
		for(int i=1;i<=fac.getReceita().timeToBuild()-1;i++)
		{
			assertEquals(0,(int)fac.handler());
		}
		assertEquals(14,fac.getTime());
		fac.handler();
		assertEquals(0,fac.getTime());
		assertEquals(0,fac.getExternalStorage().size());
		fac.getInternalStorage().clear();
		for(int i=1;i<=fac.getReceita().timeToBuild();i++)
		{
			assertEquals(0,(int)fac.handler());
		}
		assertEquals(15,fac.getTime());
		MaterialL mat3=new MaterialL(0,0,"copper_plate");
		fac.getInternalStorage().add(mat3);
		fac.handler();
		assertEquals(1,fac.getExternalStorage().size());
		fac.getInternalStorage().clear();
		fac.getExternalStorage().clear();
		MaterialL adicionar=new MaterialL(0,0,"iron_plate");
		MaterialL adicionar2=new MaterialL(0,0,"copper_plate");
		map.addMap(adicionar2);
		assertEquals(1,map.getLista_material().size);
		assertFalse(fac.receiveMaterial(adicionar));
		assertTrue(fac.receiveMaterial(adicionar2));
		assertEquals(0,map.getLista_material().size);
		assertEquals(1,fac.getInternalStorage().size());
		
	}
	
	@Test
	public void tryHouse() {
		Map map= new Map(500);
		HouseL h= new HouseL(0,0,1);
		assertEquals(h.getMaxNumber(),h.getInside().size());
		assertEquals(0,(int)h.handler());
		PersonL retirar=h.getInside().get(0);
		assertEquals(1,retirar.getId());
		FactoryL fac=new FactoryL(0,0,1);
		assertEquals(0,map.getLooking_for_worker().size);
		fac.lookForWorker();
		assertEquals(1,map.getLooking_for_worker().size);
		h.handler();
		assertEquals(0,map.getLooking_for_worker().size);
		assertEquals(0,retirar.getId());
		assertTrue(fac==retirar.getTarget());
		assertEquals(9,h.getInside().size());
		map.setMoney(0);
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		assertEquals(0,map.getLista_material().size);
		map.addMap(mat);
		assertEquals(1,map.getLista_material().size);
		assertEquals(0,h.getInternalStorage().size());
		assertTrue(h.receiveMaterial(mat));
		assertEquals(0,map.getLista_material().size);
		assertEquals(1,h.getInternalStorage().size());
		h.handler();
		assertEquals(1,map.getMoney());
		assertEquals(0,h.getInternalStorage().size());
		map.setMoney(0);
		mat.setType("copper_ore");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(1,map.getMoney());
		map.setMoney(0);
		mat.setType("iron_plate");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(3,map.getMoney());
		map.setMoney(0);
		mat.setType("copper_plate");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(3,map.getMoney());
		map.setMoney(0);
		mat.setType("copper_cable");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(3,map.getMoney());
		map.setMoney(0);
		mat.setType("electronic_circuit");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(20,map.getMoney());
		map.setMoney(0);
		mat.setType("gear");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(3,map.getMoney());
		map.setMoney(0);
		mat.setType("advanced_circuit");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(100,map.getMoney());
		map.setMoney(0);
		mat.setType("processing_unit");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(500,map.getMoney());
		map.setMoney(0);
		mat.setType("pipe");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(5,map.getMoney());
		map.setMoney(0);
		mat.setType("engine_unit");
		h.getInternalStorage().add(mat);
		h.handler();
		assertEquals(40,map.getMoney());
		
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
		
		fac.addToStorage(mat);
		fac.addToStorage(mat2);
		
		map.recreateMap();
		
		MaterialL smelt = new MaterialL(0,0,"iron_ore");
		smelt.smelt();
		assertEquals("iron_plate",smelt.getType());
		smelt.setType("copper_ore");
		smelt.smelt();
		assertEquals("copper_plate",smelt.getType());
		assertEquals(0,(int)smelt.handler());
		assertFalse(smelt.receiveMaterial(mat));
		Map map2=new Map(500);
		assertTrue(map2.addMap(smelt));
		assertEquals(1,map2.getLista_material().size);
		assertNull(smelt.pickUp("t"));
		assertTrue(smelt==smelt.pickUp("any"));
		assertEquals(0,map2.getLista_material().size);
		map2.addMap(smelt);
		assertTrue(smelt==smelt.pickUp(smelt.getType()));
		assertEquals(0,map2.getLista_material().size);
		smelt.addEntity();
		map2.setMoney(-1);
		assertFalse(smelt.addEntity());
		
		
		
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
	public void tryMine() {
		Map map=new Map(500);
		map.getLista_background().get(0).set(0, new BackGroundL(0,0,"iron_ore",200));
		MineL mine=new MineL(0,0,1);
		assertEquals(0,mine.getTime());
		for(int i=1;i<=mine.getWork_time()-1;i++)
		{
			assertEquals(0,(int)mine.handler());
		}
		assertEquals(mine.getWork_time()-1,mine.getTime());
		assertEquals(1,(int)mine.handler());
		assertEquals(1,mine.getExternalStorage().size());
		assertEquals("iron_ore",mine.getExternalStorage().get(0).getType());
		assertEquals(MineL.price,mine.getPrice());
		assertFalse(mine.receiveMaterial(null));
		assertEquals(0,mine.getTime());
	}
	
	@Test
	public void trySmelter() {
		Map map=new Map(500);
		SmelterL s=new SmelterL(0,0,1);
		assertEquals(0,s.getTime());
		for(int i=1;i<=s.getWork_time()-1;i++)
		{
			assertEquals(0,(int)s.handler());
		}
		assertEquals(s.getWork_time()-1,s.getTime());
		s.handler();
		assertEquals(s.getWork_time(),s.getTime());
		MaterialL mat=new MaterialL(0,0,"iron_ore");
		MaterialL mat2=new MaterialL(0,0,"copper_ore");
		MaterialL mat3=new MaterialL(0,0,"t");
		map.addMap(mat);
		assertEquals(1,map.getLista_material().size);
		assertTrue(s.receiveMaterial(mat));
		assertEquals(0,map.getLista_material().size);
		assertEquals(1,s.getInternalStorage().size());
		map.addMap(mat2);
		assertEquals(1,map.getLista_material().size);
		assertTrue(s.receiveMaterial(mat2));
		assertEquals(0,map.getLista_material().size);
		assertEquals(2,s.getInternalStorage().size());
		assertFalse(s.receiveMaterial(mat3));
		assertEquals(SmelterL.price,s.getPrice());
		s.handler();
		assertEquals("iron_plate",s.getExternalStorage().get(0).getType());
		assertEquals(1,s.getInternalStorage().size());
	}

}
