import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	
	public static final int window_width = 800;
	public  static final int window_height = 600;
	
	private final String title = "Conway's Game of Life 2020";
	
	//needs to be visible for Cell class
	public static final int tile_size = 20;
	public static final int tile_amount_x = (window_width / tile_size);
	public static final int tile_amount_y = (window_height / tile_size);
	
	private final Color gridColor = Color.WHITE;
	private final int strokeWeight = 3;
	
	private canvas game_canvas;
	
	private Cell[][] cells;
	
	//UI and Listeners
	M_MouseListener mouseListener;
	
	public Frame()
	{
		setTitle(title);
		setSize(window_width, window_height);
		
		cells = new Cell[tile_amount_y][tile_amount_x];
		
		initializeCells();
		
		mouseListener = new M_MouseListener();
		
		// by using pack(), offset doesnt have to be set manually
		game_canvas = new canvas();
		
		game_canvas.addMouseListener(mouseListener);
		game_canvas.addMouseMotionListener(mouseListener);
		
		add(game_canvas);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private void initializeCells()
	{
		for(int i = 0; i< tile_amount_y; i++)
		{
			for(int j = 0; j < tile_amount_x; j++)
			{
				int position_y = i * tile_size;
				int position_x = j * tile_size;
				
				cells[i][j] = new Cell(position_x, position_y);
			}
		}
	}
	
	public void run()
	{
		game_canvas.update();
		game_canvas.render();
	}
	
	
	private class canvas extends Canvas{
		
		
		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(window_width, window_height);
		}
		
		private void update()
		{
			placeCells();
		}
		
		private void placeCells()
		{
			//check if the cursor intersects with a tile
			for(int i = 0; i< tile_amount_y; i++)
			{
				for(int j = 0; j< tile_amount_x; j++)
				{
					//mouse has been pressed
					if(mouseListener.returnPostion() != null)
					{
						//temporary rectangle for collision detection gets set
						Rectangle temp_mouse_rect = new Rectangle(mouseListener.returnPostion().width,
								mouseListener.returnPostion().height, 1,1);
						
						if(cells[i][j].getBounds().intersects(temp_mouse_rect)) {
							cells[i][j].setStatus("ALIVE");
						}
					}
				}
			}
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
			drawCells(g2);
			
			/// ALL THE DIFFERENT DRAWING METHODS END HERE
			g.dispose();
			bs.show();
			
		}
		
		private void drawCells(Graphics2D g2)
		{
			for(int i = 0; i< tile_amount_y; i++)
			{
				for(int j = 0; j< tile_amount_x; j++)
				{
					cells[i][j].render(g2);
				}
			}
		}
		
		private void drawBackground(Graphics2D g2)
		{
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, window_width, window_height);
		}
		
		private void drawGrid(Graphics2D g2)
		{
			
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
