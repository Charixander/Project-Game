import java.util.ArrayList;
import java.util.Scanner;

/*
* Primary room of the game
* Fight random enemy here
* Enemies are designed so each enemyType has
* the same porportion of stats and simply scales.
* So for example if you fight a golem you know it will
* always have high defense and low attack.
*/



public class CombatRoom extends Room {
    /*lists of components for different enemies
    * The index of each list is same enemytype.
    * Meaning index 0 for enemyType, that enemyType has
    * its health at index 0 in healthMultiplier.
    */
    private ArrayList<Enemy> enemies;
    private String enemyType;
    private String[] enemyList;
    private String[] startOfName;
    private String[] endOfName;
    private int[] healthMultiplier;
    private double[] attackMultiplier;
    private double[] defenseMultiplier;
    private int[] healSelf;
    private int[] healOthers;
    

  public CombatRoom(int floorLevel) {
    super(floorLevel);
    enemyList = FileReader.toStringArray("EnemyList.txt");
    startOfName = FileReader.toStringArray("StartOfName.txt");
    endOfName = FileReader.toStringArray("EndOfName.txt");
    healthMultiplier = FileReader.toIntArray("HealthMultiplier.txt");
    attackMultiplier = FileReader.toDoubleArray("AttackMultiplier.txt");
    defenseMultiplier = FileReader.toDoubleArray("DefenseMultiplier.txt");
    healSelf =  FileReader.toIntArray("HealSelf.txt");
    healOthers =  FileReader.toIntArray("HealOthers.txt");
    enemies = new ArrayList<Enemy>();

    int health = 1;
    int attack = 1;
    int defense = 1;
    int healSelfModifier = 0;
    int healOthersModifier = 0;

    //intializes stats from tables and multiples by difficulty
    int random = (int)(Math.random()*enemyList.length);
    health = healthMultiplier[random]*getTotalRank();
    attack = (int)(attackMultiplier[random]*getTotalRank());
    defense = (int)(defenseMultiplier[random]*getTotalRank());
    enemyType = enemyList[random];
    healSelfModifier = (int)(healSelf[random]*getTotalRank());
    healOthersModifier = (int)(healOthers[random]*getTotalRank());

    random = (int)(Math.random()*3) + 1;
    int randomStart = (int)(Math.random()*startOfName.length);
    int randomEnd = (int)(Math.random()*endOfName.length);
    //statChanger designed to weaken enemies when there is multiple to balance them.
    double statChanger = 1;
    if(random == 2) {
      statChanger = 0.8;
    } else if(random == 3) {
      statChanger = 0.6;
    }

    //alters stats based on statChanger.
    health = (int)(health*statChanger);
    attack = (int)(attack*statChanger);
    defense = (int)(defense*statChanger);
    healSelfModifier = (int)(healSelfModifier*statChanger);
    healOthersModifier = (int)(healOthersModifier*statChanger);
    if(health < 1) {
        health = 1;
      } if((attack < 1) && !(enemyType.equals("CHEESE"))) {
        attack = 1;
      } if(defense < 1) {
        defense = 1;
      }

    //creates the list of enemies.
    int MonsterDifficulty = (attack+defense+(health/5));
    String name = startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType;
    int gold = (int)(Math.random()*MonsterDifficulty) + MonsterDifficulty/2;
    for(int i = 0; i < random; i++) {
      enemies.add(new Enemy(name, health, attack, defense, MonsterDifficulty, gold, healSelfModifier, healOthersModifier));
      while(name.equals(startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType)) {
      randomStart = (int)(Math.random()*startOfName.length);
      randomEnd = (int)(Math.random()*endOfName.length);
      }
       name = startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType;
       gold = (int)(Math.random()*MonsterDifficulty) + MonsterDifficulty/2;
    }
  }

  //prints the info of the enemies in the room.
  public String getEnemies() {
    String result = "";
    for(int i = 0; i < enemies.size(); i++) {
      result += enemies.get(i) + "\n";
    }
    return result;
  }

    //describes room based off enemyType and amount of enemies.
    public String getRoomContents() {
    if(enemies.size() == 1) {
      return "a wondering "  + enemyType + "!";
    } else if(enemies.size() == 2) {
      return "a pair of " + enemyType + "s!";
    } else {
      return "a hoard of " + enemyType + "s!";
    }
  }

  /*
  * helper method for runRoom.
  * runs combat based on enemyCount for CombatRoom.
  */
  private void runCombat(Player player) {
    if(enemies.size() == 1) {
      Combat combatA = new Combat(player, enemies.get(0));
      combatA.combatBegin();
    } else if(enemies.size() == 2) {
      Combat combatB = new Combat(player, enemies.get(0), enemies.get(1));
      combatB.combatBegin();
    } else {
      Combat combatC = new Combat(player, enemies.get(0), enemies.get(1), enemies.get(2));
      combatC.combatBegin();
    }
  }

  /*
  * helper method for runRoom.
  * For the options that happens after combat.
  */
  private void postCombat(Player player) {
    boolean flag = true;
     int random = 0;
    Scanner input = new Scanner(System.in);
    while (flag) {
      System.out.println("The room is now empty, what do you want to do now?");
       System.out.println("\n|| [ Leave ] || [ Inventory ] ||\n");
       String action = input.nextLine();
        if(action.toLowerCase().equals("leave")) {
          flag = false;
          System.out.println("you walk through the hallway to the next door and open it...");
    } else if(action.toLowerCase().equals("inventory")) {
            player.hud();
    } else {
       System.out.println("invalid Input."); 
    }
  }
}

  /*
  * Runs the room and the different options.
  * Sneaking and searching have a chance to do things but it's partially luck
  */
  public void runRoom(Player player) {
     System.out.println(getDesc());
     Scanner input = new Scanner(System.in);
     boolean flag = true;
     int random = 0;
    while (flag)
    {
      System.out.println("\n|| [ Sneak ] || [ Search ] || [ Inventory ] ||\n");
      String action = input.nextLine();
      
      if(action.toLowerCase().equals("sneak")) {
        flag = false;
        random = (int)(Math.random()*10);
        if(random <= 2) {
          System.out.println("you were ambushed by " + getRoomContents());
          runCombat(player);
          if(DungeonManager.getGameAlive()) {
            postCombat(player);
          }
        } else {
          System.out.println("you passed the room peacefully...");
        }
      
      } else if(action.toLowerCase().equals("search")) {
        flag = false;
        System.out.println("You Spotted " + getRoomContents());
        random = (int)(Math.random()*10);
        if(random <= 2) {
           System.out.println("They didn't notice you...");
           boolean newFlag = true;
             while(newFlag) {
               System.out.println("\n|| [ Attack ] || [ Leave ] || [ Inventory ] || \n");
               action = input.nextLine();
                if(action.toLowerCase().equals("attack")) {
                  newFlag = false;
                  runCombat(player);
                  if(DungeonManager.getGameAlive()) {
                   postCombat(player);
                  }
                } else if(action.toLowerCase().equals("leave")) {
                  newFlag = false;
                  System.out.println("You move on to the next room.");
                } else if(action.toLowerCase().equals("inventory")) {
                  player.hud();
                  } else {
                  System.out.println("invalid Input.");
                }
             }
        } else {
          System.out.println("They saw you at the same time!");
          runCombat(player);
          if(DungeonManager.getGameAlive()) {
            postCombat(player);
          }
        }
      
      }else if(action.toLowerCase().equals("inventory")) {
        player.hud();
      }else {
        System.out.println("invalid Input.");
      }
    }
}

}
