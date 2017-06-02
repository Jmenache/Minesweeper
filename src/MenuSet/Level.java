package MenuSet;

public class Level {
	private String numOfMines;
	private String height;
	private String width;
	
	public Level() {
		this("","","");
	}
	
	public Level(String num, String h, String w) {
		this.numOfMines=num;
		this.height=h;
		this.width=w;
	}
	public String getNumOfMines() {
		return numOfMines;
	}
	public void setNumOfMines(String numOfMines) {
		this.numOfMines = numOfMines;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}


}
