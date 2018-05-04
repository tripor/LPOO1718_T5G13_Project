package logic.path.byAStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
    
    private static int GAP = 10;
    // leave some pixels around the origin and destination
    
    private static int MAX_BLOCKS = 2*GAP + 20;
    // limit the grid-map for better performance
    // NOTE: MAX_BLOCKS must be larger than 2*GAP, or the ratio will never be 1x1.
    
    
    /**
     * Constructor
     */
    public AStar(Node initialNode, Node finalNode) {
    		this.hvCost = DEFAULT_HV_COST;
    		this.diagonalCost = DEFAULT_DIAGONAL_COST;
    		
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
    	
    		int init_row = this.initialNode.getRow(),
    			init_col = this.initialNode.getCol(),
    			target_row = this.finalNode.getRow(),
    			target_col = this.finalNode.getCol();

        // save a copy of the initial node and final node.
        this.finalNode_raw = new Node(target_row, target_col);
    	
    		// count the distance.
    		int rowDistance = GAP + Math.abs(init_row - target_row) + GAP,
    		    colDistance = GAP + Math.abs(init_col - target_col) + GAP;
    		
    		this.mapRatio = Math.max(rowDistance, colDistance) / MAX_BLOCKS;
    		
    		if(this.mapRatio < 1) {
    			this.mapRatio = 1;
    		}

        this.mapOrigin_left = Math.min(init_col, target_col) - GAP;
        this.mapOrigin_top  = Math.min(init_row, target_row) - GAP;
        
        // Console.log("MAP_ORIGIN=" + this.mapOrigin_left + "," + this.mapOrigin_top);
        
        // set the initial node to the new coordinates.
        this.initialNode.setRow(convertPixelToGrid_row(init_row));
        this.initialNode.setCol(convertPixelToGrid_col(init_col));

        // set the final node to the new coordinates.
        this.finalNode.setRow(convertPixelToGrid_row(target_row));
        this.finalNode.setCol(convertPixelToGrid_col(target_col));
        
        // make the grid bigger, make the a* area smaller.
        int searchAreaRowsCount = rowDistance / mapRatio;
        int searchAreaColsCount = colDistance / mapRatio;
        
        this.searchArea = new Node[searchAreaRowsCount + 1][searchAreaColsCount + 1];
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
    public void setBlocks(List<Place> places) {

    		for(Place p : places) {
    			boolean noSpace_atTop = p.getBoundTop() % mapRatio == 0,
    					noSpace_atBottom = (p.getBoundBottom() + 1) % mapRatio == 0,
    					noSpace_atLeft = p.getBoundLeft() % mapRatio == 0,
    					noSpace_atRight = (p.getBoundRight() + 1) % mapRatio == 0;

    			if(noSpace_atTop && noSpace_atBottom && noSpace_atLeft && noSpace_atRight) {
    				setBlock(p.getBoundTop(), p.getBoundRight(), p.getBoundBottom(), p.getBoundLeft());
    			}
		}
    }

    /**
     * Main function being called by another files.
     */
    public List<Node> findPath() {
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
        		if(!this.searchArea[row][col].isBlock()) {
                	this.searchArea[row][col].setBlock(true);
        		}	
    		}
    }
    
    private void setBlock(int top, int right, int bottom, int left) {
		int i, j;
		
		top    = convertPixelToGrid_row(top);
		right  = convertPixelToGrid_col(right);
		bottom = convertPixelToGrid_row(bottom);
		left   = convertPixelToGrid_col(left);
				
		for(i=top; i<=bottom; i++) {
			for(j=left; j<=right; j++) {
				setBlock(convertPixelToGrid_row(i), convertPixelToGrid_col(j));
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
    		return (row * this.mapRatio) + this.mapOrigin_top;
    }
    private int convertGridToPixel_col(int col) {
		return (col * this.mapRatio) + this.mapOrigin_left;
    }
    private int convertPixelToGrid_row(int row) {
		return (row - this.mapOrigin_top) / this.mapRatio;
	}
	private int convertPixelToGrid_col(int col) {
		return (col - this.mapOrigin_left) / this.mapRatio;
	}

}
