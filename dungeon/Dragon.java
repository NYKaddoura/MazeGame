package dungeon;

public class Dragon extends Monster{
   public Dragon(){
      super("Dragon", 250, 10, 90, 5, 0.35, 0.2);
   }
   public void fight(dungeonCharacter hero){
      System.out.println(getName() + " breathes fire at " + hero.getName() + "!");
      
      super.fight(hero);
   }
}