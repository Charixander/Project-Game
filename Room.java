public class Room {
  private static int roomRanks = 1;
  private int roomRank;
  private String[] cleanliness = {"musty", "rancid", "clean", "spotless", "decayed", "moldy", "gross", "boring", "golden"};
  private String[] second = {"You can barely see ahead of you.", "The buzzing of lights dig into your head", "The janitor must've missed a spot.", "Have people been here before?"};
  private String[] lights = {"flickering candlelight", "office lights", "a glowing bonfire", "spots of sunlight scattered from the ceiling"};
  private String roomDesc;
  
  
  public Room() {
    roomRank = roomRanks;
    roomRanks++;
    roomDesc = "A " + cleanliness[(int) (Math.random() * 9)] + " room with " + lights[(int) (Math.random() * 4)] + ". " + second[(int) (Math.random() * 4)];
  }

  public static void resetRanks() {
    roomRanks = 1;
  }

  public String getDesc() {
    return roomDesc;
  }
  
}
