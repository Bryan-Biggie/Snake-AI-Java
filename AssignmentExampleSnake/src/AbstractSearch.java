
/*
 * Darren Kong 
 * AP-Java 
 * Mr.Levin
 */
public abstract class AbstractSearch{
	/*
	 * Fields
	 */
	NNode startNode;
	NNode goalNode;
	
	/*
	 * Constructor
	 */
	public AbstractSearch(NNode start, NNode goal){
		this.startNode = start;
		this.goalNode = goal;
	}
	
	/*
	 * Determines if a path can be found.
	 * @returns true if a path can be found.
	 */
	public abstract NNode search();

}
