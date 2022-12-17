package rpg;

public abstract class SpellCaster extends Hero{

    public SpellCaster(String n, int mana) {
        super(n, 3, mana, 3);
    }

    public abstract String getSpellName();

    public abstract int getSpellDamage();

    public abstract void addMana(int n);

    public abstract void choisirSpell(int n);

    public abstract int getSpellCost();

}