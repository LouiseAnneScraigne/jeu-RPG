package rpg;

import static rpg.Game.displayMessage;

public class Healer extends SpellCaster
{
    private final String name;
    private int mana;
    private Magic spell;

    public Healer(String name)
    {
        super(name,5);
        this.name = name;
        this.mana = 5;
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
    public void choisirItem(Consumable Item)
    {
        int hp = Item.getHp();
        super.bonusHp(hp,Item);
        addMana(hp*2);
    }

    @Override
    public String getSpellName()
    {
        if(this.spell != null)
            return this.spell.getName();
        else
            return getName();
    }

    @Override
    public int getSpellDamage()
    {
        return this.spell.getDamage();
    }

    @Override
    public int getSpellCost()
    {
        return this.spell.getCoutMana();
    }

    @Override
    public void fight(Combatant cible)
    {

        switch (this.spell.getName()) {
            case "Rituel d'assainissement" -> {
                displayMessage("\n" + this.name + "/restaure" + this.spell.getDamage() + " /HP de " + cible.getName());
                cible.Heal(this.spell.getDamage());
                if (cible instanceof SpellCaster) {
                    cible.addMana(this.spell.getDamage());
                }
                this.mana -= this.spell.getCoutMana();
            }
            case "Nuage de protection" -> {
                displayMessage("\n" + this.name + "/restaure" + this.spell.getDamage() + " /HP de " + cible.getName());
                cible.Heal(this.spell.getDamage());
                if (cible instanceof SpellCaster) {
                    cible.addMana(this.spell.getDamage() * 2);
                }
                this.mana -= this.spell.getCoutMana();
            }
            case "Flash puissant heal" -> {
                displayMessage("\n" + this.name + "/restaure" + this.spell.getDamage() + " /HP de " + cible.getName());
                cible.Heal(this.spell.getDamage());
                if (cible instanceof SpellCaster) {
                    cible.addMana(this.spell.getDamage() * 3);
                }
                this.mana -= this.spell.getCoutMana();
            }
        }
        if (this.mana <= 0)
        {
            displayMessage(getName() + " n'a plus de mana. ");
            this.mana = 0;
        }
    }

    @Override
    public void choisirSpell(int n)
    {
        switch (n) {
            case 1 -> this.spell = new Magic("Rituel d'assainissement", 1, 1);
            case 2 -> this.spell = new Magic("Nuage de protection", 2, 2);
            case 3 -> this.spell = new Magic("Flash puissant maison", 3, 3);
        }
    }

}