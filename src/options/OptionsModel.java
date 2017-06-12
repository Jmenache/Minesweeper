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
	private boolean alwaysContinueGameEnabled;
    private boolean questionMarkEnabled = false;
	
	public OptionsModel() {
		setToBeginnerLevel();
	}

	void setToBeginnerLevel() {
	    this.rows = 9;
        this.cols = 9;
        this.numberOfMines = 10;
    }

    void setToIntermediateLevel() {
        this.rows = 16;
        this.cols = 16;
        this.numberOfMines = 40;
    }

    void setToAdvancedLevel() {
        this.rows = 30;
        this.cols = 16;
        this.numberOfMines = 99;
    }

    void setToCustomLevel(int rows, int cols, int numberOfMines) {
        this.rows = rows;
        this.cols = cols;
        this.numberOfMines = numberOfMines;
    }

	public int getNumberOfMines() {
		return numberOfMines;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

    public boolean isQuestionMarkEnabled() {
        return questionMarkEnabled;
    }

    public boolean isSaveOnExitEnabled() {
        return saveOnExitEnabled;
    }

    public boolean isAlwaysContinueGameEnabled() {
        return alwaysContinueGameEnabled;
    }

    void setSaveOnExitEnabled(boolean saveOnExitEnabled) {
        this.saveOnExitEnabled = saveOnExitEnabled;
    }

    void setAlwaysContinueGameEnabled(boolean alwaysContinueGameEnabled) {
        this.alwaysContinueGameEnabled = alwaysContinueGameEnabled;
    }

    void setQuestionMarkEnabled(boolean questionMarkEnabled) {
        this.questionMarkEnabled = questionMarkEnabled;
    }
}
