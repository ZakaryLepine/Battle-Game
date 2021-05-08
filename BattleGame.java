/* -------------------------------------------------------
 * Assignment 4
 * Written by: Zakary Lepine 40192181
 * For COMP248 Section W – Winter 2021
 * --------------------------------------------------------
 *PROGRAM DESCRIPTION
 *Displays welcome message
 *Asks user for number of creatures for battle game. Keeps asking user until valid number of creatures is inputed (> 2 and < 9).
 *Creates Creature array of the size previously inputed by user. 
 *Asks user for name of creatures.
 *Generates random number to choose starting creature.
 *Loops through creature playing turns starting with index of creature array that matches the random number.
 *Display 6 playing choice for current creature if creature is alive.
 *Choice 1: Displays number of creatures alive.
 *Choice 2: Displays current creature's non-static attributes.
 *Choice 3: Displays all creature's non-static attributes.
 *Choice 4: Changes current creatures name by user's input.
 *Choice 5: Calls earnFood() method which adds between 0 to 15 food units to current creature.
 *Choice 6: Calls attacking() method that attacks creature with index that user inputs (user input is decreased by 1 to match the index of the creature chosen since array indexes begin at 0). 
 *Choice 6: Random number between 0-2 is generated
 *Choice 6: If attacking creature has < 2 fire power units and attacked creature has < 2 fire power units, no one is attacked.
 *Choice 6: else if attacking creature has < 2 fire power units and attacked creatures has >= 2 fire power units, attacking creature is attacked instead.
 *Choice 6: else if random number = 0, attacking creature is attacked instead.
 *Choice 6: else attacking creature attacks (random number = 1 or 2).
 *Choice 6: isAlive() method is called on attacked creature and displays "-----> <creature name> is dead!" if method is returned false.
 *If oneCreatureAliveLeft() method returns true, breaks out of creature playing turn loop.
 *Else if i = last index of the creature array, i = -1. Then, i is incremented to 0 which "resets" the creature playing turn loop to the first index of the creature array.
 *Displays game over message and non-static attributes of all creatures.
 *Displays closing message 
*/
import java.util.Scanner;
public class BattleGame {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); //Used for user input
		
		int numberOfCreatures; //Used to set number of creatures in game
		boolean sameTurn; //Used to end creature's turn
		int startingCreature = 0; //Used to set which creature starts the game
		int attackingChoice = 0; //Used to set 
		
		//-----------------Welcome message---------------------
		System.out.println("***********************************");
		System.out.println("*                                 *");
		System.out.println("*   Welcome to the Battle Game!   *");
		System.out.println("*                                 *");
		System.out.println("***********************************");
		
		//------------To choose number of creatures-----------
		System.out.print("\nHow many creatures would you like to have (minimum 2, maximum 8)? ");
		numberOfCreatures = input.nextInt(); 
		
		while (numberOfCreatures < 2 || numberOfCreatures > 8) //Asks user for input until user enters a valid number of creatures
		{
			System.out.println("\n** Illegal number of creatures requested *** ");
			System.out.print("\nHow many creatures would you like to have (minimum 2, maximum 8)? ");
			numberOfCreatures = input.nextInt();
			input.nextLine(); //To consume 'enter' from scanner buffer
		}
		Creature[] setOfCreatures = new Creature[numberOfCreatures]; //To create array of type Creature with the size inputed by user
		input.nextLine(); //To consume 'enter' from scanner buffer
		
		//-------------To set name of creatures---------------
		for (int i = 0; i < setOfCreatures.length; i++) //To set name of creatures with index 0 to value of numberOfCreatures-1
		{
			System.out.print("\nWhat is the name of creature " + (i+1) + "? ");
			String name = input.nextLine();
			setOfCreatures[i] = new Creature(name);
			System.out.println();
			System.out.println(setOfCreatures[i].toString());
		}
		
		//----------To choose a starting creature--------------
		while (startingCreature == 0)  //Generates a random number until startingCreature > 0 and <= numberOfCreatures
		{
			int i = (int) (Math.random() * (numberOfCreatures + 1)); 
			if (i != 0) startingCreature = i;
		}
		System.out.println("\nThe starting creatures is creature #" + startingCreature + "\n");
		
		startingCreature -= 1; //Decreases startingCreature to match the index of the setOfCreatures array
		
		//----------To loop through creature play turns------------
		for (int i = startingCreature; i < setOfCreatures.length; i++) //To loop through creature turns. Begins with index of startingCreature
		{
			sameTurn = true;
			if (!setOfCreatures[i].isAlive()) sameTurn = false; //To not display game options if creature is dead 
			
			//-------To display 6 player choices for current creature---------
			while (sameTurn) //Keeps same creature turn until option 5 or option 6 is chosen
			{
				System.out.println("Creature #" + (i+1) + ": " + setOfCreatures[i].getName() + ", what do you want to do?");
				System.out.println("\t1. How many creatures are alive?");
				System.out.println("\t2. See my status");
				System.out.println("\t3. See status of all players");
				System.out.println("\t4. Change my name");
				System.out.println("\t5. Work for some food");
				System.out.println("\t6. Attack another creature (Warning! may turn against you)");
				System.out.print("Your choice please > ");
				int choice = input.nextInt();
				input.nextLine(); //To consume 'enter' from scanner buffer
				
				//-------To have user input a valid choice------
				while (choice < 1 || choice > 6) //To prompt user until 1-6 player option is inputed
				{
					System.out.print("Sorry! That is not a valid choice. Try again please > ");
					choice = input.nextInt();
					input.nextLine(); //To consume 'enter' from scanner buffer
				}
				
				//-----To execute user's choice-----------
				switch (choice) //For the six player options
				{
					//--------Choice 1----------
					case 1: //To display number of creatures alive
					{
						System.out.println("\tNumber of creatures alive: " + setOfCreatures[i].getNumStillAlive() + "\n"); break;
					}//End case 1
					
					//--------Choice 2----------
					case 2: //To display status of current creatures
					{	
						System.out.println(setOfCreatures[i].toString() + "\n"); break;
					}//End case 2
					
					//--------Choice 3----------
					case 3: //To display status of all creatures
					{
						for (int k = 0; k < setOfCreatures.length; k++) //Loops through all creatures and displays status
						{
							System.out.println(setOfCreatures[k].toString());
						} 
						System.out.println();break; 
					}//End case 3
					
					//--------Choice 4----------
					case 4: //To display and change current creatures name
					{
						System.out.println("Your name is currently \"" + setOfCreatures[i].getName() + "\""); 
						System.out.print("What is the new name: "); 
						String name = input.nextLine(); 
						setOfCreatures[i].setName(name); 
						System.out.println();
						break;
					}//End case 4
					
					//--------Choice 5----------
					case 5: //To earn food
					{
						System.out.println("\nYour status before working for food: " + setOfCreatures[i].showStatus() + " ... You earned " + setOfCreatures[i].earnFood() + " food units.");
						System.out.println("\nYour status after working for food: " + setOfCreatures[i].showStatus() + "\n");
						sameTurn = false; //Changes creature turn
						break;
					}//End case 5
					
					//--------Choice 6----------
					case 6: //To attack other creature
					{
						System.out.print("\nWho do you want to attack? (enter a number from 1 to " + setOfCreatures.length + " other than yourself(" + (i+1) + "): " );
						attackingChoice = input.nextInt();
						input.nextLine(); //To consume 'enter' from scanner buffer
						
						//------To have user input a valid creature index-------------------
						//Prompts user for input until attacking choice is > 1, < last index of Creatures array, not the index of a dead creature, or not index of creature whose turn it is 
						while (attackingChoice == i+1 || attackingChoice > setOfCreatures.length || attackingChoice < 1 || !setOfCreatures[attackingChoice-1].isAlive())
						{
							if (attackingChoice == i+1) //Attacking choice is index of creature whose turn it is
							{
								System.out.println("Can't attack yourself silly! Try again ...");
								System.out.print("\nWho do you want to attack? (enter a number from 1 to " + setOfCreatures.length+ " other than yourself(" + (i+1) + "): " );
								attackingChoice = input.nextInt();
								input.nextLine(); //To consume 'enter' from scanner buffer
							}
							else if (attackingChoice > setOfCreatures.length || attackingChoice < 1) //Attacking choice is > last index of creatures array or < 1
							{
								System.out.println("That creature does not exist. Try again ...");
								System.out.print("\nWho do you want to attack? (enter a number from 1 to " + setOfCreatures.length + " other than yourself(" + (i+1) + "): " );
								attackingChoice = input.nextInt();
								input.nextLine(); //To consume 'enter' from scanner buffer
							}
							else if (!setOfCreatures[attackingChoice-1].isAlive())//Attacking choice is index of dead creature 
							{
								System.out.println("That creature is dead. Try again ...");
								System.out.print("\nWho do you want to attack? (enter a number from 1 to " + setOfCreatures.length + " other than yourself(" + (i+1) + "): " );
								attackingChoice = input.nextInt();
								input.nextLine(); //To consume 'enter' from scanner buffer
							}
						} //End while loop for attacking choice input

						attackingChoice -= 1; //Decreases attackingChoice to match the index of setOfCreatures array
						
						int random = (int) (Math.random() * 3); //For 1/3 chance of attacking creature getting attacked instead

						//-----To dictate outcome of attack------------
						if (setOfCreatures[i].getFirePowerUnits() < 2 && setOfCreatures[attackingChoice].getFirePowerUnits() < 2) //If attacking creatures has < 2 fire power units and attacked creature has < 2 fire power units
						{
							//No creature attacks 
							System.out.println("\nLucky you, the odds were that the other player attacks you, but " + setOfCreatures[attackingChoice].getName() + " doesn't have enough fire power to attack you! So is status quo!!\n");
						}
						else if  (setOfCreatures[i].getFirePowerUnits() < 2 && setOfCreatures[attackingChoice].getFirePowerUnits() >= 2) //Creature that initiated attack is attacked instead 
						{
							System.out.println("\nThat was not a good idea ... you only had " + setOfCreatures[i].getFirePowerUnits() + " Fire Power units!!!");
							System.out.println("....... Oh No!!! You are being attacked by " + setOfCreatures[attackingChoice].getName() + "!");
							System.out.println("Your status before being attacked: " + setOfCreatures[i].showStatus());
							int foodUnitsLost = (int) (Math.ceil(setOfCreatures[i].getFoodUnits() / 2.0)); //Used to display food units lost
							int healthUnitsLost = (int) (Math.ceil(setOfCreatures[i].getHealthUnits() / 2.0)); //Used to display health units lost
							setOfCreatures[attackingChoice].attacking(setOfCreatures[i]); 
							System.out.println("You lost " + foodUnitsLost + " food unit(s) and " + healthUnitsLost + " health unit(s)"); //Displays food units and health units lost 
							System.out.println("Your status after being attacked: " + setOfCreatures[i].showStatus() + "\n");
						}
						else if (random == 0) //Attacking creature gets attacked instead
						{
							System.out.println("\n....... Oh No!!! You are being attacked by " + setOfCreatures[attackingChoice].getName() + "!");
							System.out.println("Your status before being attacked: " + setOfCreatures[i].showStatus());
							int foodUnitsLost = (int) (Math.ceil(setOfCreatures[i].getFoodUnits() / 2.0)); //Used to display food units lost
							int healthUnitsLost = (int) (Math.ceil(setOfCreatures[i].getHealthUnits() / 2.0)); //Used to display health units lost
							setOfCreatures[attackingChoice].attacking(setOfCreatures[i]);
							System.out.println("You lost " + foodUnitsLost + " food unit(s) and " + healthUnitsLost + " health unit(s)"); //Displays food units and health units lost
							System.out.println("Your status after being attacked: " + setOfCreatures[i].showStatus() + "\n");
						}
						else //Attacking creature attacks chosen creature
						{
							System.out.println("\n..... You are attacking " + setOfCreatures[attackingChoice].getName() + "!");
							System.out.println("Your status before attacking: " + setOfCreatures[i].showStatus());
							int foodUnitsGained = (int) (Math.ceil(setOfCreatures[attackingChoice].getFoodUnits() / 2.0)); //Used to display food units gained
							int healthUnitsGained = (int) (Math.ceil(setOfCreatures[attackingChoice].getHealthUnits() / 2.0)); //Used to display health units gained
							setOfCreatures[i].attacking(setOfCreatures[attackingChoice]);
							System.out.println("You gained " + foodUnitsGained + " food unit(s) and " + healthUnitsGained + " health unit(s)"); //Displays food units and health units gained
							System.out.println("Your status after attacking: " + setOfCreatures[i].showStatus() + "\n");
						}
						
						//--------To display message is attacked character is dead------
						if (!setOfCreatures[i].isAlive()) //To check if creature at play is alive 
						{
							System.out.println("---->" + setOfCreatures[i].getName() + " is dead\n");
						}
						else if (!setOfCreatures[attackingChoice].isAlive()) //To check if chosen creature is alive
						{
							System.out.println("---->" + setOfCreatures[attackingChoice].getName() + " is dead\n");
						}
						sameTurn = false; //Changes creature turn 
						break;
					}//End case 6	
				} //End switch
			}//End while loop for same turn
			if (oneCreatureAliveLeft(setOfCreatures,i) == true) break; //Ends creature turn loop if there is only 1 creature left alive. 
			else if (oneCreatureAliveLeft(setOfCreatures,attackingChoice) == true) break; //Ends creature turn loop if there is only 1 creature left alive. For when attacking creature is attacked instead.
			else if (i == setOfCreatures.length-1) i = -1; //To reset creature turn if it is the creature with the last index of the array's playing turn. i = -1 because it is incremented to 0 after.
		}//End for loop to choose turn 
		
		//----------Displays end of game message and status of all creatures------
		System.out.println("GAME OVER!!!!!\n"); 
		for (int i = 0; i < setOfCreatures.length; i++)
		{
			System.out.println(setOfCreatures[i].toString() + "\n"); //Displays status of all creatures
		}
		
		//------------Closing message-----------------
		System.out.println("\nThank you for playing the Battle Game!");
		
		//Closes scanner object
		input.close();
	}
	/*
	 * Returns boolean depending on whether there is only 1 creature left alive. Skips iteration of loop where i = startingCreature 
	 * @param setOfCreatures[] the Creatures array, startingCreature index of creature whose turn it is 
	 * @return boolean true if only one creature is alive. False otherwise.
	 */ 
	public static boolean oneCreatureAliveLeft(Creature setOfCreatures[], int startingCreature)
	{
		for (int i = 0; i < setOfCreatures.length; i++)
		{
			if (i == startingCreature) //Skips iteration of i that is equal to index of setOfCreatures array
			{
				continue;
			}
			if (setOfCreatures[i].isAlive())
			{
				return false;
			}
		}
		return true;
	}
}
