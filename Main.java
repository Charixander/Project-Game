import java.util.Scanner;
/*
* Main Class.
* Sets up the Game
*/

public class Main {
  public static void main(String[] args) {

    //sets up player
    Scanner input = new Scanner(System.in);
    System.out.println("What's your name?");
    String action = input.nextLine();
    Player player = new Player(action);
    player.addInventory(new Consumable("Basic Health Potion", 10));
    player.addInventory(new Consumable("Basic Health Potion", 10));

    //sets up game size
    boolean flag = true;
    System.out.println("game size?");
    while(flag) {
      System.out.println("\n|| [ Standard ] || [ Custom ] ||\n");
      action = input.nextLine();
      if(action.toLowerCase().equals("standard")) {
        flag = false;
        DungeonManager dungeon = new DungeonManager(player);
        dungeon.runGame();

        //for a custom sized game
      } else if(action.toLowerCase().equals("custom")) {
        flag = false;
        System.out.println("How many floors?");
        int floors = input.nextInt();
        System.out.println("How many rooms?");
        int rooms = input.nextInt();
        DungeonManager customDungeon = new DungeonManager(player, floors, rooms);
        customDungeon.runGame();
    } else {
      System.out.println("Invalid input.");
    }
  }
  }
}
