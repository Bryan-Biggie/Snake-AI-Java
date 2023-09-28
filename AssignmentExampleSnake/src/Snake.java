import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Snake {
	LinkedList<SnakePosition> coordinates;
	SnakePosition headSnake = new SnakePosition(0,0);
	//LinkedList<SnakePosition> snakeBody;
	public static List<Point> drawSnake(String[] snakePostion) {
		List<Point> coordinate = new ArrayList<Point>();
		
		for(int i = 0; i<snakePostion.length; i++) {
			if(i  == 0) {
				coordinate = drawLine(snakePostion[i],snakePostion[i],coordinate);
			}
			else {
				coordinate = drawLine(snakePostion[i],snakePostion[i-1], coordinate);
			}
			
		}
		return coordinate;
	}
	public static List<Point> drawLine(String snake,String snake1, List<Point> coordinate) {
		int maxX=0, minX=0,maxY=0, minY=0;
		String[] snakePostion0 = snake.split(",");// 2nd coordinate
		String[] snakePostion1 = snake1.split(",");// 1st coordinate
		
		if(Integer.parseInt(snakePostion0[0]) < Integer.parseInt(snakePostion1[0])) {
			maxX = Integer.parseInt(snakePostion1[0]);
			minX = Integer.parseInt(snakePostion0[0]);
		}
		else {
			maxX = Integer.parseInt(snakePostion0[0]);
			minX = Integer.parseInt(snakePostion1[0]);
		}
		if(Integer.parseInt(snakePostion0[1]) < Integer.parseInt(snakePostion1[1])) {
			maxY = Integer.parseInt(snakePostion1[1]);
			minY = Integer.parseInt(snakePostion0[1]);
		}
		else {
			maxY = Integer.parseInt(snakePostion0[1]);
			minY = Integer.parseInt(snakePostion1[1]);
		}
		String s,l = "";
		for(int i = 0; i<50; i++) {
			for(int j = 0; j<50; j++) {
				
				if((minX <= j && j<=maxX) && (minY <= i && i<=maxY)) {
					s = Integer.toString(i);
					l = Integer.toString(j);
					
					coordinate.add(new Point(j, i));
					
				}
			}
		}
		return coordinate;
		
	}
	
	public Snake(String snakeLine){
		coordinates = new LinkedList<SnakePosition>();
		String[] tempp = snakeLine.split(" ");
		int life = 0;
		String[] copyArray = {};
		if (tempp[0].equals("alive") ){
			headSnake = new SnakePosition(tempp[3],",");
			life = 1;
			copyArray = Arrays.copyOfRange(tempp, 3, tempp.length);
		}else if (tempp[0].equals("invisible")){
			life = 2;
			headSnake = new SnakePosition(tempp[4],",");
			copyArray = Arrays.copyOfRange(tempp, 5, tempp.length);
		}
		//String[] copyArray = Arrays.copyOfRange(tempp, 3, tempp.length);
		List<Point> Snakecoordinates = drawSnake(copyArray);
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		List<Point> p = Snakecoordinates;
		List<Point> mp = new ArrayList<Point>();
		List<Point> newP = new ArrayList<Point>();
		
		String[] s = copyArray;
		for(int i = 0; i <s.length; i++) {
			
			String[] si = s[i].split(",");
			int x = Integer.parseInt(si[0]);
			int y = Integer.parseInt(si[1]);
			mp.add(new Point(x,y ));
		}
		
		int t =0;
		int max,min;
		boolean ok= false;
		for (int i = 1; i <mp.size(); i++) {
			List<Point> storeP = new ArrayList<Point>();
			storeP.add(new Point(mp.get(i-1).x,mp.get(i-1).y));
			
			for (int m = 0; m <p.size(); m++) {
				if(mp.get(i).x == mp.get(i-1).x) {
					if(mp.get(i).y < mp.get(i-1).y) {
						max =  mp.get(i-1).y;
						min = mp.get(i).y;
						ok = true;
					}
					else {
						max = mp.get(i).y;
						min =  mp.get(i-1).y;
					}
					if(mp.get(i).x == p.get(m).x && min < p.get(m).y && p.get(m).y < max) {
						
						storeP.add(new Point(p.get(m).x,p.get(m).y));
						
					}
					
				}
				else if(mp.get(i).y == mp.get(i-1).y) {
					if(mp.get(i).x < mp.get(i-1).x) {
						max =  mp.get(i-1).x;
						min = mp.get(i).x;
						ok = true;
					}
					else {
						max = mp.get(i).x;
						min =  mp.get(i-1).x;
					}
					if(mp.get(i).y == p.get(m).y && min < p.get(m).x && p.get(m).x < max) {
						
						storeP.add(new Point(p.get(m).x,p.get(m).y));
						
					}
					
				}
				
			}
			storeP.add(new Point(mp.get(i).x,mp.get(i).y));
			
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			if(ok) {
			int temp =0;
			for (int ii = 0; ii <storeP.size(); ii++) {
				
		          for (int j = ii+1; j <storeP.size(); j++) { 
		              if(storeP.get(ii).x == storeP.get(j).x) {
		            	  if(storeP.get(ii).y < storeP.get(j).y) {
		            		  temp = storeP.get(ii).y;
		            		  storeP.get(ii).y = storeP.get(j).y;
		            		  storeP.get(j).y = temp;
		            		  
		            	  }
		                    
		               }
		              else if(storeP.get(ii).y == storeP.get(j).y) {
		            	  if(storeP.get(ii).x < storeP.get(j).x) {
		            		  temp = storeP.get(ii).x;
		            		  storeP.get(ii).x = storeP.get(j).x;
		            		  storeP.get(j).x = temp;
		            		  
		            	  }
		              }
		            }     
		        }}
			
			//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
			newP.addAll(storeP);
		}
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		List<Point> Snakecoordinate = new ArrayList<Point>();
		
		for(Point ele: newP)
        {
            if(!Snakecoordinate.contains(ele))
            	Snakecoordinate.add(ele);
        }
		/*for (int i=1; i<Snakecoordinate.size(); i++){// the boody of my snake excluding the head
			SnakePosition sp = new SnakePosition(Snakecoordinate.get(i));
			snakeBody.add(sp);
		}*/
		if (life ==1){
			for (int i=0; i<Snakecoordinate.size(); i++){
				SnakePosition sp = new SnakePosition(Snakecoordinate.get(i));
				coordinates.add(sp);
			}
			/*for (int i=3; i<temp.length; i++){
				SnakePosition sp = new SnakePosition(temp[i],",");
				coordinates.add(sp);
			}*/
		}
		else if (life == 2){
			for (int i=0; i<Snakecoordinate.size(); i++){
				SnakePosition sp = new SnakePosition(Snakecoordinate.get(i));
				coordinates.add(sp);
			}
		}
		
		
	}
	
	public SnakePosition head(){
		//return headSnake;
		return coordinates.get(0);
	}
	public SnakePosition headPath(){
		return headSnake;
	}
	/*public SnakePosition kinky(){
		return coordinates.get(1);
	}*/
	public SnakePosition tail(){
		return coordinates.get(coordinates.size()-1);
	}
	public SnakePosition secondtail(){
		return coordinates.get(coordinates.size());
	}
	}
