package dungeon;

import java.util.*;

public class Monster extends dungeonCharacter{
   private double selfDestructChance;
   
   public Monster(String name, int health, int minDamage, int maxDamage, int attackSpeed, double hitOrMiss, double selfDestructChance){
      super(name, health, minDamage, maxDamage, attackSpeed, hitOrMiss);
      
      this.selfDestructChance = selfDestructChance;
   }
   public int selfDestructDamage(){
      return (getHealth() * (int)(50.0f/100.0f));
   }
   public boolean didExplode(){
      double exploded = Math.random();
      int damage = selfDestructDamage();
      
      if (damage != 0){
         if (exploded > selfDestructChance){
            System.out.println(getName() + " has self destructed for " + damage + " damage!");
            return true;
         } else {
            return false;
         }
      } else{
         return false;
      }
   }
   public void loseHealth(int health){
      int healthLeft = this.getHealth();
      
      if(healthLeft > 0){ 
         if (didExplode() == true ){
            super.loseHealth(1000);
         } else{
            super.loseHealth(health);
         }
      }
   }
}