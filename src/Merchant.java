public class Merchant implements Seller {

    @Override
    public String buy(Goods goods) {
        String result = "";
        if (goods == Goods.HealingPotion) {
            result = "potion";
        }
        return result;
    }

    public enum Goods {
        HealingPotion;
        /*("Healing Potion", 15);

        private final String name;
        private final int cost;

        Goods(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return String.format("%s: %d", name, cost);
        }*/
    }
}

