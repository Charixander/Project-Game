import java.util.Scanner;

public class Shop {

  /**
  * Handles the shop room allowing players to buy and sell items.
  * Handled with user interaction, inventory usage and price logic.
  */
  
  Item[] inventory = new Item[4];
  Player player;
  int floorLevel;
  
  Scanner input = new Scanner(System.in);
  
  /**
  * Constructs a shop encounter.
  * @param player; the Player.
  * Pre: Player is not null
  * Post: Initializes a shop with an inventory to buy from containing one of each form of item.
  */
  public Shop(Player player, int floor) {
    floorLevel = floor;
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
    System.out.println("Between floors you enter a shop, behind the counter a strange but friendly giant octopus runs the shop. He makes a motion offering you to peruse his wares.");
    while(flag) {
      System.out.println("\n|| [ Buy ] || [ Sell ] || [ Leave ] || " + "$" + player.getMoney() + " ||\n");
      String action = input.nextLine();
      
      if (action.toLowerCase().equals("buy")) {
        System.out.println(getInfo());
        String n1 = input.nextLine();
          if (n1.matches("-?\\d+"))
          {
            pick = Integer.parseInt(n1);
          }
        if (pick > 0 && pick < 5) {
          int check = dialougeBuy(pick);
          if (check > 0) {
            player.updateInventory();
            System.out.println("Thank you for the purchase!");
          } else if (check < 0){
            System.out.println("You do not have enough money to buy this.");
          }
        } else {
            System.out.println("Invalid input.\n");
        }
      } else if (action.toLowerCase().equals("sell")) {
        System.out.println(player.openInventory());
        System.out.println("| Which slot would like to sell? |");
        String n2 = input.nextLine();
          if (n2.matches("-?\\d+"))
          {
            pick = Integer.parseInt(n2);
          }
        if (pick > 0 && pick < 11) {
          if (player.getInventory(pick - 1) == null) {
            System.out.println("Nothing to sell in this slot.\n");
          } else {
            player.setMoney((player.getInventory(pick - 1).getPrice()) / 2);
            System.out.println("Thanks for doing business! $" + player.getInventory(pick - 1).getPrice() / 2 + " returned.\n");
            player.setInventory(null, pick);
            player.updateInventory();
          }
        } else {
          System.out.println("Invalid input.\n");
        }
      } else if (action.toLowerCase().equals("leave")) {
        flag = false;
        System.out.println("The octopus waves goodbye as you descend into the lower floor...");
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
    if (player.getMoney() >= inventory[interact - 1].getPrice()) {
      player.addInventory(inventory[interact - 1]);
      player.setMoney(0 - (inventory[interact - 1].getPrice()));
      System.out.println(player.openInventory());
      if(interact == 3) {
        inventory[interact - 1] = generateArmor();
      } else if(interact == 4) {
        inventory[interact - 1] = generateWeapon();
      }
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
    Consumable health = new Consumable("Health Potion", "A health potion.", floorLevel, 15 * floorLevel, 1, floorLevel*5);
    health.setDescription(health.getDescription() + " Heals for " + health.getHeal() + " HP.");
    return health;
  }
  
  /**
  * Constructs a regeneration potion for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The regeneration potion is created and returned.
  */
  private Consumable generateRegen() {
    Consumable regen = new Consumable("Regeneration Potion", "A regeneration potion.", floorLevel, 5, floorLevel+3, 5 * floorLevel);
    regen.setDescription(regen.getDescription() + " Heals for 5 for " + (3 + floorLevel) + " turns.");
    return regen;
  }

  /**
  * Constructs armor for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The armor is created and returned.
  */
  private Equippable generateArmor() {
    int randomModifier = (int)(Math.random()*4) + 3;
    Equippable armor = new Equippable(Equippable.randomDescription() + " Armor", "A piece of armor.", floorLevel, randomModifier * floorLevel, false, randomModifier * 5 * floorLevel);
    armor.setDescription(armor.getDescription() + " Affects defense by " + armor.getDefense() + ".");  
    return armor;
  }

  /**
  * Constructs a weapon for the shop to sell.
  * Pre: Called when shop is created. 
  * Post: The weapon is created and returned.
  */
  private Equippable generateWeapon() {
    int randomModifier = (int)(Math.random()*5) + 4;
    Equippable weapon = new Equippable(Equippable.randomDescription() + " Sword", "A weapon.", floorLevel, randomModifier * floorLevel, true, randomModifier * 5 * floorLevel);
    weapon.setDescription(weapon.getDescription() + " Affects attack by " + weapon.getAttack() + ".");  
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
      if (inventory[i] == null) {
        info += "[" + (i + 1) + "]" + " | (" + "N/A" + " || $" + inventory[i].getPrice() + " )" + " |\n";
      } else {
        info += "[" + (i + 1) + "]" + " | (" + inventory[i].getName() + " || " + inventory[i].getDescription() + " || $" + inventory[i].getPrice() + " )" + " |\n";
      }
    }
    return info;
  }
}
