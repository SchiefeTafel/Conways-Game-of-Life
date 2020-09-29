import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class M_MouseListener  extends Component implements MouseListener, MouseMotionListener {
	
	private int mouseX;
	private int mouseY;
	
	private boolean writePosition = false;
	
	public M_MouseListener()
	{
		
	}
	
	public Dimension returnPostion()
	{
		if(writePosition)
			return new Dimension(this.mouseX, this.mouseY);
		
		else
			return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.writePosition = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		this.writePosition = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		this.writePosition = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		this.writePosition = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
