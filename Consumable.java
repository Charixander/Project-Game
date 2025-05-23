/*
* Consumable for health potions
*/


public class Consumable extends Item {

  //player will heal healthRestore amount every turn for turnDuration amount of turns.
  private int healthRestore;
  private int turnDuration;

  //constructors:
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

  public Consumable (String name, String description, int level, int hp, int turns, int price)
  {
    super(name, description, level, price);
    healthRestore = hp;
    turnDuration = turns;
    if (turnDuration < 1)
    {
      turnDuration = 1;
    }
  }

  //accesor method
  public int getHeal()
  {
    return healthRestore;
  }

  //consumes the item
  public void consume(Player player)
  {
    player.addHealth(healthRestore);
    turnDuration--;
  }

  //accesor method
  public int turnsLeft()
  {
    return turnDuration;
  }
  
}

  //for getting info on consumable
  public String info()
  {
    String text = super.info();
    text += "\n\nHealing : " + healthRestore + "\n";
    if (turnDuration > 1)
    {
      text += "Regeneration Turn Time: " + (turnDuration-1) + "\n";
    }

    text += "\n Value Worth: $ " + super.getPrice() + "\n\n";

    return text;
  }
  
}
