import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Main
{
    
    static boolean stopped = false;
    static int counter = 0;
    static Scanner read = new Scanner(System.in);
    public static void main(String[] args) 
    {
        List <Player> players = new ArrayList<>();
        
        System.out.println("         488.0    ----- welcome to the game -----             ");
        System.out.println("-- Please Input The Items to be Included in the market --");
        Market.inputItems();

        System.out.println("             'Press' Enter to enter the game            ");
        read.nextLine();
        System.out.println();
        System.out.print("Enter the number of the player : ");
        int n = read.nextInt();
        read.nextLine();
        boolean turn[] = new boolean[n];
        System.out.println();
        for(int i = 0; i < n; i++)
        {
            playerMaker(i,players);
        }

        System.out.println();
        while(!stopped)
        {
            int i = switchTurn(turn, counter);
            System.out.println(players.get(i).getName() + "'s turn");
            selectMove(players);
        }

        read.close();
        
    }

    static void playerMaker(int i, List <Player> players)
    {
        System.out.println("Input player " + (i+1) + " name : ");
        String name = read.nextLine();
        System.out.println("Avalaible Role :");
        System.out.println("1. Assasin  2. Mage  3. Dracula");
        System.out.println("Input player " + (i+1) + " role : ");
        String role = read.nextLine().toLowerCase();
        if(role.equals("assasin") || role.equals("mage") || role.equals("dracula"))
        {
            players.add(new Player(name, role));
        }
        else
        {
            System.out.println("Please input again. your role is no available");
            playerMaker(i, players);
        }
    }

    static int switchTurn(boolean turn[],int counter)
    {
        int currTurn = counter % turn.length;
        for(int i = 0; i < turn.length; i++)
        {
            turn[i] = i == currTurn ? true:false;
        }

        return currTurn;
    }

    static void selectMove(List <Player> players)
    {
        System.out.println("Select Move :");
        System.out.println("1. Attack");
        System.out.println("2. Use Skill");
        System.out.println("3. Use Item");
        System.out.println("4. Show Skills");
        System.out.println("5. Show Items");
        System.out.println("6. Show Status");
        System.out.println("7. Buy");
        System.out.println("8. Sell");
        System.out.println("9. Quit");

        int n = read.nextInt();
        int currentTurn = counter % players.size();
        switch(n)
        {
            case 1:
                for(int i = 0; i < players.size(); i++)
                {
                    if(currentTurn == i)
                    {
                        continue;
                    }
                    System.out.println((i+1) + ". " + players.get(i).getName());
                
                }
                System.out.println("which player do you want to attack ? ");
                int enemy = read.nextInt();
                System.out.println("You attack " + players.get(enemy-1).getName());
                players.get(currentTurn).attack(players.get(enemy-1));
                if(players.get(currentTurn).dead(players.get(enemy-1)))
                {
                    players.remove(enemy-1);
                }
                counter++;
                break;
            
            case 2:
                System.out.println("select player to use your skill !");
                for(int i = 0; i < players.size(); i++)
                {
                    System.out.println((i+1) + ". " + players.get(i).getName());
                }
                int player = read.nextInt();
                System.out.println("Select your skill !");
                for(int i = 0; i < players.get(currentTurn).getSkills().size(); i++)
                {
                    System.out.println((i+1) + ". " + players.get(currentTurn).getSkills().get(i).getName());
                }
                int skillUsed = read.nextInt();
                System.out.println("You use your skill to " + players.get(player-1).getName());
                    
                players.get(currentTurn).useSkill(players.get(currentTurn).getSkills().get(skillUsed-1)
                                                 , players.get(player-1));
                if(players.get(currentTurn).dead(players.get(player-1)))
                {
                    players.remove(player-1);
                }      
                counter++;                           
                break;
            
            case 3:
                System.out.println("select player to use your item !");
                for(int i = 0; i < players.size(); i++)
                {
                    System.out.println((i+1) + ". " + players.get(i).getName());
                }
                player = read.nextInt();
                players.get(currentTurn).useItem(players.get(player-1));
                if(players.get(currentTurn).dead(players.get(player-1)))
                {
                    players.remove(player-1);
                }
                counter++;
                break;
            
            case 4:
                players.get(currentTurn).showSkill();
                break;
                
            case 5:
                players.get(currentTurn).showItems();
                break;
                
            case 6:
                players.get(currentTurn).showStat();
                break;
                
            case 7:
                players.get(currentTurn).buy();
                counter++;
                break;
                
            case 8:
                players.get(currentTurn).sell();
                counter++;
                break;
            
            case 9:
                System.out.println("are you sure you want to quit ? (y/n)");
                char c = read.next().charAt(0);
                stopped = c == 'Y' || c == 'y' ? true:false;
                break;    

            default:
                System.out.println("you input the wrong move");
                selectMove(players);
                break;
        }

        System.out.println();
    }
}