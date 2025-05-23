/*
* Game is composed of a series of floors.
* Floors are composed of a series of Rooms.
* Most rooms are CombatRooms with some TreasureRooms
* Last room is always BoosRoom.
*/


public class Floor {
  //the array of rooms.
  private Room[] rooms;
  /*keeps track of current floor.
  * Needed for scaling enemies and loot properly
  */
  private static int floorLevel = 0;
  private int currentFloorLevel;


  //constructors
  public Floor()  {
    floorLevel++;
    currentFloorLevel = floorLevel;
    rooms = new Room[5];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(currentFloorLevel);
      } else {
        rooms[i] = new CombatRoom(currentFloorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(currentFloorLevel, false);
  }

  public Floor(int RoomCount)  {
    floorLevel++;
    currentFloorLevel = floorLevel;
    rooms = new Room[RoomCount];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(currentFloorLevel);
      } else {
        rooms[i] = new CombatRoom(currentFloorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(currentFloorLevel, false);
  }

  public Floor(boolean isFinalFloor)  {
    floorLevel++;
    currentFloorLevel = floorLevel;
    rooms = new Room[5];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(currentFloorLevel);
      } else {
        rooms[i] = new CombatRoom(currentFloorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(currentFloorLevel, isFinalFloor);
  }

  public Floor(int RoomCount, boolean isFinalFloor)  {
    floorLevel++;
    currentFloorLevel = floorLevel;
    rooms = new Room[RoomCount];
    Room.resetRank();
    int random = 0;
    for(int i = 0; i < rooms.length-1; i++) {
      random = (int)(Math.random()*10);
      if(random <= 1) {
        rooms[i] = new TreasureRoom(currentFloorLevel);
      } else {
        rooms[i] = new CombatRoom(currentFloorLevel);
      }
    }
    rooms[rooms.length-1] = new BossRoom(currentFloorLevel, isFinalFloor);
  }

  /*prints the contents of each room.
  * Not needed in game but useful for checking contents and checking floors.
  */
  public String getRoomContents() {
    String contents = "";
    for(int i = 0; i < rooms.length; i++) {
      contents += "room " + (i+1) + ": \n";
      contents += rooms[i].getRoomContents() + "\n\n";
    }
    return contents;
  }

  //runs floor by running all the rooms in floor.
  public void runFloor(Player player) {
    for(int i = 0; i < rooms.length; i++) {
      if(DungeonManager.getGameAlive()) {
      rooms[i].runRoom(player);
      } else {
        i = rooms.length;
      }
    }
  }
  
  //accessor method for the floor level.
  public static int getLevel()
  {
    return floorLevel;
  }
}
