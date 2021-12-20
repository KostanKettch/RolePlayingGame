import java.util.ArrayList;
import java.util.List;

public class Player extends Unit {
    protected Player(String name, int hp, int st, int ag, int xp, int gld) {
        super(name, hp, st, ag, xp, gld);
    }

    Backpack backpack = new Backpack();

    void take(String item) {
        backpack.putIn(item);
    }

    void usePotion() {
        if (backpack.contains("Healing Potion")) {
            backpack.putOut("Healing Potion");
            setHp(getHp()+15);
        } else System.out.println("You don't have any potion");
    }


}


class Backpack {
    List<String> inventory = new ArrayList<>();

    public String toString() {
        if (!inventory.isEmpty()) return inventory + " in the backpack";
        else return "the backpack is empty";
    }

    public void putIn(String item) {
        inventory.add(item);
    }

    public void putOut(String item) {
        inventory.remove(item);
    }

    public boolean contains(String item) {
        return inventory.contains(item);
    }
}

