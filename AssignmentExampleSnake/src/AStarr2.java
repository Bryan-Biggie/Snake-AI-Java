

import java.util.*;

/**
 * A Star Algorithm
 *
 * @author Marcelo Surriabre
 * @version 2.1, 2017-02-23
 */
public class AStarr2 {
    private static int DEFAULT_HV_COST = 0; // Horizontal - Vertical Cost
    private static int DEFAULT_DIAGONAL_COST = 14;
    private int hvCost;
    private int diagonalCost;
    private Noode[][] searchArea;
    private PriorityQueue<Noode> openList;
    private Set<Noode> closedSet;
    private Noode initialNode;
    private Noode finalNode;

    public AStarr2(int rows, int cols, Noode initialNode, Noode finalNode, int hvCost, int diagonalCost) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Noode[rows][cols];
        this.openList = new PriorityQueue<Noode>(new Comparator<Noode>() {
            @Override
            public int compare(Noode node0, Noode node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        setNodes();
        this.closedSet = new HashSet<>();
    }

    public AStarr2(int rows, int cols, Noode initialNode, Noode finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST);
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Noode noode = new Noode(i, j);
                noode.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = noode;
            }
        }
    }

    /*public void setBlocks(int[][] blocksArray) {
        for (int i = 0; i < blocksArray.length; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBlock(row, col);
        }
    }*/
    
    public void setBlocks(LinkedList<Snake> otherSnakes, Snake mySnake) {
    	for(int i = 0; i < otherSnakes.size(); i++) {
    		Snake s = otherSnakes.get(i);
    		for(int j = 0; j < s.coordinates.size(); j++) {
    			if(j == 0) {
    				if(s.coordinates.get(j).y != 0) {
    				setBlock(s.coordinates.get(j).y-1, s.coordinates.get(j).x);}
    				if(s.coordinates.get(j).y != 49) {
    				setBlock(s.coordinates.get(j).y+1, s.coordinates.get(j).x);}
    				if(s.coordinates.get(j).x != 0) {
    				setBlock(s.coordinates.get(j).y, s.coordinates.get(j).x-1);}
    				if(s.coordinates.get(j).x != 49) {
    				setBlock(s.coordinates.get(j).y, s.coordinates.get(j).x+1);
    				}
    			}
    			setBlock(s.coordinates.get(j).y, s.coordinates.get(j).x);
    		}
    	}
    	for(int j = 0; j < mySnake.coordinates.size()-1; j++) {
    		setBlock(mySnake.coordinates.get(j).y, mySnake.coordinates.get(j).x);
		}
    }
    
    public Noode findPathh() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Noode currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return currentNode;
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        //return new ArrayList<Node>();
        return new Noode(-1, -1);
    }

    public List<Noode> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Noode currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        //return new ArrayList<Node>();
        return null;
    }

    private List<Noode> getPath(Noode currentNode) {
        List<Noode> path = new ArrayList<Noode>();
        path.add(currentNode);
        Noode parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Noode currentNode) {
    	
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Noode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                //checkNode(currentNode, col - 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                //checkNode(currentNode, col + 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    private void addAdjacentMiddleRow(Noode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        /*if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }*/
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
    }

    private void addAdjacentUpperRow(Noode currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                //checkNode(currentNode, col - 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                //checkNode(currentNode, col + 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    private void checkNode(Noode currentNode, int col, int row, int cost) {
        Noode adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
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

    private boolean isFinalNode(Noode currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Noode> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Noode getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Noode initialNode) {
        this.initialNode = initialNode;
    }

    public Noode getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Noode finalNode) {
        this.finalNode = finalNode;
    }

    public Noode[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Noode[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Noode> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Noode> openList) {
        this.openList = openList;
    }

    public Set<Noode> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Noode> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }

    private int getDiagonalCost() {
        return diagonalCost;
    }

    private void setDiagonalCost(int diagonalCost) {
        this.diagonalCost = diagonalCost;
    }
}

