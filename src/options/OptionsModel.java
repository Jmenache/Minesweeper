package options;

public class OptionsModel {
	private int rows;
    private int cols;
    private int numberOfMines;

	static final int BEGINNER = 0;
    static final int INTERMEDIATE = 1;
    static final int ADVANCED = 2;
    static final int CUSTOM = 3;
    private int level;

	private boolean saveOnExitEnabled;
	private boolean loadSavedGameEnabled;
    private boolean questionMarkEnabled = false;
	
	public OptionsModel() {
		setToBeginnerLevel();
	}

	private void setToBeginnerLevel() {
	    this.rows = 9;
        this.cols = 9;
        this.numberOfMines = 10;
    }

    void setToInterediateLevel() {
        this.rows = 16;
        this.cols = 16;
        this.numberOfMines = 40;
    }

    void setToAdvancedLevel() {
        this.rows = 16;
        this.cols = 30;
        this.numberOfMines = 99;
    }

    void setToCutomLevel(int rows, int cols, int numberOfMines) {
        this.rows = rows;
        this.cols = cols;
        this.numberOfMines = numberOfMines;
    }






	public OptionsModel(int num, int h, int w) {
		this.numberOfMines = num;
		this.rows = h;
		this.cols = w;
	}
	public int getNumberOfMines() {
		return numberOfMines;
	}
	public void setNumberOfMines(int numberOfMines) {
		this.numberOfMines = numberOfMines;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}

    public boolean isQuestionMarkEnabled() {
        return questionMarkEnabled;
    }
}
