public class Room {
  private static int roomRanks = 1;
  private int roomRank;

  public Room() {
    roomRank = roomRanks;
    roomRanks++;
  }

  public static void resetRanks() {
    roomRanks = 1;
  }

  public String getDesc() {
    return roomDesc
  }
  
}
