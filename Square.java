import java.awt.Graphics;
import java.awt.Color;

public class Square{
	private int red;
	private int green;
	private int blue;

	public Square(int red, int green, int blue){
		this.red = red;
		this.green =  green;
		this.blue = blue;
	}

	public void drawMe(Graphics g, int x, int y){
		Color myColor = new Color(red,green,blue);
		g.setColor( myColor );
		g.fillRect(x,y,30,30);
		//border
		g.setColor( Color.black );
		g.drawRect(x,y,30,30);
	}

	public void changeColor(int red, int green, int blue){
		this.red = red;
		this.green =  green;
		this.blue = blue;
	}
	
	public int getRed(){
		return this.red;
	}
	
	public int getGreen(){
		return this.green;
	}
	
	public int getBlue(){
		return this.blue;
	}

	public String toString(){
		return "" + getRed() + " " + getGreen() + " " + getBlue();
	}


}