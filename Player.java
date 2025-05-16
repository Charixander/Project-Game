public class Player extends Mob {

  private int level;
  private double xp;
  Item[] inventory = new Item[10];
  private int money;

  public Player ()
  {
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name)
  {
    super(name);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name, int HP, int ATK, int DEF)
  {
    super(name, HP, ATK, DEF, null, null);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name, int HP, int ATK, int DEF, Equippable weapon, Equippable armor)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    money = 0;
  }

  public Item[] getInventory()
  {
    return inventory;
  }

  public void setInventory(Item item, int index)
  {
    inventory[index-1] = item;
  }

  public void updateInventory()
  {
    Item[] newInventory = new Item[inventory.length];

    int index = 0;

    for (int i = 0; i < inventory.length; i++)
    {
      if (inventory[i] != null)
      {
        newInventory[index] = inventory[i];
        index++;
      }
    }

    inventory = newInventory;
  }

  public String openInventory()
  {
    String text = "";
    for (int i = 0; i < 5; i++)
    {
      if (inventory[i] == null)
      {
        text += "| N/A (" + (i+1) + ")|";
      }
      else 
      {
        text += "| " + inventory[i].getName() + "(" + (i+1) + ") |";
      }
    }
    text += "\n";
    for (int j = 5; j < 10; j++)
    {
      if (inventory[j] == null)
      {
        text += "| N/A (" + (j+1) + ")|";
      }
      else 
      {
        text += "| " + inventory[j].getName() + "(" + (j+1) + ") |";
      }
    }
    return text;
  }
}
