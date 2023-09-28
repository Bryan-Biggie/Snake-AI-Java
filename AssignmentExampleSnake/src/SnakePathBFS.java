import java.util.*;


public class SnakePathBFS {
	LinkedList<Snake> otherSnakes;
	Snake mySnake;
	SnakePosition apple;
	boolean hiderun;
	int hy;
	int hx;
	int snakelength;
	SnakePosition lastTail;
	Point TailPoint;
	SnakePathBFS(LinkedList<Snake> otherSnakes, Snake mySnake, SnakePosition apple, boolean hiderun, int snakelength, SnakePosition lastTail){
		this.otherSnakes = otherSnakes;
		this.mySnake = mySnake;
		this.apple = apple;
		this.hiderun = hiderun;
		hy = mySnake.head().x;
		hx = mySnake.head().y;
		this.snakelength = snakelength;
		this.lastTail =lastTail;
		this.TailPoint = new Point(lastTail.y, lastTail.x);
		
		//Bfs();
	}
	
	public void Bfs() {
		if(hiderun || snakelength == 5) {
			Node[] path = new Main().BFS(mySnake.head().y, mySnake.head().x, new Point(apple.y, apple.x), otherSnakes, mySnake);
			if(path[0].row != -1) {
				BFSApple(path);
			}
			else {
				Node[] pathh = new Main().BFS(mySnake.head().y, mySnake.head().x, TailPoint, otherSnakes, mySnake);
					BFSApple(pathh);
			}
		}
		else {
			Node[] pathh = new Main().BFS(mySnake.head().y, mySnake.head().x, TailPoint, otherSnakes, mySnake);
				BFSApple(pathh);
		}
		
	}
	private void BFSApple(Node[] path) {
		// hy & hx are the snake's head coordinates
		Node start = path[0];
		Node end = path[1];
		Node endNode = end;
		if(start.row != -1) {// checks if there is a path
			endNode = endNode.parent;
			/*if(endNode.col == lastTail.x && endNode.row == lastTail.y) {
				SnakePathClosest close = new SnakePathClosest(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
				close.ClosestPathTail();
			}*/
			if(hy == endNode.col && hx+1 == endNode.row) {//down
		    	System.out.println(1);
		    }
			else if(hy == endNode.col && hx-1 == endNode.row) {//up
				System.out.println(0);
			}
		    else if(hx == endNode.row && hy+1 == endNode.col) {//right
		    	System.out.println(3);
	 	    }
		   else if(hx == endNode.row && hy-1 == endNode.col) {//left
			   System.out.println(2);
		    }
		}
	   else {// if there is no path it goes straight
		   SnakePathAstar AstarPath = new SnakePathAstar(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
		   AstarPath.AstarApple();
    		//System.out.println(5);
    	}
		
	}
	
	public void BFSTail() {
		Node[] path = new Main().BFS(mySnake.head().y, mySnake.head().x, TailPoint, otherSnakes, mySnake);
		Node start = path[0];
		Node end = path[1];
		Node endNode = end;
		if(start.row != -1) {// checks if there is a path
			endNode = endNode.parent;
			if(endNode.col == lastTail.x && endNode.row == lastTail.y) {
				System.out.println(5);
			}
			if(hy == endNode.col && hx+1 == endNode.row) {//down
		    	System.out.println(1);
		    }
			else if(hy == endNode.col && hx-1 == endNode.row) {//up
				System.out.println(0);
			}
		    else if(hx == endNode.row && hy+1 == endNode.col) {//right
		    	System.out.println(3);
	 	    }
		   else if(hx == endNode.row && hy-1 == endNode.col) {//left
			   System.out.println(2);
		    }
		}
	   else {// if there is no path it goes straight
		   SnakePathAstar AstarPath = new SnakePathAstar(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
		   AstarPath.AstarApple();
    		//System.out.println(5);
    	}
	    
	}
	

}
