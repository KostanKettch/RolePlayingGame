public abstract class Unit implements Attackable {
    public Player backpack;
    private String name;
    private int hp;
    private int st;
    private int ag;
    private boolean destroyed = false;

    private int xp;
    private int gld;

    protected Unit(String name, int hp, int st, int ag, int xp, int gld) {
        this.name = name;
        this.hp = hp;
        this.st = st;
        this.ag = ag;
        this.xp = xp;
        this.gld = gld;
    }


    @Override
    public int attack() {
        int dc = Dice.dice(100);

        if (ag * 3 > dc * 2) return st * 2;
        else if (ag * 3 > dc && ag * 3 <= dc * 2) return st;
        else return 0;
    }

    public boolean damage(int dmg) {
        setHp(getHp() - dmg);
        if (getHp() <= 0) {
            setDestroyed();
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAg() {
        return ag;
    }

    public void setAg(int ag) {
        this.ag = ag;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGld() {
        return gld;
    }

    public void setGld(int gld) {
        this.gld = gld;
    }

    public void setDestroyed() {
        this.destroyed = true;
        System.out.println(name + " was defeated");
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public String toString() {
        return String.format("%s HP: %d", name, hp);
    }



}
