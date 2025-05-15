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

  public Player (String name, int HP, int ATK, int DEF, Equippable weapon, Equippable armor)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    money = 0;
  }
}
