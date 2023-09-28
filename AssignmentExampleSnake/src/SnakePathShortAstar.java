import java.util.*;


public class SnakePathShortAstar {
	LinkedList<Snake> otherSnakes;
	Snake mySnake;
	
	SnakePosition apple;
	int hy;
	int hx;
	SnakePosition lastTail;
	NNode TailPoint;
	boolean hiderun;
	int snakelength;
	
	SnakePathShortAstar(SnakePosition apple, LinkedList<Snake> otherSnakes, Snake mySnake, SnakePosition lastTail, boolean hiderun, int snakelength){
		this.otherSnakes = otherSnakes;
		this.hiderun = hiderun;
		this.mySnake = mySnake;
		this.apple = apple;	
		hy = mySnake.head().x;
		hx = mySnake.head().y;
		this.lastTail =lastTail;
		this.TailPoint = new NNode(lastTail.y, lastTail.x);
		this.snakelength = snakelength;
	}
	
	public void PathLength() {
		//int length = 0;
		AbstractSearch aStarpath = new AAstar(new NNode(mySnake.head().y, mySnake.head().x), new NNode(apple.y, apple.x), otherSnakes, mySnake);
        NNode goal = aStarpath.search();
        List<NNode> p = new ArrayList<NNode>();
        NNode g =  goal.getParent();
        System.out.println("log");
        //System.out.println("log" + "g : " + g.getPosY()+","+g.getPosX());
        if(goal.getPosY() != -1 && hiderun) {
        	while (goal.getParent().getParent() != null){
	        	//p.add(goalss);// put the path coordinates in a list because they are in reverse order
				goal = goal.getParent();
			}
        	System.out.println("log" + "goal : " + goal.getPosY()+","+goal.getPosX());
        	System.out.println("log" + "Head : " + hy+","+hx);
        	System.out.println("log" + "Tail : " + TailPoint.getPosY()+","+TailPoint.getPosX());
        if(hy == goal.getPosY() && hx+1 == goal.getPosX()) {//down
    			System.out.println(1);
    	}
        else if(hy == goal.getPosY() && hx-1 == goal.getPosX()) {
        	System.out.println(0);
        }
    	else if(hx == goal.getPosX() && hy+1 == goal.getPosY()) {//right
    			System.out.println(3);
    	}
    	else if(hx == goal.getPosX() && hy-1 == goal.getPosY()) {//left
    		System.out.println(2);
    	}
        }
        else {
			SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
			Bfspath.Bfs();

        	//PathTail();
    	}
    	
    	//goal = goal.getParent();
    }
        
	    
	
	public void PathTail() {
		AbstractSearch aStarpath = new AAstar(new NNode(mySnake.head().y, mySnake.head().x), TailPoint, otherSnakes, mySnake);
        NNode goal = aStarpath.search();
        List<NNode> p = new ArrayList<NNode>();
        NNode g =  goal.getParent();
        System.out.println("log");
        System.out.println("log" + "Tail : " + TailPoint.getPosY()+","+TailPoint.getPosX());
        if(goal.getPosY() != -1) {
        	while (goal.getParent().getParent() != null){
	        	//p.add(goalss);// put the path coordinates in a list because they are in reverse order
				goal = goal.getParent();
			}
        	System.out.println("log" + "goal : " + goal.getPosY()+","+goal.getPosX());
        	System.out.println("log" + "Head : " + hy+","+hx);
        if(hy == goal.getPosY() && hx+1 == goal.getPosX()) {//down
    			System.out.println(1);
    	}
        else if(hy == goal.getPosY() && hx-1 == goal.getPosX()) {
        	System.out.println(0);
        }
    	else if(hx == goal.getPosX() && hy+1 == goal.getPosY()) {//right
    			System.out.println(3);
    	}
    	else if(hx == goal.getPosX() && hy-1 == goal.getPosY()) {//left
    		System.out.println(2);
    	}
        }
        else {
    		System.out.println(5);
    	}
	}
}
