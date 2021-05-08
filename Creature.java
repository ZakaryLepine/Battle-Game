import java.util.Date;
public class Creature {
	
	//------------ static variables---------
	private static final int FOOD2HEALTH = 6; //Used to decrease food units when normalizing units
	private static final int HEALTH2POWER = 4; //Used to decrease health units when normalizing units
	private static int numStillAlive = 0; //Used to display number of creatures alive during the game
	
	//-----non-static variables---------
	private String name; //Used to set creature names
	private int foodUnits; //Used to set creature food units
	private int healthUnits; //Used to set creature health units
	private int firePowerUnits; //Used to set creature fire power units
	private Date dateCreated; //Used to set date and time of creatures' creation
	private Date dateDied; //Used to set date and time of creatures' death
	
	//---------default constructor-----------------
	public Creature()
	{
		firePowerUnits = 0;
		setName("default Creature name");
		setFoodUnit(1); //Because food units cannot be 0
		setHealthUnit(1); //Because health units cannot be 0
		dateCreated = new Date(); 
		dateDied = null; 
	}
	
	//--------overriding Creature constructor-----------
	public Creature(String name) 
	{
		numStillAlive++;
		setName(name);
		while (this.getFoodUnits() == 0) //To not have food units set to 0
		{
			int randomNumberFood = (int) (Math.random() * 13); 
			if (randomNumberFood != 0) 
			{
				this.setFoodUnit(randomNumberFood);
			}
		}
		while (this.getHealthUnits() == 0) //To not have health units set to 0
		{
			int randomNumberHealth = (int) (Math.random() * 8); 
			if (randomNumberHealth != 0) 
			{
				this.setHealthUnit(randomNumberHealth);
			}
		}
		firePowerUnits = (int) (Math.random() * 11);
		this.normalizeUnits();
		dateCreated = new Date(); 
		dateDied = null; 	
	}
	
	//---------mutator methods-----------------
	public void setName(String newName) //To set name of creature
	{
		name = newName;
	}
	public void setHealthUnit(int n) //To set health units of creature
	{
		healthUnits += n;		
	}
	public void setFoodUnit(int n) //To set food units of creature
	{
		foodUnits += n;
	}
	
	//---------accessor methods-----------------
	public String getName() //To return name of creature
	{
		return name;
	}
	public int getFoodUnits() //To return food units of creature
	{
		return foodUnits;
	}
	public int getHealthUnits() //To return health units of creature
	{
		return healthUnits;
	}
	public int getFirePowerUnits() //To return fire power units of creature
	{	
		return firePowerUnits;
	}
	public Date getDateCreated() //To return date created of creature
	{
		return dateCreated;
	}
	public Date getDateDied() //To return date of death of creature
	{
		return dateDied;
	}
	public static int getNumStillAlive() //To return number of creatures alive
	{
		return numStillAlive;
	}
	
	
	public void reduceFirePowerUnits(int n) //To reduce fire power units by value of parameter inputed
	{
		firePowerUnits -= n;
		if (firePowerUnits < 0) firePowerUnits = 0; //To not have negative units of fire power
	}
	public boolean isAlive() //To return boolean whether creature has a date of death
	{
		return (this.getDateDied() == null); //Returns true if dateDied is null. Returns false otherwise
	}
	public int earnFood() //For option 5 of battle game. Generate a random number, add it to creatures food units, and return number.
	{
		int foodEarned = (int) (Math.random()*16); //Random number between 0 and 15
		this.setFoodUnit(foodEarned);  
		normalizeUnits();
		return foodEarned;
	}
	public void attacking(Creature player) /*For option 6 of the battle game. Adds half of the attacked creatures health units and food units to the attacking creatures health and food units
										   	 Decreases attacked creatures health units and food units by half. Calls normalizeUnits() method on attacking creature
										   	 Calls healthFoodUnitsZero() method on attacked creature*/
	{
		this.setFoodUnit((int) (Math.ceil(player.foodUnits / 2.0))); 
		this.setHealthUnit((int) (Math.ceil(player.healthUnits / 2.0)));  
		this.reduceFirePowerUnits(2);
		player.setFoodUnit((int) -(Math.ceil(player.foodUnits / 2.0))); 
		player.setHealthUnit((int) -(Math.ceil(player.healthUnits / 2.0))); 
		this.normalizeUnits();
		player.healthFoodUnitsZero();
	}
	public boolean healthFoodUnitsZero() //To return boolean whether the creature has health units and food units <= 0. If health units and food units <= 0, calls died() method to set date of death
	{
		if (this.getHealthUnits() <= 0 && this.getFoodUnits() <= 0 && this.getDateDied() == null) this.died(); //To call died() method on creature
		if (this.getHealthUnits() <= 0 && this.getFoodUnits() <= 0) return true; //To return true when health units and food units are <= 0
		else return false;
	}
	public void died() //To set a date and time of death to creature 
	{
		this.dateDied = new Date();
		numStillAlive--; //Decreases number of creatures alive by 1
	}
	public String toString() //To show values of non-static variables of creature 
	{
		String attributeMessage;
		if (this.getDateDied() == null) //To display a different Date Died message when creature is still alive
		{
			attributeMessage = "Food units \t Health units \t Fire power units \t Name \n"
								+ "---------- \t ------------ \t ---------------- \t ---- \n"
								+ this.getFoodUnits() + "\t\t" + " " + this.getHealthUnits() + "\t\t" + " " + this.getFirePowerUnits() + "\t\t\t" + " " + this.getName() 
								+ "\nDate Created: " + this.getDateCreated() 
								+ "\nDate Died: is still alive";
		}
		else //To call getDateDied() method  in date Died message when creature is dead 
		{
			attributeMessage = "Food units \t Health units \t Fire power units \t Name \n"
					+ "---------- \t ------------ \t ---------------- \t ---- \n"
					+ this.getFoodUnits() + "\t\t" + " " + this.getHealthUnits() + "\t\t" + " " + this.getFirePowerUnits() + "\t\t\t" + " " + this.getName() 
					+ "\nDate Created: " + this.getDateCreated() 
					+ "\nDate Died: " + this.getDateDied();
		}
		return attributeMessage;
	}
	public String showStatus() //To show values of food units,health units, and fire power units of creature in a string
	{
		String statusMessage = this.getFoodUnits() + " food units, " + this.getHealthUnits() + " health units, " + this.getFirePowerUnits() + " fire power units";
		return statusMessage;
	}
	private void normalizeUnits() //Set to private because user does not need access in main. To transform every 6 food units into 1 health unit and every 4 health units into 1 fire power unit 
	{
		while (this.getFoodUnits() >= 6) //To normalize food units when food units >= 6 
		{
			this.setHealthUnit(1); //Increases health units by 1
			this.setFoodUnit(-FOOD2HEALTH); //Decreases food units by 6
		}
		while (this.getHealthUnits() >= 4) //To normalize health units when health units >= 4
		{
			if (this.getFoodUnits() == 0 && this.getHealthUnits() == 4) break; //To not normalize units and have health units = 0 and food units = 0
			else
			{
				firePowerUnits += 1; //Increases fire power units by 1
				this.setHealthUnit(-HEALTH2POWER); //Decreases health units by 4
			}
		}
	}
}
