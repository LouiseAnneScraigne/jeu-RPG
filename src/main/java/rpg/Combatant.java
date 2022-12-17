package rpg;

import static rpg.Game.displayMessage;

public abstract class Combatant {
    private final String name;
    private int  mana, healthPoint, maxHealthPoint, defense, maxDefense;

    Weapon Weapon;

    public Combatant(String n, int mana, int defense, int hp) {
        this.name = n;

        this.healthPoint = hp;
        this.maxHealthPoint = healthPoint;

        this.defense = defense;
        this.maxDefense = defense;

        this.mana = mana;
    }

    public void addMana(int n)
    {
        this.mana += n;
    }
    public int getMana()
    {
        return this.mana;
    }


    public void seeInfo(){
        displayMessage("Bienvenue au joueur" + this.getName() + "/ Points de vie:" + this.getHealthPoint() + "/ Le joueur a t-il son arme ?" + this.hasWeapon().getName()+ "/ Attaque:"+ this.Weapon.getDamages());

    }

    public String getName() {
        return this.name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public abstract void fight(Combatant combatant);

    //méthode pour verifier si le joueur a une arme
    public Weapon hasWeapon() { return Weapon;}

    public void bonusHp(int n, Item item)
    {
        this.healthPoint += n;
        if (this.healthPoint > this.maxHealthPoint && item instanceof Food)
        {
            System.out.println("Impossible d'ajouter des HP : HP maximum = " + this.maxHealthPoint);
            this.healthPoint = this.maxHealthPoint;
        }

    }

    public int getDefense()
    {
        return this.defense;
    }


    public void Heal(int n)
    {
        this.healthPoint += n;
        if(this.healthPoint > this.maxHealthPoint)
        {
            int rest = this.healthPoint - this.maxHealthPoint;
            this.healthPoint = this.maxHealthPoint;
            this.defense += rest;
            if(this.defense > this.maxDefense)
            {
                this.defense = this.maxDefense;
                System.out.println(this.name + " est complétement guéri");
            }
        }
    }

    public void Damage(int n)
    {
        this.defense -= n;
        if(this.defense <= 0)
        {
            int reste = -this.defense;
            this.defense = 0;
            this.healthPoint -= reste;
            if(this.healthPoint <= 0)
            {
                this.healthPoint = 0;
                System.out.println(this.name + " est mort");
            }
        }
    }
}