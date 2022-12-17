package rpg;

public class Magic {
    private int damages;
    private String name;
    private int coutMana;

    public Magic(String name, int damages, int coutMana)
    {
        this.damages = damages;
        this.name = name;
        this.coutMana = coutMana;
    }

    public String getName()
    {
        return this.name;
    }

    public int getCoutMana()
    {
        return this.coutMana;

    }
    public int getDamage()
    {
        return this.damages;
    }
}