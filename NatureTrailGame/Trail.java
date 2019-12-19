
import java.util.*;
import java.io.*;
import java.util.Scanner;

//Trail Class
public class Trail
{   
    private NatureFeature[] features = new NatureFeature[4];
    
    public Trail()             //Constructor of Trail class
    {
        features[0] = new NatureFeature(1,"Creek",-2);
        features[1] = new NatureFeature(1,"Bridge",4);
        features[2] = new NatureFeature(1,"Fallen Tree",-3);
        features[3] = new NatureFeature(1,"Landslide",-5);
    }
    
    /** 
    * This is an accessor method to get the position of a particular feature.
    */
    public int getFeaturePosition(int index)
    {
        return features[index].getPosition();
    }
    
    /** 
    * This is an accessor method to get the type of a particular feature.
    */
    public String getFeatureType(int index)
    {
        return features[index].getType();
    }
    
    /** 
    * This is an accessor method to get the space penalty of a particular feature.
    */
    public int getFeatureSpacepenalty(int index)
    {
        return features[index].getSpacePenalty();
    }
    
    /** 
    * This is a mutator method to set random position to a particular feature.
    * @param randomNumber: The random position of the feature.
    * @param index: Index of the feature to which the position is to be assigned.
    */
    public void setFeaturePosition(int randomNumber,int index)
    {
        features[index].setPosition(randomNumber);
    }
    
}
