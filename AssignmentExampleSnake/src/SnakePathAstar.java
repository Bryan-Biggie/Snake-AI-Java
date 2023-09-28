import java.util.*;

public class SnakePathAstar {
	LinkedList<Snake> otherSnakes;
	Snake mySnake;
	SnakePosition apple;
	boolean hiderun;
	int hy;
	int hx;
	int snakelength;
	SnakePosition lastTail;
	Point TailPoint;
	
	SnakePathAstar(LinkedList<Snake> otherSnakes, Snake mySnake, SnakePosition apple, boolean hiderun, int snakelength, SnakePosition lastTail){
		this.otherSnakes = otherSnakes;
		this.mySnake = mySnake;
		this.apple = apple;
		this.hiderun = hiderun;
		hy = mySnake.head().x;
		hx = mySnake.head().y;
		this.snakelength = snakelength;
		this.lastTail =lastTail;
		this.TailPoint = new Point(lastTail.y, lastTail.x);
		//AstarApples();
	}
	public void AstarApples(/*Noode endNodee*/) {
		/*if(snakelength == 5) {
			System.out.println("log" + "******  DEAD  ********************************** ");
        }*/
		AStarr aStar = new AStarr(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(apple.y, apple.x));
		aStar.setBlocks(otherSnakes, mySnake);
        //List<Noode> ath = aStar.findPath();
        Noode endNodee = aStar.findPathh();
        //endNodee = endNodee.getParent();
		if(endNodee.getCol() != -1 && hiderun) {
			while (endNodee.getParent().getParent() != (null)) {
				endNodee = endNodee.getParent();
			}
			/*Node[] pathh = new Main().BFS(endNodee.getRow(), endNodee.getCol(), TailPoint, otherSnakes, mySnake);
			if(pathh[0].row == -1) {
				SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
				Bfspath.BFSTail();
				System.out.println("log" + " @@@@@@@@@@@@@@@@ NO PATH!!  @@@@@@@@@@@@@@@@");
			}*/
			if(hy == endNodee.getCol() && hx+1 == endNodee.getRow()) {//down
		    		System.out.println(1);
		    	}
		    	else if(hy == endNodee.getCol() && hx-1 == endNodee.getRow()) {// up
		    		System.out.println(0);
		    	}
		    	else if(hx == endNodee.getRow() && hy+1 == endNodee.getCol()) {//right
		    		System.out.println(3);
		    			
		    	}
		    	else if(hx == endNodee.getRow() && hy-1 == endNodee.getCol()) {//left
		    		System.out.println(2);
		    	}
		    	
		    	
		}
		else {
			//AstarTail();
			SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
			Bfspath.Bfs();
    	}
		    
	}
	
	public void AstarApple() { // goes towards apple if cant go to tail
		AStarr aStar = new AStarr(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(apple.y, apple.x));
		aStar.setBlocks(otherSnakes, mySnake);
        
        Noode endNodee = aStar.findPathh();
        
		if(endNodee.getCol() != -1) {
			while (endNodee.getParent().getParent() != (null)) {
				endNodee = endNodee.getParent();
			}
			
		    	if(hy == endNodee.getCol() && hx+1 == endNodee.getRow()) {//down
		    		System.out.println(1);
		    	}
		    	else if(hy == endNodee.getCol() && hx-1 == endNodee.getRow()) {// up
		    		System.out.println(0);
		    	}
		    	else if(hx == endNodee.getRow() && hy+1 == endNodee.getCol()) {//right
		    		System.out.println(3);
		    			
		    	}
		    	else if(hx == endNodee.getRow() && hy-1 == endNodee.getCol()) {//left
		    		System.out.println(2);
		    	}
		    	
		    	
		}
		else {
			SnakePathClosest close = new SnakePathClosest(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
			//close.ClosestPath();
			close.zigZag();
			//System.out.println(5);
			//SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
    	}
	}
	
	private void AstarTail() {
		AStarr2 aaStar = new AStarr2(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(lastTail.y, lastTail.x));
		aaStar.setBlocks(otherSnakes, mySnake);
        Noode endNodee = aaStar.findPathh();
       
        if(endNodee.getCol() != -1) {
			while (endNodee.getParent().getParent() != (null)) {
				endNodee = endNodee.getParent();
			}
			/*System.out.println("log" + " ");
			System.out.println("log" + "LastTail: " + lastTail.x + "," + lastTail.y);
			System.out.println("log" + "Tail: " + mySnake.tail().x +"," + mySnake.tail().y);
			System.out.println("log" + "Head: " + hy +"," + hx);
			System.out.println("log" + "Node: " + endNodee.getCol() +"," + endNodee.getRow());
			*/
				if(endNodee.getCol() == lastTail.x && endNodee.getRow() == lastTail.x) {
					/*for(int j = 0; j < mySnake.coordinates.size(); j++) {// find the tail of my snake
						System.out.print("log " + mySnake.coordinates.get(j).x+","+ mySnake.coordinates.get(j).y+"-> ");
					}*/
					System.out.println(5);
				}
		    	if(hy == endNodee.getCol() && hx+1 == endNodee.getRow()) {//down
		    		System.out.println(1);
		    	}
		    	else if(hy == endNodee.getCol() && hx-1 == endNodee.getRow()) {// up
		    		System.out.println(0);
		    	}
		    	else if(hx == endNodee.getRow() && hy+1 == endNodee.getCol()) {//right
		    		System.out.println(3);
		    			
		    	}
		    	else if(hx == endNodee.getRow() && hy-1 == endNodee.getCol()) {//left
		    		System.out.println(2);
		    	}
		    	
		    	
		}
		else {
			/*System.out.println("log" + " Straight ");
			System.out.println("log" + "LastTail: " + lastTail.x + "," + lastTail.y);
			System.out.println("log" + "Head: " + hy +"," + hx);
			System.out.println("log" + "Node: " + endNodee.getCol() +"," + endNodee.getRow());*/
			SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
			//System.out.println(5);
    	}
        
	}

}
