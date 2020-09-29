import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
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
	private SimulationManager sim;
	
	public static boolean isSimulationRunning = false;
	public static boolean isControlScreenActive = true;
	
	private Image play_img;
	private Image stop_img;
	private Image control_img;
	
	//UI and Listeners
	M_MouseListener mouseListener;
	M_KeyListener keyListener;
	
	public static float speed_factor = 1.0f;
	
	public Frame() throws IOException, URISyntaxException
	{
		setTitle(title);
		setSize(window_width, window_height);
		
		cells = new Cell[tile_amount_y][tile_amount_x];
		sim = new SimulationManager(cells);
		
		initializeCells();
		
		mouseListener = new M_MouseListener();
		keyListener = new M_KeyListener();
		
		// by using pack(), offset doesnt have to be set manually
		game_canvas = new canvas();
		
		game_canvas.addMouseListener(mouseListener);
		game_canvas.addMouseMotionListener(mouseListener);
		game_canvas.addKeyListener(keyListener);
		
		play_img = ImageIO.read(new File(this.getClass().getClassLoader().getResource("assets/Play_BTN.png").toURI()));
		stop_img = ImageIO.read(new File(this.getClass().getClassLoader().getResource("assets/STOP_BTN.png").toURI()));
		control_img = ImageIO.read(new File(this.getClass().getClassLoader().getResource("assets/Control_screen.png").toURI()));

		
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
	
	public void run(long deltaTime) throws FontFormatException, IOException
	{
		
		if(!isControlScreenActive) 
			game_canvas.update(deltaTime);

		game_canvas.render();	
	}
	
	
	private class canvas extends Canvas{
		
		
		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(window_width, window_height);
		}
		
		private void update(long deltaTime)
		{
			if(!Frame.isSimulationRunning)
				placeCells();
			
			if(Frame.isSimulationRunning) {
				//clear mouse listener
				mouseListener.clear();
				sim.runSimulation(deltaTime);
			}
				
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
							
							if(mouseListener.returnStatus() == WRITE_MODE.SET)
									cells[i][j].setStatus("ALIVE");
							
							else if(mouseListener.returnStatus() == WRITE_MODE.DELETE)
								cells[i][j].setStatus("DEAD");
								
						}
					}
				}
			}
		}
		
		private void render() throws FontFormatException, IOException
		{
			BufferStrategy bs = getBufferStrategy();
			
			if(bs == null) {
				createBufferStrategy(3);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			Graphics2D g2 = (Graphics2D) g;
			
			/// ALL THE DIFFERENT DRAWING METHODS START HERE
			if(!isControlScreenActive) 
			{
				//when it switches the mouselistener has to be cleared
				mouseListener.clear();
				
				drawBackground(g2);
				drawGrid(g2);
				drawCells(g2);
				drawStartStopButton(g2);
				drawSpeed(g2);
			}
			else
			{
				drawControlScreen(g2);
			}
			/// ALL THE DIFFERENT DRAWING METHODS END HERE
			g.dispose();
			bs.show();
			
		}
		private void drawControlScreen(Graphics2D g2)
		{
			g2.drawImage(control_img, 0,0,null);
		}
		private void drawSpeed(Graphics2D g2) throws FontFormatException, IOException
		{
			int panel_width = Frame.tile_size*6;
			int panel_height = Frame.tile_size*2;
			
			int panel_x = Frame.window_width - 8*Frame.tile_size;
			int panel_y = 2;
			
			int font_offset_y = (int) (1.5*tile_size);
			int font_offset_x = (int) (tile_size/2);
			
			g2.setColor(Color.black);
			g2.fillRect(panel_x, panel_y, panel_width, panel_height);
			
			InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("assets/TheBrooklynSmoothBold.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(24f);
			g2.setColor(Color.white);
			g2.setFont(font);
			g2.drawString("SPEED:  "+speed_factor, panel_x+font_offset_x,panel_y+font_offset_y);
			
			//RAHMEN
			
			int border_width = panel_width + 2*tile_size;

			g2.setStroke(new BasicStroke(strokeWeight));
			g2.drawRect(panel_x, panel_y, border_width, panel_height);
			
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
		
		private void drawStartStopButton(Graphics2D g2)
		{
			int button_size = Frame.tile_size*2;
			
			int button_x = Frame.window_width-button_size;
			int button_y = 2;
			
			g2.setColor(Color.black);
			g2.fillRect(button_x, button_y, button_size, button_size);
			
			if(Frame.isSimulationRunning)
				g2.drawImage(play_img, button_x, button_y, button_size, button_size, null);
			
			else
				g2.drawImage(stop_img, button_x, button_y, button_size, button_size, null);
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
