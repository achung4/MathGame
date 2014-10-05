import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
	This class creates the menu frame of my MATH GAME; it asks the name of the player and the level it wants to play the game
 */
public class MenuMathFrame extends JFrame
{
	/**
		This is the constructor it creates the panels, labels, buttons, texfield, buttongroup and player classes.
	 */
	public MenuMathFrame()
	{
		controlPanel = new JPanel();//
		levelPanel = new JPanel();//
		titlePanel = new JPanel();//
		namePanel = new JPanel();//
		playButtonPanel = new JPanel();//

		titleLabel = new JLabel();//
		askNameLabel = new JLabel();//

		nameField = new JTextField(textFieldLength);//

		playButton = new JButton("Play");//

		group = new ButtonGroup();//
		playerName = new Player();



		createMainMenuPanel(); //helper method
		setTitle("Math Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setVisible(!readyToPlay);

	}

	// MENU FRAME
	/**
		This is an helper method that creates a panel and it adds it to the frame
		The panel consists of other panels
	 */
	private void createMainMenuPanel()
	{        
		controlPanel.setLayout(new GridLayout(4,1));
		controlPanel.add(createTitle());
		controlPanel.add(createAskName());
		controlPanel.add(createRadioButtonLevels());
		controlPanel.add(createPlayButton());

		add(controlPanel, BorderLayout.CENTER);
	}

	/**
		This is a helper method that creates the title greeting in a panel
		@return the greeting in a panel
	 */
	private JPanel createTitle()
	{
		titleLabel.setText("Welcome to Math Game !!");
		titleLabel.setFont(new Font("Comic Sans MS",Font.BOLD,36));

		titlePanel.add(titleLabel);

		return titlePanel;
	}

	/**
		This is a helper method that creates a label that asks the name of the player and a text field to input the name
		@return returns the label and textfield in a panel
	 */
	private JPanel createAskName()
	{
		askNameLabel.setText("Enter name: ");
		askNameLabel.setFont(new Font("Comic Sans MS",Font.BOLD,25));

		namePanel.add(askNameLabel);
		namePanel.add(nameField);

		return namePanel;
	}

	/**
		This is a helper method that creates a level chooser
		@return the different levels in a panel
	 */
	private JPanel createRadioButtonLevels()
	{
		levelOne = new JRadioButton("Level 1");

		levelTwo = new JRadioButton("Level 2");

		levelThree = new JRadioButton("Level 3");

		group.add(levelOne);
		group.add(levelTwo);
		group.add(levelThree);

		levelPanel.setBorder(new TitledBorder(new EtchedBorder(), "Levels"));
		levelPanel.add(levelOne);
		levelPanel.add(levelTwo);
		levelPanel.add(levelThree);

		return levelPanel;
	}

	/**
		This is a helper method that creates the play button on the menu frame
		@return the play button in a panel
	 */
	private JPanel createPlayButton()
	{
		/**
			If you press the button it will make the menu frame invisible and clicking the button will start the game
		 */
		class PlayButtonListener implements ActionListener
		{
			/**
				This method will listen if the button is clicked
				It will start the game
			 */
			public void actionPerformed(ActionEvent event)
			{
				readyToPlayWhatGame();
				if(readyToPlay == true)
				{
					setVisible(!readyToPlay);
					gameFrame = new GameMathFrame(nameField.getText(),levelDifficulty);
					gameFrame.setVisible(readyToPlay);                            
				}
			}            
		}
		ActionListener listener = new PlayButtonListener();
		playButton.addActionListener(listener);

		playButtonPanel.add(playButton);

		return playButtonPanel;
	}

	/**
		This is a helper method that asks what level of the game the player wants
	 */
	private void readyToPlayWhatGame()
	{
		if(levelOne.isSelected())
		{
			readyToPlay = true;
			levelDifficulty = 1;
		}
		else if(levelTwo.isSelected())
		{
			readyToPlay = true;
			levelDifficulty = 2;
		}
		else if(levelThree.isSelected())
		{
			readyToPlay = true;
			levelDifficulty = 3;
		}
	}
	// Menu Frame

	//Instant Fields and Constants
	private boolean readyToPlay = false;
	private boolean ableToNext = false;
	private int WIDTH = 500;
	private int HEIGHT = 300;
	private int levelDifficulty = 0;
	private int levelUp = 0;
	private int maxCorrect = 5;
	private int maxDifficulty = 3;
	private int textFieldLength = 10;
	private Player playerName;
	private GameMathFrame gameFrame;
	private JPanel controlPanel;
	private JPanel levelPanel;
	private JPanel playButtonPanel;
	private JPanel titlePanel;
	private JPanel namePanel;
	private JLabel titleLabel;
	private JLabel askNameLabel;
	private JTextField nameField;
	private JButton playButton;
	private ButtonGroup group;
	private JRadioButton levelOne;
	private JRadioButton levelTwo;
	private JRadioButton levelThree;
}