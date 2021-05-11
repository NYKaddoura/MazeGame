package dungeon;

public class Skeleton extends Monster{
   public Skeleton(){
      super("Skeleton", 100, 5, 35, 2, 0.4, 0.4);
   }
   public void fight(dungeonCharacter hero){
      System.out.println(getName() + " attacks " + hero.getName() + "!");
      
      super.fight(hero);
   }
}