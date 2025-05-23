import java.util.ArrayList;
import java.util.Scanner;

public class BossRoom extends Room {
   private static String[] bosses = {"Eric Lich E, the Fifth Lich of The Order", "Kyle Phantom, Haunter of Minecraft Servers",
                                    "Miku, Brit of Darkness", "Great Spirit Mango, The Tea Conquer"};
  private static String[] bossRooms = {"You open the door to a massive throneroom of a castle, at the end appears to be a lich munching on watermelon, his emerald green eyes spot you.",
                                      "A Ghost like figure with a powerful aura sits outside an underground base as if waiting for someone to come out, it looks tired and depressed, it hears you enter and rises to its feet.",
                                      "You see a young blue haired girl atop a mountain of corpses twirling a knife, \"Oh Bloody hell\" it says with a robotic voice.",
                                      "You see a calm Spirit tending to a large garden seemly of its own creation, it turns to you, angry to see a trespasser in it's domain."};
   Enemy boss;
   Equippable treasure;
  public BossRoom(int floorlevel, boolean isFinalBoss) {
    super(floorlevel);
    if(isFinalBoss) {
      boss = new Enemy("Ms. Satija, Supreme Ruler of Computer Science", floorlevel*100, floorlevel*10, floorlevel*10, floorlevel*500, floorlevel*200, floorlevel*15, 0);
      setDesc("An empty Dark Hallway lies in front of you, As you enter the first door on the left, you are summoned to a dimension of ones and zeros. \n In front of you a giant altering the plane around you. That giant being Ms. Satija, Supreme Ruler of Computer Science.");
      treasure = null; 
    } else if(floorlevel <= bosses.length) {
      boss = new Enemy(bosses[floorlevel-1], floorlevel*50, floorlevel*5, floorlevel*5, floorlevel*100, floorlevel*50, floorlevel*10, 0);
      setDesc(bossRooms[floorlevel-1] + "\nYou now face " + bosses[floorlevel-1] + ".");
      int random = (int)(Math.random()*2);
      if(random == 0) {
        treasure = new Equippable(bosses[floorlevel-1].substring(0, bosses[floorlevel-1].indexOf(",")) + "'s armor", "The armor of " + bosses[floorlevel-1], ((int)(floorlevel*1.5)), 20, false, floorlevel*100);
      } else {
        treasure = new Equippable(bosses[floorlevel-1].substring(0, bosses[floorlevel-1].indexOf(",")) + "'s sword", "The blade of " + bosses[floorlevel-1], ((int)(floorlevel*1.5)), 20, true, floorlevel*100);
      }
    }
    
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

  
}
