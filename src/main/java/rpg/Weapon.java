package rpg;

public class Weapon extends Item{

    private int damages; // Une arme inflige des points de dégats
    private boolean malus;

    public Weapon(String name) {

        super(name);

        switch (name) {
            case "épée" -> {
                this.damages = 3;
                this.malus = true;
            }
            case "hâche" -> {
                this.damages = 2;
                this.malus = false;
            }
            case "boomerang" -> {
                this.damages = 1;
                this.malus = false;
            }
            case "arc avec des fléches" -> {
                this.damages = 2;
                this.malus = false;
            }
            case "carabine" -> {
                this.damages = 2;
                this.malus = false;
            }
        }
    }
    public boolean attaquer()
    // Si le personnage a un malus, la fonction retournera false dans 20% des cas, sinon elle retournera toujours true
    {
        if(this.malus)
        {
            return !(Math.random() < 0.3);
        }
        else
        {
            return true;
        }
    }
    public int getDamages() {

        return this.damages;
    }
}
