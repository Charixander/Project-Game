import java.util.Scanner;

public class Combat {

  private Player player;
  private Enemy enemy;
  private boolean playerTurn;

  public Combat (Player player, Enemy enemy) 
  {
    this.player = player;
    this.enemy = enemy;
    playerTurn = true;
  }

  public void combatBegin()
  {
    System.out.println("\nCombat Sparked!\n");
    playerTurn();
    enemyTurn();
  }

  public void playerTurn()
  {
    System.out.println("\nYour turn!\n\n");

    Scanner input = new Scanner(System.in);
    
    boolean flag = true;
    while (flag)
    {
      System.out.println("|| [ Attack ] || [ Item ] ||\n");
      String action = input.nextLine();

      if (action.toLowerCase().equals("attack"))
      {
        System.out.println("\nYou attack!\n");
        flag = false;
      }
      else if (action.toLowerCase().equals("item"))
      {
        System.out.println("\nYou use item!\n");
        flag = false;
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
    System.out.println("\n" + name + " turn!\n");

    System.out.println("\n" + name + " attacks!\n");
  }

  public void endTurn()
  {
    System.out.println("\nBattle Over.\n");
  }
  
}
