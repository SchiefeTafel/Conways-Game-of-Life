import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

enum WRITE_MODE{
	SET,
	DELETE
}

public class M_MouseListener  extends Component implements MouseListener, MouseMotionListener {
	
	private int mouseX;
	private int mouseY;
	
	private boolean writePosition = false;
	private WRITE_MODE status = WRITE_MODE.SET;
	
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
	
	public void setWritePosition(boolean writePosition)
	{
		this.writePosition = writePosition;
	}
	
	public void clear()
	{
		this.mouseX = -1;
		this.mouseY = -1;
		
		setWritePosition(false);
	}
	
	public WRITE_MODE returnStatus()
	{
		return this.status;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		this.writePosition = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
		// should something be set or deleted
		if(e.getButton() == MouseEvent.BUTTON1)
			this.status = WRITE_MODE.SET;
		
		else if(e.getButton() == MouseEvent.BUTTON3)
			this.status = WRITE_MODE.DELETE;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		this.writePosition = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
		//should something be set or deleted
		if(e.getButton() == MouseEvent.BUTTON1)
			this.status = WRITE_MODE.SET;
		
		else if(e.getButton() == MouseEvent.BUTTON3)
			this.status = WRITE_MODE.DELETE;
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
		
		//should something be set or deleted
		if(e.getButton() == MouseEvent.BUTTON1)
			this.status = WRITE_MODE.SET;
		
		else if(e.getButton() == MouseEvent.BUTTON3)
			this.status = WRITE_MODE.DELETE;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
