import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class World {
    private static BufferedReader reader;
    private static Unit player = null;
    private static Battle battle;

    public static void main(String[] args) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();

        System.out.println("Enter your character's Name:");
        try {
            command(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(string, 150, 25, 25, 0, 0);
            System.out.println(String.format("Hello there, %s!", player.getName()));
            menu();
        }

        switch (string) {
            case "1":
                duel();
                break;
            case "2":
                merchant();
                break;
            case "3":
                System.exit(1);
                break;
            case "y":
                menu();
                command(reader.readLine());
            case "n":
                command("1");
                break;


        }
        command(reader.readLine());

    }

    private static void merchant() throws IOException {
        Merchant merchant = new Merchant();
        Merchant.Goods goods = null;
//        goods.toString();
        String item=reader.readLine();
        if (player.getGld() < 15 /*goods.getCost()*/) {
            System.out.println("You don't have money?! Go away, beggar!");
        } else {
            player.setGld(player.getGld() - 15);
            player.backpack.take(item);
            player.backpack.usePotion();

        }

        merchant.buy(Merchant.Goods.HealingPotion);
    }

    private static void duel() {
        battle.duel(player, createMonster(), new Fight() {
            @Override
            public void duelWin() {
                System.out.println(String.format("You Won, %s. You have now: %d of gold, %d of exp and %d of HP left", player.getName(), player.getGld(), player.getXp(), player.getHp()));
                System.out.println("Do you want to return to the Town? (y/n)");
                try {
                    command(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void duelLost() {
            }
        });
    }

    private static Unit createMonster() {
        if (Dice.dice(3) % 3 == 0) return new Skeleton("Skeleton", 50, 20, 20, 100, 15);
        else return new Goblin("Goblin", 30, 15, 15, 50, 5);
    }

    private static void menu() {
        System.out.println("Where you want to go?");
        System.out.println("1. I want to kick some goblins!");
        System.out.println("2. Let's go to the shop!");
        System.out.println("3. Exit");
    }


    public interface Fight {
        void duelWin();
        void duelLost();
    }
}
