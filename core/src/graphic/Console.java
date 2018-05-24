package graphic;

// import java.util.List;
// 
// import logic.map.Map;
// import logic.path.byAStar.Node;
// import place.Place;

public class Console {
	
//	Map map;
//
//	public Console(Map m) {
//		this.map = m;
//	}
	
	public static void log(String s) {
		
		if(s.toLowerCase().equals("hr")) {
			System.out.println("========================================");
		}
		else {
			System.out.println(s.replaceAll("  ", " \t"));
		}
	}
	
	public static void log(int s) {
		Console.log("" + s);
	}
}
	
//	public String print_num(int i) {
//		if(i < 10) {
//			return " " + i;
//		}
//		if(i < 0) {
//			return "  ";
//		}
//		return "" + i;
//	}
//	
//	public String printFactory(int i) {
//		if(i < 10) {
//			return "" + i + i + i + i;
//		}
//		return "" + i + i;
//	}
//
//	public void printMap(List<Node> path) {
//
//		System.out.println("\n" + "Called Print Map();");
//		System.out.println("Finish.");
//		
//		int i, j, k;
//		String[][] path_map = new String[map.getMapHeight()][map.getMapWidth()];
//		
//		i = 0;
//		for(Node n : path) {
//			path_map[n.getRow()][n.getCol()] = (" :: ");	// print(i++)
//		}
//		
//		k = 0;
//		for(Place pl : map.getPlaceList()) {
//			k++;
//    			for(i=pl.getBoundTop(); i<=pl.getBoundBottom(); i++) {
//    				for(j=pl.getBoundLeft(); j<=pl.getBoundRight(); j++) {
//    					path_map[i][j] = printFactory(k);
//    				}
//    			}
//
//    			path_map[pl.getDoorRow()][pl.getDoorCol()] = " DD ";
//    		}
//		
//		for(j = -1; j < path_map[0].length; j++) {
//			System.out.print("[" + print_num(j) + "]");
//		}
//		
//		for(i = 0; i < path_map.length; i++) {
//			System.out.print("\n" + "[" + print_num(i) + "]");
//
//			// middle
//			for(j = 0; j < path_map[0].length; j++) {
//				if(path_map[i][j]==null) {
//					System.out.print("    ");
//				}
//				else {
//					System.out.print(path_map[i][j]);
//				}
//			}
//		}
//	}
