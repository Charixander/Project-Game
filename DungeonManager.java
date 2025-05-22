public class DungeonManager {
  private Player player;
  private Floor[] floors;
  private int roomCount;
  private static boolean gameAlive = true;

  public DungeonManager(int floorCount, int roomCount) {
    floors = New Floor[5];
  }

  public static boolean getGameAlive() {
    return gameAlive;
  }

  public static void gameOver() {
    gameAlive = false;
  }
}
