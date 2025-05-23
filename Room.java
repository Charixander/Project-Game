import java.util.Scanner;

/*
* Superclass for BossRoom, CombatRoom,and TreasureRoom.
* Has the basic info of a room.
*/

public class Room {
  private static int roomRank = 1;
  private static final double DIFFICULTY_MULTIPLIER = 0.5;
  private int totalRank;
  // for describing the room:
  private String[] cleanliness = {"musty", "rancid", "clean", "spotless", "decayed", "moldy", "gross", "boring", "golden"};
  private String[] second = {"You can barely see ahead of you.", "The buzzing of lights dig into your head", "The janitor must've missed a spot.", "Have people been here before?"};
  private String[] lights = {"flickering candlelight", "office lights", "a glowing bonfire", "spots of sunlight scattered from the ceiling"};
  private String roomDesc;
  
  
  public Room(int floorRank) {
    //IMPORTANT: total rank decides monster strength.
    totalRank = (int)(floorRank*floorRank*DIFFICULTY_MULTIPLIER) + roomRank;
    if(totalRank < 1) {
      totalRank = 1;
    }
    roomDesc = "You've made it to room " + roomRank + " of floor " + floorRank + ". " + "A " + cleanliness[(int) (Math.random() * 9)] + " room with " + lights[(int) (Math.random() * 4)] + ". " + second[(int) (Math.random() * 4)];
    roomRank++;
  }

  //accessor methods: 
  public String getDesc() {
    return roomDesc;
  }

  public int getTotalRank() {
    return totalRank;
  }

  public String getRoomContents() {
    return "Nothing appears to be here...";
  }

  //mutator methods: 
  public void setDesc(String newDesc) {
   roomDesc = newDesc;
  }

  //for making sure roomRank doesn't surpass the amount of rooms per floor
  public static void resetRank() {
    roomRank = 1;
  }

  //basic runRoom method
  public void runRoom(Player player) {
    System.out.println(getRoomContents());
     System.out.println("You move on.");
  }
  
}
