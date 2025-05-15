public class Enemy extends Mob {

  private int xpReward;
  private int moneyReward;

  public Enemy ()
  {
    xpReward = 0;
    moneyReward = 0;
  }

  public Enemy (String name)
  {
    super(name);
    xpReward = 0;
    moneyReward = 0;
  }

  public Enemy (String name, int HP, int ATK, int DEF, Equippable weapon, Equippable armor)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    xpReward = 0;
    moneyReward = 0;
    weapon = null;
    armor = null;
  }

  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money, Equippable weapon, Equippable armor)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    xpReward = xp;
    moneyReward = money;
  }
  
}
