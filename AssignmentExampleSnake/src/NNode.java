
/*
 * Darren Kong 
 * AP-Java 
 * Mr.Levin
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * The Node class represents a city,
 * as well as an ArrayList of nodes that will store
 * any instantiated nodes children.
 */
public class NNode implements Comparable<NNode>{
	
	public String name;
	private ArrayList<NNode> children;
	private NNode parent;
	private int minDistance = Integer.MAX_VALUE;
	private int index;
	private double cost;
	private int posX;
	private int posY;
	
	/*
	 * Creates a Node
	 */
	public NNode(String name, ArrayList<NNode> children){
		this.name = name;
		this.children = new ArrayList<>();
	}
	
	public NNode(String name, ArrayList<NNode> children, int index){
		this.name = name;
		this.children = children;
		this.setIndex(index);
	}
	
	public NNode(int x , int y){
		this.name = "(" + x + "," + y + ")";
		this.posX = x;
		this.posY = y;
	}
	/*Do not use this for AStar
	 * @return The children of the Node
	 */
    public ArrayList<NNode> getChildren(){
		ArrayList<NNode> childNodes = new ArrayList<>();
		for (NNode x : this.children){
			if (x!=null){
				childNodes.add(x);
			}
		}
		return childNodes;
	}
	
    /*
     * Adds children/connecting Nodes to the Node.
     */
    public void setChildren(ArrayList<NNode> arrayList){
    	this.children = arrayList;
    }
    /**
     * Sets the Parent of the Node
     * @param parent of the Node
     */
    public void setParent(NNode parent){
    	this.parent = parent;
    }
    /**
     * Sets the distance of the Node
     * @param distance from the start Node
     */
    public void setDistance(int distance){
    	this.minDistance = distance;
    }
    public int getDistance(){
    	return this.minDistance;
    }
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(NNode other) {
		return Double.compare(this.cost, other.getCost());
	}

	public double getCost() {
		return this.cost;
	}
	
	public void setCost(double c){
		this.cost = c;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public NNode getParent() {
		return this.parent;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
