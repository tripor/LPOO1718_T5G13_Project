package logic.path.byAStar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import place.Place;

/**
 * A Star Algorithm
 *
 * @version 2.0, 2017-02-23
 * @author Marcelo Surriabre
 */
public class AStar {

    private static int DEFAULT_HV_COST = 10;	    // Move Horizontally / Vertically
    private static int DEFAULT_DIAGONAL_COST = 14;	// Move Diagonally

    private int hvCost;
    private int diagonalCost;
    
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private List<Node> closedList;
    private Node initialNode;
    private Node finalNode;

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
    		this.hvCost = DEFAULT_HV_COST;
    		this.diagonalCost = DEFAULT_DIAGONAL_COST;
    		
    		setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<Node>(1, new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return node0.getF() < node1.getF() ? -1 : node0.getF() > node1.getF() ? 1 : 0;
            }
        });
        setNodes();
        this.closedList = new ArrayList<Node>();
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
    
    public void setBlocks(List<Place> places, int grid_size) {
    		int i, j;
    		for(Place p : places) {
    			for(i=p.getBoundTop(); i<=p.getBoundBottom(); i++) {
    				for(j=p.getBoundLeft(); j<=p.getBoundRight(); j++) {
    					setBlock(i/grid_size, j/grid_size);
    				}
    			}
    		}
    }

    public void setBlocks(List<Place> places) {
    		setBlocks(places, 1);
    }

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

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
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
    		
    		// if need to walk diagonal, the corner grid need to be empty.

    		boolean	horizonal_is_clear = !getSearchArea()[ row ][ col+delta_col ].isBlock(),
    				vertical_is_clear  = !getSearchArea()[ row+delta_row ][ col ].isBlock();
    	
    		if(horizonal_is_clear && vertical_is_clear) {
    			checkNode(currentNode, col+delta_col, row+delta_row, cost);
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
        this.searchArea[row][col].setBlock(true);
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

}
