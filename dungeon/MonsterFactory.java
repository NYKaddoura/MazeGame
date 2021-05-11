package dungeon;

public class MonsterFactory{
   public static Monster createMonster(int monsterGenerated){
      Monster monster = null;
      
      if (monsterGenerated == 1){
         monster = new Dragon();
      } else if (monsterGenerated == 2){
         monster = new Demon();
      } else if (monsterGenerated == 3){
         monster = new Skeleton();
      } else {
         System.out.println("Invalid choice, generating Skeleton");
         monster = new Skeleton();
      }
      
      return monster;
   }
}