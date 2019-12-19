
//NatureFeature class
public class NatureFeature
{
   private int featurePosition;
   private String featureType;
   private int spacePenalty;
    
   public NatureFeature(int featurePosition,String featureType,int spacePenalty)    //Constructor of the NatureFeature class
   {
       this.featurePosition = featurePosition;
       this.featureType = featureType;
       this.spacePenalty = spacePenalty;
   }
   
   /** 
   * This is an accessor method to get the position of the feature.
   */
   public int getPosition()
   {
       return this.featurePosition;
   }
   
   /** 
   * This is an accessor method to get the type of the feature.
   */
   public String getType()
   {
       return this.featureType;
   }
   
   /** 
   * This is an accessor method to get the space penalty of the feature.
   */
   public int getSpacePenalty()
   {
       return this.spacePenalty;
   }
   
   /** 
   * This is a mutator method to set position to a feature.
   * @param randomNumber: The random position of the feature.
   */
   public void setPosition(int randomNumber)
   {
       this.featurePosition = randomNumber;
   }
   
}

