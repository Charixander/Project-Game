/*
* Superclass for items player has.
* Contains basic info of an item.
*/


public class Item {

  String name;
  String description;
  int level;
  int price;

  //constructors:
  public Item ()
  {
    name = "N/A";
    description = "Placeholder";
    level = 1;
    price = 1;
  }

  public Item (String name)
  {
    this.name = name;
    description = "An item.";
    level = 1;
    price = 1;
  }

  public Item (String name, String description)
  {
    this.name = name;
    this.description = description;
    level = 1;
    price = 1;
  }

  public Item (String name, int level)
  {
    this.name = name;
    this.description = "An item.";
    this.level = level;
    price = 1;
  }

  public Item (String name, String description, int level)
  {
    this.name = name;
    this.description = description;
    this.level = level;
    price = 1;
  }

  public Item (String name, String description, int level, int price)
  {
    this.name = name;
    this.description = description;
    this.level = level;
    this.price = price;
  }

  
  //accessor methods:
  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String desc)
  {
    description = desc;
  }

  public int getLevel()
  {
    return level;
  }

  public int getPrice()
  {
    return price;
  }

  //prints the info of an item
  public String info()
  {
    String text = "\"" + name + "\"";
    text += "\n------\n";
    text += description;
    return text;
  }
  
}
