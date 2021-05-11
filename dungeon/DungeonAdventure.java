//If you type 9 whenever a menu option is offered after the player selection, the whole dungeon should print with its contents
//The entire dungeon automatically prints so the player can tell where to go
//When picking a character, choose 8 and be rewared with a secret overpowered character

package dungeon;

import java.util.*;

public class DungeonAdventure {
   private static boolean gameIsRunning = true;
   
   public static void main(String[] args){
      System.out.println("Welcome to my Dungeon Adventure game!");
      System.out.println("The rules are simple.\nThere are 4 Pillars of OO that you need to find.");
      System.out.println("Once you have found them, head for the exit.\nBut beware, for the dungeon is a dangerous place");
      System.out.println("Good luck and have fun! You will start at the entrance.");
      System.out.println("Legend:\n I: Entrance\n O: Exit\n X: Monster\n H: Healing Potion\n P: Pit\n $: Pillar of OO\n M: Multiple items/monsters\n •: Room has been visited\n ∆: Your Current Location\n");
      
      playGame();
      
   }
   public static void playGame(){
      Scanner kb = new Scanner(System.in);
      
      Dungeon dungeon = new Dungeon(characterSelect());
      
      System.out.println("You need to find the 4 Pillars. Good luck " + dungeon.getHero().getName() + "!");
                  
      while(gameIsRunning){
         if (dungeon.getHero().getHealth() <= 0){
            gameIsRunning = false;
            
            System.out.println("Better luck next time!");
            
            break;
         }
         
         dungeon.printEmptyDungeon();
         
         int playerMoved = movement(dungeon);
         
         if (playerMoved == 7){
            gameIsRunning = false;
            break;
         }
         if (playerMoved == 9){
            dungeon.printDungeon();
         }
         
         if (dungeon.ifExit()){
            if (dungeon.getHero().getPillars() < 4){
               System.out.println("You don't have the 4 Pillars!\nYou only have " + dungeon.getHero().getPillars() + " Pillars!\nGo get the rest then return!");
            } else if (dungeon.getHero().getPillars() == 4){
               System.out.println("Congrats " + dungeon.getHero().getName() +"! You have gotten the 4 Pillars of OO!");
                  
               gameIsRunning = false;
               break;
            }
         }            
         
         dungeon.collectRoomItems();
         dungeon.reset();
      }
   }
   public static Hero characterSelect(){
      Scanner kb = new Scanner(System.in);
      
      int choice = 0;
      
      System.out.println("Pick a hero to play as: ");
      System.out.println("1: Knight\n2: Wizard\n3: Archer\n");
      
      choice = kb.nextInt();
      
      return HeroFactory.createHero(choice);
   }
   public static int movement(Dungeon dungeon){
      Scanner kb = new Scanner(System.in);
      
      int choice = 0;
      
      do {
         System.out.println("What do you want to do?");
         System.out.println("1: Move North\n2: Move South\n3: Move West\n4: Move East\n5: View Hero\n6: Use Health Potion\n7: Quit Game");
         
         choice = kb.nextInt();
         
         if (!(dungeon.canMove(choice))){
            System.out.println("You can no longer go that way, pick a different route.");
         }
      } while (!dungeon.canMove(choice) && choice != 7);
      
      if (choice == 7){
         gameIsRunning = false;
         return choice;
      }
      
      if (dungeon.canMove(choice)){
         if (choice == 1){
            dungeon.getHero().setLocation(-1, 0);
         } else if (choice == 2){
            dungeon.getHero().setLocation(1, 0);
         } else if (choice == 3){
            dungeon.getHero().setLocation(0, -1);
         } else if (choice == 4){
            dungeon.getHero().setLocation(0, 1);
         } else if (choice == 5){
            System.out.println(dungeon.getHero().toString());
         } else if (choice == 6){
            if (dungeon.getHero().getHealthPotions() > 0){
               int healthGained = (int) (Math.random() * (15 - 5 + 1) + 5);
               
               dungeon.getHero().addHealth(healthGained);
               
               System.out.println("You gained " + healthGained + " health!");
               System.out.println("You now have " + dungeon.getHero().getHealth() + " health.");
               System.out.println("You have " + dungeon.getHero().getHealthPotions() + " health potions left!");
            } else {
               System.out.println("You don't have any potions!");
            }
         }
      }
      
      return choice;
   }
}