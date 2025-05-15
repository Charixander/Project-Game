import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    System.out.println("What is your name?");
    String userName = input.nextLine();
    Player player = new Player(userName);
    Enemy enemy = new Enemy("billy");

    Combat test = new Combat(player, enemy);

    test.combatBegin();
  }
}
