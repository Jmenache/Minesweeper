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
	    rows = 9;
        cols = 9;
        numberOfMines = 10;
        level = BEGINNER;
    }

    void setToIntermediateLevel() {
        rows = 16;
        cols = 16;
        numberOfMines = 40;
        level = INTERMEDIATE;
    }

    void setToAdvancedLevel() {
        rows = 30;
        cols = 16;
        numberOfMines = 99;
        level = ADVANCED;
    }

    void setToCustomLevel(int rows, int cols, int numberOfMines) {
        this.rows = rows;
        this.cols = cols;
        this.numberOfMines = numberOfMines;
        level = CUSTOM;
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

    int getLevel() {
        return level;
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
