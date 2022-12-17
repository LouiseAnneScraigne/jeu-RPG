package rpg;

import java.util.Random;

public class Enemy extends Combatant {

    private int damages;

    public Enemy(String n, int hp, int defense) {
        super(n, 20, 3, 4 );
    }

    public int getDamage()
    {
        Random random = new Random();
        this.damages = random.nextInt(4 - 2 + 1) + 2;
        // Cette expression calcule d'abord 4 - 2 + 1, ce qui donne 3.
        // Le résultat est ensuite utilisé comme argument de random.nextInt(),
        // ce qui signifie que la méthode va renvoyer un entier aléatoire compris entre 0 et 2 (inclus).
        // Enfin, l'expression ajoute 2 au résultat, ce qui étend l'intervalle de valeurs possibles de 0 à 2 à 2 à 4.
        //La valeur générée aléatoirement est enregistrée dans l'attribut this.damages de l'objet courant,
        // puis la fonction retourne cette valeur en utilisant l'instruction return this.damages;.
        return this.damages;
    }
    // Les points de dégats sont intégrés aux ennemis (ils n'ont pas d'arme)

    @Override
    public void fight(Combatant cible)
    {
        cible.Damage(this.damages);
    }

}
