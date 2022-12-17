package rpg;

public class Mage extends SpellCaster {

    private Magic spell = null;

    private String name;
    private int mana;

    public Mage(String n) {

        super(n, 6);
        this.name = name;
        this.mana = 6;
    }

    @Override
    public String getSpellName()
    {
        if(this.spell != null)
            return this.spell.getName();
        else
            return null;
    }

    @Override
    public int getSpellDamage()
    {
        return this.spell.getDamage();
    }

    @Override
    public void fight(Combatant cible) {
        {
            System.out.println(getName() + " inflige " + this.spell.getDamage() + " dommages à " + cible.getName());
            switch (this.spell.getName()) {
                case "éclairs de glace" -> {
                    cible.Damage(this.spell.getDamage());
                    this.mana -= this.spell.getCoutMana();
                }
                case "boules de feu" -> {
                    cible.Damage(this.spell.getDamage());
                    this.mana -= this.spell.getCoutMana();
                }
            }

            if (this.mana <= 0)
            {
                System.out.println(getName() + " n'a plus de mana");
                this.mana = 0;
            }
        }
    }

    @Override
    public int getSpellCost()
    {
        return this.spell.getCoutMana();
    }

    @Override
    public void choisirSpell(int n) {
        switch (n) {
            case 1 -> this.spell = new Magic("éclairs de glace", 1, 1);
            case 2 -> this.spell = new Magic("boules de feu", 3, 3);
        }
    }

    @Override
    public int getMana()
    {
        return this.mana;
    }

    @Override
    public void addMana(int n)
    {
        this.mana += n;
    }

    @Override
    public void choisirItem(Consumable item) {
        // "bonusHp()" qui ajoute des points de vie au héros
        int hp = item.getHp();
        super.bonusHp(hp,item);
        addMana(hp*2); // ajoute deux fois la valeur des points de vie ajoutés au total de mana du héros
    }

}