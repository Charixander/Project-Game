public class Enemy extends Mob {

  //rewards for beating the enemy
  private int xpReward;
  private int moneyReward;
  //abilities to heal (if given the ability).
  private int healSelf;
  private int healOthers;

  //constructor
  public Enemy ()
  {
    xpReward = 0;
    moneyReward = 0;
    healSelf = 0;
    healOthers = 0;
  }

  //constructor
  public Enemy (String name)
  {
    super(name);
    xpReward = 0;
    moneyReward = 0;
    healSelf = 0;
    healOthers = 0;
  }

  //constructor
  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money)
  {
    super(name, HP, ATK, DEF, null, null);
    xpReward = xp;
    moneyReward = money;
    healSelf = 0;
    healOthers = 0;
  }

  //constructor
  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money, int healSelf, int healOthers)
  {
    super(name, HP, ATK, DEF, null, null);
    xpReward = xp;
    moneyReward = money;
    this.healSelf = healSelf;
    this.healOthers = healOthers;
  }

  //constructor
  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money, Equippable weapon, Equippable armor, int healSelf, int healOthers)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    xpReward = xp;
    moneyReward = money;
    this.healSelf = healSelf;
    this.healOthers = healOthers;
  }

  //accessor methods
  public int getXP()
  {
    return xpReward;
  }

  public int getMoney()
  {
    return moneyReward;
  }

  public boolean canHealSelf()
  {
    return healSelf > 0;
  }

  public boolean canHealOthers()
  {
    return healOthers > 0;
  }

  public int healSelfAmount()
  {
    return healSelf;
  }

  public int healOtherAmount()
  {
    return healOthers;
  }

  //methods for healing self/others
  public void healSelf()
  {
    super.addHealth(healSelf);
  }

  public void healOther(Enemy e)
  {
    e.addHealth(healOthers);
  }

  //toString method to return info about enemy
  public String toString() {
    return super.toString() + "\n" + " XP: " + xpReward + "      Gold: " +  moneyReward;
  }
}
