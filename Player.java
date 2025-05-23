import java.util.Scanner;

public class Player extends Mob {

  private int level;
  private int xp;
  private int xpCap;
  Item[] inventory = new Item[10];
  private int money;

  public Player ()
  {
    level = 1;
    xp = 0;
    xpCap = 100;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name)
  {
    super(name);
    level = 1;
    xp = 0;
    xpCap = 100;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name, int HP, int ATK, int DEF)
  {
    super(name, HP, ATK, DEF, null, null);
    level = 1;
    xp = 0;
    xpCap = 100;
    inventory = new Item[10];
    money = 0;
  }

  public Player (String name, int HP, int ATK, int DEF, Equippable weapon, Equippable armor)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    level = 1;
    xp = 0;
    xpCap = 100;
    inventory = new Item[10];
    money = 0;
  }

  public int getMoney()
  {
    return money;
  }

  public void setMoney(int num) 
  {
    money += num;
  }

  public int getXP()
  {
    return xp;
  }

  public void setXP(int num)
  {
    xp += num;
    while (xp > xpCap)
    {
      xp -= xpCap;
      levelUp();
    }
  }

  public int getMaxXP()
  {
    return xpCap;
  }

  public int getLevel()
  {
    return level;
  }
  
  public void levelUp()
  {
    level++;
    xpCap *= 1.2;
    double temp = super.getStatHP() * 1.2;
    super.setHP((int) temp);
    temp = super.getHealth() * 1.1;
    super.setCurrentHP((int) temp);
    temp = super.getBaseATK() * 1.2;
    super.setATK((int) temp);
    temp = super.getBaseDEF() * 1.2;
    super.setDEF((int) temp);

    System.out.println("You leveled up!\n" + (level-1) + " --> " + level + "\n");
  }

  public boolean checkInventory()
  {
    boolean flag = false;
    updateInventory();
    for (int i = 0; i < inventory.length; i++)
    {
      if (inventory[i] == null)
      {
        flag = true;
        break;
      }
    }
    return flag;
  }

  private void replaceInventory(Item item)
  {
    Scanner input = new Scanner(System.in);
    updateInventory();

    while (true)
    {
      System.out.println("\n" + openInventory() + "\nInsert the number value for what item you wish to replace with " + item.getName() + ".");
      System.out.println("Type 'cancel' or 'back' to exit out.\n");
      int n = -1;
      String temp = input.nextLine();
      if (temp.matches("-?\\d+"))
      {
        n = Integer.parseInt(temp);
      }
      if (temp.toLowerCase().equals("cancel") || temp.toLowerCase().equals("back"))
      {
        System.out.println("\nYou discarded " + item.getName() + ".\n");
        break;
      }
      else if (n >= 1 && n <= 10)
      {
        System.out.println("\nAre you sure you want to replace " + inventory[n-1].getName() + "?");
        System.out.println("(Yes/No)");
        temp = input.nextLine();
        if (temp.equals("y") || temp.equals("yes"))
        {
          System.out.println("\n" + item.getName() + " was added to your inventory.");
          System.out.println(inventory[n-1].getName() + " was discarded.\n");
          setInventory(item, n);
          break;
        }
        else
        {
          
        }
      }
      else 
      {
        System.out.println("Invalid input!\n");
      }
    }
    input.close();
    
  }
  
  public void addInventory(Item item)
  {
    updateInventory();
    boolean flag = true;
    for (int i = 0; i < inventory.length; i++)
    {
      if (inventory[i] == null)
      {
        inventory[i] = item;
        flag = false;
      }
    }
    if (flag)
    {
      replaceInventory(item);
    }
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
