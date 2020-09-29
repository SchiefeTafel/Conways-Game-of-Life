import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	
	private final int window_width = 800;
	private final int window_height = 600;
	
	private final String title = "Conway's Game of Life 2020";
	
	private final int tile_size = 20;
	private final Color gridColor = Color.WHITE;
	private final int strokeWeight = 3;
	
	private canvas game_canvas;
	
	public Frame()
	{
		setTitle(title);
		setSize(window_width, window_height);
		
		// by using pack(), offset doesnt have to be set manually
		game_canvas = new canvas();
		add(game_canvas);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void render()
	{
		game_canvas.render();
	}
	
	
	private class canvas extends Canvas{
		
		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(window_width, window_height);
		}
		
		private void render()
		{
			BufferStrategy bs = getBufferStrategy();
			
			if(bs == null) {
				createBufferStrategy(3);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			Graphics2D g2 = (Graphics2D) g;
			
			/// ALL THE DIFFERENT DRAWING METHODS START HERE
			drawBackground(g2);
			drawGrid(g2);
			
			/// ALL THE DIFFERENT DRAWING METHODS END HERE
			g.dispose();
			bs.show();
			
		}
		
		private void drawBackground(Graphics2D g2)
		{
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, window_width, window_height);
		}
		
		private void drawGrid(Graphics2D g2)
		{
			int tile_amount_x = window_width / tile_size;
			int tile_amount_y = window_height / tile_size;
			
			Stroke s = new BasicStroke(strokeWeight);
			
			g2.setColor(gridColor);
			g2.setStroke(s);
			
			for(int i = 0; i< tile_amount_y; i++)
			{
				for(int j = 0; j< tile_amount_x; j++)
				{
					g2.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
				}
			}
			
		}
	}
}
