import java.util.Scanner;

public class TreasureRoom extends Room {
    Equippable treasure;

    public TreasureRoom(int floorLevel) {
      super(floorLevel);
      int randomStat = (int)(Math.random()*floorLevel*floorLevel*5) + 5;
      int random = (int)(Math.random()*2);
      if(random == 0) {
      treasure = new Equippable(Equippable.randomDescription() + " Armor", "A piece of armor. Affects defense by " + randomStat, 1, randomStat, false, randomStat);
        } else {
         treasure = new Equippable(Equippable.randomDescription() + " Sword", "A blade. Affects attack by " + randomStat, 1, randomStat, true, randomStat);
        }
    }

    public String getRoomContents() {
    return "a chest with a " + treasure.getName() + "!";
  }
  
   public void runRoom(Player player) {
     System.out.println(getDesc());
     Scanner input = new Scanner(System.in);
     boolean flag = true;
     int random = 0;
    while (flag)
    {
      System.out.println("\n|| [ Sneak ] || [ Search ] || [ Inventory ] ||\n");
      String action = input.nextLine();
      
      if(action.toLowerCase().equals("sneak")) {
        flag = false;
        System.out.println("you passed the room peacefully...");
        } else if(action.toLowerCase().equals("search")) {
        flag = false;
        System.out.println("You Found Treasure!!!! There was " + getRoomContents());
        //UPDATE HERE
      } else if(action.toLowerCase().equals("inventory")) {
        //UPDATE HERE
      } else {
        System.out.println("invalid Input.");
      }
    }
}

}
