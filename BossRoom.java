import java.util.ArrayList;
import java.util.Scanner;

/*
* Room for facing a boss
* always one BossRoom per floor and always at end
*/

public class BossRoom extends Room {
  //list of bosses (a lot of them are puns based on friend's names).
   private static String[] bosses = {"Eric Lich E, the Fifth Lich of The Order", "Kyle Phantom, Haunter of Minecraft Servers",
                                    "Miku, Brit of Darkness", "Great Spirit Mango, The Tea Conquer"};
  //list of boss descriptions, in same order as bosses
  private static String[] bossRooms = {"You open the door to a massive throneroom of a castle, at the end appears to be a lich munching on watermelon, his emerald green eyes spot you.",
                                      "A Ghost like figure with a powerful aura sits outside an underground base as if waiting for someone to come out, it looks tired and depressed, it hears you enter and rises to its feet.",
                                      "You see a young blue haired girl atop a mountain of corpses twirling a knife, \"Oh Bloody hell\" it says with a robotic voice.",
                                      "You see a calm Spirit tending to a large garden seemly of its own creation, it turns to you, angry to see a trespasser in it's domain."};
   Enemy boss;
   boolean isLastBoss;
   //the treasure gained after the fight
   Equippable treasure;
  public BossRoom(int floorlevel, boolean isFinalBoss) {
    super(floorlevel);
    isLastBoss = isFinalBoss;
    //for deciding if a treasure is armor or weapon
    int random = (int)(Math.random()*2);
    //if it's the final boss it is always Ms.Satija
    if(isFinalBoss) {
      boss = new Enemy("Ms. Satija, Supreme Ruler of Computer Science", floorlevel*100, floorlevel*10, floorlevel*10, floorlevel*500, floorlevel*200, floorlevel*15, 0);
      setDesc("An empty Dark Hallway lies in front of you, As you enter the first door on the left, you are summoned to a dimension of ones and zeros. \n In front of you a giant altering the plane around you. That giant being Ms. Satija, Supreme Ruler of Computer Science.");
      treasure = null; 
      //standard game has exactly enough bosses
    } else if(floorlevel <= bosses.length) {
      boss = new Enemy(bosses[floorlevel-1], floorlevel*50, floorlevel*5, floorlevel*5, floorlevel*100, floorlevel*50, floorlevel*10, 0);
      setDesc(bossRooms[floorlevel-1] + "\nYou now face " + bosses[floorlevel-1] + ".");
      if(random == 0) {
        treasure = new Equippable(bosses[floorlevel-1].substring(0, bosses[floorlevel-1].indexOf(",")) + "'s Armor", "The armor of " + bosses[floorlevel-1], ((int)(floorlevel*1.5)), 20, false, floorlevel*100);
      } else {
        treasure = new Equippable(bosses[floorlevel-1].substring(0, bosses[floorlevel-1].indexOf(",")) + "'s Sword", "The blade of " + bosses[floorlevel-1], ((int)(floorlevel*1.5)), 20, true, floorlevel*100);
      }
      //backup if they do more floors than bosses: generic boss.
    } else {
      boss = new Enemy("Great Spirit of Chaos", floorlevel*50, floorlevel*5, floorlevel*5, floorlevel*100, floorlevel*50, floorlevel*10, 0);
      setDesc("A colosseum lies in front of you, inside a tornado in the shape of a person. \nYou now face a Great Spirit of Chaos.");
      if(random == 0) {
        treasure = new Equippable("Chaos" + " Armor", "The armor of a Great Spirit of Chaos", ((int)(floorlevel*1.5)), 20, false, floorlevel*100);
      } else {
        treasure = new Equippable("Chaos" + " Sword", "The blade of a Great Spirit of Chaos", ((int)(floorlevel*1.5)), 20, true, floorlevel*100);
      }
    }
    
    }

    //accessor methods
    public String getRoomContents() {
      return getDesc();
    }

    public String getBoss() {
      return boss.toString();
    }

    public String getTreasure() {
      if(treasure != null) {
      return treasure.info();
      } else {
        return "No Treasure";
      }
    }

    /*
    *@param player; the player for the game.
    *precondition: player and BossRoom are intialized.
    *poscondition: runs the the encounter and the options before/after the fight.
    */
    public void runRoom(Player player) {
     System.out.println("A giant door lies in front of you, you feel a powerful aura behind it...");
     Scanner input = new Scanner(System.in);
     boolean flag = true;
     int random = 0;
      //have to choose one of the options and will reset until they do
    while (flag)
    {
      System.out.println("\n|| [ Fight ] || [ Inventory ] ||\n");
      String action = input.nextLine();

      //code for fighting the boss and after
      if(action.toLowerCase().equals("fight")) {
        flag = false; 
        System.out.println(getDesc());
        Combat bossFight = new Combat(player, boss);
        bossFight.combatBegin();
        if(DungeonManager.getGameAlive()) {
          if(treasure != null) {
          System.out.println("You've gained" + treasure.getName() + "!");
          player.addInventory(treasure);
          }
          System.out.println("You've conquered the floor!");
          boolean newFlag = true;
          //measure so it doesn't ramble on after game is beaten.
          if(isLastBoss) {
            System.out.println("The Dungeon has been conquered!");
          } else {
          while(newFlag) {
            System.out.println("The room is now empty, what do you want to do now?");
            System.out.println("\n|| [ Leave ] || [ Inventory ] ||\n");
            action = input.nextLine();
            if(action.toLowerCase().equals("leave")) {
                newFlag = false;
               System.out.println("you descend the stairs to the next floor...");
            } else if(action.toLowerCase().equals("inventory")) {
              player.hud();
            }
          }
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
