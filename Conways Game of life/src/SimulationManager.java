
public class SimulationManager {
	
	private int numberOfGenerations = 0;
	private Cell[][] cells;
	
	public SimulationManager()
	{
		this.cells = null;
	}
	
	public SimulationManager(Cell[][] cells)
	{
		this.cells = cells;
	}
	
	public void setCells(Cell[][] cells)
	{
		this.cells = cells;
	}
	
	public void runSimulation()
	{
		cells[0][1].setStatus("ALIVE");
	}
	/*
	public int countLivingNeighbours(int posx, int posy)
	{
		int numLivingNeighbours = 0;
		
		for(int i = posy-1; i<3; i++)
		{
			for(int j = posx-1; j<3; j++)
			{
				
				
			}
		}
			
	}
	*/
	

}
