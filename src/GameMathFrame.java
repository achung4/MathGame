import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
	This class creates the game frame of the MATH GAME; it will display a question and it will respond to what the player answers
 */
public class GameMathFrame extends JFrame
{
	/**
		This is the constructor and creates the  panels, labels, buttons, texfield,  and the question classes.
	 */
	public GameMathFrame(String playerName,int gameDifficulty)
	{
		name = playerName;
		levelDifficulty = gameDifficulty;

		gamePanel = new JPanel();
		questionPanel = new JPanel(); 
		restartButtonPanel = new JPanel();
		playerPanel = new JPanel();
		inGameButtonPanel = new JPanel();
		nextButtonPanel = new JPanel();
		enterButtonPanel = new JPanel();

		playerNameLabel = new JLabel();
		questionLabel = new JLabel();

		questionField = new JTextField(10);
		respondField = new JTextField();

		enterButton = new JButton("enter");
		restartButton = new JButton("Restart");
		nextButton = new JButton("Next");

		question = new Question();

		setTitle("Math Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		createGamePanel();
	}

	// Game Frame
	/**
	 	This is an helper method that creates a panel and it adds it to the frame
		The panel consists of other panels
	 */
	private void createGamePanel()
	{       
		inGameButtonPanel.add(createRestartButton());
		inGameButtonPanel.add(createNextButton());

		playerNameLabel.setText("Player: "+name);

		gamePanel.setLayout(new GridLayout(4,1));
		gamePanel.add(createPlayerName());
		gamePanel.add(createQuestionPanel());
		gamePanel.add(createRespondTextField());
		gamePanel.add(inGameButtonPanel);        

		add(gamePanel, BorderLayout.CENTER);
	}

	/**
		This is a helper method that always shows the name of the player on top of the game
		@return the name of the player in a panel
	 */
	private JPanel createPlayerName()
	{
		playerNameLabel.setFont(new Font("Comic Sans MS",Font.BOLD,25));

		playerPanel.add(playerNameLabel);

		return playerPanel;
	}

	/**
		This is a helper method that creates the question and display it in a label and get an answer from the player from a textfield; everything is in a panel
		@return a panel that contains a question label and an answer textfield
	 */
	private JPanel createQuestionPanel()
	{
		question.generateQuestion(levelDifficulty);

		questionLabel.setText(question.getQuestion()+" = ");

		questionLabel.setFont(new Font("Comic Sans MS",Font.BOLD,25));

		questionPanel.add(questionLabel);
		questionPanel.add(questionField);
		questionPanel.add(createEnterButton());

		return questionPanel;
	}

	/**
	 	This is a helper method that creates a text field in a panel; this text field displays the condition of the player's answer
	 */
	private JTextField createRespondTextField()
	{
		return respondField;
	}

	/**
		This is a helper method that creates an enter button; it reads the player's answer
		@return the enter button in a panel
	 */
	private JPanel createEnterButton()
	{
		/**
	 		If you press the button it will check the player's question if it's right according to conditions and etc.
		 */
		class EnterButtonListener implements ActionListener
		{
			/**
				This method listens if the button is clicked 
	 			It will check if the player's answer is correct
			 */
			public void actionPerformed(ActionEvent event)
			{
				respondField.setFont(new Font("Comic Sans MS",Font.BOLD,25));

				if(checkIfValidAnswer()&&ableToEnter)
				{
					if(checkIfCorrectAnswer())
					{
						respondField.setForeground(Color.GREEN);
						if(levelUp == maxCorrect && levelDifficulty < maxDifficulty)
						{
							respondField.setText("Correct!! You've been Level up!! ");
							levelDifficulty++;
							ableToNext = true;
							levelUp-=levelUp;
							ableToEnter = false;
						}
						else
						{
							respondField.setText("Correct!! yay");
							ableToNext = true;
							levelUp++;
							ableToEnter = false;
						}
					}
					else
					{
						respondField.setForeground(Color.ORANGE);
						respondField.setText("Wrong Answer!! Please Try again");
						ableToNext = false;
						levelUp-=levelUp;
					}
				}
				//System.out.println(levelUp);
			}            
		}
		ActionListener listener = new EnterButtonListener();
		enterButton.addActionListener(listener);

		enterButtonPanel.add(enterButton);

		return enterButtonPanel;
	}

	/**
		This is a helper method that creates a restart button; it will take you back to the menu frame
		@return the restart button in a panel
	 */
	private JPanel createRestartButton()
	{
		/**
			If you press this button you will restart the whole game
		 */
		class RestartButtonListener implements ActionListener
		{
			/**
				This method listens if the button is clicked; it will restart the game if it did
			 */
			public void actionPerformed(ActionEvent event)
			{
				readyToPlay = false;
				menuFrame = new MenuMathFrame();
				menuFrame.setVisible(!readyToPlay);
				setVisible(readyToPlay);
			}            
		}
		ActionListener listener = new RestartButtonListener();
		restartButton.addActionListener(listener);

		restartButtonPanel.add(restartButton);

		return restartButtonPanel;
	}

	/**
		This is a helper method that creates a next button; it will give the next question only if you got the previous question right
	 	@return the next button in a panel
	 */
	private JPanel createNextButton()
	{
		/**
			If you press this button you will have another question 
		 */
		class NextButtonListener implements ActionListener
		{
			/**
				This method listens if the button is clicked; it will display another question and repaint the game frame
			 */
			public void actionPerformed(ActionEvent event)
			{
				if(ableToNext)
				{
					ableToEnter = true;
					ableToNext = false;
					respondField.setText("");
					questionField.setText("");
					question.generateQuestion(levelDifficulty);
					questionLabel.setText(question.getQuestion()+" = ");
					repaint();
				}
			}            
		}
		ActionListener listener = new NextButtonListener();
		nextButton.addActionListener(listener);

		nextButtonPanel.add(nextButton);

		return nextButtonPanel;
	}

	/**
		This is a helper method that checks if the player's answer is correct    
	 */
	private boolean checkIfCorrectAnswer()
	{
		if(Integer.parseInt(questionField.getText()) == question.getAnswer())
		{
			//System.out.println("true");
			return true;
		}
		else
		{
			//System.out.println("false");
			return false;
		}
	}

	/**
		This is a helper method that checks if the player's answer is a valid answer
	 */
	private boolean checkIfValidAnswer()
	{
		try
		{
			Integer.parseInt(questionField.getText());
		}
		catch(NumberFormatException exception)
		{
			respondField.setForeground(Color.RED);
			respondField.setText("Invalid Answer!! Please input a number");
			levelUp-=levelUp;
			ableToEnter = true;
			return false;
		}
		return true;
	}
	// Game Frame

	//Instant Fields and Constants
	private boolean readyToPlay = false;
	private boolean ableToNext = false;
	private boolean ableToEnter = true;
	private int WIDTH = 500;
	private int HEIGHT = 300;
	private int levelDifficulty = 0;
	private int levelUp = 1;
	private int maxCorrect = 5;
	private int maxDifficulty = 3;
	private String name;
	private Question question;
	private JPanel restartButtonPanel;
	private JPanel nextButtonPanel;
	private JPanel gamePanel;
	private JPanel questionPanel;
	private JPanel playerPanel;
	private JPanel inGameButtonPanel;
	private JPanel enterButtonPanel;
	private JLabel playerNameLabel;
	private JLabel questionLabel;
	private JTextField respondField;
	private JTextField questionField;
	private JButton enterButton;
	private JButton restartButton;
	private JButton nextButton;
	private MenuMathFrame menuFrame;
}