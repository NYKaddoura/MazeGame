package dungeon;

public class Demon extends Monster{
   public Demon(){
      super("Demon", 125, 15, 35, 3, 0.3, 0.4);
   }
   public void fight(dungeonCharacter hero){
      System.out.println(getName() + " lunges at " + hero.getName() + "!");
      
      super.fight(hero);
   }
}