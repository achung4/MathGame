/**
        This class creates a question depending on what the difficulty is.
 */
public class Question
{
	/**
                This method will generate a question 
                @param level the level of the game
	 */
	public void generateQuestion(int level)
	{
		gameLevel = level;
		if(level == LEVEL_ONE)
		{
			do{
				firstDigit = generateNumber(maxNumber);
				secondDigit = generateNumber(maxNumber);
			}while(firstDigit+secondDigit >= 10);

			theQuestion = "" + firstDigit + " + " + secondDigit;
		}
		else if(level == LEVEL_TWO)
		{
			do{
				firstDigit = generateNumber(maxNumber);
				secondDigit = generateNumber(maxNumber);
			}while(firstDigit+secondDigit >=19);

			theQuestion = "" + firstDigit + " + " + secondDigit;
		}
		else if(level == LEVEL_THREE)
		{
			do{
				firstDigit = generateNumber(maxNumber);
				secondDigit = generateNumber(firstDigit);
			}while(firstDigit-secondDigit <0);

			theQuestion = "" + firstDigit + " - " + secondDigit;
		}
	}

	/**
                This method will generate a random number
                @param maxNum the maximum random integer it will generate
                @return a random integer
	 */
	private int generateNumber(int maxNum)
	{
		return (int)(Math.round(Math.random()*maxNum));
	}

	/**
                This method will create the string version of the qestion
                @return the question
	 */
	public String getQuestion()
	{
		return theQuestion;
	}

	/**
                This method will return an answer with the sum or difference of the first digit and second digit
                @return the answer
	 */
	public int getAnswer()
	{
		if(gameLevel == LEVEL_THREE)
			return (firstDigit - secondDigit);
		else
			return (firstDigit + secondDigit);
	}

	// Instant Fields and Constants
	private int firstDigit;
	private int secondDigit;
	private String theQuestion;
	private int maxNumber = 9;
	private int gameLevel;
	public static final int LEVEL_ONE = 1;
	public static final int LEVEL_TWO = 2;
	public static final int LEVEL_THREE = 3;
}