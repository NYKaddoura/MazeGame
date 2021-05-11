package dungeon;

import java.util.*;

public class dungeonCharacter{
   private String name;
   private int health;
   private int totalHealthPotions;
   private int totalPillars;
   private int attackSpeed;
   private int minDamage, maxDamage;
   private double hitOrMiss;
   
   //hero constructor
   public dungeonCharacter(String name, int health, int totalHealthPotions, int totalPillars, int attackSpeed, int minDamage, int maxDamage, double hitOrMiss){
      this.name = name;
      this.health = health;
      this.totalHealthPotions = totalHealthPotions;
      this.totalPillars = totalPillars;
      this.attackSpeed = attackSpeed;
      this.minDamage = minDamage;
      this.maxDamage = maxDamage;
      this.hitOrMiss = hitOrMiss;
   }
   //monster constructor
   public dungeonCharacter(String name, int health, int minDamage, int maxDamage, int attackSpeed, double hitOrMiss){
      this.name = name;
      this.health = health;
      this.attackSpeed = attackSpeed;
      this.minDamage = minDamage;
      this.maxDamage = maxDamage;
      this.hitOrMiss = hitOrMiss;
   }
   public String getName(){
      return this.name;
   }
   public void setName(String name){
      this.name = name;
   }
   public int getHealth(){
      return this.health;
   }
   public int getHealthPotions(){
      return this.totalHealthPotions;
   }
   public int getPillars(){
      return this.totalPillars;
   }
   public int getAttackSpeed(){
      return this.attackSpeed;
   }
   public boolean isAlive(){
      return (health > 0);
   }
   public void addPillars(){
      this.totalPillars++;
   }
   public void addPotions(){
      this.totalHealthPotions++;
   }
   public void loseHealth(int healthLost){
      if (healthLost < 0){
         System.out.println("Health is less than 0");
      } else if (healthLost == 0){
         System.out.print(name + " has been killed!");
      } else {
         health -= healthLost;
         
         if (health < 0){
            health = 0;
         }
         
         System.out.println(name + " got hit for " + healthLost + " health!");
         
         if (this.health < 0){
            System.out.println(name + " has been defeated!");
         } else {
            System.out.println(name + " has " + getHealth() + " health left!");
            System.out.println();
         }
      }  
   }
   public void addHealth(int healthGained){
      this.health += healthGained;
      
      totalHealthPotions--;
   }
   public void fight(dungeonCharacter enemy){
      int damage;
      boolean willAttack;
      
      willAttack = Math.random() <= hitOrMiss;
      
      if (willAttack){
         damage = (int)(Math.random() * (maxDamage - minDamage + 1)) + minDamage;
         enemy.loseHealth(damage);
      } else {
         System.out.println(getName() + "'s attack missed!");
         System.out.println("");
      }
   }
}