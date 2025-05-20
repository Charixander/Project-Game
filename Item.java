public class Item {

  String name;
  String description;
  int level;
  int price;

  public Item ()
  {
    name = "N/A";
    description = "Placeholder";
    level = 1;
  }

  public Item (String name)
  {
    this.name = name;
    description = "An item.";
    level = 1;
    price = 0;
  }

  public Item (String name, String description)
  {
    this.name = name;
    this.description = description;
    level = 1;
    price = 0;

  }

  public Item (String name, String description, int level)
  {
    this.name = name;
    this.description = description;
    this.level = level;
    price = 0;
  }

  public Item (String name, String description, int level, int price)
  {
    this.name = name;
    this.description = description;
    this.level = level;
    this.price = price;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public int getLevel()
  {
    return level;
  }

  public String info()
  {
    String text = "\"" + name + "\"";
    text += "\n------\n";
    text += description;
    return text;
  }
  
}
