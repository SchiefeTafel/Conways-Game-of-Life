
public class Main {
	
	public static void main(String[] args){
		
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
