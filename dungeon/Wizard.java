package dungeon;

import java.util.*;

public class Wizard extends Hero{
   public Wizard(){
      super("Wizard", 100, 0, 0, 5, 20, 45, 0.55, 0.35);
   }
   public void Fireball(dungeonCharacter enemy){
      if (Math.random() <= 0.42){
         int damage = (int)(Math.random() * (45 - 20 + 1) + 20);
         
         System.out.println(getName() + " casts a fireball for " + damage + " damage!");

         enemy.loseHealth(damage);
      } else {
         System.out.println(getName() + "'s fireball missed!");
         System.out.println("");
      }
   }
   public void fight(dungeonCharacter enemy){
      System.out.println(getName() + " casts a spell at " + enemy.getName());
      
      super.fight(enemy);
   }
   public void options(dungeonCharacter enemy){
      super.options(enemy);
      
      Scanner kb = new Scanner(System.in);
      
      int choice = 0;
      int turns = getNumTurns();
      
      do {
         System.out.println("Make A Choice:");
         System.out.println("1. Attack");
         System.out.println("2. Fireball");
         System.out.println("3. Use Health Potion(s)");
         System.out.println("4. End Turn");
         
         choice = kb.nextInt();
         
         if (choice == 1){
            fight(enemy);
         } else if (choice == 2){
            Fireball(enemy);
         } else if (choice == 3){
            if (getHealthPotions() > 0){
               int healthGained = (int) (Math.random() * (15 - 5 + 1) + 5);
               
               addHealth(healthGained);
               
               System.out.println("You gained " + healthGained + " health!");
               System.out.println("You have " + getHealthPotions() + " health potions left!");
            } else {
               System.out.println("You don't have any potions!");
            }
         }
         
         turns--;
         
         if (enemy.isAlive() == false){
            return;
         }
         if (turns > 0){
            System.out.println("You have " + turns +" turns left.");
         }
         if (turns == 0){
            break;
         }
         
      } while(choice != 4);
   }
}