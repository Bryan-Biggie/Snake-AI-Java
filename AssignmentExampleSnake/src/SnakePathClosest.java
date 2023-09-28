import java.util.LinkedList;

public class SnakePathClosest {
	LinkedList<Snake> otherSnakes;
	Snake mySnake;
	SnakePosition apple;
	boolean hiderun;
	int hy;
	int hx;
	int snakelength;
	SnakePosition lastTail;
	Point TailPoint;
	/*int[][] adj = {
			{-3, 3}, {3, 3}, {3, -3}, {3, 3}, 
			{-3, 2}, {3, 2}, {2, -3}, {2, 3},
			{-3, 1}, {3, 1}, {1, -3}, {1, 3},
			{-3, 0}, {3, 0}, {0, -3}, {0, 3},
			{-2, 1}, {2, 1}, {1, -2}, {1, 2},
			{-2, 0}, {2, 0}, {0, -2}, {0, 2},
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}};*/
	
	int[][] adj = {
			//{-3, 3}, {-3, -3}, {3, -3}, {3, 3},   
			//{-3, 2}, {-3,-2}, {2, -3}, {-2,-3}, {2, 3},  {3,-2},{3, 2},
			{-3, 1}, {-3,-1}, {1, -3}, {1, 3}, {-1,-3}, {3,-1}, {3, 1}, 
			//{-3, 0},  {0, -3}, {0, 3}, {3, 0},
			//{-2, 2}, {-2, -2}, {2, -2}, {2, 2},
			{-2, 1}, {-2,-1},  {1, -2}, {1, 2},{-1,-2},{-2,-1},{2,-1},{2, 1},
			//{-2, 0}, {0, -2}, {0, 2}, {2, 0},
			{-1, 1}, {-1, -1}, {1, -1}, {1, 1},
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	int[][] adj1 ={
			/*{-2, 2}, {-2, -2}, {2, -2}, {2, 2},
			{-2, 1}, {-2,-1},  {1, -2}, {1, 2},{-1,-2},{-2,-1},{2,-1},{2, 1},
			{-2, 0}, {0, -2}, {0, 2}, {2, 0},*/
			//{-1, 1}, {-1, -1}, {1, -1}, {1, 1},
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	//static int[][] adj2 = {{-3, 2}, {3, 2}, {2, -3}, {2, 3}};
	//static int[][] adj3 = {{-3, 3}, {3, 3}, {3, -3}, {3, 3}};
	
	public SnakePathClosest(LinkedList<Snake> otherSnakes, Snake mySnake, SnakePosition apple, boolean hiderun, int snakelength, SnakePosition lastTail) {
		this.otherSnakes = otherSnakes;
		this.mySnake = mySnake;
		this.apple = apple;
		this.hiderun = hiderun;
		hy = mySnake.head().x;
		hx = mySnake.head().y;
		this.snakelength = snakelength;
		this.lastTail =lastTail;
		this.TailPoint = new Point(lastTail.y, lastTail.x);
		//System.out.println("log" + "***************************** SHORT!!!!!!  ********************************* ");
		//ClosestPath();
	}
	
	public void ClosestPath() {
		boolean noClosePath = true;
		for (int[] off : adj)
		{
			Node nb = new Node(hy + off[0], hx+ off[1]);
			//Node nb = new Node(hx + off[0], hy+ off[1]);

			if (isInvalidPoint(nb))// checks if the move is valid
				continue;
			
			AStarr2 aaStar = new AStarr2(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(nb.col, nb.row));
			aaStar.setBlocks(otherSnakes, mySnake);
	        Noode endNodee = aaStar.findPathh();
	        
	        if(endNodee.getCol() != -1) {
	        	noClosePath = false;
				while (endNodee.getParent().getParent() != (null)) {
					endNodee = endNodee.getParent();
				}
				System.out.println("log" + "$$$$$$$$$$$$$$$$$$$$$$ SHORT!!!!!!  $$$$$$$$$$$$$$$$$$$$$$$$ ");

				/*System.out.println("log" + " ");
				System.out.println("log" + "LastTail: " + lastTail.x + "," + lastTail.y);
				System.out.println("log" + "Tail: " + mySnake.tail().x +"," + mySnake.tail().y);
				System.out.println("log" + "Head: " + hy +"," + hx);
				System.out.println("log" + "Node: " + endNodee.getCol() +"," + endNodee.getRow());
				*/
					
			    	if(hy == endNodee.getCol() && hx+1 == endNodee.getRow()) {//down
			    		System.out.println(1);
			    		break;
			    	}
			    	else if(hy == endNodee.getCol() && hx-1 == endNodee.getRow()) {// up
			    		System.out.println(0);
			    		break;
			    	}
			    	else if(hx == endNodee.getRow() && hy+1 == endNodee.getCol()) {//right
			    		System.out.println(3);
			    		break;
			    			
			    	}
			    	else if(hx == endNodee.getRow() && hy-1 == endNodee.getCol()) {//left
			    		System.out.println(2);
			    		break;
			    	}
			    	
			    	
			}
			/*else {
				System.out.println("log" + "***************************** STRAIGHT!!!!!!  ********************************* ");
				System.out.println(5);
				break;
			}*/
		}
		if(noClosePath) {
			System.out.println("log" + "***************************** STRAIGHT!!!!!!  ********************************* ");
			System.out.println(5);
		}
		
	}
	
	public void ClosestPathTail() {
		boolean noClosePath = true;
		for (int[] off : adj1)
		{
			Node nb = new Node(hy + off[0], hx+ off[1]);
			//Node nb = new Node(hx + off[0], hy+ off[1]);

			if (isInvalidPoint(nb))// checks if the move is valid
				continue;
			
			AStarr2 aaStar = new AStarr2(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(nb.col, nb.row));
			aaStar.setBlocks(otherSnakes, mySnake);
	        Noode endNodee = aaStar.findPathh();
	        
	        if(endNodee.getCol() != -1) {
	        	noClosePath = false;
				while (endNodee.getParent().getParent() != (null)) {
					endNodee = endNodee.getParent();
				}
				System.out.println("log" + "$$$$$$$$$$$$$$$$$$$$$$ SHORT TAIL!!!!!!  $$$$$$$$$$$$$$$$$$$$$$$$ ");

				/*System.out.println("log" + " ");
				System.out.println("log" + "LastTail: " + lastTail.x + "," + lastTail.y);
				System.out.println("log" + "Tail: " + mySnake.tail().x +"," + mySnake.tail().y);
				System.out.println("log" + "Head: " + hy +"," + hx);
				System.out.println("log" + "Node: " + endNodee.getCol() +"," + endNodee.getRow());
				*/
					
			    	if(hy == endNodee.getCol() && hx+1 == endNodee.getRow()) {//down
			    		System.out.println(1);
			    		break;
			    	}
			    	else if(hy == endNodee.getCol() && hx-1 == endNodee.getRow()) {// up
			    		System.out.println(0);
			    		break;
			    	}
			    	else if(hx == endNodee.getRow() && hy+1 == endNodee.getCol()) {//right
			    		System.out.println(3);
			    		break;
			    			
			    	}
			    	else if(hx == endNodee.getRow() && hy-1 == endNodee.getCol()) {//left
			    		System.out.println(2);
			    		break;
			    	}
			    	
			    	
			}
			/*else {
				System.out.println("log" + "***************************** STRAIGHT!!!!!!  ********************************* ");
				System.out.println(5);
				break;
			}*/
		}
		if(noClosePath) {
			System.out.println("log" + "***************************** STRAIGHT TAIL !!!!!!  ********************************* ");
			System.out.println(5);
		}
		
	}
	
	public boolean isInvalidPoint(Node pointt)
	{
		boolean snake  = false;
		Node point = new Node(pointt.col,pointt.row);
		for(int i = 0; i < otherSnakes.size(); i++) {
    		Snake s = otherSnakes.get(i);
    		for(int j = 0; j < s.coordinates.size(); j++) {
    			
    			if((point.row == s.coordinates.get(j).y) && (point.col == s.coordinates.get(j).x)) {// it doesnt eat other snakes
    				snake = true;
    				break;
    			}
    		}
    	}
		for(int j = 1; j < mySnake.coordinates.size(); j++) {
			
			if((point.row == mySnake.coordinates.get(j).y) && (point.col == mySnake.coordinates.get(j).x)) {// checks that it doesn't eat its self
				snake = true;
				break;
			}
		}
		return point.row < 0 || point.row >= 50 || point.col < 0 || point.col >= 50 || snake;
	}
	
	public void zigZag(/*Node point*/) {
		//boolean equal = false;
		//Node head = new Node(hy,hx);
		
			
		if((hx == mySnake.coordinates.get(1).y) && (hy-1 == mySnake.coordinates.get(1).x)) {// looks for the neck if its horizontal head on the right
			System.out.println("log" + " ");
			System.out.println("log" + " @@ HORIZONTAL HEAD RIGHT!!  @@@@@@@@@@@@@@@@");
			if(isInvalidPoint( new Node(hy,hx+1))) {// if down/ right side is closed
				if(isInvalidPoint( new Node(hy+1,hx))) {// if front is closed
					if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ left side is closed
						System.out.println(5);// its trapped move stright
					}
					else{
						System.out.println(4);// move up
						//System.out.println(0);// move up
					}
				}
				else {// if front is opened
					if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ left side is closed
						System.out.println(5);// move straight
					}
					else{
						System.out.println(5);// move straight
						
					}
				}
			}
			else {//if right/down is opened
				if(isInvalidPoint( new Node(hy+1,hx))) {// if front is closed
					if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ left side is closed
						System.out.println(6);// move down/ right
						//System.out.println(1);// move down
					}
					else{// left/up is opened
						System.out.println(4);// move up/ left
						//System.out.println(0);// move up
					}
				}
				else {// if front is opened
					if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ left side is closed
						System.out.println(5);// move straight
					}
					else{
						System.out.println(5);// move straight
						
					}
				}
			}
		}
		else if((hx+1 == mySnake.coordinates.get(1).y) && (hy == mySnake.coordinates.get(1).x)) {// looks for the neck if its vertical head up
			System.out.println("log" + " ");
			System.out.println("log" + " @@ VERTICAL HEAD UP!!  @@@@@@@@@@@@@@@@");
			if(isInvalidPoint( new Node(hy +1,hx))) {// if right side is closed
				if(isInvalidPoint( new Node(hy,hx-1))) {// if front is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {//if left side is closed
						System.out.println(5);// its trapped move stright
					}
					else{//left is opened
						System.out.println(4);// move left
					}
				}
				else {//if front is OPENED
					if(isInvalidPoint( new Node(hy-1,hx))) {//if left side is closed
						System.out.println(5);// it moves straight/front/UP
					}
					else{//left is opened
						System.out.println(4);// move left
					}
				}
			}
			else {// if right side is opened
				if(isInvalidPoint( new Node(hy,hx-1))) {// if front is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {//if left side is closed
						System.out.println(3);//move right
					}
					else{
						System.out.println(4);// move left
					}
				}
				else {//if front is opened
					if(isInvalidPoint( new Node(hy-1,hx))) {//if left side is closed
						System.out.println(3);// it moves right
					}
					else{//left is opened
						System.out.println(4);// move left
					}
				}
			}
		}
		else if((hx == mySnake.coordinates.get(1).y) && (hy+1 == mySnake.coordinates.get(1).x)) {// looks for the neck if its horizontal, head on the left
			System.out.println("log" + " ");
			System.out.println("log" + " @@ HORIZONTAL HEAD LEFT!!  @@@@@@@@@@@@@@@@");
			if(isInvalidPoint( new Node(hy,hx+1))) {// if down/ left side is closed
				if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ right side is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {// if front is closed
						System.out.println(5);// its trapped move straight
						
					}
					else{//front is opened
						System.out.println(5);// if front is opened move straight
						
					}
				}
				else {//if up/ right side is opened
					if(isInvalidPoint( new Node(hy-1,hx))) {// if front is closed
						System.out.println(3);// it moves up/right
						//System.out.println(0);// it moves up/right
						
					}
					else{//front is opened
						System.out.println(5);// if front is opened move straight
					}
				}
			}
			else {//  if down/ left side is opened
				if(isInvalidPoint( new Node(hy,hx-1))) {//if up/ right side is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {// if front is closed
						System.out.println(4);// move left/ down
						//System.out.println(1);// move down
					}
					else{// front is opened
						//System.out.println(5);// if front is opened move straight
						System.out.println(2);// if front is opened move straight
					}
				}
				else {//if up/ right side is opened
					if(isInvalidPoint( new Node(hy-1,hx))) {// if front is closed
						System.out.println(6);// it moves up/right
						//System.out.println(0);// it moves up/right
					}
					else{//front is opened
						System.out.println(5);// if front is opened move straight
						//System.out.println("log" + " !!!!!!!!!!!!!!!!!!!!!!!!! ITS ME THE PROBLEM !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
				}
			}
		}
		else if((hx-1 == mySnake.coordinates.get(1).y) && (hy == mySnake.coordinates.get(1).x)) {// looks for the neck if its vertical head down
			System.out.println("log" + " ");
			System.out.println("log" + " @@ VERTICAL HEAD DOWN!!  @@@@@@@@@@@@@@@@");
			if(isInvalidPoint( new Node(hy +1,hx))) {// if left side is closed
				if(isInvalidPoint( new Node(hy,hx+1))) {// if front is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {//if right side is closed
						System.out.println(5);// its trapped move stright
					}
					else{
						System.out.println(6);// move left
					}
				}
				else {//if front is opened
					if(isInvalidPoint( new Node(hy-1,hx))) {//if right side is closed
						System.out.println(5);// it moves straight/front
					}
					else{//right is opened
						System.out.println(6);// move left
					}
				}
			}
			else {// if left side is opened
				if(isInvalidPoint( new Node(hy,hx+1))) {// if front is closed
					if(isInvalidPoint( new Node(hy-1,hx))) {//if right side is closed
						System.out.println(4);// move left
					}
					else{//right is opened
						System.out.println(4);// move left
					}
				}
				else {//if front is opened
					if(isInvalidPoint( new Node(hy-1,hx))) {//if right side is closed
						System.out.println(4);// it moves left
					}
					else{//right is opened
						System.out.println(4);// move left
					}
				}
			}
		}
		else {
			System.out.println("log" + " ################### STRAIGHT!!  ###########");
			System.out.println(5);
		}
	}

}
