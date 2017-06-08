package options;

public class OptionsModel {
	private int numOfMines;
	private int height;
	private int width;
	
	public OptionsModel() {
		this(5,5,5);
	}
	
	public OptionsModel(int num, int h, int w) {
		this.numOfMines=num;
		this.height=h;
		this.width=w;
	}
	public int getNumOfMines() {
		return numOfMines;
	}
	public void setNumOfMines(int numOfMines) {
		this.numOfMines = numOfMines;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}


}
