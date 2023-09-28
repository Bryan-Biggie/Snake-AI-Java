//import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {

    public static void main(String args[]) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            Point pTail = new Point(0,0);
            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                String apple1 = line;
                SnakePosition apple11;
                String apple2 = br.readLine();
                SnakePosition apple22;
                //do stuff with apples
              //*************************************
                /*SnakePosition apple;
                if(!apple1.equals("-1 -1")) {
                	
                	apple = new SnakePosition(apple1, " ");
                }else {
                	apple = new SnakePosition(apple2, " ");
                }*/
				Snake mySnake = null;
				int snakelength = 0;
				SnakePosition lastTail = new SnakePosition(-1,-1);;
				//*******************************
				boolean hiderun  = true;
				boolean blue  = false;
				boolean ImInvisible = false;
				LinkedList<Snake> otherSnakes = new LinkedList<Snake>();
                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    if (i == mySnakeNum) {
                    	mySnake = new Snake(snakeLine);
                    	String[] tempp = snakeLine.split(" ");
                    	snakelength = Integer.parseInt(tempp[1]);
                    	lastTail = new SnakePosition(tempp[tempp.length-1], ",");
                    	if (tempp[0].equals("invisible")){
                    		ImInvisible = true;
                    	}
                    	
                        //hey! That's me :)
                    }else {
                    	Snake NotMySnake = new Snake(snakeLine);
                    	String[] tempp = snakeLine.split(" ");
                    	if (tempp[0].equals("invisible")){
                    		hiderun = false;
                    	}
                    	otherSnakes.add(NotMySnake);
                    }
                    
                    //do stuff with other snakes
                }
                SnakePosition apple;
                if(!apple1.equals("-1 -1")) {
                	apple11 = new SnakePosition(apple1, " ");
                	apple22 = new SnakePosition(apple2, " ");
                	int a1 = Math.abs(mySnake.head().y-apple11.y) + Math.abs(mySnake.head().x-apple11.x);
                	int a2 = Math.abs(mySnake.head().y-apple22.y) + Math.abs(mySnake.head().x-apple22.x);
                	if(a1 <= a2) {
                		apple = apple11;
                		blue  = true;
                	}
                	else if(a1 > a2) {
                		apple = apple22;
                		hiderun = false;
                	}
                	else {
                		apple = apple22;
                	}
                	//apple = new SnakePosition(apple1, " ");
                }else {
                	apple = new SnakePosition(apple2, " ");
                }
               
              //===================================================================================
                if(!hiderun) {
                }
                else if(blue) {
                	int mySnakePath = Math.abs(mySnake.head().y-apple.y) + Math.abs(mySnake.head().x-apple.x);
                    int[] Opath= new int[3];
                    //System.out.println("log" + " ");
                   // System.out.println("log" + "My Snake: " + mySnakePath);
                    for(int mm = 0; mm < otherSnakes.size(); mm++) {
                    	Snake osnake = otherSnakes.get(mm);
                    	int OtherSnakePath = Math.abs(osnake.headPath().y-apple.y) + Math.abs(osnake.headPath().x-apple.x);
                    	Opath[mm] = OtherSnakePath;
                    	//System.out.println("log" + "Snake " + mm + ": " + OtherSnakePath);
                    }
                    for(int v = 0; v < Opath.length; v++) {
                    	//System.out.println("log" + "Snake " + v + ": " + Opath[v]);
                    	if(Opath[v] <= mySnakePath) {
                        	
                        	hiderun = false;
                        }
                    }
                }
                else if(ImInvisible || snakelength <= 180) {
                	int mySnakePath = Math.abs(mySnake.head().y-apple.y) + Math.abs(mySnake.head().x-apple.x);
                    int[] Opath= new int[3];
                    //System.out.println("log" + " ");
                   // System.out.println("log" + "My Snake: " + mySnakePath);
                    for(int mm = 0; mm < otherSnakes.size(); mm++) {
                    	Snake osnake = otherSnakes.get(mm);
                    	int OtherSnakePath = Math.abs(osnake.headPath().y-apple.y) + Math.abs(osnake.headPath().x-apple.x);
                    	Opath[mm] = OtherSnakePath;
                    	//System.out.println("log" + "Snake " + mm + ": " + OtherSnakePath);
                    }
                    for(int v = 0; v < Opath.length; v++) {
                    	//System.out.println("log" + "Snake " + v + ": " + Opath[v]);
                    	if(Opath[v] == mySnakePath) {
                        	
                        	hiderun = false;
                        }
                    }
                }
                else {
                int mySnakePath = Math.abs(mySnake.head().y-apple.y) + Math.abs(mySnake.head().x-apple.x);
                int[] Opath= new int[3];
                //System.out.println("log" + " ");
               // System.out.println("log" + "My Snake: " + mySnakePath);
                for(int mm = 0; mm < otherSnakes.size(); mm++) {
                	Snake osnake = otherSnakes.get(mm);
                	int OtherSnakePath = Math.abs(osnake.headPath().y-apple.y) + Math.abs(osnake.headPath().x-apple.x);
                	Opath[mm] = OtherSnakePath;
                	//System.out.println("log" + "Snake " + mm + ": " + OtherSnakePath);
                }
                for(int v = 0; v < Opath.length; v++) {
                	//System.out.println("log" + "Snake " + v + ": " + Opath[v]);
                	if(Opath[v] <= mySnakePath) {
                    	
                    	hiderun = false;
                    }
                }
                }
                
                //===================================================================================
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ CALLING THE SEARCHES $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                
                
                //SnakePathBFS Bfspath = new SnakePathBFS(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
                
               SnakePathAstar AstarPath = new SnakePathAstar(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
               AstarPath.AstarApples();
                
               // SnakePathShortAstar astarp = new SnakePathShortAstar(apple, otherSnakes, mySnake, lastTail, hiderun, snakelength);
               // astarp.PathLength();
                
              // SnakePathClosest close = new SnakePathClosest(otherSnakes, mySnake, apple, hiderun, snakelength, lastTail);
                
                
                
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                /*
                Node[] path = new Main().BFS(mySnake.head().y, mySnake.head().x, new Point(apple.y, apple.x), otherSnakes, mySnake);
                Node start = path[0];
                Node end = path[1];
                //Node endNode = end.parent;
                Node endNode = end;
        	    if(start.row != -1 && hiderun) {
        	    	
        	    int hy = mySnake.head().x;
        	    int hx = mySnake.head().y;
        	    while (!endNode.equals(start)) {
        	    	endNode = endNode.parent;
        	    	if(hy == endNode.col) {
        	    		if(hx+1 == endNode.row) {//down
        	    			System.out.println(1);
        	    			break;
            	    	}
        	    		else if(hx-1 == endNode.row){//up
        	    			System.out.println(0);
        	    			break;
        	    		}
        	    	}
        	    	else if(hx == endNode.row) {
        	    		if(hy+1 == endNode.col) {//right
        	    			System.out.println(3);
        	    			break;
            	    	}
        	    		else if(hy-1 == endNode.col){//left
        	    			System.out.println(2);
        	    			break;
        	    		}
        	    	}
        	    }
        	    }
        	    else {
        	    	//int movee = new Random().nextInt(7);
        	    	//Point pp = new Point(movee, movee);
        	    	Point pp = new Point(mySnake.tail().y, mySnake.tail().x);
        	    	
        	    	List<Node> bfsPath = new ArrayList<Node>();
        	    	Node[] pathh = new Main().BFS(mySnake.head().y, mySnake.head().x, pp, otherSnakes, mySnake);
                    Node startt = pathh[0];
                    Node enddd = pathh[1];
                    Node endd = enddd;
                    if(startt.row != -1) {
                    	while (!endd.equals(startt))
                		{
                    		endd = endd.parent;
                			bfsPath.add(endd);
                		}
                    	//Node endNodee = endd;
                	    int hy = mySnake.head().x;
                	    int hx = mySnake.head().y;
                	    //]]***************************************************************
                	    //Node endNodee = bfsPath.get(0);
            	    	//endNodee = endNodee.parent;
            	    	
                	    //]]****************************************************************
                	    //while (!endNodee.parent.equals(startt)) {
                	    for(int c = 0; c < bfsPath.size(); c++) {
                	    	Node endNodee = bfsPath.get(c);
                	    	//endNodee = endNodee.parent;
                	    	if(hy == endNodee.col) {
                	    		if(hx+1 == endNodee.row) {//down
                	    			System.out.println(1);
                	    			break;
                    	    	}
                	    		else if(hx-1 == endNodee.row){//up
                	    			System.out.println(0);
                	    			break;
                	    		}
                	    	}
                	    	else if(hx == endNodee.row) {
                	    		if(hy+1 == endNodee.col) {//right
                	    			System.out.println(3);
                	    			break;
                    	    	}
                	    		else if(hy-1 == endNodee.col){//left
                	    			System.out.println(2);
                	    			break;
                	    		}
                	    	}
                	    	
                	    	//endNodee = endNodee.parent;
                	    }
                	    }else {
            	    		
            	    		int move = new Random().nextInt(4);
        					System.out.println(5);
            	    	}
    	    	}*/
 // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        	    
 // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        	    /*AStarr aStar = new AStarr(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(apple.y, apple.x));
        		aStar.setBlocks(otherSnakes, mySnake);
                //List<Noode> ath = aStar.findPath();
                Noode endNodee = aStar.findPathh();
                
                
        	    if(endNodee.getCol() != -1 && hiderun) {
        	    	
        	    int hy = mySnake.head().x;
        	    int hx = mySnake.head().y;
        	    while (endNodee.getParent() != (null)) {
        	    	//endNode = endNode.parent;
        	    	if(hy == endNodee.getCol() && hx == endNodee.getRow()) {
        	    		continue;
        	    	}
        	    	if(hy == endNodee.getCol()) {
        	    		if(hx+1 == endNodee.getRow()) {//down
        	    			System.out.println(1);
        	    			break;
            	    	}
        	    		else if(hx-1 == endNodee.getRow()){//up
        	    			System.out.println(0);
        	    			break;
        	    		}
        	    	}
        	    	else if(hx == endNodee.getRow()) {
        	    		if(hy+1 == endNodee.getCol()) {//right
        	    			System.out.println(3);
        	    			break;
            	    	}
        	    		else if(hy-1 == endNodee.getCol()){//left
        	    			System.out.println(2);
        	    			break;
        	    		}
        	    	}
        	    	
        	    	endNodee = endNodee.getParent();
        	    }
        	    }
        	    else {
        	    	AStarr aaStar = new AStarr(50, 50, new Noode(mySnake.head().y, mySnake.head().x), new Noode(mySnake.tail().y, mySnake.tail().x));
            		aaStar.setBlocks(otherSnakes, mySnake);
                    //List<Noode> ath = aStar.findPath();
                    Noode endNodeee = aaStar.findPathh();
        	    	
                    if(endNodeee.getCol() != -1) {
                	    int hy = mySnake.head().x;
                	    int hx = mySnake.head().y;
                	    endNodeee = endNodeee;
                	    while (endNodeee.getParent() != (null)) {
                	    	//endNodee = endNodee.parent;
                	    	if(hy == endNodeee.getCol() && hx == endNodeee.getRow()) {
                	    		continue;
                	    	}
                	    	if(hy == endNodeee.getCol()) {
                	    		if(hx+1 == endNodeee.getRow()) {//down
                	    			System.out.println(1);
                	    			break;
                    	    	}
                	    		else if(hx-1 == endNodeee.getRow()){//up
                	    			System.out.println(0);
                	    			break;
                	    		}
                	    	}
                	    	else if(hx == endNodeee.getRow()) {
                	    		if(hy+1 == endNodeee.getCol()) {//right
                	    			System.out.println(3);
                	    			break;
                    	    	}
                	    		else if(hy-1 == endNodeee.getCol()){//left
                	    			System.out.println(2);
                	    			break;
                	    		}
                	    	}
                	    	
                	    	endNodeee = endNodeee.getParent();
                	    }
                	    }else {
            	    		
            	    		int move = new Random().nextInt(4);
        					System.out.println(5);
            	    	}
    	    	}*/
              
 // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                /*AbstractSearch aStarpath = new AAstar(new NNode(mySnake.head().y, mySnake.head().x), new NNode(apple.y, apple.x), otherSnakes, mySnake);
                //AbstractSearch aStarpath = new AAstar(new NNode(apple.y, apple.x), new NNode(mySnake.head().y, mySnake.head().x), otherSnakes, mySnake);
                NNode goalss = aStarpath.search();
                List<NNode> p = new ArrayList<NNode>();
                
                
        	    if(goalss.getPosY() != -1 && hiderun) {
        	    while (goalss.getParent() != null){
                	//goal = goal.getParent();
                	p.add(goalss);// put the path coordinates in a list because they are in reverse order
        			goalss = goalss.getParent();
        			
        		}	
        	    	
        	    int hy = mySnake.head().x;
        	    int hx = mySnake.head().y;
        	    for(int l =p.size()-1; l >= 0; l--) {
        	    	NNode goal = p.get(l);
        	    	//endNode = endNode.parent;
        	    	
        	    	if(hy == goal.getPosY()) {
        	    		if(hx+1 == goal.getPosX()) {//down
        	    			System.out.println(1);
        	    			break;
            	    	}
        	    		else if(hx-1 == goal.getPosX()){//up
        	    			System.out.println(0);
        	    			break;
        	    		}
        	    	}
        	    	else if(hx == goal.getPosX()) {
        	    		if(hy+1 == goal.getPosY()) {//right
        	    			System.out.println(3);
        	    			break;
            	    	}
        	    		else if(hy-1 == goal.getPosY()){//left
        	    			System.out.println(2);
        	    			break;
        	    		}
        	    	}
        	    	
        	    	//goal = goal.getParent();
        	    }
        	    }
        	    else {
        	    	AbstractSearch aaStarpath = new AAstar(new NNode(mySnake.head().y, mySnake.head().x), new NNode(mySnake.tail().y, mySnake.tail().x), otherSnakes, mySnake);
                    NNode gggoals = aaStarpath.search();
                    
        	    	
                    if(gggoals.getPosY() != -1) {
                    	NNode ggoals = gggoals.getParent();
                    	while (ggoals.getParent() != null){
                    	//goal = goal.getParent();
                    	p.add(ggoals);
            			ggoals = ggoals.getParent();
            			
                    	}
                	    int hy = mySnake.head().x;
                	    int hx = mySnake.head().y;
                	    
                	    for(int l =p.size()-1; l > 0; l--) {
                	    	NNode goals = p.get(l);// List with the coordinates
                	    	//endNodee = endNodee.parent;
                	    	if(hy == goals.getPosY() && hx == goals.getPosY()) {
                	    		continue;
                	    	}
                	    	if(hy == goals.getPosY()) {
                	    		if(hx+1 == goals.getPosX()) {//down
                	    			System.out.println(1);
                	    			break;
                    	    	}
                	    		else if(hx-1 == goals.getPosX()){//up
                	    			System.out.println(0);
                	    			break;
                	    		}
                	    	}
                	    	else if(hx == goals.getPosX()) {
                	    		if(hy+1 == goals.getPosY()) {//right
                	    			System.out.println(3);
                	    			break;
                    	    	}
                	    		else if(hy-1 == goals.getPosY()){//left
                	    			System.out.println(2);
                	    			break;
                	    		}
                	    	}
                	    	
                	    	//goals = goals.getParent();
                	    }
                	    }else {
            	    		
            	    		int move = new Random().nextInt(4);
        					System.out.println(5);
            	    	}
    	    	}*/
 // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
             
                
 // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ DOnt use the code just for test $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}