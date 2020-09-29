import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class M_KeyListener implements KeyListener{
	
	private boolean pressable = true;

	@Override
	public void keyTyped(KeyEvent e) {
			
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && pressable && !Frame.isControlScreenActive) {
			
			if(Frame.isSimulationRunning)
				Frame.isSimulationRunning = false;
			
			else if(!Frame.isSimulationRunning)
				Frame.isSimulationRunning = true;
				
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_UP && pressable && !Frame.isControlScreenActive)
		{
			if(Frame.speed_factor < 3)Frame.speed_factor +=0.25;
			SimulationManager.setSpeed(Frame.speed_factor);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN && pressable && !Frame.isControlScreenActive)
		{
			if(Frame.speed_factor > 0.5)Frame.speed_factor -=0.25;
			SimulationManager.setSpeed(Frame.speed_factor);
			System.out.println(Frame.speed_factor);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE && pressable)
		{
			//alternating between the two states
			if(Frame.isControlScreenActive)
				Frame.isControlScreenActive = false;
			else
				Frame.isControlScreenActive = true;
		}
		
		pressable = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		pressable = true;
		
	}

}
