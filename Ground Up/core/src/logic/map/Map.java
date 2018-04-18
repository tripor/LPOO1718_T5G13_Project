package Game.Storage;

import java.util.List;

import Game.Logic.Node;
import Game.Model.Place;

public class Map {
	
	PlaceList placeList;

	public int mapWidth;
	public int mapHeight;

	public Map() {
		// TODO Auto-generated constructor stub
		
		placeList = new PlaceList();
	}
	
	public boolean addPlace(Place p) {
		boolean result = placeList.addPlace(p);
		
		if(!result) {
			System.out.println("Place not added.");
		}
		return result;
	}
	public void removePlace(Place p) {
		placeList.removePlace(p);
	}
	public List<Place> getPlaceList(){
		return placeList.getPlaceList();
	}
	
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	
	
	//======FOR CONSOLE======
	public String print(int i) {
		if(i < 10) {
			return "0" + i;
		}
		else {
			return "" + i;
		}
	}
	public void print(List<Node> path) {
		int i, j, k;
		String[][] path_map = new String[this.getMapHeight()][this.getMapWidth()];
		
		i = 0;
		for(Node n : path) {
			path_map[n.getRow()][n.getCol()] = (":" + print(i++) + ":");
		}
		
		k = 0;
		for(Place pl : this.getPlaceList()) {
			k++;
    			for(i=pl.getBoundTop(); i<=pl.getBoundBottom(); i++) {
    				for(j=pl.getBoundLeft(); j<=pl.getBoundRight(); j++) {
    					path_map[i][j] = "=" + print(k) + "=";
    				}
    			}
		}
		
		for(i = -1; i <= path_map.length; i++) {
			System.out.print("\n");

			// beginning
			if(i < 0 || i==path_map.length) {
				System.out.print("    ");
			}
			else {
				System.out.print("<" + print(i) + ">");
			}
			
			// middle
			for(j = 0; j < path_map[0].length; j++) {
				if(i < 0 || i==path_map.length) {
					System.out.print("<" + print(j) + ">");
				}
				else {
					if(path_map[i][j]==null) {
						System.out.print("    ");
					}
					else {
						System.out.print(path_map[i][j]);
					}
				}
			}

			// end
			if(!(i < 0 || i==path_map.length)) {
				System.out.print("<" + print(i) + ">");
			}
		}
	}

}
