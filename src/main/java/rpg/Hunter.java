package rpg;

public class Hunter extends Hero{

    private Weapon weapon;
    private int damages= 1 ;
    private String name;
    private int nbFleches;

    public Hunter(String n) {
        super(n, 7, 2, 0);
        this.name = name;
    }

    @Override
    public void fight(Combatant cible) {
        boolean attaquer = this.weapon.attaquer();
        if(attaquer)
        {
            System.out.println(getName() + " inflige " + this.damages + " dommages à " + cible.getName());
            cible.Damage(this.damages);
        }
        else
        {
            System.out.println(this.name + " a raté son attaque en utilisant " + this.weapon.getName());
        }
    }

    public void setnbfleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

    public void choisirWeapons(int n)
    {
        Weapon item;
        System.out.println(n);
        switch (n) {
            case 1 -> {
                item = new Weapon("arcs avec des flèches");
                this.weapon = item;
                setnbfleches(damages);
                System.out.println("Le nombre de flèches actif est : " + nbFleches);
            }
            case 2 -> {
                item = new Weapon("carabine");
                this.weapon = item;
            }
        }
        this.damages = this.weapon.getDamages();
    }

    @Override
    public void choisirItem(Consumable item)
    {
        int hp = item.getHp();
        super.bonusHp(hp,item);
        addMana(hp*2);
    }

    public String getNomWeapon()
    {
        if(this.weapon != null)
            return this.weapon.getName();
        else
            return null;
    }

    public int getDamage()
    {
        return this.damages;
    }
}