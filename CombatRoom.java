import java.util.ArrayList;

public class CombatRoom extends Room {
    private ArrayList<Enemy> enemies;
    private String[] enemyList;
    private String[] startOfName;
    private String[] endOfName;
    private int[] healthMultiplier;
    private double[] attackMultiplier;
    private double[] defenseMultiplier;
    

  public CombatRoom(int floorLevel) {
    super(floorLevel);
    enemyList = FileReader.toStringArray("EnemyList.txt");
    startOfName = FileReader.toStringArray("StartOfName.txt");
    endOfName = FileReader.toStringArray("EndOfName.txt");
    healthMultiplier = FileReader.toIntArray("HealthMultiplier.txt");
    attackMultiplier = FileReader.toDoubleArray("AttackMultiplier.txt");
    defenseMultiplier = FileReader.toDoubleArray("DefenseMultiplier.txt");
    enemies = new ArrayList<Enemy>();

    int health = 1;
    int attack = 1;
    int defense = 1;
    String enemyType = "";

    int random = (int)(Math.random()*enemyList.length);
    health = healthMultiplier[random]*getTotalRank();
    attack = (int)(attackMultiplier[random]*getTotalRank());
    defense = (int)(defenseMultiplier[random]*getTotalRank());
    enemyType = enemyList[random];

    random = (int)(Math.random()*3) + 1;
    int randomStart = (int)(Math.random()*startOfName.length);
    int randomEnd = (int)(Math.random()*endOfName.length);
    double statChanger = 1;
    if(random == 2) {
      statChanger = 0.8;
    } else if(random == 3) {
      statChanger = 0.6;
    }
    health = (int)(health*statChanger);
    attack = (int)(attack*statChanger);
    defense = (int)(defense*statChanger);
    if(health < 1) {
        health = 1;
      } if((attack < 1) && !(enemyType.equals("CHEESE"))) {
        attack = 1;
      } if(defense < 1) {
        defense = 1;
      }

    int MonsterDifficulty = (attack+defense+(health/5));
    String name = startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType;
    int gold = (int)(Math.random()*MonsterDifficulty) + MonsterDifficulty/2;
    for(int i = 0; i < random; i++) {
      enemies.add(new Enemy(name, health, attack, defense, MonsterDifficulty, gold, null, null));
      while(name.equals(startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType)) {
      randomStart = (int)(Math.random()*startOfName.length);
      randomEnd = (int)(Math.random()*endOfName.length);
      }
       name = startOfName[randomStart] + endOfName[randomEnd] + " the " + enemyType;
       gold = (int)(Math.random()*MonsterDifficulty) + MonsterDifficulty/2;
    }
  }


  public String getEnemies() {
    String result = "";
    for(int i = 0; i < enemies.size(); i++) {
      result += enemies.get(i) + "\n";
    }
    return result;
  }

  

  
}
