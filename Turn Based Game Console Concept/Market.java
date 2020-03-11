import java.util.Scanner;

class Market
{

    static private Item listItems[] = new Item[8];
    static Scanner read = new Scanner(System.in);     

    // Input lists of items that can be bought during the gameplay
    static void inputItems()
    {
        for(int i = 0; i < 8; i++)
        {
            System.out.println((i+1) + ".");
            System.out.println("Input name of the item");
            String name = read.nextLine();
            System.out.println("Input the price");
            int price = read.nextInt();
            read.nextLine();
            System.out.println("Input the Info");
            String info = read.nextLine();
            System.out.println("Input the type of (spell/HP potion/MP potion)");
            String type = read.nextLine();
            System.out.println("Effect of the item\n(spell to attack)\n(HP Potion to heal)\n(MP potion to regen energy)");
            System.out.println("input in number");
            int effect = read.nextInt();
            read.nextLine();
            listItems[i] = new Item(name, price, info, type, effect);
        }
    }

    static Item getListItems(int index)
    {
        return listItems[index];
    }

    // show the Items that has been Listed in the Market
    static void showListItems()
    {
        for(int i = 0; i < 8; i++)
        {
            System.out.println("Item number " + i);
            System.out.println("Item's Name : " + listItems[i].getName());
            System.out.println("Price       : " + listItems[i].getPrice());
            System.out.println("Info        : " + listItems[i].getInfo());
            System.out.println("item's type : " + listItems[i].getType());
            System.out.println();
        }
    }
}