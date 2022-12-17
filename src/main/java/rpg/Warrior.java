package rpg;

public class Warrior extends Hero{

    private Weapon weapon;
    private int damages=9;

    private final String name;

    public Warrior(String n) {
        // Le guerrier possède 3 points de vie
        super(n,5, 2, 3);
        this.name = n;
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

    @Override
    public void choisirItem(Consumable item)
    {
        int hp = item.getHp();
        super.bonusHp(hp,item);
    }

    public String getNomWeapon()
    {
        if(this.weapon != null)
            return this.weapon.getName();
        else
            return null;
    }

    // Implémentation de la méthode abstraite "take" par le guerrier :
    // Le guerrier ne peut utiliser que les objets de type "Weapon"

    public int getDamage()
    {
        return this.damages;
    }

    public void choisirWeapons(int n) {
        Weapon item;
        switch (n) {
            case 1 -> {
                item = new Weapon("épée");
                this.weapon = item;
            }
            case 2 -> {
                item = new Weapon("hâche");
                this.weapon = item;
            }
            case 3 -> {
                item = new Weapon("boomerang");
                this.weapon = item;
            }
        }
    }
}
