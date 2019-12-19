import java.util.Scanner;

//Player Class
public class Player
{
    private String name;
    private int position;
    private int score;
    
    public Player()    //Default Constructor of the Player Class
    {
        position = 1;
        score = 0;
    }
    
    public Player(String name,int position,int score)        //Alternate constructor of the Player Class
    {
        this.name = name;
        this.position = position;
        this.score = score;
    }
    
    /** 
    * This is an accessor method to get the name of the Player one.
    */
    public String getPlayerOneName()
    {
        return this.name;
    }
    
    /** 
    * This is an accessor method to get the position of the Player one.
    */
    public int getPlayerOnePosition()
    {
        return this.position;
    }
    
    /** 
    * This is an accessor method to get the score of the Player one.
    */
    public int getPlayerOneScore()
    {
        return this.score;
    }
    
    /** 
    * This is an accessor method to get the name of the Player two.
    */
    public String getPlayerTwoName()
    {
        return this.name;
    }
    
    /** 
    * This is an accessor method to get the position of the Player two.
    */
    public int getPlayerTwoPosition()
    {
        return this.position;
    }
    
    /** 
    * This is an accessor method to get the score of the Player two.
    */
    public int getPlayerTwoScore()
    {
        return this.score;
    }
    
    /** 
    * This is a mutator method to set position of player one.
    * @param name: Position to be set.
    */
    public void setPlayerOnePosition(int pos)
    {
        this.position = pos;
    }
    
    /** 
    * This is a mutator method to set score of player one.
    * @param name: Score to be set.
    */
    public void setPlayerOneScore(int score)
    {
        this.score = score;
    }
    
    /** 
    * This is a mutator method to set name of player one.
    * @param name: Name to be set.
    */
    public void setPlayerOneName(String name)
    {
        this.name = name;
    }
    
    /** 
    * This is a mutator method to set position of player two.
    * @param name: Position to be set.
    */
    public void setPlayerTwoPosition(int pos)
    {
        this.position = pos;
    }
    
    /** 
    * This is a mutator method to set score of player two.
    * @param name: Score to be set.
    */
    public void setPlayerTwoScore(int score)
    {
        this.score = score;
    }
    
    /** 
    * This is a mutator method to set name of player two.
    * @param name: Name to be set.
    */
    public void setPlayerTwoName(String name)
    {
        this.name = name;
    }
    
    
}
