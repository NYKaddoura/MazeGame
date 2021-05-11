package dungeon;

import java.util.*;

public class Dungeon{
   private Room[][] dungeon = new Room[5][5];
   private Hero hero;
   private char roomItem;
   public int[] currentSpot = new int[3];
   
   public Dungeon(Hero hero){
      this.hero = hero;
            
      for (int i = 0; i < 5; i++){
         for (int j = 0; j < 5; j++){
            if (i == 4 && j == 2){//hard coding entrance
               dungeon[i][j] = new Room(i, j, 'I');
               hero.setLocation(4, 2);
            } else if (i == 1 && j == 1){//hard coding exit
               dungeon[i][j] = new Room(i, j, 'O');
            } else if (i == 0 && j == 4){//hard coding 4 pillars locations
               dungeon[i][j] = new Room(i, j, '$');
            } else if (i == 2 && j == 2){
               dungeon[i][j] = new Room(i, j, '$');
            } else if (i == 0 && j == 0){
               dungeon[i][j] = new Room(i, j, '$');
            } else if (i == 3 && j == 1){
               dungeon[i][j] = new Room(i, j, '$');
            } else {
               dungeon[i][j] = new Room(i, j, ' ');
            }
         }
      }
   }
   public Hero getHero(){
      return this.hero;
   }
   public void printEmptyDungeon(){//try moving to dungeonAdventure
      StringBuilder theRoom = new StringBuilder();
      StringBuilder topRow, middleRow, bottomRow;
      
      currentSpot[0] = hero.getLocation()[0];
      currentSpot[1] = hero.getLocation()[1];
      currentSpot[2] = dungeon[currentSpot[0]][currentSpot[1]].currentLocation();
      
      for (int r = 0; r < 5; r++){
         topRow = new StringBuilder();
         middleRow = new StringBuilder();
         bottomRow = new StringBuilder();
         
         for (int c = 0; c < 5; c++){
            if (r != hero.getLocation()[0] || c != hero.getLocation()[1]){
               if (dungeon[r][c].hasBeenVisited()){
                  dungeon[r][c].exposed();
               } else {
                  dungeon[r][c].hidden();
               }
            }
            
            topRow.append(dungeon[r][c].toString().substring(0,3));
            middleRow.append(dungeon[r][c].toString().substring(3,6));
            bottomRow.append(dungeon[r][c].toString().substring(6,9));
         }
         
         theRoom.append(topRow);
         theRoom.append(middleRow);
         theRoom.append(bottomRow);
      }
      for (int t = 0; t < theRoom.length(); t++){
         if (t % 16 == 0){
            theRoom.insert(t, '\n');
         }
      }
      
      System.out.println(theRoom);
   }
   public void printDungeon(){
      StringBuilder aRoom = new StringBuilder();
      StringBuilder rowT, rowM, rowB;
      
      for (int r = 0; r < 5; r++){
         rowT = new StringBuilder();
         rowM = new StringBuilder();
         rowB = new StringBuilder();
         
         for (int c = 0; c < 5; c++){
            dungeon[r][c].exposed();
            
            rowT.append(dungeon[r][c].toString().substring(0,3));
            rowM.append(dungeon[r][c].toString().substring(3,6));
            rowB.append(dungeon[r][c].toString().substring(6,9));
         }
         
         aRoom.append(rowT);
         aRoom.append(rowM);
         aRoom.append(rowB);
      }
      for (int t = 0; t < aRoom.length(); t++){
         if (t % 16 == 0){
            aRoom.insert(t, '\n');
         }
      }
      
      System.out.println(aRoom);
   }
   public boolean ifExit(){
      if (hero.getLocation()[0] == 1 && hero.getLocation()[1] == 1){
         return true;
      }
      
      return false;
   }
   public boolean canMove(int choice){
      if (hero.getLocation()[0] == 0 && choice == 1){
         return false;
      } else if (hero.getLocation()[0] == 4 && choice == 2){
         return false;
      } else if (hero.getLocation()[1] == 0 && choice == 3){
         return false;
      } else if (hero.getLocation()[1] == 4 && choice == 4){
         return false;
      }
   
      return true;
   }
   public Monster monsterGenerator(){
      int monster = (int)(Math.random() * 3) + 1;
      
      return MonsterFactory.createMonster(monster);
   }
   public void reset(){
      int[] roomSpot = new int[2];
      roomSpot[0] = hero.getLocation()[0];
      roomSpot[1] = hero.getLocation()[1];
      
      char roomItem =dungeon[roomSpot[0]][roomSpot[1]].getItem();
      
      dungeon[roomSpot[0]][roomSpot[1]].roomReset(roomItem);
   }
   public void collectRoomItems(){
      int row = hero.getLocation()[0];
      int column = hero.getLocation()[1];
      
      if (dungeon[row][column].getItem() == 'P'){
         int damage = (int)(Math.random() * 20) + 1;
         
         System.out.println("You have fallen in a pit and lost " + damage + " health!");
         
         hero.loseHealth(damage);
      }
      if (dungeon[row][column].getItem() == '$'){
         System.out.println("You have found a Pillar of OO!");
         
         hero.addPillars();
            
         System.out.println("You know have " + hero.getPillars() +"/4 Pillars of OO!");
            
         dungeon[row][column].roomReset('$');
      }
      if (dungeon[row][column].getItem() == 'X'){
         System.out.println("You have encountered a monster!");
            
         Monster monster = monsterGenerator();
         
         combat(hero, monster);
         
         dungeon[row][column].roomReset('X');
      }
      if (dungeon[row][column].getItem() == 'H'){
         System.out.println("You have found a health potion!");
         
         hero.addPotions();
         
         dungeon[row][column].roomReset('H');
      }
      if (dungeon[row][column].getItem() == 'M'){
         int itemChance = (int)(Math.random() * 3) + 1;
         
         System.out.println("You have come across a room with multiple items!");
         
         if (itemChance == 1) {
            int damage = (int)(Math.random() * 20) + 1;
            
            System.out.println("You have found a health potion but you fell in a pit and lost " + damage + " health!");
            
            hero.addPotions();
            hero.loseHealth(damage);
         } else if (itemChance == 2){
            int potions = (int)(Math.random() * 3) + 2;//up to 4 potions
            System.out.println("Congrats! You have found " + potions + " health potions!");
            
            for (int i = 0; i < potions; i++){
               hero.addPotions();
            }
            
            dungeon[row][column].roomReset('M');
         } else if (itemChance == 3){
            System.out.println("Oh no! You've come across 2 monsters!");
            
            Monster monster = monsterGenerator();
            Monster aMonster = monsterGenerator();
            
            combat(hero, monster);
            combat(hero, aMonster);
            
            dungeon[row][column].roomReset('M');
         }
      }
   }
   public void combat(Hero player, Monster enemy){
      Scanner in = new Scanner(System.in);
      
      char quit = 'p';
      
      System.out.println(player.getName() + " is fighting a " + enemy.getName() + "!");
      System.out.println("---------------------------------------------");

      while (player.isAlive() && enemy.isAlive() && quit != 'q'){
         player.options(enemy);
      
         if (enemy.isAlive()){
            enemy.fight(player);
         }
         if (!(enemy.isAlive()) || !(player.isAlive())){
            return;
         }
      
         System.out.print("\n-->q to quit, anything else to continue: ");
         quit = in.next().charAt(0);
      }
   
      if (!(enemy.isAlive())){
         System.out.println(player.getName() + " was victorious!");
         
         if (Math.random() < .5){
            System.out.println("The monster dropped a health potion!");
            
            hero.addPotions();
         }
      } else if (!(player.isAlive())){
         System.out.println(player.getName() + " was defeated!");
         
         int numOfPillars = hero.getPillars();
         
         if (numOfPillars == 4){
            System.out.println("So close! You just needed to find the exit!");
         } else {
            System.out.println("Pillars of OO found: " + numOfPillars);
         }
      } else{
         System.out.println("Quitters never win ;)");
      }
   }
}