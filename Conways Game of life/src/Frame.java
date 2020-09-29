import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	
	private final int window_width = 800;
	private final int window_height = 600;
	
	private final String title = "Conway's Game of Life 2020";
	
	private final int tile_size = 20;
	private final Color gridColor = Color.WHITE;
	private final int strokeWeight = 3;
	
	private Canvas canvas;
	
	public Frame()
	{
		setTitle(title);
		setSize(window_width, window_height);
		
		canvas = new Canvas();
		add(canvas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	
	private class Canvas extends JPanel{
		
		protected void paintComponent(Graphics g)
		{
			//switch to Graphics2D for more functionality
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);
			
			drawBackground(g2);
			drawGrid(g2);
		}
		
		private void drawBackground(Graphics2D g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, window_width, window_height);
		}
		
		private void drawGrid(Graphics2D g)
		{
			int tile_amount_x = window_width / tile_size;
			int tile_amount_y = window_height / tile_size;
			
			Stroke s = new BasicStroke(strokeWeight);
			
			g.setColor(gridColor);
			g.setStroke(s);
			
			for(int i = 0; i< tile_amount_y; i++)
			{
				for(int j = 0; j< tile_amount_x; j++)
				{
					g.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
				}
			}
			
		}
	}
}
