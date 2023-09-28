
/*
 * Darren Kong 
 * AP-Java 
 * Mr.Levin
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;



import java.util.PriorityQueue;

public class AAstar extends AbstractSearch{
	/**
	 * Fields
	 */
	private int size = 50;
	private NNode [][] grid = new NNode [size][size];
	
	
	private int [][] graph = new int[size][size];
	LinkedList<Snake> otherSnakes;
	Snake mySnake;
	/**
	 * Constructs the A-StarAlgorithmn
	 * @param start - Start node
	 * @param goal - Goal Node
	 * @param adjMatrix - The array of distances between any two adjacent cities
	 */	
	public AAstar(NNode start, NNode goal,LinkedList<Snake> otherSnakes, Snake mySnake){
		super(start, goal);
		//this.graph = graph;
		//this.graph = new int[size][size];
		//this.grid = new NNode [size][size];
		this.grid[start.getPosX()][start.getPosY()] = start;
		this.grid[goal.getPosX()][goal.getPosY()] = goal;
		//this.size = size;
		this.otherSnakes = otherSnakes;
		this.mySnake = mySnake;
		
		//Populates the Grid with Nodes
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(grid[i][j] != start && grid[i][j] != goal){
					grid[i][j] = new NNode(i,j);
					grid[i][j].setCost(calcDist(grid[i][j]));
				}
			}
		}
	}
	/**
	 * Uses our grid to determine the children
	 * we have from our current node.
	 * @param current Node
	 * @return a list of the children nodes
	 */
	private ArrayList<NNode> getChildren(NNode current){
		int x = current.getPosX();
		int y = current.getPosY();
		ArrayList<NNode> children = new ArrayList<NNode>();
		//Makes sure the current Node position is not on the "edges" of the Array.
		if (y < size-1){
			if (graph[x][y+1]!=1){
				children.add(grid[x][y+1]);
			}
		}
		if (x < size-1){
			if (graph[x+1][y] != 1){
				children.add(grid[x+1][y]);
			}
		}
		if (x > 0){
			if (graph[x-1][y] != 1){
				children.add(grid[x-1][y]);
			}
		}
		if (y > 0){
			if (graph[x][y-1] != 1){
				children.add(grid[x][y-1]);
			}
		}
		return children;
	}
	/*private ArrayList<NNode> getChildren(NNode current){
		int x = current.getPosX();
		int y = current.getPosY();
		ArrayList<NNode> children = new ArrayList<NNode>();
		//Makes sure the current Node position is not on the "edges" of the Array.
		for(int i = 0; i < otherSnakes.size(); i++) {
    		Snake mySnakes = otherSnakes.get(i);
    		for(int j = 0; j < mySnakes.coordinates.size(); j++) {
    			if (x < size-1){
    				if (mySnakes.coordinates.get(j).y == x+1 && mySnakes.coordinates.get(j).x == y){
    					children.add(grid[x+1][y]);
    				}
    			}
		if (y < size-1){
			if (mySnakes.coordinates.get(j).y == x && mySnakes.coordinates.get(j).x == y+1){
				children.add(grid[x][y+1]);
			}
		}
		
		if (y > 0){
			if (mySnakes.coordinates.get(j).y == x && mySnakes.coordinates.get(j).x == y-1){
				children.add(grid[x][y-1]);
			}
		}
		if (x > 0){
			if (mySnakes.coordinates.get(j).y == x-1 && mySnakes.coordinates.get(j).x == y){
				children.add(grid[x-1][y]);
			}
		}
    	}}
		/*for(int j = 1; j < mySnake.coordinates.size(); j++) {
			if (y < size-1){
				if (mySnake.coordinates.get(j).y != x && mySnake.coordinates.get(j).x != y+1){
					children.add(grid[x][y+1]);
				}
			}
			if (x < size-1){
				if (mySnake.coordinates.get(j).y != x+1 && mySnake.coordinates.get(j).x != y){
					children.add(grid[x+1][y]);
				}
			}
			if (x > 0){
				if (mySnake.coordinates.get(j).y != x-1 && mySnake.coordinates.get(j).x != y){
					children.add(grid[x-1][y]);
				}
			}
			if (y > 0){
				if (mySnake.coordinates.get(j).y != x && mySnake.coordinates.get(j).x != y-1){
					children.add(grid[x][y-1]);
				}
			}
		}*
		return children;
	}*/
	
	/**
	 * Distance Formula
	 * @param current Node
	 * @return Euclidean/Heuristic distance from current to goal Node
	 */
	private double calcDist(NNode current){
		double x = (current.getPosX()-this.goalNode.getPosX())*(current.getPosX()-this.goalNode.getPosX());
		double y = (current.getPosY()-this.goalNode.getPosY())*(current.getPosY()-this.goalNode.getPosY());
		return Math.sqrt(x + y);
	}
	private void getGraph(){
		for(int i = 0; i < otherSnakes.size(); i++) {
    		Snake s = otherSnakes.get(i);
    		for(int j = 0; j < s.coordinates.size(); j++) {
    			if(j == 0) {
    				if(s.coordinates.get(j).y != 0) {
    					graph[s.coordinates.get(j).y-1][ s.coordinates.get(j).x] = 1;}
    				if(s.coordinates.get(j).y != 49) {
    					graph[s.coordinates.get(j).y+1][s.coordinates.get(j).x] = 1;}
    				if(s.coordinates.get(j).x != 0) {
    					graph[s.coordinates.get(j).y][s.coordinates.get(j).x-1] = 1;}
    				if(s.coordinates.get(j).x != 49) {
    					graph[s.coordinates.get(j).y][s.coordinates.get(j).x+1] = 1;
    				}
    			}
    			graph[s.coordinates.get(j).y][ s.coordinates.get(j).x] = 1;
    		}
		}
		for(int j = 0; j < mySnake.coordinates.size(); j++) {
			graph[mySnake.coordinates.get(j).y][ mySnake.coordinates.get(j).x] = 1;
		}
		return;
	}

	@Override
	public NNode search() {
		getGraph();
		//System.out.println("AStar Algorithmn:");
		this.startNode.setDistance(0);
		if(this.startNode.equals(goalNode))
		{
			//System.out.println("Goal Node Found!");
			//System.out.println(startNode);
			return new NNode(-1,-1);
		}
		
		PriorityQueue<NNode> unexplored = new PriorityQueue<NNode>();
		unexplored.add(startNode);
		ArrayList<NNode> explored = new ArrayList<NNode>();
		
		while (!unexplored.isEmpty()){
			NNode current = unexplored.remove();
			if (current.equals(this.goalNode)){
				explored.add(current);
				//printPath(current);
				return current;
			}
			else{
				for (NNode x : getChildren(current)){
					if (!explored.contains(x) && !unexplored.contains(x))
					{
						x.setDistance(current.getDistance() + 1);
						x.setCost(x.getDistance() + calcDist(x));
						x.setParent(current);
						unexplored.add(x);
					}
					else if (x.getDistance() > current.getDistance() + 1)
					{
						x.setDistance(current.getDistance() + 1);
						x.setCost(x.getDistance() + calcDist(x));
						x.setParent(current);
					}
				}
				explored.add(current);
			}
		}
		return new NNode(-1,-1);
	}

	private void printPath(NNode goal) {
		while (goal.getParent() != null){
			System.out.print(goal + " <--- ");
			goal = goal.getParent();
		}
		System.out.println(goal);
	}
	
}
