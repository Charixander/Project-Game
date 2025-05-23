//Creates and manages the entire game

public class DungeonManager {
  private Player player;
  private Floor[] floors;
  private Shop[] shops;
  private int roomCount;
  //gameAlive is to check the player is still alive and therefore the game is still going.
  private static boolean gameAlive = true;

  //Standard constructor with 5 floors and 5 rooms per floor.
  public DungeonManager(Player newPlayer) {
    player = newPlayer;
    floors = new Floor[5];
    shops = new Shop[4];
    for(int i = 0; i < floors.length-1; i++) {
      floors[i] = new Floor();
      shops[i] = new Shop(player, Floor.getLevel());
    }
    floors[floors.length-1] = new Floor(true);
  }

  //custom constructor for makin larger or smaller games.
  public DungeonManager(Player newPlayer, int floorCount, int roomCount) {
    player = newPlayer;
    floors = new Floor[floorCount];
    shops = new Shop[floorCount-1];
     for(int i = 0; i < floors.length-1; i++) {
      floors[i] = new Floor(roomCount);
      shops[i] = new Shop(player, Floor.getLevel());
    }
    floors[floors.length-1] = new Floor(roomCount, true);
  }

  //accessor method
  public static boolean getGameAlive() {
    return gameAlive;
  }

  //end game
  public static void gameOver() {
    gameAlive = false;
  }

  /*
  precondition: a contructor of DungeonManage has been run.
  postconditon: game is started.
  */
  public void runGame() {
    for(int i = 0; i < floors.length-1; i++) {
      floors[i].runFloor(player);
      //checking game isn't over, if it is stop immediately.
      if(DungeonManager.getGameAlive()) {
        shops[i].dialouge();
      } else {
        i = floors.length;
      }
    }
    //for final floor
    if(DungeonManager.getGameAlive()) {
        floors[floors.length-1].runFloor(player);
        if(DungeonManager.getGameAlive()) {
          System.out.println("YOU WIN!!!!!!");
        }
      }
  }
}
