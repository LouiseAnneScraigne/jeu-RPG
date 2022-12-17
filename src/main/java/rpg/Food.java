package rpg;

public class Food extends Consumable{

    private int pouvoir;

    public Food(String name)
    {
        super(name);

        switch (name) {
            case "jus d'orange" -> this.pouvoir = 1;
            case "salade grecque" -> this.pouvoir = 2;
            case "oeufs brouillés" -> this.pouvoir = 3;
            case "lembas" -> this.pouvoir = 4;
            case "poulet rôti" -> this.pouvoir = 5;
        }
    }

    @Override
    public int getHp()
    {
        return this.pouvoir;
    }
}

