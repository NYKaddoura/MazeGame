package dungeon;

import java.util.*;

public class SecretUnit extends Hero{
   public SecretUnit(){
      super("Tom", 99999999, 0, 0, 10, 100, 1000, 1, 1);
   }
   public void Quiz(dungeonCharacter enemy){
      if (Math.random() <= 0.9){
         System.out.println(getName() + " has handed out a quiz!");
         
         int damage = (int)(Math.random() * (1000 - 100 + 1) + 100);
         
         enemy.loseHealth(damage);
         
         System.out.println(enemy.getName() + " has failed the quiz!");
      } else {
         System.out.println(enemy.getName() + " has aced the quiz!");
         System.out.println("");
      }
   }
   public void fight(dungeonCharacter enemy){
      System.out.println(getName() + " lectures at " + enemy.getName());
      
      super.fight(enemy);
   }
   public void options(dungeonCharacter enemy){
      super.options(enemy);
      
      Scanner kb = new Scanner(System.in);
      
      int choice = 0;
      int turns = getNumTurns();
      
      do {
         if (!enemy.isAlive() || choice == 4){
            break;
         }
         
         System.out.println("Make A Choice:");
         System.out.println("1. Lecture");
         System.out.println("2. Quiz");
         System.out.println("3. Use Health Potion(s)");
         System.out.println("4. End Turn");
         
         choice = kb.nextInt();
         
         if (choice == 1){
            fight(enemy);
         } else if (choice == 2){
            Quiz(enemy);
         } else if (choice == 3){
            System.out.println("I don't need this, I've got plenty as is!\n Tom has " + getHealth() + " left.");
            System.out.println("How dare you think Tom needs this?!");
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