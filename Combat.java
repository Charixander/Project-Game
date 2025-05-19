import java.util.Scanner;

public class Combat {

  private Player player;
  private Enemy enemy;
  private boolean playerTurn;
  private Consumable playerEffect;

  public Combat (Player player, Enemy enemy) 
  {
    this.player = player;
    this.enemy = enemy;
    playerTurn = true;
    playerEffect = null;
  }

  public void combatBegin()
  {
    System.out.println("\nCombat Sparked!\n");
    while (player.isAlive() && enemy.isAlive())
    {
      newTurn();
      playerTurn();
      if (enemy.isAlive())
      {
        enemyTurn();
      }
    }
    battleEnd();
  }

  public void playerTurn()
  {
    System.out.println("\nYour turn!\n\n");

    System.out.println(player.getName() + ": " + player.getHealth()+"/"+player.getStatHP() + " HP, " + player.getStatATK() + " ATK, " + player.getStatDEF() + " DEF");
    System.out.println(enemy.getName() + ": " + enemy.getHealth()+"/"+enemy.getStatHP() + " HP");

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
        System.out.println("\nYou hit " + enemy.getName() + " for " + enemy.takeDamage(player.getStatATK()) + " damage!\n");
        flag = false;
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
  }

  public void enemyTurn()
  {
    String name = enemy.getName();

    System.out.print("\n" + name + " attacks you!\n");
    System.out.println("You take " + player.takeDamage(enemy.getStatATK()) + " damage.\n");
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
    System.out.println("\nBattle Over.");
  }
  
}
