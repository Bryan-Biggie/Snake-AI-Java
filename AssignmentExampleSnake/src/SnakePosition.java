import java.awt.Point;

public class SnakePosition {
	int x;
	int y;
	
	public SnakePosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public SnakePosition(Point s){
			
			this.x = s.x;
			this.y = s.y;
		}
	
	public SnakePosition(String s, String split){
		String[] temp = s.split(split);
		this.x = Integer.parseInt(temp[0]);
		this.y = Integer.parseInt(temp[1]);
	}
	
	
	public boolean above(SnakePosition other){
		return y<other.y;
	}
	
	public boolean below(SnakePosition other){
		return y>other.y;
	}
	
	public boolean left(SnakePosition other){
		return x<other.x;
	}
	
	public boolean right(SnakePosition other){
		return x>other.x;
	}
	public boolean samelineAbove(SnakePosition head,SnakePosition body){
		boolean bool = false;
		if((Math.abs(body.x - x) < (Math.abs(head.x- x))) && head.y >= y) {
			bool = true;
		}
		return bool;
	}
	public boolean samelineBelow(SnakePosition head,SnakePosition body){
		boolean bool = false;
		if((Math.abs(body.x - x) < (Math.abs(head.x- x))) && head.y < y) {
			bool = true;
		}
		return bool;
	}
	public boolean samelineLeft(SnakePosition head,SnakePosition body){
		boolean bool = false;
		if((Math.abs(body.y - y) < (Math.abs(head.y- y))) && head.x >= x) {
			bool = true;
		}
		return bool;
	}
	public boolean samelineRight(SnakePosition head,SnakePosition body){
		boolean bool = false;
		if((Math.abs(body.y - y) < (Math.abs(head.y- y))) && head.x < x) {
			bool = true;
		}
		return bool;
	}
	
}
