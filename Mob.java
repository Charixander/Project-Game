/*
*Superclass for different creatures including player and enemy
*basic info for a creature that will fight
*
*/


public class Mob {

  private String name;
  private int currentHealth;
  private int healthStat;
  private int attackStat;
  private int defenseStat;
  //keeps track of creature has died or not
  private boolean isAlive;
  private Equippable weapon;
  private Equippable armor;

  //constructors:
  public Mob ()
  {
    name = "John Doe";
    healthStat = 20;
    currentHealth = 20;
    attackStat = 1;
    defenseStat = 1;
    isAlive = true;
    weapon = null;
    armor = null;
  }
  
  public Mob (String name)
  {
     if (name.length() < 1)
    {
      this.name = "John Doe";
    }
    else
    {
      this.name = name;
    }

    healthStat = 20;
    currentHealth = 20;
    attackStat = 1;
    defenseStat = 1;
    isAlive = true;
    weapon = null;
    armor = null;
  }
  
  public Mob (String name, int healthStat, int attackStat, int defenseStat, Equippable weapon, Equippable armor)
  {
    if (name.length() < 1)
    {
      this.name = "John Doe";
    }
    else
    {
      this.name = name;
    }
    if (healthStat <= 0)
    {
      this.healthStat = 1;
      currentHealth = 1;
    }
    else
    {
      this.healthStat = healthStat;
      currentHealth = healthStat;
    }
    this.attackStat = attackStat;
    this.defenseStat = defenseStat;
    isAlive = true;
    this.weapon = weapon;
    this.armor = armor;
  }

  //set max HP of the mob.
  public void setHP(int amount)
  {
    healthStat = amount;
    if (healthStat < 1)
    {
      healthStat = 1;
    }
    if (currentHealth > healthStat)
    {
      currentHealth = healthStat;
    }
    else if (currentHealth < 1)
    {
      currentHealth = 1;
    }
  }

  //sets the current HP of a mob.
  public void setCurrentHP(int amount)
  {
    currentHealth = amount;
    if (currentHealth < 1)
    {
      currentHealth = 1;
    }
    if (currentHealth > healthStat)
    {
      currentHealth = healthStat;
    }
  }

  //mutator methods:
  public void setATK(int amount)
  {
    attackStat = amount;
  }

  public void setDEF(int amount)
  {
    defenseStat = amount;
  }

  //accessor methods:
  public String getName()
  {
    return name;
  }

  public int getHealth()
  {
    return currentHealth;
  }

  public int getStatHP()
  {
    return healthStat;
  }

  public int getStatATK()
  {
    if (weapon != null)
    {
      return attackStat + weapon.getAttack();
    }
    return attackStat;
  }

  public int getBaseATK()
  {
    return attackStat;
  }

  public int getStatDEF()
  {
    if (armor != null)
    {
      return defenseStat + armor.getDefense();
    }
    return defenseStat;
  }

  public int getBaseDEF()
  {
    return defenseStat;
  }

  public boolean isAlive()
  {
    return isAlive;
  }

  public Equippable getWeapon()
  {
    return weapon;
  }

  public Equippable getArmor()
  {
    return armor;
  }

  //accessor methods:
  public void setWeapon(Equippable weapon)
  {
    this.weapon = weapon;
  }

  public void setArmor(Equippable armor)
  {
    this.armor = armor;
  }

  //heals mob by amount
  public void addHealth(int amount) {
    currentHealth += amount;
    if (currentHealth > healthStat) {
      currentHealth = healthStat;
    }
  }

  //mob takes damage, damage mitigated by defense. 
  public int takeDamage(int amount)
  {
    int damage = amount;
    damage -= getStatDEF();
    if (damage <= 0)
    {
      damage = 1;
    }
    currentHealth -= damage;
    if (currentHealth <= 0)
    {
      currentHealth = 0;
      isAlive = false;
    }
    return damage;
  }

  //toString method for mob.
  public String toString() {
    return name + "\n HP: " + healthStat + "     ATK: " + attackStat + "     DEF: " + defenseStat;
  }
}
