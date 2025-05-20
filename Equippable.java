public class Equippable extends Item {

  private int baseAttack;
  private int baseDefense;
  private boolean isWeapon;

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

  public Equippable (String name, String description, int level, int stat, boolean isWeapon)
  {
    super(name, description, level);
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

  public Equippable (String name, String description, int level, int price, int stat, boolean isWeapon)
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

  public int getAttack()
  {
    return baseAttack;
  }

  public int getDefense()
  {
    return baseDefense;
  }

  public boolean isWeapon()
  {
    return isWeapon;
  }

  public String info()
  {
    String text = super.info();
    text += "\n\n";
    if (isWeapon)
    {
      text += "Increases ATK by " + baseAttack;
    }
    else 
    {
      text += "Increases DEF by " + baseDefense;
    }

    return text;
  }
  
}
