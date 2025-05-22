public class Enemy extends Mob {

  private int xpReward;
  private int moneyReward;
  private int healSelf;
  private int healOthers;

  public Enemy ()
  {
    xpReward = 0;
    moneyReward = 0;
    healSelf = 0;
    healOthers = 0;
  }

  public Enemy (String name)
  {
    super(name);
    xpReward = 0;
    moneyReward = 0;
    healSelf = 0;
    healOthers = 0;
  }

  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money)
  {
    super(name, HP, ATK, DEF, null, null);
    xpReward = xp;
    moneyReward = money;
    healSelf = 0;
    healOthers = 0;
  }

  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money, int healSelf, int healOthers)
  {
    super(name, HP, ATK, DEF, null, null);
    xpReward = xp;
    moneyReward = money;
    this.healSelf = healSelf;
    this.healOthers = healOthers;
  }

  public Enemy (String name, int HP, int ATK, int DEF, int xp, int money, Equippable weapon, Equippable armor, int healSelf, int healOthers)
  {
    super(name, HP, ATK, DEF, weapon, armor);
    xpReward = xp;
    moneyReward = money;
    this.healSelf = healSelf;
    this.healOthers = healOthers;
  }

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

  public void healSelf()
  {
    super.addHealth(healSelf);
  }

  public void healOther(Enemy e)
  {
    e.addHealth(healOthers);
  }

  public String toString() {
    return super.toString() + "\n" + " XP: " + xpReward + "      Gold: " +  moneyReward;
  }
}
