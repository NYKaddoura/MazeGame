package dungeon;

import java.util.*;

public class Hero extends dungeonCharacter{   
   private double blockChance;
   private int[] location = new int[2];
   private int numTurns;
   
   public Hero(String name, int health, int totalHealthPotions, int totalPillars, int attackSpeed, int minDamage, int maxDamage, double hitOrMiss, double blockChance){
      super(name, health, totalHealthPotions, totalPillars, attackSpeed, minDamage, maxDamage, hitOrMiss);
      
      this.blockChance = blockChance;
      
      if (name.equals("Tom")){
         System.out.println("You are now playing as Tom.\nNo need to put a name, Tom is a good name. :D");
      } else {
         theName();
      }
   }
   public void theName(){
      String userName = "";
      Scanner kb = new Scanner(System.in);
      
      System.out.print("Enter your characters name: ");
      userName = kb.nextLine();
      
      setName(userName);
   }
   public boolean blocked(){
      return (Math.random() <= blockChance);
   }
   public void loseHealth(int health){
      if (this.isAlive() == false){
         System.out.println(getName() + " has died!");
      } else {
         super.loseHealth(health);
         
         System.out.println(getName() + " has lost " + health + " health!");
      }
   }
   public int[] getLocation(){
      return this.location;
   }
   public void setLocation(int row, int column){
      location[0] += row;
      location[1] += column;
   }
   public void options(dungeonCharacter opponent){
      numOfTurns(opponent);
   }
   public void numOfTurns(dungeonCharacter opponent){
      numTurns = getAttackSpeed()/opponent.getAttackSpeed();
   
      if (numTurns == 0){
         numTurns++;
      }
      
      System.out.println("Number of turns this round is: " + numTurns);
      
      setNumTurns(numTurns);
   }
   public int getNumTurns(){
      return numTurns;
   }
   
   public void setNumTurns(int numTurns){
      this.numTurns = numTurns;
   }
   public String toString(){
      return getName() + " has " + getHealth() + " health, " + getHealthPotions() + " health potions, and " + getPillars() + " Pillars of OO.";
   }
}