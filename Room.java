import java.util.Scanner;

public class Room {
  private static int roomRank = 1;
  private static final double DIFFICULTY_MULTIPLIER = 0.5;
  private int totalRank;
  private String[] cleanliness = {"musty", "rancid", "clean", "spotless", "decayed", "moldy", "gross", "boring", "golden"};
  private String[] second = {"You can barely see ahead of you.", "The buzzing of lights dig into your head", "The janitor must've missed a spot.", "Have people been here before?"};
  private String[] lights = {"flickering candlelight", "office lights", "a glowing bonfire", "spots of sunlight scattered from the ceiling"};
  private String roomDesc;
  
  
  public Room(int floorRank) {
    totalRank = (int)(floorRank*roomRank*DIFFICULTY_MULTIPLIER);
    if(totalRank < 1) {
      totalRank = 1;
    }
    roomRank++;
    roomDesc = "A " + cleanliness[(int) (Math.random() * 9)] + " room with " + lights[(int) (Math.random() * 4)] + ". " + second[(int) (Math.random() * 4)];
  }

  public String getDesc() {
    return roomDesc;
  }

  public int getTotalRank() {
    return totalRank;
  }

  public static void resetRank() {
    roomRank = 1;
  }

  public String getRoomContents() {
    return "Nothing appears to be here...";
  }
  
}
