import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Screen extends JPanel implements MouseListener{
	

	private Square[][] grid;
	private Color[][] color;
	
	int red = 0;
	int green = 0;
	int blue = 0;

	Stack<Square[][]> undoGrid = new Stack<Square[][]>();

	Stack<Square[][]> redoGrid = new Stack<Square[][]>();

	public Screen(){

		addMouseListener(this);
		
		grid = new Square[16][16];
		for(int r=0; r<grid.length; r++){
			for(int c = 0; c < grid[r].length; c++){
				grid[r][c] = new Square(255,255,255);
			}
		}
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		//draw the grid
		int x = 0;
		int y = 0;
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[r].length; c++){
				grid[r][c].drawMe(g,x,y);
				x += 30;
			}
			y += 30;
			x = 0;
		}
		
		//create buttons to change colors
		int buttonX = 600;
		int buttonY = 400;
		int counter = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 2; j++){
				if(counter == 0){
					g.setColor(Color.red);
				}
				else if (counter == 1){
					g.setColor(Color.green);
				}
				else if(counter == 2){
					g.setColor(Color.blue);
				}
				else if(counter == 3){
					g.setColor(Color.white);
				}
				else if (counter == 4){
					g.setColor(Color.black);
				}
				else if(counter == 5){
					g.setColor(Color.yellow);
				}
				else if(counter == 6){
					g.setColor(Color.orange);
				}
				else if(counter == 7){
					g.setColor(Color.cyan);
				}
				g.fillRect(buttonX, buttonY, 50, 35);
				buttonX += 70;
				counter++;
			}
			buttonX = 600;
			buttonY += 45;
		}
		
		//create reset button
		int resetX = 600;
		int resetY = 200;
		g.setColor(Color.white);
		g.fillRect(resetX, resetY, 50, 35);
		g.setColor(Color.black);
		g.drawString("reset", 610, 215);
		
		//create undo button
		int undoX = 600;
		int undoY = 100;
		g.setColor(Color.white);
		g.fillRect(undoX, undoY, 50, 35);
		g.setColor(Color.black);
		g.drawString("undo", 610, 115);

		//create redo button
		int redoX = 600;
		int redoY = 150;
		g.setColor(Color.white);
		g.fillRect(redoX, redoY, 50, 35);
		g.setColor(Color.black);
		g.drawString("redo", 610, 165);

	}

	public void mousePressed(MouseEvent e) {
		int row = 0;
		int col = 0;

		
		System.out.println(e.getX() + ", " + e.getY());
		
		
		int buttonX = 600;
		int buttonY = 400;
		
		int counter = 0;
		
		//undo method
		if(e.getX() > 600 && e.getY() > 100 && e.getX() < 650 && e.getY() < 135){
			Square[][] newCopy = new Square[16][16];
			for(int r = 0; r < grid.length; r++){
				for(int c = 0; c < grid[r].length; c++){
					newCopy[r][c] = new Square(grid[r][c].getRed(), grid[r][c].getGreen(), grid[r][c].getBlue());
					redoGrid.push(newCopy);
				}
			}

			if(!undoGrid.empty()){
				for(int r = 0; r < grid.length; r++){
					for(int c = 0; c < grid[r].length; c++){
						undoGrid.pop();
						Square temp = new Square(undoGrid.peek()[r][c].getRed(), undoGrid.peek()[r][c].getGreen(), undoGrid.peek()[r][c].getBlue());
						grid[r][c] = temp;

						repaint();
					}
				}
			}
		}


		//redo method
		if(e.getX() > 600 && e.getY() > 150 && e.getX() < 650 && e.getY() < 185){

			System.out.println("redo");

			if(!redoGrid.empty()){
				for(int r = 0; r < grid.length; r++){
					for(int c = 0; c < grid[r].length; c++){
						redoGrid.pop();
						Square temp = new Square(redoGrid.peek()[r][c].getRed(), redoGrid.peek()[r][c].getGreen(), redoGrid.peek()[r][c].getBlue());
						grid[r][c] = temp;

						repaint();
					}
				}


			}
			
			repaint();
		}
		


		//reset
		if(e.getX() > 600 && e.getY() > 200 && e.getX() < 650 && e.getY() < 235){

			Square[][] newCopy = new Square[16][16];
			for(int r = 0; r < grid.length; r++){
				for(int c = 0; c < grid[r].length; c++){
					newCopy[r][c] = new Square(grid[r][c].getRed(), grid[r][c].getGreen(), grid[r][c].getBlue());
					undoGrid.push(newCopy);
				}
			}


			red = 255;
			green = 255;
			blue = 255;
			for(int r = 0; r < 16; r++){
				for(int c = 0; c < 16; c++){
					grid[r][c].changeColor(red, green, blue);
				}
			}
		}

		
		//chose your color
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 2; j++){
				if(e.getX() > buttonX && e.getX() < buttonX + 50 && e.getY() > buttonY && e.getY() < buttonY + 35){
					if (counter == 0){
						red = 255;
						green = 0;
						blue = 0;
					}
					else if (counter == 1){
						red = 0;
						green = 255;
						blue = 0;
					}
					else if (counter == 2){
						red = 0;
						green = 0;
						blue = 255;
					}
					else if (counter == 3){
						red = 255;
						green = 255;
						blue = 255;
					}
					else if (counter == 4){
						red = 0;
						green = 0;
						blue = 0;
					}
					else if (counter == 5){
						red = 255;
						green = 255;
						blue = 0;
					}
					else if (counter == 6){
						red = 240;
						green = 171;
						blue = 0;
					}
					else if (counter == 7){
						red = 35;
						green = 182;
						blue = 182;
					}
				}
				buttonX += 70;
				counter++;
			}
			buttonX = 600;
			buttonY += 45;
		}
		

		

		if(e.getY()/30 <= 16){
			if(e.getX()/30 <= 16){
				row = e.getY() / 30;
				col = e.getX() / 30;

				Square[][] newCopy = new Square[16][16];
				for(int r = 0; r < grid.length; r++){
					for(int c = 0; c < grid[r].length; c++){
						newCopy[r][c] = new Square(grid[r][c].getRed(), grid[r][c].getGreen(), grid[r][c].getBlue());
						undoGrid.push(newCopy);
					}
				}
				


				grid[row][col].changeColor(red, green, blue);
			}
		}
		


		repaint();
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}