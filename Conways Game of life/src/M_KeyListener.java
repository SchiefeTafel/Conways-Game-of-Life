import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class M_KeyListener implements KeyListener{
	
	private boolean pressable = true;

	@Override
	public void keyTyped(KeyEvent e) {
			
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && pressable) {
			
			if(Frame.isSimulationRunning)
				Frame.isSimulationRunning = false;
			
			else if(!Frame.isSimulationRunning)
				Frame.isSimulationRunning = true;
				
			System.out.println(Frame.isSimulationRunning);
		}
		pressable = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		pressable = true;
		
	}

}
