package rpg;

public class Potion extends Consumable {

    private int pouvoir;

    public Potion(String name)
    {
        super(name);

        switch (name) {
            case "potion endurance" -> this.pouvoir = 1;
            case "potion mystérieuse" -> this.pouvoir = 3;
        }
    }

    @Override
    public int getHp()
    {
        return this.pouvoir;
    }
}

