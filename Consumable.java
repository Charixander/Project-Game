public class Consumable extends Item {

  private int healthRestore;
  private int turnDuration;

  public Consumable ()
  {
    healthRestore = 1;
    turnDuration = 1;
  }

  public Consumable (int hp)
  {
    healthRestore = hp;
    turnDuration = 1;
  }

  public Consumable (String name, int hp)
  {
    super(name);
    healthRestore = hp;
    turnDuration = 1;
  }

  public Consumable (String name, int hp, int turns)
  {
    super(name);
    healthRestore = hp;
    turnDuration = turns;
  }

  public Consumable (String name, String description, int level, int hp, int turns)
  {
    super(name, description, level);
    healthRestore = hp;
    turnDuration = turns;
    if (turnDuration < 1)
    {
      turnDuration = 1;
    }
  }

  public int getHeal()
  {
    return healthRestore;
  }

  public void consume(Player player)
  {
    player.addHealth(healthRestore);
    turnDuration--;
  }

  public int turnsLeft()
  {
    return turnDuration;
  }
  
}
