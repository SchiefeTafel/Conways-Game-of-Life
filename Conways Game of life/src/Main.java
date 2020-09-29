import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
	
	public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException{
		
		Frame frame = new Frame();
		
		//calculating the DELTATIME
		
		long last_frame = System.currentTimeMillis();
		long this_frame;
		long deltaTime; 
		
		///
		while(true)
		{
			this_frame = System.currentTimeMillis();
			deltaTime = this_frame - last_frame;
			
			frame.run(deltaTime);
			
			last_frame = this_frame;
		}
	}
}
