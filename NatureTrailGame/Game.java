import java.util.Scanner;
import java.util.*;
import java.io.*;

//the Class Game which mainly has all the methods to run this game
public class Game
{
    private int trailLength;
    private Trail natureTrail;
    private Player playerOne;
    private Player playerTwo;
    private Animals animal;
    
    
    public Game()   //Constructor of the Game Class
    {
         playerOne = new Player();
         playerTwo = new Player();
         natureTrail = new Trail();
         animal = new Animals();
    }
    
    /** 
    * This method assigns random positions to the nature features on the trail.
    * @param low: An integer specifying the lower limit of the random number(which is 2)
    * @param high: An integer specifying the higher limit of the random number(which is trailLength-2)
    */
    public void assignRandomPositionsToFeatures(int low,int high)
    {
        //the for loop inside the for loop ensures that no two features are placed on a single tile of the trail
        int range  =  (high - low) + 1;
        for(int i = 0;i < 4;i++)
        {
            int randNumber =  (int)(Math.random() * range) + low;
            natureTrail.setFeaturePosition(randNumber,i);                //calls the setFeaturePosition method to set position to a feature
            for(int j = 0;j < i;j++)                                     //so as to avoid placing different nature features on the same place
            {
                if (natureTrail.getFeaturePosition(i) == natureTrail.getFeaturePosition(j))
                {
                i--;
                break;
                }
            }
        }
    }
    
    /** 
    * This method is executed when the computer rolls the highest number on the dice, and starts the game first.
    * @param playerOne: An object of Player Class
    * @param playerTwo: An object of Player Class
    * @param trailLength: Length of the trail
    */
    public void computerStarts(Player playerOne,Player playerTwo,int trailLength)
    {
        //Here,Computer will play first(Computer is the playerTwo object and human is the playerOne object)
        Dice dice = new Dice();
        String message = "";       //concatenate all messages for the computer player and then print in the end
        String message1 = "";      //concatenate all messages for the human player and then print in the end
        int dieValue = 0;
        Scanner sc = new Scanner(System.in);
        String initialTrailString = "S-H";
        for(int l = 2;l < trailLength;l++)             //making the initial trail string of human using the for loop
        {
            initialTrailString = initialTrailString + " " + "~" + l + "~";
        }
        initialTrailString = initialTrailString + " " + "F";
        String trailStringForHuman = initialTrailString;  //to use inside the while loop
        System.out.print("\u000C");
        System.out.println("Human's trail:    "+trailStringForHuman+"\n");
        
        String startingStringForComputer = "S-C";            
        for(int l = 2;l < trailLength;l++)            //making the initial trail string of computer using the for loop
        {
            startingStringForComputer = startingStringForComputer + " " + "~" + l + "~";
        }
        startingStringForComputer = startingStringForComputer + " " + "F";
        String trailStringForComputer = startingStringForComputer;   //to use inside the while loop
        System.out.println("Computer's trail: "+trailStringForComputer);
         
        //this while loop will execute till any player passes the final position
        while (playerOne.getPlayerOnePosition() < trailLength && playerTwo.getPlayerTwoPosition() < trailLength) 
        {
             trailStringForHuman = initialTrailString;
             trailStringForComputer = startingStringForComputer; 
             System.out.println("");
             System.out.println(playerTwo.getPlayerTwoName() + " is on tile " + playerTwo.getPlayerTwoPosition() + " and a score of " + 
             playerTwo.getPlayerTwoScore() + ",Roll your dice.");
             System.out.println("");
             
             dieValue = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
             System.out.println(playerTwo.getPlayerTwoName() + " rolled a " + (int)dieValue + "...");
             playerTwo.setPlayerTwoPosition(playerTwo.getPlayerTwoPosition() + (int)dieValue);  //move the player
             
             //to see if an animal has been sighted
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 animal = animal.animalPositions();       //call the function to see which animal has been sighted
                 if (animal.getAnimalName().equals("No animal"))
                 {
                      message = message + "Unfortunately, no animals spotted." + "Now," + playerTwo.getPlayerTwoName() + "'s score is " 
                      + playerTwo.getPlayerTwoScore() + ". \n";
                 }
                 else
                 {
                     playerTwo.setPlayerTwoScore(playerTwo.getPlayerTwoScore() + animal.getAnimalPoints());   //increment the player's points
                     if (animal.getAnimalName().equals("Redback spider"))
                     {
                        message = message + "Oops!" + playerTwo.getPlayerTwoName() + " spotted a dangerous spider,which is...the " 
                        + animal.getAnimalName() + ",and hence, " + playerTwo.getPlayerTwoName() + " will get 5 negative points! \nNow," 
                        + playerTwo.getPlayerTwoName() + "'s score is " + playerTwo.getPlayerTwoScore() + ". \n";
                     }
                     else
                     {
                         message = message + "Woah!Nice work,found an interesting animal." + playerTwo.getPlayerTwoName() 
                         + " spotted a " + animal.getAnimalName() + " and hence will get " + animal.getAnimalPoints() + " points! Now," 
                         + playerTwo.getPlayerTwoName() + "'s score is " + playerTwo.getPlayerTwoScore() + ". \n";
                     }
                 }
             }
             
             int natureFeatureFound=0;
             int spacePenaltyForComputer=0;
             //too see if the player has come across a nature feature
             for(int i = 0;i <= 3;i++)
             {
                 if (natureTrail.getFeaturePosition(i) == playerTwo.getPlayerTwoPosition())
                 {
                     spacePenaltyForComputer = natureTrail.getFeatureSpacepenalty(i); 
                     natureFeatureFound=1;
                     if (natureTrail.getFeatureType(i).equals("Creek"))
                     {
                         message = message + "Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         ".Though it is exciting to cross a creek,unfortunately " + playerTwo.getPlayerTwoName() + 
                         " will have to move backwards two places now.\n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Bridge"))
                     {
                         message = message + "Wohoo!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerTwo.getPlayerTwoName() + " will move forward four places now.Have fun crossing the bridge! \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Fallen Tree"))
                     {
                         message = message+"Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerTwo.getPlayerTwoName() + " will move backwards three places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Landslide"))
                     {
                         message = message + "Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerTwo.getPlayerTwoName() + " will move backards five places now. \n";
                     }
                     
                 }
             }
             playerTwo.setPlayerTwoPosition(playerTwo.getPlayerTwoPosition() + spacePenaltyForComputer); //move the player
             if (playerTwo.getPlayerTwoPosition() < 1)
             {
                playerTwo.setPlayerTwoPosition(1);
             }
             if(natureFeatureFound==0)
             {
                message = message + "No nature features found...\n";  
             }
             
             System.out.println(message);   //print the whole message regarding if animals or nature features have been found
             
             if (playerTwo.getPlayerTwoPosition() >= trailLength)
             {
                 System.out.println(playerTwo.getPlayerTwoName() + " has finished the trail and hence will get 10 bonus points!");
                 System.out.println("Press enter to view the scoreboard and the winner.");
                 sc.nextLine();
                 break;  //break the while loop if the playertwo finishes first
             }
             
             System.out.println(playerTwo.getPlayerTwoName() + " has landed on tile number " + playerTwo.getPlayerTwoPosition() + ".");
             System.out.println("");
             System.out.println(playerOne.getPlayerOneName() + "'s score:" + playerOne.getPlayerOneScore() + "\n");
             System.out.println(playerTwo.getPlayerTwoName() + "'s score:" + playerTwo.getPlayerTwoScore());
             System.out.println("");
             System.out.println("Please enter to continue.");
             sc.nextLine();
             
             //modifying the trail strings for human as well as the computer
             trailStringForHuman = trailStringForHuman.replace("~" + playerOne.getPlayerOnePosition() + "~","~H~");
             trailStringForComputer = trailStringForComputer.replace("~" + playerTwo.getPlayerTwoPosition() + "~","~C~");
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S-H","S");
             }
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S-C","S");
             }
             if (playerOne.getPlayerOnePosition() == 1 && trailStringForHuman.indexOf("S-H")==-1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S","S-H");   
             }
             if (playerTwo.getPlayerTwoPosition() == 1 && trailStringForComputer.indexOf("S-C")==-1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S","S-C");   
             }
             System.out.print("\u000C");
             System.out.println("Human's trail:    "+trailStringForHuman+"\n");
             System.out.println("Computer's trail: "+trailStringForComputer);
             System.out.println("");
             
             //--------------------------------------------------------------------------------------------------------------------------
             
             System.out.println(playerOne.getPlayerOneName() + " is on tile " + playerOne.getPlayerOnePosition() + " and a score of "
              + playerOne.getPlayerOneScore() + ",is going to roll...");
             System.out.println("");
             System.out.println("Press enter to continue.");
             sc.nextLine();
             dieValue = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
             System.out.println(playerOne.getPlayerOneName() + " rolled a "+ (int)dieValue+"...");
             playerOne.setPlayerOnePosition(playerOne.getPlayerOnePosition() + (int)dieValue);    //move the player
             //to see if an animal has been sighted
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 animal = animal.animalPositions();       //call the animalPositions to generate the random sighting of the animals
                 if (animal.getAnimalName().equals("No animal"))
                 {
                     message1 = message1 + "Unfortunately,no animals spotted." + "Now," + playerOne.getPlayerOneName() + "'s score is " 
                     + playerOne.getPlayerOneScore() + ". \n";
                 }
                 else
                 {
                    playerOne.setPlayerOneScore(playerOne.getPlayerOneScore() + animal.getAnimalPoints());  //increment the player's points
                    if (animal.getAnimalName().equals("Redback spider"))
                    {
                       message1 = message1 + "Oops!" + playerOne.getPlayerOneName() + " spotted a dangerous spider,which is...the " 
                       + animal.getAnimalName() + ",and hence " + playerOne.getPlayerOneName() + " will get 5 negative points!Now," 
                       + playerOne.getPlayerOneName() + "'s score is " + playerOne.getPlayerOneScore() + ". \n";
                    }
                    else
                    {
                       message1 = message1 + "Woah!Nice work,you found an interesting animal." + playerOne.getPlayerOneName() 
                       + " spotted a " + animal.getAnimalName() + ",and will get " + animal.getAnimalPoints() + " points!Now," 
                       + playerOne.getPlayerOneName() + "'s score is " + playerOne.getPlayerOneScore() + ". \n";
                    }
                 }
             }
             
             int natureFeatureFoundForHuman=0;
             int spacePenaltyForHuman=0;
             //too see if the player has come across a nature feature
             for(int i = 0;i <= 3;i++)
             {
                 if (natureTrail.getFeaturePosition(i) == playerOne.getPlayerOnePosition())
                 {
                     spacePenaltyForHuman = natureTrail.getFeatureSpacepenalty(i);
                     natureFeatureFoundForHuman=1;
                     if (natureTrail.getFeatureType(i).equals("Creek"))
                     {
                         message1 = message1 + "Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) 
                         + ".Though it is exciting to cross a creek,unfortunately " + playerOne.getPlayerOneName() 
                         + " will have to move backwards two places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Bridge"))
                     {
                         message1 = message1 + "Wohoo!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + "." 
                         + playerOne.getPlayerOneName() + " will move forward four places now.Have fun crossing the bridge! \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Fallen Tree"))
                     {
                         message1 = message1 + "Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + "." 
                         + playerOne.getPlayerOneName() + " will move backwards three places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Landslide"))
                     {
                         message1 = message1 + "Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + "." 
                         + playerOne.getPlayerOneName() + " will move backards five places now. \n";
                     }
                 }
             }
             playerOne.setPlayerOnePosition(playerOne.getPlayerOnePosition() + spacePenaltyForHuman); //move the player
             if (playerOne.getPlayerOnePosition()<1)
             {
                 playerOne.setPlayerOnePosition(1);
             }
             if(natureFeatureFoundForHuman==0)
             {
                message1 = message1 + "No nature features found...\n";
             }
             
             System.out.println(message1);  //print the whole message regarding if animals or nature features have been found
             if (playerOne.getPlayerOnePosition() >= trailLength)
             {
                System.out.println(playerOne.getPlayerOneName() + " has finished the trail and hence will get 10 bonus points!");
                System.out.println("Press enter to view the scoreboard and the winner.");
                sc.nextLine();
                break;  //break the while loop if the playerone finishes first
             }
             System.out.println(playerOne.getPlayerOneName() + " has landed on tile number " + playerOne.getPlayerOnePosition());
             System.out.println("");
             System.out.println(playerOne.getPlayerOneName() + "'s score:" + playerOne.getPlayerOneScore() + "\n");
             System.out.println(playerTwo.getPlayerTwoName() + "'s score:" + playerTwo.getPlayerTwoScore());
             System.out.println("");
             message = "";
             message1 = "";
             System.out.println("Please enter to continue.");
             sc.nextLine();
             System.out.print("\u000C");
             
             //modifying the trail strings for human as well as the computer
             trailStringForHuman = initialTrailString;
             trailStringForComputer = startingStringForComputer;
             trailStringForComputer = trailStringForComputer.replace("~" + playerTwo.getPlayerTwoPosition() + "~","~C~");
             trailStringForHuman = trailStringForHuman.replace("~" + playerOne.getPlayerOnePosition() + "~","~H~");
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S-H","S");
             }
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S-C","S");
             }
             if (playerOne.getPlayerOnePosition() == 1 && trailStringForHuman.indexOf("S-H")==-1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S","S-H");   
             }
             if (playerTwo.getPlayerTwoPosition() == 1 && trailStringForComputer.indexOf("S-C")==-1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S","S-C");   
             }
             System.out.println("Human's trail:    "+trailStringForHuman+"\n");
             System.out.println("Computer's trail: "+trailStringForComputer);
       }
        
       display(playerOne,playerTwo);  //call the display function to display the result
    }
    
    /** 
    * this method is executed when the game is over and the results are to be displayed.
    * @param playerOne: An object of Player Class
    * @param playerTwo: An object of Player Class
    */
    public void display(Player playerOne,Player playerTwo)
    {
        System.out.print("\u000C");
        System.out.println("***************************************************************************************************************************");
        System.out.println("                                                        GAME OVER                                                          ");
        System.out.println("***************************************************************************************************************************");
        System.out.println("\n \n");
        if (playerOne.getPlayerOnePosition() > playerTwo.getPlayerTwoPosition())
        {
            playerOne.setPlayerOneScore(10 + playerOne.getPlayerOneScore());
        }
        else if (playerTwo.getPlayerTwoPosition() > playerOne.getPlayerOnePosition())
        {
            playerTwo.setPlayerTwoScore(10 + playerTwo.getPlayerTwoScore());
        }
        
        System.out.println(playerOne.getPlayerOneName() + "'s score:" + playerOne.getPlayerOneScore());
        System.out.println(playerTwo.getPlayerTwoName() + "'s score:" + playerTwo.getPlayerTwoScore());
        System.out.println("\n");
        
        if (playerOne.getPlayerOneScore() > playerTwo.getPlayerTwoScore())
        {
            System.out.println(playerOne.getPlayerOneName() + " wins with " + playerOne.getPlayerOneScore() + " points! Congrats!!!");
            System.out.println("It's okay to lose sometimes," + playerTwo.getPlayerTwoName() + ".Better luck next time mate!");
        }
        else if (playerTwo.getPlayerTwoScore() > playerOne.getPlayerOneScore())
        {
            System.out.println(playerTwo.getPlayerTwoName() + " wins with " + playerTwo.getPlayerTwoScore() + " points!");
            System.out.println("It's okay to lose sometimes," + playerOne.getPlayerOneName() + ".Better luck next time mate!");
        }
        else
        {
            System.out.println("It's a tie!Play again to find the real winner.");
        }
    }
    
    /** 
    * this method is called to get the length of the trail.
    */
    public int getTrailLength()
    {
        System.out.println("Please enter the length of the trail.Please put the length between 10 and 20.");
        Scanner sc = new Scanner(System.in);
        trailLength = sc.nextInt();
        while (trailLength < 10 || trailLength > 20)
        {
            System.out.println("Please enter a valid length for the trail:");
            trailLength = sc.nextInt();
        }
        return trailLength;
    }
    
    /** 
    * this method is executed when the human rolls the highest number on the dice and starts first to play the game.
    * @param playerOne: An object of Player Class
    * @param playerTwo: An object of Player Class
    * @param trailLength: Length of the trail
    */
    public void humanStarts(Player playerOne,Player playerTwo,int trailLength)
    {
        //Here,Human will play first(Computer is the playerTwo object and human is the playerOne object)
        Dice dice = new Dice();
        String message = "";       //message for the human player which concatenates all the messages and displays the final message in the end
        String message1 = "";      //message for the computer player which concatenates all the messages and displays the final message in the end
        int dieValue = 0;
         
        Scanner sc = new Scanner(System.in);
        String initialTrailString = "S-H";
        for(int l = 2;l < trailLength;l++)
        {
            initialTrailString = initialTrailString + " " + "~" + l + "~";
        }
        initialTrailString = initialTrailString + " " + "F";
        String trailStringForHuman = initialTrailString;  
        System.out.print("\u000C");
        System.out.println("Human's trail:    "+trailStringForHuman+"\n");
        
        String startingStringForComputer = "S-C";            
        for(int l = 2;l < trailLength;l++)
        {
            startingStringForComputer = startingStringForComputer + " " + "~" + l + "~";
        }
        startingStringForComputer = startingStringForComputer + " " + "F";
        String trailStringForComputer = startingStringForComputer; 
        System.out.println("Computer's trail: "+trailStringForComputer);
        
         //this while loop will execute till any player passes the final position
         while (playerOne.getPlayerOnePosition() < trailLength && playerTwo.getPlayerTwoPosition() < trailLength)
         {
             trailStringForHuman = initialTrailString;
             trailStringForComputer = startingStringForComputer;
             System.out.println("");
             System.out.println(playerOne.getPlayerOneName() + ", is on tile " + playerOne.getPlayerOnePosition() + " and a score of " + 
             playerOne.getPlayerOneScore() + ",Roll your dice.");
             System.out.println("");
             System.out.println("Press enter to continue.");
             sc.nextLine();
             dieValue = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
             System.out.println(playerOne.getPlayerOneName() + " rolled a " + (int)dieValue + ".");
             playerOne.setPlayerOnePosition(playerOne.getPlayerOnePosition() + (int)dieValue);  //move the player
             
             //to see if an animal has been sighted
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 animal = animal.animalPositions();       //call the animalpositions function to see which animal has been sighted
                 if (animal.getAnimalName().equals("No animal"))
                 {
                     message = message + "Unfortunately,no animals spotted." + "Now,"+playerOne.getPlayerOneName() + "'s score is " 
                     + playerOne.getPlayerOneScore() + ". \n";
                 }
                 else
                 {
                     playerOne.setPlayerOneScore(playerOne.getPlayerOneScore()+animal.getAnimalPoints());  //increment the player's points
                     if (animal.getAnimalName().equals("Redback spider"))
                     {
                         message = message + "Oops!" + playerOne.getPlayerOneName() + " spotted a dangerous spider,which is...the " 
                         + animal.getAnimalName() + ",and hence " + playerOne.getPlayerOneName() + " will get 5 negative points!\nNow " 
                         + playerOne.getPlayerOneName() + "'s score is " + playerOne.getPlayerOneScore() + ". \n";
                     }
                     else
                     {
                         message = message + "Woah!Nice work,you found an interesting animal." + playerOne.getPlayerOneName() 
                         + " spotted a " + animal.getAnimalName() + ",and will get " + animal.getAnimalPoints() + " points!Now " 
                         + playerOne.getPlayerOneName() + "'s score is " + playerOne.getPlayerOneScore() + ". \n";
                     }
                 }
             }
             
             int natureFeatureFoundForHuman=0;
             //too see if the player has come across a nature feature
             int spacePenaltyForHuman=0;
             for(int i = 0;i <= 3;i++)
             {
                 if(natureTrail.getFeaturePosition(i) == playerOne.getPlayerOnePosition())
                 {
                     spacePenaltyForHuman=natureTrail.getFeatureSpacepenalty(i);
                     natureFeatureFoundForHuman=1;
                     if (natureTrail.getFeatureType(i).equals("Creek"))
                     {
                         message = message+"Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + 
                         ".Though it is exciting to cross a creek,unfortunately " + playerOne.getPlayerOneName() 
                         + " will have to move backwards two places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Bridge"))
                     {
                         message = message + "Wohoo!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerOne.getPlayerOneName() + " will move forward four places now.Have fun crossing the bridge! \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Fallen Tree"))
                     {
                         message = message + "Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerOne.getPlayerOneName() + " will move backwards three places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Landslide"))
                     {
                         message = message + "Oops!" + playerOne.getPlayerOneName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerOne.getPlayerOneName() + " will move backards five places now. \n";
                     }
                 }
             }
             playerOne.setPlayerOnePosition(playerOne.getPlayerOnePosition() + spacePenaltyForHuman); //move the player
             if (playerOne.getPlayerOnePosition() < 1)
             {
                 playerOne.setPlayerOnePosition(1);
             }
             if(natureFeatureFoundForHuman==0)
             {
                 message = message + "No nature features found...\n"; 
             }
             
             System.out.println(message);  //print the whole message regarding if animals or nature features have been found
             if (playerOne.getPlayerOnePosition() >= trailLength)
             {
                 System.out.println(playerOne.getPlayerOneName() + " has finished the trail and hence will get 10 bonus points!");
                 System.out.println("Press enter to view the scoreboard and the winner.");
                 sc.nextLine();
                 break;  //break the while loop if the playerone finishes first
             }
           
             System.out.println(playerOne.getPlayerOneName() + " has landed on tile number " + playerOne.getPlayerOnePosition() + ".");
             System.out.println("");
             System.out.println(playerOne.getPlayerOneName() + "'s score:" + playerOne.getPlayerOneScore() + "\n");
             System.out.println(playerTwo.getPlayerTwoName()+ "'s score:" + playerTwo.getPlayerTwoScore());
             System.out.println("");
             System.out.println("Press enter to continue.");
             sc.nextLine();
             
             //modifying the trail strings for human as well as the computer
             trailStringForHuman = trailStringForHuman.replace("~" + playerOne.getPlayerOnePosition() + "~","~H~");
             trailStringForComputer = trailStringForComputer.replace("~" + playerTwo.getPlayerTwoPosition() + "~","~C~");
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S-H","S");
             }
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S-C","S");
             }
             if (playerOne.getPlayerOnePosition() == 1 && trailStringForHuman.indexOf("S-H")==-1)
             {
                   trailStringForHuman = trailStringForHuman.replace("S","S-H");   
             }
             if (playerTwo.getPlayerTwoPosition() == 1 && trailStringForComputer.indexOf("S-C")==-1)
             {
                  trailStringForComputer = trailStringForComputer.replace("S","S-C");   
             }
             
             System.out.print("\u000C");
             System.out.println("Human's trail:    "+trailStringForHuman+"\n");
             System.out.println("Computer's trail: "+trailStringForComputer+"\n");
             System.out.println("");
             
             //--------------------------------------------------------------------------------------------------------------------------
             
             System.out.println(playerTwo.getPlayerTwoName() + ",is on tile " + playerTwo.getPlayerTwoPosition() + " and a score of " + 
             playerTwo.getPlayerTwoScore() + ",is going to roll...");
             System.out.println("");
             dieValue = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
             System.out.println(playerTwo.getPlayerTwoName() + " rolled a " + (int)dieValue + "...");
             playerTwo.setPlayerTwoPosition(playerTwo.getPlayerTwoPosition() + (int)dieValue);  //move the player
             //to see if an animal has been sighted
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 animal = animal.animalPositions();
                 if (animal.getAnimalName().equals("No animal"))
                 {
                     message1 = message1 + "Unfortunately,no animals spotted." + "Now," + playerTwo.getPlayerTwoName() + "'s" + " score is " + 
                     playerTwo.getPlayerTwoScore() + ". \n";
                 }
                 else
                 {
                    playerTwo.setPlayerTwoScore(playerTwo.getPlayerTwoScore()+animal.getAnimalPoints());  //increment the player's points
                    if (animal.getAnimalName().equals("Redback spider"))
                    {
                        message1 = message1 + "Oops!" + playerTwo.getPlayerTwoName() + " spotted a spotted a dangerous spider,which is...the " 
                        + animal.getAnimalName() + " and hence " + playerTwo.getPlayerTwoName() + " will get 5 negative points! \nNow," 
                        + playerTwo.getPlayerTwoName() + "'s score is " + playerTwo.getPlayerTwoScore() + ". \n";
                    }
                    else
                    {
                       message1 = message1 + "Woah!Nice work," + playerTwo.getPlayerTwoName() + " found an interesting animal." 
                       + playerTwo.getPlayerTwoName() + " spotted a " + animal.getAnimalName() + " and hence will get " 
                       + animal.getAnimalPoints() + " points!Now," + playerTwo.getPlayerTwoName() + "'s score is " + playerTwo.getPlayerTwoScore() 
                       + ".\n";
                    }
                 }
             }
             
             int natureFeatureFound=0;
             int spacePenaltyForComputer=0;
             //too see if the player has come across a nature feature
             for(int i = 0;i <= 3;i++)
             {
                 if (natureTrail.getFeaturePosition(i) == playerTwo.getPlayerTwoPosition())
                 {
                     spacePenaltyForComputer=natureTrail.getFeatureSpacepenalty(i);  //move the player
                     natureFeatureFound=1;
                     if (natureTrail.getFeatureType(i).equals("Creek"))
                     {
                         message1 = message1 + "Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         ".Though it is exciting to cross a creek,unfortunately " + playerTwo.getPlayerTwoName() 
                         + " will have to move backwards two places now.\n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Bridge"))
                     {
                         message1 = message1 + "Wohoo!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + 
                         "." + playerTwo.getPlayerTwoName() + " will move forward four places now.Have fun crossing the bridge! \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Fallen Tree"))
                     {
                         message1 = message1 + "Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + "." 
                         + playerTwo.getPlayerTwoName() + " will move backwards three places now. \n";
                     }
                     else if (natureTrail.getFeatureType(i).equals("Landslide"))
                     {
                         message1 = message1 + "Oops!" + playerTwo.getPlayerTwoName() + " came across a " + natureTrail.getFeatureType(i) + "."
                         + playerTwo.getPlayerTwoName() + " will move backards five places now. \n";
                     }
                 }
             }
             playerTwo.setPlayerTwoPosition(playerTwo.getPlayerTwoPosition() + spacePenaltyForComputer); //move the player
             if (playerTwo.getPlayerTwoPosition() < 1)
             {
                playerTwo.setPlayerTwoPosition(1);
             }
             if(natureFeatureFound==0)
             {
                message1 = message1 + "No nature features found...\n"; 
             }
             
             System.out.println(message1);  //print the whole message regarding if animals or nature features have been found
             if (playerTwo.getPlayerTwoPosition() >= trailLength)
             {
                 System.out.println(playerTwo.getPlayerTwoName() + " has finished the trail and hence will get 10 bonus points!");
                 System.out.println("Press enter to view the scoreboard and the winner.");
                 sc.nextLine();
                 break;  //break the while loop if the playertwo finishes first
             }
             System.out.println(playerTwo.getPlayerTwoName() + " has landed on tile number " + playerTwo.getPlayerTwoPosition());
             System.out.println("");
             System.out.println(playerOne.getPlayerOneName() + "'s score:" + playerOne.getPlayerOneScore() + "\n");
             System.out.println(playerTwo.getPlayerTwoName() + "'s score:" + playerTwo.getPlayerTwoScore());
             System.out.println("");
             message = "";
             message1 = "";
             System.out.println("Please enter to continue.");
             sc.nextLine();
             System.out.print("\u000C");
             
             //modifying the trail strings for human as well as the computer
             trailStringForHuman = initialTrailString;
             trailStringForComputer = startingStringForComputer;
             trailStringForComputer = trailStringForComputer.replace("~"+playerTwo.getPlayerTwoPosition()+"~","~C~");
             trailStringForHuman = trailStringForHuman.replace("~"+playerOne.getPlayerOnePosition()+"~","~H~");
             if (playerOne.getPlayerOnePosition() != 1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S-H","S");
             }
             if (playerTwo.getPlayerTwoPosition() != 1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S-C","S");
             }
             if (playerOne.getPlayerOnePosition() == 1 && trailStringForHuman.indexOf("S-H")==-1)
             {
                 trailStringForHuman = trailStringForHuman.replace("S","S-H");   
             }
             if (playerTwo.getPlayerTwoPosition() == 1 && trailStringForComputer.indexOf("S-C")==-1)
             {
                 trailStringForComputer = trailStringForComputer.replace("S","S-C");   
             }
             System.out.println("Human's trail:    "+trailStringForHuman+"\n");
             System.out.println("Computer's trail: "+trailStringForComputer);
       }
        
       display(playerOne,playerTwo);   //call the display function to display the result
    }
    
    /** 
    * this method is called to take the input for the Human player's name.
    */
    public String playerOneName()
    {
        System.out.println("Setting up the player One...");
        System.out.println("");
        System.out.println("Please enter your name(number of characters should not be more than 6):");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        while (name.length() > 6 || name.trim().equals(""))
        {
            System.out.println("Your name contains more than 6 characters or it contains no characters at all,please enter your name again:");
            name = sc.nextLine();
        }
        return name.trim(); 
    }
    
    /** 
    * this is the Start method.Execution of the program starts from here.
    */
    public void start()
    {
        
        Dice dice = new Dice();
        Scanner sc = new Scanner(System.in);
        welcome(); //to display the welcome message
        trailLength = getTrailLength(); //to take the length of the trail
        int dieValueForPlayerOne = 0;
        int dieValueForPlayerTwo = 0;
        String playerOneName = playerOneName();
        playerOne.setPlayerOneName(playerOneName);
        playerTwo.setPlayerTwoName("Computer Rosita");
        System.out.println("Welcome " + playerOne.getPlayerOneName() + "!");
        System.out.println("");
        System.out.println("Setting up the Computer Player...");
        System.out.println(playerOne.getPlayerOneName() + ",your opponent is " + playerTwo.getPlayerTwoName());
        System.out.println("Setting up nature features along the trail.This may take a while...");
        assignRandomPositionsToFeatures(2,trailLength - 1);
        System.out.println("");
        System.out.println("Press enter to continue");
        sc.nextLine();
        System.out.println("Okay then,let's decide who plays first! \n");
        boolean ifEqual = true;
        
        while (ifEqual)
        {
            System.out.println(playerOne.getPlayerOneName() + " is on tile 1 with a score of 0, and will roll the dice now..");
            System.out.println("");
            System.out.println("Press enter to roll the dice");
            sc.nextLine();   
                
            dieValueForPlayerOne = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
            System.out.println(playerOne.getPlayerOneName() + " rolled a ..." + dieValueForPlayerOne);
            System.out.println("");
            System.out.println(playerTwo.getPlayerTwoName() + " is on tile 1 with a score of 0");
            System.out.println("");
            dieValueForPlayerTwo = dice.getTheDieValue(dice.getMinimumValue(),dice.getMaximumValue());
            System.out.println(playerTwo.getPlayerTwoName() + " rolled a ..." + dieValueForPlayerTwo);
            System.out.println("");
            
            if(dieValueForPlayerOne>dieValueForPlayerTwo)
            {
                System.out.println("Alrighty then!" + playerOne.getPlayerOneName() + " starts....");
                System.out.println("All set....Let's begin");
                System.out.println("Please enter to continue");
                sc.nextLine();
                ifEqual = false;
                humanStarts(playerOne,playerTwo,trailLength); 
                //Here,Human will play first(Computer is the playerTwo object and human is the playerOne object)
            }
            else if (dieValueForPlayerOne < dieValueForPlayerTwo)
            {
                System.out.println("Alrighty then!" + playerTwo.getPlayerTwoName() + " starts....");
                System.out.println("All set....Let's begin");
                System.out.println("Please enter to continue");
                sc.nextLine();
                ifEqual = false;
                computerStarts(playerOne,playerTwo,trailLength);
                //Here,Computer will play first(Computer is the playerTwo object and human is the playerOne object)
            }
            else
            {
                ifEqual = true;
                System.out.println("Oops!Both the players rolled the same number!Roll again to decide who goes first. \n");
            }
        }  
   
    }
    
    /** 
    * To print the welcome message.
    */
    public void welcome()
    {
       System.out.println("***************************************************************************************************************************");
       System.out.println("                                                           Welcome to the Nature Trail Game");
       System.out.println("***************************************************************************************************************************");
       System.out.println("Setting up the forest nature trail....");
       System.out.println("");
    }
    
}
