public class Mob {

  private String name;
  private int currentHealth;
  private int healthStat;
  private int attackStat;
  private int defenseStat;
  private boolean isAlive;

  public Mob ()
  {
    name = "John Doe";
    healthStat = 20;
    currentHealth = 20;
    attackStat = 1;
    defenseStat = 1;
    isAlive = true;
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
  }
  
  public Mob (String name, int healthStat, int attackStat, int defenseStat)
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
  }

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
    return attackStat;
  }

  public int getStatDEF()
  {
    return defenseStat;
  }

  public boolean getLife()
  {
    return isAlive;
  }

  public void takeDamage(int amount)
  {
    currentHealth -= amount;
    if (currentHealth <= 0)
    {
      currentHealth = 0;
      isAlive = false;
    }
  }

  
}
