package dungeon;

import java.util.*;

public class Room{
   private boolean north = true;
   private boolean west = true;
   private boolean east = true;
   private boolean south = true;
   private boolean hasVisited;
   private int row;
   private int column;
   private ArrayList<Character> items;
   private char[] room;
   private char[] contents;
   
   public Room(int row, int column, char item){
      setRow(row);
      setColumn(column);
      hasVisited = false;
      room = new char[9];
      contents = new char[2];
      
      items = new ArrayList<Character>();
      items.add('M');//more than one item
      items.add('P');//pit
      items.add('H');//healing potion
      items.add('X');//Monster
      
      room[0] = '*';
      room[2] = '*';
      room[6] = '*';
      room[8] = '*';
            
      if (row == 0){
         this.north = false;
         
         room[1] = '*';
      }
      if (row == 4){
         this.south = false;
      
         room[7] = '*';
      }
      if (column == 0){
         this.west = false;
      
         room[3] = '*';
      }
      if (column == 4){
         this.east = false;
         
         room[5] = '*';
      }
      if (item != ' '){
         room[4] = item;
         contents[1] = room[4];
      } else {
         room[4] = roomItem();
      }
      if (north){
         room[1] = '-';
      }
      if (south){
         room[7] = '-';
      }
      if (east){
         room[5] = '|';
      }
      if (west){
         room[3] = '|';
      }
      
      this.contents[0] = ' ';
      this.contents[1] = room[4];
   }
   public char roomItem(){
      double chance = Math.random();
      
      if (chance <= .7){//70% chance the room isnt empty
         int random = (int)(Math.random() * (3 - 0 + 1) + 0);
         return items.get(random);
      }
      
      return ' ';
   }
   public char getItem(){
      return contents[1];
   }
   public void printItems(){
      if (room[4] != ' '){
         System.out.println("The room you have just entered has " + room[4] + ".");
      } else {
         System.out.println("This room is empty.");
      }
   }
   public void roomReset(char item){
      if (item == 'I' || item == 'O'){
         room[4] = item;
         contents[1] = item;
      } else {
         room[4] = '•';
         contents[1] = '•';
      }
   }
   public void setRow(int row){
      this.row = row;
   }
   public int getRow(){
      return this.row;
   }
   public void setColumn(int column){
      this.column = column;
   }
   public int getColumn(){
      return this.column;
   }
   public boolean hasBeenVisited(){
      return this.hasVisited;
   }
   public void hidden(){
      room[4] = this.contents[0];
   }
   public void exposed(){
      room[4] = this.contents[1];
   }
   public char currentLocation(){
      room[4] = '∆';
      
      this.hasVisited = true;
      return this.contents[1] ;
   }
   public String toString(){
      return new String(room);
   }
}