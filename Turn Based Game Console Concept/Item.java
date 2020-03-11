class Item
{
    private String name;
    private int price;
    private String info;
    private String type;
    private int effect;

    Item(String name, int price, String info, String type, int effect)
    {
        this.name = name;
        this.price = price;
        this.info = info;
        this.type = type;
        this.effect = effect;
    }


    String getName()
    {
        return name;
    }

    int getPrice()
    {
        return price;
    }

    String getInfo()
    {
        return info;
    }

    String getType()
    {
        return type;
    }

    int getEffect()
    {
        return effect;
    }

    void descItem()
    {
        System.out.println("Item's Name : " + name);
        System.out.println("Price       : " + price);
        System.out.println("Info        : " + info);
        System.out.println("item's type : " + type);
        System.out.println();
    }


}