//WIP (everything needs a price?)
import java.util.Scanner;

public class Shop extends Room {
  String roomDesc = "A shop for weary travelers.";
  Item[] inventory = new Item[5];
  
  public Shop(Player player) {
    super(roomRank);
    createInventory();
  }

  public void createInventory() {
    for (int i = 0; i < inventory.length; i++) {
      select = (int) (Math.random() * 4 + 1);
      if (select == 1) {
        inventory[i] = generateHealth();
      } else if (select == 2) {
        inventory[i] = generateRegen();
      } else if (select == 3) {
        inventory[i] = generateArmor();
      } else if (select == 2) {
        inventory[i] = generateWeapon();
      }
    }
  }

  public void dialouge() {
    boolean flag = true;

    while(flag) {
      System.out.println("Would you like to buy or sell?");
      System.out.println("\n|| [ Buy ] || [ Sell ] ||\n");
      String action = input.nextLine();

      if (action.toLowerCase().equals("buy")) {
        //select stuff from list
        System.out.println(getInfo());
        flag = false;
      } else if (action.toLowerCase().equals("sell")) {
        //select stuff from inventory
        flag = false;
      } else {
        System.out.println("Invalid input.\n\n");
        dialouge();
      }
    }
  }
  
  private Consumable generateHealth() {
    Consumable health = new Consumable(3 * roomRank());
    return health;
  }
  
  private Consumable generateRegen() {
    Consumable regen = new Consumable(2 * roomRank());
    return regen;
  }

  private Equippable generateArmor() {
    Equippable armor = new Equippable(5 * roomRank(), false);
      return armor;
  }

  private Equippable generateWeapon() {
    Equippable weapon = new Equippable(5 * roomRank(), true);
    return weapon;
  }

  public String getInventory() {
    String info = "";
    for (int i = 0; i < inventory.length; i++) {
      info += inventory[i] + " ";
    }
    return info;
  }
}
