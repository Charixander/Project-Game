/*
* Equippable is for items the player can equip
* Will either affect attack or defense depending on if it's a weapon or not
*/

public class Equippable extends Item {

  private int baseAttack;
  private int baseDefense;
  private boolean isWeapon;
  //for giving items custom names;
  private static String[] ItemDescriptions = {"Flamboyant", "Peculiar", "Shiny", "Massive", "Colorful", "Fancy", "Epic", "Demonic", "Worn Out", "Interesting", "Monster", "Suspicious",
                                             "Super", "Dense", "Weird Tasting", "Light", "Squishy"};

  //constructors
  public Equippable () 
  {
    baseAttack = 1;
    baseDefense = 0;
    isWeapon = true;
  }

  public Equippable (int stat, boolean isWeapon)
  {
    this.isWeapon = isWeapon;
    if (isWeapon)
    {
      baseAttack = stat;
      baseDefense = 0;
    }
    else 
    {
      baseAttack = 0;
      baseDefense = stat;
    }
  }

  public Equippable (String name, int stat, boolean isWeapon)
  {
    super(name);
    this.isWeapon = isWeapon;
    if (isWeapon)
    {
      baseAttack = stat;
      baseDefense = 0;
    }
    else 
    {
      baseAttack = 0;
      baseDefense = stat;
    }
  }

  public Equippable (String name, int stat, boolean isWeapon, int level)
  {
    super(name, "an item", level);
    this.isWeapon = isWeapon;
    if (isWeapon)
    {
      baseAttack = stat;
      baseDefense = 0;
    }
    else 
    {
      baseAttack = 0;
      baseDefense = stat;
    }
  }

  public Equippable (String name, String description, int level, int stat, boolean isWeapon, int price)
  {
    super(name, description, level, price);
    this.isWeapon = isWeapon;
    if (isWeapon)
    {
      baseAttack = stat;
      baseDefense = 0;
    }
    else 
    {
      baseAttack = 0;
      baseDefense = stat;
    }
  }

  //for giving weapons/armor descriptions
  public static String randomDescription() {
    int random = (int)(Math.random()*ItemDescriptions.length);
    return ItemDescriptions[random];
  }

  //accesor methods
  public int getAttack()
  {
    int value = baseAttack;
    value *= super.getLevel();
    return value;
  }

  public int getDefense()
  {
    int value = baseDefense;
    value *= super.getLevel();
    return value;
  }

  public boolean isWeapon()
  {
    return isWeapon;
  }

  //prints info about the item
  public String info()
  {
    String text = super.info();
    text += "\n\n";
    if (isWeapon)
    {
      text += "Attack Power: " + baseAttack;
    }
    else 
    {
      text += "Defense Power: " + baseDefense;
    }

    return text;
  }
  
}
