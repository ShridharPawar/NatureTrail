import java.util.*;
import java.io.*;
import java.util.Scanner;

//Animals class
public class Animals
{
    
    private String name;
    private int animalPoints;
    
    public Animals()    //Default constructor the Animals class
    {
    
    }
    
    /** 
    * This method tells that which animals has been sighted by the player based on random number generator.
    */
    public Animals animalPositions()
    {
        //1 is for Koala,2=emu,3=Wombat,4=kangaroo,5=spider,(6,7,8,9,10)=no animal.This ensures that there is 50% chance of sighting an animal 
        //by the player and there is an equal chance of sighting an animal.
      
        int range = (10 - 1) + 1; 
        int randNumber = (int)(Math.random() * range) + 1;
        Animals tempAnimal = new Animals();
        if (randNumber == 1)
        {
            tempAnimal.setAnimalName("Koala");
            tempAnimal.setAnimalPoints(10);
        }
        else if (randNumber == 2)
        {
            tempAnimal.setAnimalName("Emu");
            tempAnimal.setAnimalPoints(7);
        }
        else if (randNumber == 3)
        {
            tempAnimal.setAnimalName("Wombat");
            tempAnimal.setAnimalPoints(5);
        }
        else if (randNumber == 4)
        {
            tempAnimal.setAnimalName("Kangaroo");
            tempAnimal.setAnimalPoints(2);
        }
        else if (randNumber == 5)
        {
            tempAnimal.setAnimalName("Redback spider");
            tempAnimal.setAnimalPoints(-5);
        }
        else
        {
            tempAnimal.setAnimalName("No animal");
            tempAnimal.setAnimalPoints(0);
        }
        return tempAnimal;
    }
    
    /** 
    * This is an accessor method to get the name of the animal.
    */
    public String getAnimalName()
    {
        return this.name;
    }
    
    /** 
    * This is an accessor method to get the points of the animal.
    */
    public int getAnimalPoints()
    {
        return this.animalPoints;
    }
    
    /** 
    * This is a mutator method to set name to an animal.
    * @param name: Name of the animal to be set.
    */
    public void setAnimalName(String name)
    {
        this.name=name;
    }
    
    /** 
    * This is a mutator method to set points to an animal.
    * @param name: Points of the animal to be set.
    */
    public void setAnimalPoints(int animalPoints)
    {
        this.animalPoints=animalPoints;
    }
    
    
    
}
