import java.util.Random;

public class Battle {

    public void duel(Unit player, Unit monster, World.Fight fight) {
        System.out.println(String.format("%s appeared!", monster.getName()));
        Runnable runnable = () -> {
            int turn = 1;
            boolean destroyed = false;
            while (!destroyed) {
                System.out.println("Round: " + turn);
                if (turn++ % 2 != 0) {
                    destroyed = hit(monster, player, fight);
                } else {
                    destroyed = hit(player, monster, fight);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private boolean hit(Unit victim, Unit fighter, World.Fight fight) {
        int hit = fighter.attack();
        int victimHealth = victim.getHp() - hit;
        if (hit == 0) {
            System.out.println(String.format("%s missed", fighter.getName()));
        } else {
            System.out.println(String.format("%s damage %d points", fighter.getName(), hit));
            System.out.println(String.format("%s have %d HP left", victim.getName(), victimHealth));
        }
        if (victimHealth > 0) {
            victim.setHp(victimHealth);
            return false;
        } else {
            if (victim instanceof Player) {
                System.out.println("YOU DIED");
                fight.duelLost();
            } else {
                System.out.println(String.format("You defeat %s. You get %d exp and %d gold", victim.getName(), victim.getXp(), victim.getGld()));
                fighter.setXp(fighter.getXp() + victim.getXp());
                fighter.setGld(fighter.getGld() + victim.getGld());
                fight.duelWin();
            }
            return true;
        }
    }
}
