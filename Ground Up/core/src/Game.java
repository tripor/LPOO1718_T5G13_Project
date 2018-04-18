package Game.Controller;

import java.util.List;

import Game.Logic.Node;
import Game.Model.Person;
import Game.Model.Place;
import Game.Storage.Map;
import Model.Place.Factory;

public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
		
		Map map = new Map();
		map.setMapWidth(20);
		map.setMapHeight(20);
		
		Person p = new Person(map);
		p.setCurrentRow(19);
		p.setCurrentCol(15);
		
		map.addPlace(new Factory(6,5,5,3));
		map.addPlace(new Factory(5,8));
		map.addPlace(new Factory(13,11,7,1));
		
		List<Node> path = p.getPath(1, 8);
		map.print(path);
	}

}
