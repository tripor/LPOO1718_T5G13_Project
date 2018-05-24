package logic.path.byAStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import inserter.Inserter;
import logic.console.Console;
import place.Place;

/**
 * A Star Algorithm
 *
 * @version 2.0.1, 2018-04
 * @author Johnson Ho
 * @remarks for LPOO.
 *
 * @version 2.0, 2017-02-23
 * @author Marcelo Surriabre
 */
public class AStar {

    private static int DEFAULT_HV_COST = 10;	    // Move Horizontally / Vertically
    private static int DEFAULT_DIAGONAL_COST = 14;	// Move Diagonally
    
    private static int GAP = 5;
    // leave some grids around the origin and destination
    
    private static int MAX_BLOCKS = 30;
    // limit the grid-map for better performance
    
    private boolean skip_calc = false;
    // if start==end, skip calculation.
    
    private boolean start_jump = false;
    // for force-moving origin outside the building.
    
    private long start_at,
    	             init_at,
    	             end_init_at,
    	             setblock_at,
    	             end_setblock_at,
    	             findpath_at,
    	             end_findpath_at;
    // for console log: time marker.
    
    private GameStage game;
    
    
    /**
     * Constructor
     */
    public AStar(GameStage game, Node initialNode, Node finalNode) {
    	
    		this.game = game;

		// for console log: time marker.
    		this.start_at = System.currentTimeMillis();
    		
    		System.out.print("A*(); ");
    	
    		this.hvCost = DEFAULT_HV_COST;
    		this.diagonalCost = DEFAULT_DIAGONAL_COST;
    		
    		if(initialNode.equals(finalNode)) {
    			this.skip_calc = true;
    		}
    		else {
	    		// Move the origin and destination out of a building.
	    		Place start_building = game.places().checkIfPointInBuilding(initialNode.getRow(), initialNode.getCol());
	    		Place end_building   = game.places().checkIfPointInBuilding(finalNode.getRow(), finalNode.getCol());
	    		
	    		if(start_building != null) {
	    			
	    			// Console.log("Start in Building, DOOR(" + start_building.getDoorRow() + "," + start_building.getDoorCol() + ")");
		    		initialNode.setRow(start_building.getDoorRow());
		    		initialNode.setCol(start_building.getDoorCol());
	    			
	    			start_jump = true;
	    		}
	    		if(end_building != null) {
	
	    			// Console.log("End in Building, DOOR(" + end_building.getDoorRow() + "," + end_building.getDoorCol() + ")");
	    			finalNode.setRow(end_building.getDoorRow());
	    			finalNode.setCol(end_building.getDoorCol());
	    			
	    			// end_jump = true;
	    		}
	    		
	    		this.initialNode = initialNode;
	    		this.finalNode = finalNode;
	        
	        this.initSearchMap();
	        this.openList = new PriorityQueue<Node>(1, new Comparator<Node>() {
	            @Override
	            public int compare(Node node0, Node node1) {
	                return node0.getF() < node1.getF() ? -1 : node0.getF() > node1.getF() ? 1 : 0;
	            }
	        });
	        setNodes();
	        this.closedList = new ArrayList<Node>();
    		}
    }

    /**
     * Variables
     */
	// to make the grid-map smaller
    private int mapOrigin_left = 0;
    private int mapOrigin_top = 0;
    
    private int mapRatio = 1;	// 1 unit in grid = ? units in pixels.
    private Node finalNode_raw;	// raw value, not being used inside grids.

    private int hvCost;
    private int diagonalCost;
    
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private List<Node> closedList;
    
    private Node initialNode, finalNode;

    /**
     * Initialize Search Map
     * 
     * @concept
     * Make the map smaller by cropping the original map and condense it.
     */
    private void initSearchMap() {

		// for console log: time marker.
		this.init_at = System.currentTimeMillis();
    	
    		int init_row = this.initialNode.getRow(),
    			init_col = this.initialNode.getCol(),
    			target_row = this.finalNode.getRow(),
    			target_col = this.finalNode.getCol();

        // save a copy of the initial node and final node.
    		// note: this copy already ensured that destination is outside of buildings.
        this.finalNode_raw = new Node(target_row, target_col);
    	
    		// count the distance.
    		int rowDistance = Math.abs(init_row - target_row),
    		    colDistance = Math.abs(init_col - target_col);
    		
    		int rowRatio = rowDistance / MAX_BLOCKS,
    			colRatio = colDistance / MAX_BLOCKS;
    		
    		this.mapRatio = Math.max(rowRatio, colRatio);
    		
    		if(this.mapRatio <= 2) {
    			this.mapRatio = 1;
    		}
    		
    		// Console.log("" + this.mapRatio);

        this.mapOrigin_left = Math.min(init_col, target_col);
        this.mapOrigin_top  = Math.min(init_row, target_row);
        
        // Move the map to leave the gap
        this.mapOrigin_left -= GAP * this.mapRatio;
        this.mapOrigin_top  -= GAP * this.mapRatio;
        
        // set the initial node to the new coordinates.
        this.initialNode.setRow(convertPixelToGrid_row(init_row));
        this.initialNode.setCol(convertPixelToGrid_col(init_col));

        // set the final node to the new coordinates.
        this.finalNode.setRow(convertPixelToGrid_row(target_row));
        this.finalNode.setCol(convertPixelToGrid_col(target_col));
        
        // make the grid bigger, make the a* area smaller.
        int searchAreaRowsCount = rowDistance / mapRatio;
        int searchAreaColsCount = colDistance / mapRatio;
        
        searchAreaRowsCount += GAP * 2;
        searchAreaColsCount += GAP * 2;
        
        // Console.log("AREA=" + searchAreaRowsCount+1 + "," + searchAreaColsCount+1);
        // Console.log("INIT=" + this.initialNode.getRow() + "," + this.initialNode.getCol());
        // Console.log("FIN=" + this.finalNode.getRow() + "," + this.finalNode.getCol());
        
        this.searchArea = new Node[searchAreaRowsCount + 1][searchAreaColsCount + 1];

        int i, j;
        for(i = 0; i <= searchAreaRowsCount; i++) {
        		for(j = 0; j <= searchAreaColsCount; j++) {
        			
        			this.searchArea[i][j] = new Node(i, j);
        		}
        }

		// for console log: time marker.
		this.end_init_at = System.currentTimeMillis();
		
		this.setBlocks();
    }
    
    /**
     * Mark buildings
     * 
     * @concept
     * Just mark the buildings which block the whole grid.
     * 
     * @remarks
     * grid = pixel, when ratio is 1.
     */
    private void setBlock(Actor p) {
    	
    		// Note: getY returns top-bound; getTop returns bottom-bound;
		
		int blockTop = convertPixelToGrid_row((int) p.getY()),
		    blockLeft = convertPixelToGrid_col((int) p.getX()),
		    blockRight = convertPixelToGrid_col((int) p.getRight()),
		    blockBottom = convertPixelToGrid_row((int) p.getTop());

		// If there is a street at North
		if(p.getY() % this.mapRatio > 0) {
			blockTop++;
		}
		
		// South
		if((p.getTop() + 1) % this.mapRatio > 0) {
			blockBottom--;
		}
		
		// left (West)
		if(p.getX() % this.mapRatio > 0) {
			blockLeft++;
		}
		
		// right (East)
		if((p.getRight() + 1) % this.mapRatio > 0) {
			blockRight--;
		}
		
		if(blockTop > blockBottom) {
			// factory didn't occupy the full height of a grid.
		}
		else if(blockLeft > blockRight) {
			// factory didn't occupy the full width of a grid.
		}
		else {
			Console.log("setBlock(T=" + blockTop
					+ " R=" + blockRight
					+ " B=" + blockBottom
					+ " L=" + blockLeft + ");");
			setBlock(blockTop, blockRight, blockBottom, blockLeft);
		}
    }
    
    
    private void setBlocks() {
    	
    		if(this.skip_calc == true) {
    			return;
    		}

    		// for console log: time marker.
    		this.setblock_at = System.currentTimeMillis();
    		
    		ArrayList<Place>    Ps = this.game.places().getLista();
    		ArrayList<Conveyor> Cs = this.game.conveyors().getLista();
    		ArrayList<Inserter> Is = this.game.inserters().getLista();

    		for(Actor p : Ps) {
    			setBlock(p);
		}
    		for(Actor c : Cs) {
    			setBlock(c);
		}
    		for(Actor i : Is) {
    			setBlock(i);
		}

    		// for console log: time marker.
    		this.end_setblock_at = System.currentTimeMillis();
    }

    /**
     * Main function being called by another files.
     */
    public List<Node> findPath() {
    	
    		if(this.skip_calc == false) {

        		// for console log: time marker.
        		this.findpath_at = System.currentTimeMillis();
        		
	        openList.add(initialNode);
	        while (!isEmpty(openList)) {
	            Node currentNode = openList.poll();
	            closedList.add(currentNode);
	            if (isFinalNode(currentNode)) {
	                return getPath(currentNode);
	            } else {
	                addAdjacentNodes(currentNode);
	            }
	        }

	    		// for console log: time marker.
	    		this.end_findpath_at = System.currentTimeMillis();
	    		
	    		Console.log("A-Star Finished. "
	    			+ "Total:" + ((int) (System.currentTimeMillis() - this.start_at)) + " ms (incl "
	    			+ "Init:" + ((int) (this.end_init_at - this.init_at)) + " ms, "
	    	    		+ "SetBlock:" + ((int) (this.end_setblock_at - this.setblock_at)) + " ms, "
	    		    	+ "FindPath:" + ((int) (this.end_findpath_at - this.findpath_at)) + " ms"
	    			+ ")"
	    		);
    		}
    		else {
    			Console.log("A-Star Skipped. Total:" + ((int) (System.currentTimeMillis() - this.start_at)) + "ms");
    		}
        return new ArrayList<Node>();
    }

    /**
     * Return the full path (except the origin point itself)
     * 
     * @remarks
     * this path is with the ORIGINAL coordinates. (i.e., no condense)
     */
    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        
        for(Node n : path) {
        		n.setCol(convertGridToPixel_col(n.getCol()));
        		n.setRow(convertGridToPixel_row(n.getRow()));
        }

   		path.remove(0);
   		
        if(path.size() > 0) {
            path.set(path.size()-1, this.finalNode_raw);

            if(start_jump == true) {
            		path.get(0).setJumping(true);
            }
        }
        
        return path;
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }
    
    private void addAdjacentNodes(Node currentNode) {
        addAdjacentRow(currentNode);
        addAdjacentCol(currentNode);
    }
    
    private void addAdjacentRow(Node currentNode) {
    		addAdjacentRow(currentNode, +1);
    		addAdjacentRow(currentNode, -1);
    }

    private void addAdjacentRow(Node currentNode, int delta_row) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        
        int targetRow = row + delta_row;
        
        boolean	want_go_up   = delta_row < 0,
        			want_go_down = delta_row > 0;
        			
        boolean	can_go_up    = targetRow >= 0,
        			can_go_down  = targetRow < this.getSearchAreaRowsCount();
        
        if((want_go_up && can_go_up) || (want_go_down && can_go_down)) {
			// TopLeft / BottomLeft Corner
			if(col-1 >= 0) {
				checkNode(currentNode, col, row, getDiagonalCost(), -1, delta_row);
			}
			// TopRight / BottomRight Corner
			if(col+1 < this.getSearchAreaColsCount()) {
				checkNode(currentNode, col, row, getDiagonalCost(), +1, delta_row);
			}
        		// Up/Down
        		checkNode(currentNode, col, targetRow, getHvCost());
        }
    }

    private void addAdjacentCol(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        
        if (col-1 >= 0) {
            checkNode(currentNode, col-1, row, getHvCost());
        }
        if (col+1 < this.getSearchAreaColsCount()) {
            checkNode(currentNode, col+1, row, getHvCost());
        }
    }
    
    // for diagonal
    private void checkNode(Node currentNode, int col, int row, int cost, int delta_col, int delta_row) {
    		
    		int target_col = col + delta_col;
    		int target_row = row + delta_row;

    		// if need to walk diagonal, the corner grid need to be empty.
    		boolean	horizonal_is_clear = !getSearchArea()[ row ][ target_col ].isBlock(),
    				vertical_is_clear  = !getSearchArea()[ target_row ][ col ].isBlock();
    		
    		if(horizonal_is_clear && vertical_is_clear) {
    			checkNode(currentNode, target_col, target_row, cost);
    		}
	    		
    }

    // for horizontal / vertical
    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedList().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
    	
    		if(row >= this.getSearchAreaRowsCount()
    			|| col >= this.getSearchAreaColsCount()
    			|| row < 0
    			|| col < 0) {

    			// the factory has no relation to the person's path (i.e., too far from him)
    		}
    		else {
        		Console.log("setBlock(); - R=" + row + " C=" + col + " VS Rs=" + this.getSearchAreaRowsCount() + " Cs=" + this.getSearchAreaColsCount());
        		
        		if(!this.searchArea[row][col].isBlock()) {
                	this.searchArea[row][col].setBlock(true);
        		}	
    		}
    }
    
    private void setBlock(int top, int right, int bottom, int left) {
		int i, j;
				
		for(i=top; i<=bottom; i++) {
			for(j=left; j<=right; j++) {
				setBlock(i, j);
			}
		}
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return this.searchArea;
    }

    public int getSearchAreaColsCount() {
        return this.searchArea[0].length;
    }
    
    public int getSearchAreaRowsCount() {
    		return this.searchArea.length;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public List<Node> getClosedList() {
        return closedList;
    }

    public void setClosedList(List<Node> closedList) {
        this.closedList = closedList;
    }

    public int getHvCost() {
        return hvCost;
    }

	private int getDiagonalCost() {
	    return diagonalCost;
	}

    private int convertGridToPixel_row(int row) {
    		return (row * this.mapRatio) + this.mapOrigin_top + (this.finalNode_raw.getRow() % this.mapRatio);
    		// return the pixel of the grid making the destination locate at left-top corner
    }
    private int convertGridToPixel_col(int col) {
		return (col * this.mapRatio) + this.mapOrigin_left + (this.finalNode_raw.getCol() % this.mapRatio);
    }
    private int convertPixelToGrid_row(int row) {
		return (row - this.mapOrigin_top) / this.mapRatio;
	}
	private int convertPixelToGrid_col(int col) {
		return (col - this.mapOrigin_left) / this.mapRatio;
	}

}
