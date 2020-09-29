import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {
	
	private boolean alive = false;
	
	private int position_x;
	private int position_y;
	
	private Color cellColor = Color.white;
	
	public Cell(int position_x, int position_y)
	{
		this.position_x = position_x;
		this.position_y = position_y;
	}
	
	public boolean isAlive()
	{
		return this.alive;
	}
	
	public void setStatus(String status)
	{
		if(status.equals("ALIVE"))
			this.alive = true;
		
		else if(status.equals("DEAD"))
			this.alive = false;
	}
	
	public void render(Graphics2D g2)
	{
		g2.setColor(cellColor);
		
		if(this.alive)
			g2.fillRect(this.position_x, this.position_y, Frame.tile_size, Frame.tile_size);
	}
}
