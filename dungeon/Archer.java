package dungeon;

import java.util.*;

public class Archer extends Hero{
   public Archer(){
      super("Archer", 125, 0, 0, 7, 25, 50, 0.4, 0.2);
   }
   public void threeArrows(dungeonCharacter enemy){
      if (Math.random() <= .45){
         int damage = (int)(Math.random() * (50 - 25 + 1)) + 25;
         damage = damage * 3;
         
         System.out.println(getName() + " fires 3 arrows at once for " + damage + "  damage!");
         
         enemy.loseHealth(damage);
      } else {
         System.out.println(getName() + " has missed their shot!");
         System.out.println("");
      }
   }
   public void fight(dungeonCharacter enemy){
      System.out.println(getName() + " shoots an arrow at " + enemy.getName());
      
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
         System.out.println("2. Triple Arrows");
         System.out.println("3. Use Health Potion(s)");
         System.out.println("4. End Turn");
         
         choice = kb.nextInt();
         
         if (choice == 1){
            fight(enemy);
         } else if (choice == 2){
            threeArrows(enemy);
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