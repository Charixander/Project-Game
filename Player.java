public class Player extends Mob {

  private int level;
  private double xp;
  Item[] inventory = new Item[10];
  private Equippable armor;
  private Equippable weapon;
  private int money;

  public Player ()
  {
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    armor = null;
    weapon = null;
    money = 0;
  }

  public Player (String name)
  {
    super(name);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    armor = null;
    weapon = null;
    money = 0;
  }

  public Player (String name, int HP, int ATK, int DEF)
  {
    super(name, HP, ATK, DEF);
    level = 1;
    xp = 0.0;
    inventory = new Item[10];
    armor = null;
    weapon = null;
    money = 0;
  }
}
