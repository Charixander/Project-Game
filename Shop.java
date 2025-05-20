import java.util.Scanner;

public class Shop {

  /**
  * Handles the shop room allowing players to buy and sell items.
  * Handled with user interaction, inventory usage and price logic.
  */
  
  Item[] inventory = new Item[4];
  Player player;
  
  Scanner input = new Scanner(System.in);
  
  /**
  * Constructs a shop encounter.
  * @param player; the Player.
  * Pre: Player is not null
  * Post: Initializes a shop with an inventory to buy from containing one of each form of item.
  */
  public Shop(Player player) {
    this.player = player;
    createInventory();
    
  }

  /**
  * Constructs all of the items in teh shop inventory. 
  * Pre: Shop inventory array is initiallized.
  * Post: Shop inventory is initiallized with every kind of item with stats dependent on floor level.
  */
  public void createInventory() {
    inventory[0] = generateHealth();
    inventory[1] = generateRegen();
    inventory[2] = generateArmor();
    inventory[3] = generateWeapon();
  }

  /**
  * Begins the shop encounter. Run until either the user decides to leave. 
  * Pre: Run everytime the shop is entered.
  * Post: Allows player to buy items for money or sell items for money;
  */
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
            player.updateInventory();
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
  
  /**
  * Runs the buy option for the shop encounter.
  * @param interact; inventory index.
  * Pre: Called when buy is selected. 
  * Post: Player may buy items for money.
  */
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
  
  /**
  * Constructs a health potion for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The health potion is created and returned.
  */
  private Consumable generateHealth() {
    Consumable health = new Consumable("Health Potion", "A health potion.", Floor.getLevel(), 5, 1);
    return health;
  }
  
  /**
  * Constructs a regeneration potion for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The regeneration potion is created and returned.
  */
  private Consumable generateRegen() {
    Consumable regen = new Consumable("Regeneration Potion", "A regeneration potion.", Floor.getLevel(), 5, 4);
    return regen;
  }

  /**
  * Constructs armor for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The armor is created and returned.
  */
  private Equippable generateArmor() {
    Equippable armor = new Equippable("Armor", "A piece of armor.", Floor.getLevel(), 10, 5, false);
      return armor;
  }

  /**
  * Constructs a weapon for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The weapon is created and returned.
  */
  private Equippable generateWeapon() {
    Equippable weapon = new Equippable("Weapon", "A weapon.", Floor.getLevel(), 10, 5, true);
    return weapon;
  }

  /**
  * Creates the item list to buy in the shop.
  * Pre: Called when buy is selected. 
  * Post: List of items to buy is returned.
  */
  public String getInfo() {
    String info = "";
    for (int i = 0; i < inventory.length; i++) {
      info += "[" + (i + 1) + "]" + " | (" + inventory[i].getName() + ") |\n";
    }
    return info;
  }
}
