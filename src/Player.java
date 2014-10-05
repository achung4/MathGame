/**
        This class creates a profile of a player
 */
public class Player
{   
	/**
                This method will set the name of the player
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
                This method will return the name of the player
                @return the name of the player
	 */
	public String getName()
	{
		return this.name;
	}

	// INSTANT FIELD!!
	private String name;
}