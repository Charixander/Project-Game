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
    boolean flag = false;
    int currentLv = level;
    xp += num;
    while (xp > xpCap)
    {
      flag = true;
      xp -= xpCap;
      levelUp();
    }
    if (flag)
    {
      System.out.println("You leveled up!\n" + (currentLv) + " --> " + level + "\n");
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
        break;
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
    updateInventory();
    String text = "";
    for (int i = 0; i < 5; i++)
    {
      if (inventory[i] == null)
      {
        text += "| N/A (" + (i+1) + ")|";
      }
      else 
      {
        text += "| " + inventory[i].getName() + " (" + (i+1) + ") |";
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

  public String openStats()
  {
    String text = "";
    text += super.getName() + "'s Info\n\n";
    text += "LV " + level + " [" + xp + "/" + xpCap + "]\n";
    text += "$" + money + "\n";
    text += "HP: " + super.getHealth() + "/" + super.getStatHP() + "\n";
    text += "ATK: " + super.getStatATK();
    if (super.getWeapon() != null)
    {
      text += " ("+ super.getBaseATK() + " +" + super.getWeapon().getAttack() + ")\n";
    }
    else
    {
      text += "\n";
    }
    text += "DEF: " + super.getStatDEF();
    if (super.getArmor() != null)
    {
      text += " ("+ super.getBaseDEF() + " +" + super.getArmor().getDefense() + ")\n\n";
    }
    else
    {
      text += "\n\n";
    }
    if (super.getWeapon() != null)
    {
      text += "Weapon: " + super.getWeapon().getName() + "\n";
    }
    else
    {
      text += "Weapon: N/A\n";
    }
    if (super.getArmor() != null)
    {
      text += "Armor: " + super.getArmor().getName() + "\n";
    }
    else
    {
      text += "Armor: N/A\n";
    }

    return text;
  }

  public void displayHud()
  {
    System.out.println(openStats());
    System.out.println(openInventory());
  }

  public void hud()
  {
    Scanner input = new Scanner(System.in);
    
    while (true)
    {
      displayHud();
      System.out.println("Type the number value to select an inventory item.");
      System.out.println("Type 'armor' or 'weapon' to select it.");
      System.out.println("Type 'back' to exit.");
      int n = -1;
      String temp = input.nextLine();
      if (temp.matches("-?\\d+"))
      {
        n = Integer.parseInt(temp);
      }
      if (temp.toLowerCase().equals("back"))
      {
        break;
      }
      else if (temp.toLowerCase().equals("weapon"))
      {
        if (super.getWeapon() == null)
        {
          System.out.println("No weapon is equipped.");
        }
        else
        {
          boolean flagA = true;
          while (flagA)
          {
            System.out.println("\n[Info]  [Unequip]  [Discard]  [Back]\n");
            temp = input.nextLine();
            System.out.println("");
            if (temp.toLowerCase().equals("info"))
            {
              System.out.println(super.getWeapon().info());
            }
            else if (temp.toLowerCase().equals("unequip"))
            {
              if (checkInventory())
              {
                Equippable w = super.getWeapon();
                super.setWeapon(null);
                addInventory(w);
                flagA = false;
              }
              else
              {
                System.out.println("There's no space in your inventory.");
              }
            }
            else if (temp.toLowerCase().equals("discard"))
            {
              System.out.println("You discarded the " + super.getWeapon().getName());
              super.setWeapon(null);
              flagA = false;
            }
            else if (temp.toLowerCase().equals("back"))
            {
              flagA = false;
            }
            else
            {
              System.out.println("Invalid input!");
            }
          }
        }
      }
      else if (temp.toLowerCase().equals("armor"))
      {
        if (super.getArmor() == null)
        {
          System.out.println("No armor is equipped.");
        }
        else
        {
          boolean flagA = true;
          while (flagA)
          {
            System.out.println("\n[Info]  [Unequip]  [Discard]  [Back]\n");
            temp = input.nextLine();
            System.out.println("");
            if (temp.toLowerCase().equals("info"))
            {
              System.out.println(super.getArmor().info());
            }
            else if (temp.toLowerCase().equals("unequip"))
            {
              if (checkInventory())
              {
                Equippable a = super.getArmor();
                super.setArmor(null);
                addInventory(a);
                flagA = false;
              }
              else
              {
                System.out.println("There's no space in your inventory.");
              }
            }
            else if (temp.toLowerCase().equals("discard"))
            {
              System.out.println("You discarded the " + super.getArmor().getName());
              super.setArmor(null);
              flagA = false;
            }
            else if (temp.toLowerCase().equals("back"))
            {
              flagA = false;
            }
            else
            {
              System.out.println("Invalid input!");
            }
          }
        }
      }
      else if (n >= 1 && n <= 10)
      {
        System.out.println("\nYou selected " + inventory[n-1].getName() + ".");
        boolean flagA = true;
        while (flagA)
        {
          System.out.println("\n[Info]  [Use]  [Discard]  [Back]\n");
          temp = input.nextLine();
          System.out.println("");
          if (temp.toLowerCase().equals("info"))
          {
            System.out.println(inventory[n-1].info());
          }
          else if (temp.toLowerCase().equals("use"))
          {
            if (inventory[n-1] instanceof Consumable)
            {
              Consumable c = (Consumable) inventory[n-1];
              System.out.println("You use the " + inventory[n-1].getName());
              super.addHealth(c.getHeal());
              inventory[n-1] = null;
              flagA = false;
            }
            else 
            {
              Equippable e = (Equippable) inventory[n-1];
              System.out.println("You equip the " + inventory[n-1].getName());
              if (e.isWeapon())
              {
                if (super.getWeapon() == null)
                {
                  super.setWeapon(e);
                  inventory[n-1] = null;
                  flagA = false;
                }
                else
                {
                  System.out.println("You unequip the " + super.getWeapon().getName());
                  Equippable f = super.getWeapon();
                  super.setWeapon(e);
                  inventory[n-1] = null;
                  addInventory(f);
                  flagA = false;
                }
              }
              else
              {
                if (super.getArmor() == null)
                {
                  super.setArmor(e);
                  inventory[n-1] = null;
                  flagA = false;
                }
                else
                {
                  System.out.println("You unequip the " + super.getArmor().getName());
                  Equippable f = super.getArmor();
                  super.setArmor(e);
                  inventory[n-1] = null;
                  addInventory(f);
                  flagA = false;
                }
              }
            }
          }
          else if (temp.toLowerCase().equals("discard"))
          {
            System.out.println("You discarded the " + inventory[n-1].getName());
            setInventory(null, n);
            flagA = false;
          }
          else if (temp.toLowerCase().equals("back"))
          {
            flagA = false;
          }
          else
          {
            System.out.println("Invalid input!\n");
          }
        }
      }
      else 
      {
        System.out.println("Invalid input!\n");
      }
    }
    input.close();
  }
}
