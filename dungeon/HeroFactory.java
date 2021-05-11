package dungeon;

public class HeroFactory{
   public static Hero createHero(int character){
      Hero hero = null;
      
      if (character == 1){
         hero = new Knight();
      } else if (character == 2){
         hero = new Wizard();
      } else if (character == 3){
         hero = new Archer();
      } else if (character == 8){
         hero = new SecretUnit();
      } else {
         System.out.println("Invalid choice, generating Knight");
         hero = new Knight();
      }
      
      return hero;
   }
}