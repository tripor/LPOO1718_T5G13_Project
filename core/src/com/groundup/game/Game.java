package com.groundup.game;

import java.util.ArrayList;
import java.util.List;

import place.Place;
import place.type.Factory;
import logic.console.Console;
import logic.map.Map;
import logic.path.Node;
import person.Person;

public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
		
		Map map = new Map();
		map.setMapWidth(20);
		map.setMapHeight(20);
		
		Console console = new Console(map);
		
		map.addPlace(new Factory(6,5,5,3,1,1));
		map.addPlace(new Factory(5,8));
		map.addPlace(new Factory(13,11,7,1,3,1));
		
		for(int i = 0; i < 20; i++) {
			
			int s_row = map.randRow(),
				s_col = map.randCol(),
				t_row = map.randRow(),
				t_col = map.randCol();
			
			boolean pathStart_in_a_Place = false,
					pathEnd_in_a_Place   = false,
					startEnd_samePlace   = false;

			List<Place> places = map.getPlaceList();
			
			// Loop through the place list.
			for(int f = 0; f < places.size(); f++) {

				// the object `Place` includes the ending point.
				boolean this_place_wraps_endPoint = places.get(f).including(t_row, t_col);
				
				// if the object `Place` includes the starting point.
				if(places.get(f).including(s_row, s_col)) {
					pathStart_in_a_Place = true;
					
					// Move the person to the door.
					s_row = places.get(f).getDoorRow();
					s_col = places.get(f).getDoorCol();

					// if the same object `Place` includes the ending point.
					if(this_place_wraps_endPoint) {
						startEnd_samePlace = true;	// They are in the same point.
					}
				}
				
				if(this_place_wraps_endPoint) {
					pathEnd_in_a_Place = true;
					
					// Move the person to the door.
					t_row = places.get(f).getDoorRow();
					t_col = places.get(f).getDoorCol();
				}
			}
			
			Person p = new Person(map);
			p.setCurrentRow(s_row);
			p.setCurrentCol(s_col);
			
			// List<Node> path;
			List<Node> path = p.getPath(t_row, t_col);
			
			console.log("Person " + i + "  from [" + s_col + "," + s_row + "]"
					+ "  to [" + t_col + "," + t_row + "]"
					+ "  total " + path.size() + " steps.  "
					+ (pathStart_in_a_Place ? "[StartFK] " : "")
					+ (pathEnd_in_a_Place ? "[EndFK] " : "")
					+ (startEnd_samePlace ? "[Same] " : "")
				);
		}
	}
	
	public static void main (String[] arg) {
		new Game();
	}

}
