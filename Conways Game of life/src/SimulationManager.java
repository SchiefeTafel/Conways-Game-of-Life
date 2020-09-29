
public class SimulationManager {
	
	private int numberOfGenerations = 0;
	private Cell[][] cells;
	
	private static long lifeTimeOfGeneration = 100; //in ms
	private long timer = 0;
	
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
	
	public static void setSpeed(double factor)
	{
		//a speed of 1.0 => 100ms per generation
		
		lifeTimeOfGeneration *= (1/factor);
	}
	
	public void runSimulation(long deltaTime)
	{
		if(timer >= lifeTimeOfGeneration) {
			
			//keep track of the amount of neighbours each cell has
			int[][] neighbour_list = nums_neighbours();
			
			boolean[][] fate_of_next_generation = determineCellStatus(neighbour_list);
			
			applyCellStatusToNextGeneration(fate_of_next_generation);
			timer = 0;
		}
		
		else
			timer += deltaTime;
		
	}
	
	private void applyCellStatusToNextGeneration(boolean[][] fate)
	{
		for(int i = 0; i< Frame.tile_amount_y; i++)
		{
			for(int j = 0; j< Frame.tile_amount_x; j++)
			{
				if(fate[i][j] == true)
					cells[i][j].setStatus("ALIVE");
				else
					cells[i][j].setStatus("DEAD");
					
			}
		}
	}
	
	private int countLivingNeighbours(int cols, int rows)
	{
		int numLivingNeighbours = 0;
		
		for(int i = cols-1; i<cols+2; i++)
		{
			if(i < 0 || i > (Frame.tile_amount_y-1))
				continue;
			for(int j = rows-1; j<rows+2; j++)
			{
				
				if(j < 0 || (j > Frame.tile_amount_x-1))
					continue;
				
				if(!(i == cols && j == rows) && cells[i][j].isAlive())
					numLivingNeighbours++;
					
			}
		}
		
		return numLivingNeighbours;
			
	}
	
	private int[][] nums_neighbours()
	{
		int[][] nums_neighbours = new int[Frame.tile_amount_y][Frame.tile_amount_x];
		
		for(int i = 0; i< Frame.tile_amount_y; i++)
		{
			for(int j = 0; j< Frame.tile_amount_x; j++)
			{
				nums_neighbours[i][j] = countLivingNeighbours(i,j);
			}
		}
		
		return nums_neighbours;
	}
	
	private boolean[][] determineCellStatus(int[][] neighbour_list)
	{
		boolean[][] status_of_next_generation = new boolean[Frame.tile_amount_y][Frame.tile_amount_x];
		
		//RULES FOR CONWAYS GAME OF LIFE
		
		for(int i = 0; i< Frame.tile_amount_y; i++)
		{
			for(int j = 0; j < Frame.tile_amount_x; j++)
			{
				if(cells[i][j].isAlive()) {
					// cell dies out of loneliness
					if(neighbour_list[i][j] < 2)
						status_of_next_generation[i][j] = false;
					
					//optimal amount of neighbours
					else if(neighbour_list[i][j] == 2 || neighbour_list[i][j] == 3)
						status_of_next_generation[i][j] = true;
					
					//overpopulation
					else if(neighbour_list[i][j] > 3)
						status_of_next_generation[i][j] = false;
				}
				else {
					if(neighbour_list[i][j] == 3)
					{
						status_of_next_generation[i][j] = true;
					}
				}
			}
		}
		
		return status_of_next_generation;
	}

}
