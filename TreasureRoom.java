import java.util.Scanner;

/*
* Room with a item in it.
* designed to give player a break and chance to comeback.
* player doesn't know it's a treasure room until they investigate.
*/


public class TreasureRoom extends Room {
    //the treasure they recieve.
    Equippable treasure;

    //contructor
    public TreasureRoom(int floorLevel) {
      super(floorLevel);
      //randomStat so quality of item is random
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

    /*
    *Simulates the room.
    * Formatted same way as CombatRoom so player
    * Doesn't know if they're in a TreasureRoom or
    * CombatRoom.
    * Purpose is to encourage exploration and relieve players with a bonus.
    */
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
        player.addInventory(treasure);
         boolean newFlag = true;
          while(newFlag) {
            System.out.println("The room is now empty, what do you want to do now?");
            System.out.println("\n|| [ Leave ] || [ Inventory ] ||\n");
            action = input.nextLine();
            if(action.toLowerCase().equals("leave")) {
                newFlag = false;
               System.out.println("You move on to the next room.");
            } else if(action.toLowerCase().equals("inventory")) { 
              player.hud();
            }
          }
         } else if(action.toLowerCase().equals("inventory")) {
            player.hud();
      } else {
        System.out.println("invalid Input.");
      }
    }
}

}
