package com.groundup.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import place.Place;
import place.type.Factory;
import logic.console.Console;
import logic.map.Map;
import logic.path.byAStar.Node;
import person.Person;

public class Game {
	
	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}

	public Game() {
		// TODO Auto-generated constructor stub
		
		Map map = new Map();
		map.setMapWidth(5000);
		map.setMapHeight(5000);
		
		Console console = new Console(map);

		int i = 0;
		int min_size = 4;
		int max_size = 20;
		
		while (i < 10) {
			
			console.log("fac- " + i);
			
			int row = map.randRow(),
			    col = map.randCol(),
			    w = Random(min_size, max_size),
			    h = Random(min_size, max_size),
			    side = Random(1,4),	// Norte, Leste, Sol, Oeste
			    door_px = 0;
			
			if(side == 1 || side == 3) {
				door_px = Random(0, w);
			}
			else {
				door_px = Random(0, h);
			}
			
			if(row+h < map.getMapHeight() || col+w < map.getMapWidth()) {
				map.addPlace(new Place(row, col, w, h, Random(1,4), door_px));
				i++;

				console.log("factory " + i + "  left-top [" + col + "," + row + "]  right-bot [" + (col+w) + "," + (row+h) + "]");
			}
		}
		
		List<Node> path = new ArrayList<Node>();
		
		for(i = 0; i < 20; i++) {
			
			console.log("per- " + i);
			
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
			path = p.getPath(t_row, t_col);
			
			console.log("Person " + i + "  from [" + s_col + "," + s_row + "]"
					+ "  to [" + t_col + "," + t_row + "]"
					+ "  total " + path.size() + " steps.  "
					+ (pathStart_in_a_Place ? "[StartFK] " : "")
					+ (pathEnd_in_a_Place ? "[EndFK] " : "")
					+ (startEnd_samePlace ? "[Same] " : "")
				);
		}

		console.printMap(path);
	}
	
	public static void main (String[] arg) {
		new Game();
	}

}
