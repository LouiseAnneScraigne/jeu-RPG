package rpg;


public abstract class Hero extends Combatant {

    public Hero(String n, int hp, int mana, int defense) {

        super(n, hp, mana,defense);

    }

    public void bonusHp(int n, Item item)
    {
        super.bonusHp(n,item);
    }

    private int mana;
    public void winCombat() {
        // Augmenter la valeur de mana de 1, PAS TERMINE
        mana = mana + 1;
    }

    public abstract void choisirItem(Consumable item);
}
