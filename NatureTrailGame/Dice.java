import java.util.*;
import java.io.*;

//The dice class
public class Dice
{
    private int maximumValue;
    private int minimumValue;
    
    public Dice()       //Constructor of Dice Class
    {
        minimumValue = 1;
        maximumValue = 4;
    }
    
    /** 
    * This is an accessor method to get the minimum value of the dice.
    */
    public int getMinimumValue()
    {
        return this.minimumValue;
    }
    
    /** 
    * This is an accessor method to get the maximum value of the dice.
    */
    public int getMaximumValue()
    {
        return this.maximumValue;
    }
    
    /** 
    * Method to generate the random value for the dice.
    * @param minValue: An integer specifying the lower limit of the dice(which is 1)
    * @param maxValue: An integer specifying the higher limit of the dice(which is 4)
    */
    public int getTheDieValue(int minValue,int maxValue)
    {
        int range = (maxValue - minValue) + 1; 
        int randNumber = (int)(Math.random() * range) + minValue; 
        return randNumber;
    }
    
    /** 
    * This is a mutator method to set the maximum value of the dice.
    * @param newMaximumValue: Maximum value of the dice
    */
    public void setMaximumValue(int newMaximumValue)
    {
        maximumValue=newMaximumValue;
    }
    
    /** 
    * This is a mutator method to set the minimum value of the dice.
    * @param newMinimumValue: Minimum value of the dice
    */
    public void setMinimumValue(int newMinimumValue)
    {
        minimumValue = newMinimumValue;
    }
    
   
    
    
}
