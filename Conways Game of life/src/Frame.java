import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private final int window_width = 800;
	private final int window_height = 600;
	
	private final String title = "Conway's Game of Life 2020";
	
	public Frame()
	{
		setTitle(title);
		setSize(window_width, window_height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setVisible(true);
	}
}
