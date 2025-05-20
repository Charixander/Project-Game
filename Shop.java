import java.util.Scanner;

public class Shop {
  String shopDesc = "A shop for weary travelers.";
  Item[] inventory = new Item[4];
  Player player;

  Scanner input = new Scanner(System.in);
  
  public Shop(Player player) {
    this.player = player;
    createInventory();
    
  }

  public void createInventory() {
    inventory[0] = generateHealth();
    inventory[1] = generateRegen();
    inventory[2] = generateArmor();
    inventory[3] = generateWeapon();
  }

  public void dialouge() {
    boolean flag = true;
    int pick = 0;
    while(flag) {
      System.out.println("\n|| [ Buy ] || [ Sell ] || [ Leave ] ||\n");   
      String action = input.nextLine();

      if (action.toLowerCase().equals("buy")) {
        System.out.println(getInfo());
        pick = Integer.parseInt(input.nextLine());
        if (pick > 0 && pick < 5) {
          if (dialougeBuy(pick) > 0) {
            flag = false;
          } else {
            System.out.println("You do not have enough money to buy this.");
          }
        } else {
            System.out.println("Invalid input.\n");
        }
      } else if (action.toLowerCase().equals("sell")) {
        System.out.println(player.openInventory());
        System.out.println("| Which slot would like to sell? |");
        pick = Integer.parseInt(input.nextLine());
        if (pick > 0 && pick < 11) {
          if (player.getInventory(pick - 1) == null) {
            System.out.println("Nothing to sell in this slot.\n");
          } else {
            player.setMoney((player.getInventory(pick - 1).getPrice()) / 2);
            player.setInventory(null, pick);
            flag = false;
          }
          
        } else {
          System.out.println("Invalid input.\n");
        }
      } else if (action.toLowerCase().equals("leave")) {
        flag = false;
      } else {
        System.out.println("Invalid input.\n");
      }
    }
  }

  public int dialougeBuy(int interact) {
    if (player.getMoney() > inventory[interact - 1].getPrice()) {
      System.out.println(player.openInventory());
      System.out.println("Which slot will you choose to replace?");
      player.setInventory(inventory[interact - 1], input.nextInt());
      player.setMoney(0 - (inventory[interact - 1].getPrice()));
      inventory[interact - 1] = null;
      return 1;
    } else {
      return -1;
    }
  }
  
  private Consumable generateHealth() {
    Consumable health = new Consumable("Health Potion", "A health potion.", Floor.getLevel(), 5, 1);
    return health;
  }
  
  private Consumable generateRegen() {
    Consumable regen = new Consumable("Regeneration Potion", "A regeneration potion.", Floor.getLevel(), 5, 4);
    return regen;
  }

  private Equippable generateArmor() {
    Equippable armor = new Equippable("Armor", "A piece of armor.", Floor.getLevel(), 10, 5, false);
      return armor;
  }

  private Equippable generateWeapon() {
    Equippable weapon = new Equippable("Weapon", "A weapon.", Floor.getLevel(), 10, 5, true);
    return weapon;
  }

  public String getInfo() {
    String info = "";
    for (int i = 0; i < inventory.length; i++) {
      info += "[" + (i + 1) + "]" + " | (" + inventory[i].getName() + ") |\n";
    }
    return info;
  }
}
