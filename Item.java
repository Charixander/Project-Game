public class Item {

  String name;
  String description;
  int level;

  public Item (String name)
  {
    this.name = name;
    description = "An item.";
    level = 1;
  }

  public Item (String name, String description)
  {
    this.name = name;
    this.description = description;
    level = 1;
  }

  public Item (String name, String description, int level)
  {
    this.name = name;
    this.description = description;
    this.level = level;
  }
  
}
