public class Floor {
  private Room[] rooms;
  private static int floorLevel = 0;
  
  public Floor()  {
    floorLevel++;
    rooms = new Room[5];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(floorLevel);
      } else {
        rooms[i] = new CombatRoom(floorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(floorLevel, false);
  }

  public Floor(int RoomCount)  {
    floorLevel++;
    rooms = new Room[RoomCount];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(floorLevel);
      } else {
        rooms[i] = new CombatRoom(floorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(floorLevel, false);
  }

  public Floor(boolean isFinalFloor)  {
    floorLevel++;
    rooms = new Room[5];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(floorLevel);
      } else {
        rooms[i] = new CombatRoom(floorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(floorLevel, isFinalFloor);
  }

  public Floor(int RoomCount, boolean isFinalFloor)  {
    floorLevel++;
    rooms = new Room[RoomCount];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(floorLevel);
      } else {
        rooms[i] = new CombatRoom(floorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(floorLevel, isFinalFloor);
  }

  public String getRoomContents() {
    String contents = "";
    for(int i = 0; i < rooms.length; i++) {
      contents += "room " + (i+1) + ": \n";
      contents += rooms[i].getRoomContents() + "\n\n";
    }
    return contents;
  }  
  
  public static int getLevel()
  {
    return floorLevel;
  }
}
