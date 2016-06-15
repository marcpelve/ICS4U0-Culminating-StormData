package initialpkg;

import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.*;


public class CenterPanel extends PApplet {
	

	public void setup(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		size((int) (screenSize.getWidth() * 0.9), (int) (screenSize.getHeight() * 0.8));
		background(255);
	}
	
	
	
	public void draw(){
		background(255);
		fill(200);
		ellipseMode(CENTER);
		
	}

	
}