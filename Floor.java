public class Floor {
  private Room[] rooms;
  private static int floorLevel = 1;
  
  public Floor()  {
    rooms = new Room[10];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        System.out.print("Need to add TreasureRoom");
      } else {
        rooms[i] = new CombatRoom(floorLevel);
      }
    }
    floorLevel++;
  }

  public static int getLevel()
  {
    return floorLevel;
  }
}
