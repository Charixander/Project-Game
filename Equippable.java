public class Equippable extends Item {

  private int baseAttack;
  private int baseDefense;

  public Equippable () 
  {
    baseAttack = 0;
    baseDefense = 0;
  }

  public Equippable (int atk, int def)
  {
    baseAttack = atk;
    baseDefense = def;
  }

  public Equippable (String name, String description, int level, int atk, int def)
  {
    super(name, description, level);
    baseAttack = atk;
    baseDefense = def;
  }

  public int getAtk() {
    return baseAttack;
  }

  public int getDef() {
    return baseDefense;
  }

  
}
