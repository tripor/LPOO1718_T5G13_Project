package com.groundup.game;

import java.util.List;
import java.util.Random;

import place.Place;
import logic.console.Console;
import logic.map.Map;
import logic.path.byAStar.Node;
import logic.storage.PersonList;
import person.Person;

public class Game {
	
	Map map = Map.getInstance();
	PersonList personList = PersonList.getInstance();
	
	public Game() {
		
		map.setMapWidth(500000);
		map.setMapHeight(500000);
		
		int i = 0;
		
		while (i < 1000) {
			i = generateFactory(i);
		}
		
		Console.log("hr");
		Console.log("Start generating people.");
		long start = System.nanoTime();
		
		for(i = 0; i < 200000; i++) {
			generatePerson(i);
		}
		
	//	Place fac1 = new Place(100, 100, 30, 30, 2, 30);
	//	map.addPlace(fac1);
	//	
	//	Console.log(fac1.toString());
		
		Console.log("hr");
		Console.log("Finish.");

//		for(i = 0; i < 100; i++) {
//			personList.popPaths();
//		}
		
		Console.log(System.nanoTime() - start + " ns");
	}
	
	public static void main (String[] arg) {
		new Game();
	}

	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}
	
	private int generateFactory(int i) {
		
		int min_size = 20;
		int max_size = 100;

		int row = map.randRow(),
		    col = map.randCol(),
		    w = Random(min_size, max_size),
		    h = Random(min_size, max_size),
		    side = Random(1,4),	// 1=Top, 2=Right, 3=Bottom, 4=Left
		    door_px = 0;
		
		if(side == 1 || side == 3) {
			door_px = Random(0, w);
		}
		else {
			door_px = Random(0, h);
		}
		
		if(row+h < map.getMapHeight() || col+w < map.getMapWidth()) {
			
			Place p = new Place(row, col, w, h, Random(1,4), door_px);
			
			map.addPlace(p);
			Console.log(p.toString());
			i++;
		}
		
		return i;
	}
	
	private void generatePerson(int i) {

		int s_row = map.randRow(),
			s_col = map.randCol(),
			t_row = map.randRow(),
			t_col = map.randCol();
		
		// boolean pathStart_in_a_Place = false,
		//		pathEnd_in_a_Place   = false,
		//		startEnd_samePlace   = false;

		List<Place> places = map.getPlaceList(s_row, s_col);
		
		for(Place pl : map.getPlaceList(t_row, t_col)) {
			places.add(pl);
		}
		// Performance? VS places.addAll(map.getPlaceList(t_row, t_col));
		
		// Loop through the place list.
		for(int f = 0; f < places.size(); f++) {

			// the object `Place` includes the ending point.
			boolean this_place_wraps_endPoint = places.get(f).including(t_row, t_col);
			
			// if the object `Place` includes the starting point.
			if(places.get(f).including(s_row, s_col)) {
				// pathStart_in_a_Place = true;
				
				// Move the person to the door.
				s_row = places.get(f).getDoorRow();
				s_col = places.get(f).getDoorCol();

				// if the same object `Place` includes the ending point.
				if(this_place_wraps_endPoint) {
					// startEnd_samePlace = true;	// They are in the same point.
				}
			}
			
			if(this_place_wraps_endPoint) {
				// pathEnd_in_a_Place = true;
				
				// Move the person to the door.
				t_row = places.get(f).getDoorRow();
				t_col = places.get(f).getDoorCol();
			}
		}
		
		Person p = new Person("" + i);
		p.setCurrentRow(s_row);
		p.setCurrentCol(s_col);
		
		personList.addPerson(p);
		
//		Console.log(p.toString());
//		Console.log(p.getId());
		
		// List<Node> path = 
		p.getPath(t_row, t_col);
	}

}
