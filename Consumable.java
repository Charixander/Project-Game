public class Consumable extends Item {

  private int healthRestore;
  private int attackBoost;
  private int defenseBoost;
  private int turnDuration;

  public Consumable ()
  {
    healthRestore = 1;
    attackBoost = 0;
    defenseBoost = 0;
    turnDuration = 1;
  }

  public Consumable (int hp)
  {
    healthRestore = hp;
    attackBoost = 0;
    defenseBoost = 0;
    turnDuration = 1;
  }

  public Consumable (String name, String description, int level, int hp, int atk, int def, int turns)
  {
    super(name, description, level);
    healthRestore = hp;
    attackBoost = atk;
    defenseBoost = def;
    turnDuration = turns;
    if (turnDuration < 1)
    {
      turnDuration = 1;
    }
  }

  
}
