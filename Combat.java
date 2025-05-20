import java.util.Scanner;

public class Combat {

  private Player player;
  private Enemy enemy;
  private Enemy enemy2;
  private Enemy enemy3;
  private int enemyCount;
  private boolean playerTurn;
  private Consumable playerEffect;

  public Combat (Player player, Enemy enemy) 
  {
    this.player = player;
    this.enemy = enemy;
    enemy2 = null;
    enemy3 = null;
    enemyCount = 1;
    playerTurn = true;
    playerEffect = null;
  }

  public Combat (Player player, Enemy enemy, Enemy enemy2)
  {
    this.player = player;
    this.enemy = enemy;
    this.enemy2 = enemy2;
    enemy3 = null;
    enemyCount = 2;
    playerTurn = true;
    playerEffect = null;
  }

  public Combat (Player player, Enemy enemy, Enemy enemy2, Enemy enemy3)
  {
    this.player = player;
    this.enemy = enemy;
    this.enemy2 = enemy2;
    this.enemy3 = enemy3;
    enemyCount = 3;
    playerTurn = true;
    playerEffect = null;
  }

  public void combatBegin()
  {
    System.out.println("\nCombat Sparked!\n");
    if (enemyCount == 1)
    {
      while (player.isAlive() && enemy.isAlive())
      {
        newTurn();
        playerTurn();
        if (enemy.isAlive())
        {
          enemyTurn(enemy);
        }
      }
      battleEnd();
    }
    else if (enemyCount == 2)
    {
      while (player.isAlive() && (enemy.isAlive() || enemy2.isAlive()))
      {
        newTurn();
        playerTurn();
        if (enemy.isAlive())
        {
          enemyTurn(enemy);
        }
        if (enemy2.isAlive())
        {
          enemyTurn(enemy2);
        }
      }
      battleEnd();
    }
    else 
    {
      while (player.isAlive() && (enemy.isAlive() || enemy2.isAlive() || enemy3.isAlive()))
      {
        newTurn();
        playerTurn();
        if (enemy.isAlive())
        {
          enemyTurn(enemy);
        }
        if (enemy2.isAlive())
        {
          enemyTurn(enemy2);
        }
        if (enemy3.isAlive())
        {
          enemyTurn(enemy3);
        }
      }
      battleEnd();
    }
  }

  public void playerTurn()
  {
    System.out.println("\nYour turn!\n\n");

    System.out.println(player.getName() + ": " + player.getHealth()+"/"+player.getStatHP() + " HP, " + player.getStatATK() + " ATK, " + player.getStatDEF() + " DEF\n");
    System.out.println(enemy.getName() + ": " + enemy.getHealth()+"/"+enemy.getStatHP() + " HP");
    if (enemy2 != null)
    {
      System.out.println(enemy2.getName() + ": " + enemy2.getHealth()+"/"+enemy2.getStatHP() + " HP");
    }
    if (enemy3 != null)
    {
      System.out.println(enemy3.getName() + ": " + enemy3.getHealth()+"/"+enemy3.getStatHP() + " HP");
    }

    if (playerEffect != null)
    {
      System.out.println("\n[" + playerEffect.getName() + " still in effect for " + playerEffect.turnsLeft() + " turns.]");
    }
    Scanner input = new Scanner(System.in);
    
    boolean flag = true;
    while (flag)
    {
      System.out.println("\n|| [ Attack ] || [ Item ] ||\n");
      String action = input.nextLine();

      if (action.toLowerCase().equals("attack"))
      {
        boolean attackCheck = true;
        while (attackCheck)
        {
          if (enemy2 == null)
          {
            System.out.println("\nYou hit " + enemy.getName() + " for " + enemy.takeDamage(player.getStatATK()) + " damage!\n");
            attackCheck = false;
            flag = false;
          }
          else 
          {
            String temp = "Enemies:\n";
            int value = 0;
            if (enemy.isAlive())
            {
              temp += "| " + enemy.getName() + " (1) | ";
            }
            else 
            {
              temp += "| [Deceased] (1) | ";
            }
            if (enemy2.isAlive())
            {
              temp += enemy2.getName() + " (2) | ";
            }
            else
            {
              temp += "[Deceased] (2) | ";
            }
            if (enemy3 != null)
            {
              if (enemy3.isAlive())
              {
                temp += enemy3.getName() + " (3) | ";
              }
              else
              {
                temp += "[Deceased (3) | ";
              }
            }
            temp += "\n(Type the enemy's number to attack them. Type back to return.)";
            System.out.println(temp);
            String n = input.nextLine();
            if (n.matches("-?\\d+"))
            {
              value = Integer.parseInt(n);
            }
            if (n.toLowerCase().equals("back"))
            {
              attackCheck = false;
            }
            else if (value == 1)
            {
              if (!(enemy.isAlive()))
              {
                System.out.println(enemy.getName() + " is already dead.\n");
              }
              else 
              {
                System.out.println("\nYou hit " + enemy.getName() + " for " + enemy.takeDamage(player.getStatATK()) + " damage!\n");
                attackCheck = false;
                flag = false;

                if (!(enemy.isAlive()))
                {
                  System.out.println(enemy.getName() + " has fallen.\n");
                }
              }
            }
            else if (value == 2)
            {
              if (!(enemy2.isAlive()))
              {
                System.out.println(enemy2.getName() + " is already dead.\n");
              }
              else 
              {
                System.out.println("\nYou hit " + enemy2.getName() + " for " + enemy2.takeDamage(player.getStatATK()) + " damage!\n");
                attackCheck = false;
                flag = false;

                if (!(enemy2.isAlive()))
                {
                  System.out.println(enemy2.getName() + " has fallen.\n");
                }
              }
            }
            else if (value == 3 && enemy3 != null)
            {
              if (!(enemy3.isAlive()))
              {
                System.out.println(enemy3.getName() + " is already dead.\n");
              }
              else 
              {
                System.out.println("\nYou hit " + enemy3.getName() + " for " + enemy3.takeDamage(player.getStatATK()) + " damage!\n");
                attackCheck = false;
                flag = false;

                if (!(enemy3.isAlive()))
                {
                  System.out.println(enemy3.getName() + " has fallen.\n");
                }
              }
            }
            else 
            {
              System.out.println("Invalid input.\n\n");
            }
          }
        }
      }
      else if (action.toLowerCase().equals("item"))
      {
        boolean invCheck = true;
        player.updateInventory();
        while (invCheck)
        {
          System.out.println("Inventory:\n" + player.openInventory() + "\n(Type the item's number to select it. Type back to return.)");
          String n = input.nextLine();
          int temp = 0;
          if (n.matches("-?\\d+"))
          {
            temp = Integer.parseInt(n);
          }
          if (n.toLowerCase().equals("back"))
          {
            invCheck = false;
          }
          else if (temp <= 10 && temp >= 1)
          {
            if (player.getInventory()[temp-1] == null)
            {
              System.out.println("There is nothing there.\n");
            }
            else
            {
              System.out.println("Do you want to use " + player.getInventory()[temp-1].getName() + "?\n[Yes/No]");
              String answer = input.nextLine().toLowerCase();
              if (answer.equals("y") || answer.equals("yes"))
              {
                System.out.println("\n");
                
                invCheck = false;
                flag = false;
                if (player.getInventory()[temp-1] instanceof Consumable)
                {
                  Consumable c = (Consumable) player.getInventory()[temp-1];
                  
                  c.consume(player);
                  if (player.getHealth() == player.getStatHP())
                  {
                    System.out.println(player.getName() + "'s HP fully restored!");
                  }
                  else 
                  {
                    System.out.println(player.getName() + " healed " + c.getHeal() + " HP!");
                  }
                  
                  if (c.turnsLeft() > 0)
                  {
                    playerEffect = c;
                  }

                  player.setInventory(null, temp);
                }
                else if (player.getInventory()[temp-1] instanceof Equippable)
                {
                  Equippable e = (Equippable) player.getInventory()[temp-1];
                  Equippable f = new Equippable();

                  if (e.isWeapon())
                  {
                    if (player.getWeapon() == null)
                    {
                      System.out.println(player.getName() + " equipped the " + e.getName());
                      player.setWeapon(e);
                      player.setInventory(null, temp);
                    }
                    else 
                    {
                      f = player.getWeapon();
                      System.out.println(player.getName() + " unequipped the " + f.getName());
                      System.out.println(player.getName() + " equipped the " + e.getName());
                      player.setWeapon(e);
                      player.setInventory(f, temp);
                    }
                  }
                  else
                  {
                    if (player.getArmor() == null)
                    {
                      System.out.println(player.getName() + " equipped the " + e.getName());
                      player.setArmor(e);
                      player.setInventory(null, temp);
                    }
                    else 
                    {
                      f = player.getArmor();
                      System.out.println(player.getName() + " unequipped the " + f.getName());
                      System.out.println(player.getName() + " equipped the " + e.getName());
                      player.setArmor(e);
                      player.setInventory(f, temp);
                    }
                  }
                }
              }
              else
              {
                System.out.println("\n");
              }
            }
          }
          else 
          {
            System.out.println("Invalid input.\n\n");
          }
        }
      }
      else
      {
        System.out.println("Invalid input. \n");
      }
    }
    input.close();
  }

  public void enemyTurn(Enemy e)
  {
    String name = e.getName();
    int randomizer = 0;
    boolean tempS = false;
    boolean tempO = false;
    if (e.canHealSelf())
    {
      tempS = true;
    }
    if (e.canHealOthers() && enemyCount >= 2)
    {
      tempO = true;
    }
    if (tempS && tempO)
    {
      randomizer = (int) (Math.random() * 2) + 1; // Either 1 or 2
    }
    else if (tempS)
    {
      randomizer = 1;
    }
    else if (tempO)
    {
      randomizer = 2;
    }

    if (randomizer == 1)
    {
      double value = 1.0 * e.getHealth() / e.getStatHP();
      if (value <= 0.1)
      {
        int rng = (int) (Math.random() * 2) + 1;
        if (rng == 2)
        {
          System.out.print("\n" + e.getName() + " heals " + e.healSelfAmount() + " HP!\n\n");
          e.healSelf();
        }
        else
        {
          System.out.print("\n" + name + " attacks you!\n");
          System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
        }
      }
      else if (value <= 0.5)
      {
        int rng = (int) (Math.random() * 6) + 1;
        if (rng == 6)
        {
          System.out.print("\n" + e.getName() + " heals " + e.healSelfAmount() + " HP!\n\n");
          e.healSelf();
        }
        else
        {
          System.out.print("\n" + name + " attacks you!\n");
          System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
        }
      }
      else
      {
        System.out.print("\n" + name + " attacks you!\n");
        System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
      }
    }
    else if (randomizer == 2)
    {
      if (e == enemy)
      {
        if (enemyCount == 2)
        {
          if (enemy2.isAlive())
          {
            healCheck(e, enemy2);
          }
          else
          {
            System.out.print("\n" + name + " attacks you!\n");
            System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
          }
        }
        else
        {
          if (enemy2.getHealth() > enemy3.getHealth())
          {
            if (enemy3.isAlive())
            {
              healCheck(e, enemy3);
            }
            else if (enemy2.isAlive())
            {
              healCheck(e, enemy2);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
          else
          {
            if (enemy2.isAlive())
            {
              healCheck(e, enemy2);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
        }
      }
      else if (e == enemy2)
      {
        if (enemyCount == 2)
        {
          if (enemy.isAlive())
          {
            healCheck(e, enemy);
          }
          else
          {
            System.out.print("\n" + name + " attacks you!\n");
            System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
          }
        }
        else
        {
          if (enemy.getHealth() > enemy3.getHealth())
          {
            if (enemy3.isAlive())
            {
              healCheck(e, enemy3);
            }
            else if (enemy.isAlive())
            {
              healCheck(e, enemy);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
          else
          {
            if (enemy.isAlive())
            {
              healCheck(e, enemy);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
        }
      }
      else if (e == enemy3 && enemyCount == 3)
      {
        if (enemy.getHealth() > enemy2.getHealth())
          {
            if (enemy2.isAlive())
            {
              healCheck(e, enemy2);
            }
            else if (enemy.isAlive())
            {
              healCheck(e, enemy);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
          else
          {
            if (enemy.isAlive())
            {
              healCheck(e, enemy);
            }
            else
            {
              System.out.print("\n" + name + " attacks you!\n");
              System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
            }
          }
      }
      else
      {
        System.out.print("\n" + name + " attacks you!\n");
        System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
      }
    }
    else
    {
      System.out.print("\n" + name + " attacks you!\n");
      System.out.println("You take " + player.takeDamage(e.getStatATK()) + " damage.\n");
    }
  }

  private void healCheck(Enemy healer, Enemy target)
  {
    double value = 1.0 * target.getHealth() / target.getStatHP();
    if (value <= 0.2)
    {
      int rng = (int) (Math.random() * 2) + 1;
      if (rng == 2)
      {
        System.out.print("\n" + healer.getName() + " heals " + target.getName() + " " + healer.healOtherAmount() + " HP!\n");
        healer.healOther(target);
      }
      else
      {
        System.out.print("\n" + healer.getName() + " attacks you!\n");
        System.out.println("You take " + player.takeDamage(healer.getStatATK()) + " damage.\n");
      }
    }
    else if (value <= 0.6)
    {
      int rng = (int) (Math.random() * 4) + 1;
      if (rng == 4)
      {
        System.out.print("\n" + healer.getName() + " heals " + target.getName() + " " + healer.healOtherAmount() + " HP!\n");
        healer.healOther(target);
      }
      else
      {
        System.out.print("\n" + healer.getName() + " attacks you!\n");
        System.out.println("You take " + player.takeDamage(healer.getStatATK()) + " damage.\n");
      }
    }
    else
    {
      System.out.print("\n" + healer.getName() + " attacks you!\n");
      System.out.println("You take " + player.takeDamage(healer.getStatATK()) + " damage.\n");
    }
  }

  public void newTurn()
  {
    if (playerEffect != null)
    {
      playerEffect.consume(player);
      System.out.println(playerEffect.getName() + " healed " + playerEffect.getHeal() + " HP");
      if (playerEffect.turnsLeft() < 1)
      {
        playerEffect = null;
      }
    }
  }

  public void battleEnd()
  {
    System.out.println("Battle Over.\n");
  }
  
}
