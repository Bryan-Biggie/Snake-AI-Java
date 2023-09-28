
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main
{
	static int r = 50, c = 50;
	static int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	//static int[][] adj = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static LinkedList<Snake> otherSnakes;
	static Snake mySnake;

	public static boolean isInvalidPoint(Node point)
	{
		boolean snake  = false;
		for(int i = 0; i < otherSnakes.size(); i++) {
    		Snake s = otherSnakes.get(i);
    		for(int j = 0; j < s.coordinates.size(); j++) {
    			/*if(j==0) {
    				if((point.row+1 == s.coordinates.get(j).y) && (point.col == s.coordinates.get(j).x)) {// it doesnt eat other snakes
        				snake = true;
        				break;
        			}
    				else if((point.row-1 == s.coordinates.get(j).y) && (point.col == s.coordinates.get(j).x)) {// it doesnt eat other snakes
        				snake = true;
        				break;
        			}
    				else if((point.row == s.coordinates.get(j).y) && (point.col+1 == s.coordinates.get(j).x)) {// it doesnt eat other snakes
        				snake = true;
        				break;
        			}
    				else if((point.row == s.coordinates.get(j).y) && (point.col-1 == s.coordinates.get(j).x)) {// it doesnt eat other snakes
        				snake = true;
        				break;
        			}
    			}
    			else {*/
    			if((point.row == s.coordinates.get(j).y) && (point.col == s.coordinates.get(j).x)) {// it doesnt eat other snakes
    				snake = true;
    				break;
    			}
    			//}
    			
    			
    		}
    	}
		for(int j = 1; j < mySnake.coordinates.size(); j++) {
			
			if((point.row == mySnake.coordinates.get(j).y) && (point.col == mySnake.coordinates.get(j).x)) {// checks that it doesnt eat its self
				snake = true;
				break;
			}
		}
		return point.row < 0 || point.row == r || point.col < 0 || point.col == c || snake;
	}

	public static Node[] BFS(int sr, int sc, Point p, LinkedList<Snake> otherSnake, Snake mySnakee)
	{
		Point end = new Point(sr,sc), start = new Point(p.row,p.col);
		otherSnakes = otherSnake;
		mySnake = mySnakee;


		Node head = new Node(start.row, start.col);
		Node apple = new Node(end.row, end.col);
		Node[] path = new Node[2];
		Node[] closedpath = new Node[2];
		closedpath[0] = new Node(-1, -1);//if the path is not found
		// BFS

		boolean[][] visited = new boolean[r][c]; // All cells marked to false
		Queue<Node> toVisit = new LinkedList<>();

		toVisit.add(head);
		visited[head.row][head.col] = true;

		while (!toVisit.isEmpty())
		{
			Node topNode = toVisit.poll();

			if (topNode.equals(apple))
			{
				
				path[0] = head;
				path[1] = topNode;
				return path;
			}

			for (int[] off : adj)
			{
				Node nb = new Node(topNode.row + off[0], topNode.col + off[1]);

				if (isInvalidPoint(nb) || visited[nb.row][nb.col])// checks if the move is valid
					continue;

				visited[nb.row][nb.col] = true;
				toVisit.add(nb);
				nb.parent = topNode;// the shortest path
			}
		}

		
		return closedpath;
	}

	
}
