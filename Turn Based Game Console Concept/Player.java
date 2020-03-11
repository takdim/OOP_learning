import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Player
{
    /*
        player's attribute
    */
    private int level;
    private int xp;
    private int maxXp;
    private double hp;
    private double maxHp;
    private double damage;
    private double defense; // always lower than damage attribute
    private int money;
    private String role;
    private String name;
    private double energy;
    private double maxEnergy;
    private List <Item> items;
    private List <Skill> skills;
    static Scanner read = new Scanner(System.in);
    private static Skill listAssasinSkillsLvUp[] = {new Skill("armorbreak", 10, 15)
                                                   , new Skill("slay", 100, 25)
                                                   , new Skill("double slash", 250, 35)};
    private static Skill listMageSkillsLvUp[] = {new Skill("IceBall", 70, 40)
                                            , new Skill("heal", 250, 50)
                                            , new Skill("EarthQuake", 300, 80)};
    private static Skill listDraculaSkillsLvUp[] = {new Skill("bite", 50, 15)
                                                    , new Skill("scratch", 100, 25)
                                                    , new Skill("double bite", 150, 35)};


    // instantiate the character
    // set name and the role
    Player(String name, String role)
    {
        skills = new ArrayList<>();
        items = new ArrayList<>();
        this.name = name;
        this.role = role;
        hp = 500;
        maxHp = 500;
        money = 500;
        xp = 0;
        maxXp = 1000;
        level = 0;
        items.add(new Item("small potion", 200, "Hp ++ 20", "HP potion", 20));
        checkRole();

    }

    String getName()
    {
        return name;
    }

    String getRole()
    {
        return role;
    }

    List<Skill> getSkills()
    {
        return skills;
    }

    // give one skill and default damage for each of the role
    // this is only executed when player is instantiated using the constructor
    void checkRole()
    {
        switch (role.toLowerCase()) 
        {
            case "assasin":
                damage = 45;
                defense = 30;
                skills.add(new Skill("stab", 30, 10)); // give damage to opponent
                energy = 50;
                break;
        
            case "mage":
                damage = 20;
                defense = 15;
                skills.add(new Skill("fireball", 45, 30)); // give damage to opponent
                energy = 100;
                break;

            case "dracula":
                damage = 35;
                defense = 30;
                skills.add(new Skill("steal", 20, 20)); // using for stealing opponent's hp
                energy = 75;
                break;
        }
    }

    // player attack enemy
    void attack(Player enemy)
    {
        Random rand = new Random();
        
        
        if((rand.nextInt(10)+1) % 5 == 0)
        {
            if((rand.nextInt(10)+1) % 5 == 0)
            {
                enemy.hp -= (damage*(0.8+0.3) - (defense*0.8));
                System.out.println("critical attack !!");
            }
            else
            {
                enemy.hp -= (damage*0.8 - (defense*0.8));
            }
            xp += 100;
            levelUp();
        }
        else
        {
            System.out.println("You missed !!");
        }

        
    }

    // using skill and get the special attack from it
    // can be used for own character
    void useSkill(Skill skill, Player enemy)
    {
        Random rand = new Random();
        if(energy < skill.getEnergyReduce())
        {
            System.out.println("your energy's not enough to use the skill");
        }
        else
        {
            if((rand.nextInt(10)+1) % 5 == 0)
            {
                xp += 100;
                levelUp();
                if((rand.nextInt(10)+1) % 5 == 0)
                {
                    switch(skill.getName())
                    {
                        case "steal":
                        enemy.hp -= ((50+skill.getEffect()) - (enemy.defense*0.8));
                        break;
                        case "armorbreak":
                            enemy.defense -= skill.getEffect();
                            enemy.hp -= ((50 + damage*(0.8+0.3)) - (defense*0.8));
                            enemy.defense += skill.getEffect();
                            break;
                            case "heal":
                            enemy.hp += skill.getEffect();
                            break;
                            default:
                            enemy.hp -= ((skill.getEffect() + 50) - (enemy.defense*0.8));
                    }
                    System.out.println("critical attack !!");
                }
                else
                {
                    switch(skill.getName())
                    {
                        case "steal":
                            enemy.hp -= (skill.getEffect() - (enemy.defense*0.8));
                            break;
                        case "armorbreak":
                            enemy.defense -= skill.getEffect();
                            enemy.hp -= (damage*(0.8+0.3) - (defense*0.8));
                            enemy.defense += skill.getEffect();
                            break;
                        case "heal":
                            enemy.hp += skill.getEffect();
                            break;
                        default:
                            enemy.hp -= (skill.getEffect() - (enemy.defense*0.8));
                    }
                }
            }
        }
    }

    // will adding items list for player
    // only if money is enough
    void buy()
    {
        Market.showListItems();
        System.out.print("what items do you want to buy ? ");
        int m = read.nextInt();
        System.out.print("How many ? ");
        int n = read.nextInt();
        for(int i = 0; i < n; i++)
        {
            if(money >= Market.getListItems(m).getPrice())
            {
                money -= Market.getListItems(m).getPrice();
                items.add(new Item(Market.getListItems(m).getName(), Market.getListItems(m).getPrice()
                                   , Market.getListItems(m).getInfo(), Market.getListItems(m).getType()
                                   , Market.getListItems(m).getEffect()));
                money = money <= 0 ? 0:money;
            }
            else
            {
                System.out.println("you don't have enough money");
                break;
            }
                
        }

        System.out.print("do you want to buy again ? (y/n)");
        char yn = read.next().charAt(0);
        if(yn == 'y' || yn == 'Y')
        {
            buy();
        }
    }

    //remove item from list
    // money + item's price
    void sell()
    {
        showItems();
        System.out.print("which item you want to sell ? (input number of the item)");
        int rem = read.nextInt();
        System.out.println("You get " + items.get(rem).getPrice());
        money += items.get(rem).getPrice();
        items.remove(rem);
    }

    // show player's item
    void showItems()
    {
        for(int i = 0; i < items.size(); i++)
        {
            System.out.println("no."+i);
            items.get(i).descItem();
        }
    }

    // show player's skill
    void showSkill()
    {
        for(int i = 0; i < skills.size(); i++)
        {
            System.out.println("no." + i);
            skills.get(i).showSkillStat();
        }
    }

    // will increase player's attr when maxXp is reached
    void levelUp()
    {
        if(xp >= maxXp)
        {
            xp = xp - maxXp;
            maxXp += 800;
            damage += 10.5;
            defense += 5.5;
            energy += 20.5;
            hp += 100;
            maxHp += 100;
            maxEnergy += 20.5;
            level++;
            checkLevel();
            System.out.println("You are now level + " + level);
        }
    }

    // adding skill list if level is divisible by 5
    // will not add any skill if level 15 is reached
    void checkLevel()
    {
        if(level % 5 == 0 && level != 0)
        {
            int n = level / 5;
            if(n < 3)
            {
                switch(role)
                {
                    case "assasin":
                        skills.add(Player.listAssasinSkillsLvUp[n]);
                        break;
                    case "mage":
                        skills.add(Player.listMageSkillsLvUp[n]);
                        break;
                    case "dracula":
                        skills.add(Player.listDraculaSkillsLvUp[n]);
                        break;
                }
            }
        }
    }

    // items can be used for another character(player)
    void useItem(Player player)
    {
        showItems();
        System.out.println("what items do you want to use ? (input a number)");
        int n = read.nextInt();
        if(items.get(n).getType().equalsIgnoreCase("HP potion"))
        {
            if(player.hp + items.get(n).getEffect() >= maxHp)
            {
                player.hp = player.maxHp;
            }
            else
            {
                player.hp += items.get(n).getEffect();
            }
            items.remove(n);
        }
        else if(items.get(n).getType().equalsIgnoreCase("MP potion"))
        {
            if(player.energy + items.get(n).getEffect() >= maxEnergy)
            {
                player.energy = maxEnergy;
            }
            else
            {
                player.energy += items.get(n).getEffect();
            }
            items.remove(n);
        }
        else if(items.get(n).getType().equalsIgnoreCase("Spell"))
        {
            player.hp -= items.get(n).getEffect();
            items.remove(n);
        }
    }

    // giving information of the character(player)
    void showStat()
    {
        System.out.println("Name    : " + name);
        System.out.println("Role    : " + role);
        System.out.println("Level   : " + level);
        System.out.println("Hp      : " + hp);
        System.out.println("Energy  : " + energy);
        System.out.println("Damage  : " + damage);
        System.out.println("Defense : " + defense);
        System.out.println("Xp      : " + xp);
        System.out.println("Money   : " + money);
    }

    //check whether the enemy is dead
    boolean dead(Player enemy)
    {
        if(enemy.hp <= 0)
        {
            System.out.println(enemy.name + " is dead");
            money += enemy.money;
            return true;
        }
        return false;
    }
    
}