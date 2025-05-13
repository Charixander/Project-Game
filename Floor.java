public class Floor {

  private static int floorLevel = 1;

  public static void nextLevel()
  {
    floorLevel++;
  }

  public static int getLevel()
  {
    return floorLevel;
  }
}
