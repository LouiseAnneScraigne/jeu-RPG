package util;


import rpg.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ConsoleParser implements InputParser{


    public int getAction(Hero hero, int size) {

        Scanner scanner = new Scanner(System.in);
        boolean c, minMana = false;
        int choix = 0;
        System.out.println("\nQu'est-ce que " + hero.getName() + " va faire: ");
        if(hero instanceof SpellCaster)
        {
            if(((SpellCaster) hero).getSpellName() != null ) //vérifie si un héros est un "SpellCaster" et s'il a un sort actif
                System.out.println("Le sort actif : " + ((SpellCaster) hero).getSpellName() + " - D=" + ((SpellCaster) hero).getSpellDamage()); //imprime le nom du sort et les dégâts qu'il inflige.
            else
                System.out.println("Aucun sort n'a été sélectionné");
            System.out.println("[1] Attaquer");
            System.out.println("[2] Choisir une arme ou un sort");
        }
        else
        {
            if(hero instanceof Warrior)
                if(((Warrior) hero).getNomWeapon() != null ) //vérifie s'il a une arme
                    System.out.println("L'arme actuelle : " +((Warrior) hero).getNomWeapon() + "\n" + " Dégats " + ((Warrior) hero).getDamage()); // imprime le nom de l'arme et les dégâts qu'elle inflige.

                else
                    System.out.println("Aucune arme n'a été sélectionnée");

                else if(hero instanceof Hunter)
                    if(((Hunter) hero).getNomWeapon() != null ) //vérifie s'il a une arme
                    System.out.println("L'arme actuelle : " +((Hunter) hero).getNomWeapon() + "\n" + " Dégats " + ((Hunter) hero).getDamage()); // imprime le nom de l'arme et les dégâts qu'elle inflige.

                else
                    System.out.println("Aucune arme n'a été sélectionnée");
                    System.out.println("[1] Attaquer");
            System.out.println("[2] Choisir une arme dans son étui");
        }
        System.out.println("[3] Utiliser des consommables");
        System.out.println("[4] Passer son tour");
        c = false;

        while(!c) //utilise une boucle "while" pour demander à l'utilisateur de choisir une option jusqu'à ce qu'il choisisse une option valide.
            //Lorsque l'utilisateur choisit une option, le code vérifie si l'option est valide en fonction du type de héros et des conditions requises pour utiliser l'option.
            //Si l'option est valide, la boucle se termine et l'action sélectionnée est effectuée.
            //Sinon, un message d'erreur est affiché et l'utilisateur est invité à choisir une autre option.
        {
            System.out.print("\nEntrer votre choix : ");
            choix = scanner.nextInt();
            if(hero instanceof SpellCaster) //si l'héro est un SpellCaster
            {
                if(hero instanceof Mage) //si c'est une magicienne (ou magicien)
                {
                    if(hero.getMana() > 0)
                    {
                        minMana = true;
                    }
                    else
                    {
                        System.out.println(" \n"+  hero.getName() + " n'a pas assez de mana pour utiliser des sorts" + "\n");
                    }
                }
                else if(hero instanceof Healer) //si c'est un guérisseur
                {
                    if(hero.getMana() > 1)
                    {
                        minMana = true;
                    }
                    else
                    {
                        System.out.println("\n"+ hero.getName() + " n'a pas assez de mana pour utiliser des sorts" + "\n");
                    }
                }
                if((choix == 1 && ((SpellCaster) hero).getSpellName() != null) && minMana)
                {
                    c = true;
                }
                else if (choix == 1)
                {
                    System.out.println("\nVeuillez d'abord choisir un sort à attaquer");
                }

                if((choix == 2 && minMana) || (choix == 3 && size != 0) || choix == 4 )
                {
                    c = true;
                }

                if(choix == 3 && size == 0)
                {
                    System.out.println("\nIl n'y a plus d'objets dans votre inventaire");
                }
            }
            else
            {
                if(hero instanceof Warrior) //si c'est un warrior
                {
                    if((choix == 1 && ((Warrior) hero).getNomWeapon() != null))
                    {
                        c = true;
                    }
                    else if(choix == 1)
                    {
                        System.out.println("\nChoisissez d'abord une arme pour attaquer, Warrior Adame ne peut pas combattre à mains nus");
                    }

                    if(choix == 2 || (choix == 3 && size != 0) || choix == 4)
                    {
                        c = true;
                    }
                    else if(choix == 3 && size == 0)
                    {
                        System.out.println("\nVous n'avez plus d'objets dans votre inventaire.");
                    }
                }
                else if(hero instanceof Hunter) // si c'est un chasseur
                {
                    if((choix == 1 && ((Hunter) hero).getNomWeapon() != null))
                    {
                        c = true;
                    }
                    else if(choix == 1)
                    {
                        System.out.println("\nChoisissez d'abord une arme pour attaquer, Hunter Chace ne peut pas combattre à mains nus");
                    }
                    if(choix == 2 || (choix == 3 && size != 0) || choix == 4)
                    {
                        c = true;
                    }

                    if(choix == 3 && size == 0)
                    {
                        System.out.println("\nIl n'y a plus d'objets dans votre inventaire");
                    }
                }
            }
        }
        return choix;
    }

    public void choisirWeapon(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int weapon;
        System.out.println("\nChoisissez votre arme : ");
        if(hero instanceof Warrior)
        {
            System.out.println("[1] épée \uD83D\uDDE1️");
            System.out.println("[2] hâche \uD83E\uDE93");
            System.out.println("[3] boomerang \uD83E\uDE83");
            c = false;
            while(!c)
            {
                System.out.print("\nEntrer le numéro correspondant à l'arme choisi: ");
                weapon = scanner.nextInt();
                if(weapon == 1 || weapon == 2 || weapon == 3)
                {
                    c = true;
                    ((Warrior) hero).choisirWeapons(weapon);
                }
            }
        }
        else if(hero instanceof Hunter)
        {
            System.out.println("[1] arcs avec des flèches \uD83C\uDFF9");
            System.out.println("[2] carabine \uD83D\uDD2B");
            c = false;
            while(!c)
            {
                System.out.print("\nEntrer le numéro correspond à l'arme choisi: ");
                weapon = scanner.nextInt();
                if(weapon == 1 || weapon == 2)
                {
                    c = true;
                    ((Hunter) hero).choisirWeapons(weapon);
                }
            }
        }
    }

        public void checkForBossAttaque() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nAppuyez sur n'importe quelle touche pour continuer le combat\n");
            scanner.nextLine();
    }

    public int getCible(ArrayList<Combatant> cibles) {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choix = 0;
        System.out.println("\nChoisissez contre qui vous souhaitez attaquer : ");
        for(int i=0;i<cibles.size();i++)
        {
            System.out.println("[" + (i + 1) + "]" + " " + cibles.get(i).getName() + "(" + cibles.get(i).getHealthPoint() +")");
        }
        c = false;
        while(!c)
        {
            System.out.print("\nEntrer votre choix : ");
            choix = scanner.nextInt();
            if(choix >= 1 && choix <= cibles.size())
            {
                c = true;
            }
        }
        return choix-1;
    }

    @Override
    public void choisirSpell(SpellCaster hero){
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int max = 0, choix;
        System.out.println("\nChoisissez un sort : ");
        if(hero instanceof Healer)
        {
            System.out.println("[1] Ritual d'asssainissement -> Damage = 1, Mana cost = 1");
            System.out.println("[2] Nuage de protection -> Damage = 2, Mana cost = 2");
            System.out.println("[3] Flash puissant maison -> Damage = 3, Mana cost = 3");
            max = 3;
        }
        else if(hero instanceof Mage)
        {
            System.out.println("[1] éclairs de glace -> Damage = 1, Mana cost = 1");
            System.out.println("[2] boules de feu -> Damage = 3, Mana cost = 3");
            max = 2;
        }
        c = false;
        while(!c)
        {
            System.out.print("\nEntrer votre choix : ");
            choix = scanner.nextInt();
            if(choix >= 1 && choix <= max)
            {
                hero.choisirSpell(choix);
                int manaCost = hero.getSpellCost();
                int manaAmount = hero.getMana();
                if(manaCost <= manaAmount)
                {
                    c = true;
                }
                else
                {
                    System.out.println("\n" +  hero.getName() + " n'a pas assez de mana pour ce sort" + "\n");
                }
            }
        }

    }
    public int getNombreHero() {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choix = 0;
        c = false;
        while(!c)
        {
            System.out.print("\nChosissez le nombre de héros que vous souhaitez (max 4) : ");
            choix = scanner.nextInt();
            if(choix > 0 )
            {
                c = true;
            }
        }
        return choix;
    }

    public Hero getClasseHero() {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choix = 0;
        // System.out.print("Enter your hero name : ");
        // String name = scanner.nextLine();
        System.out.println("\nChoisissez la classe de votre héro : ");
        System.out.println("[1] Hunter \uD83E\uDD20 : le chasseur  \n  Point de vie 7 \n Defense 0 \n Mana 2)");
        System.out.println("[2] Warrior \uD83E\uDDB8\uD83C\uDFFC : le héro \n  Point de vie 5 \n Defense 3 \n Mana 2");
        System.out.println("[3] Mage \uD83E\uDDD9\uD83C\uDFFC : la magicienne \n  Point de vie 3 \n Defense 3 \n Mana 6");
        System.out.println("[4] Healer \uD83E\uDDDE : le gérisseur \n  Point de vie 3 \n Defense 3 \n Mana 5");
        c = false;
        while(!c)
        {
            System.out.print("\nEntrer le numéro correspondant à la classe choisie : ");
            choix = scanner.nextInt();
            if(choix >= 1 && choix <= 4)
            {
                c = true;
            }
        }
        switch (choix) {
            case 1 -> {
                Hunter hunter = new Hunter("Hunter Chace");
                return hunter;
            }
            case 2 -> {
                Warrior warrior = new Warrior("Warrior Adame");
                return warrior;
            }
            case 3 -> {
                Mage mage = new Mage("Mage Flora");
                return mage;
            }
            case 4 -> {
                Healer healer = new Healer("Healer Terlin");
                return healer;
            }
        }
        return null;
    }

    public int choisirItem(ArrayList<Consumable> consumables)
    {
        Scanner scanner = new Scanner(System.in);
        boolean c;
        int choix = 0;
        System.out.println("\nChoisissez votre objet : ");
        for(int t=0;t<consumables.size();t++)
        {
            System.out.println("[" + (t + 1) + "]" + " " + consumables.get(t).getName() + " Pouvoir : " + consumables.get(t).getHp());
        }
        c = false;
        while(!c)
        {
            System.out.print("\nEntrer votre choix : ");
            choix = scanner.nextInt();
            if(choix >= 1 && choix <= consumables.size())
            {
                c = true;
            }
        }
        return choix-1; //Cette ligne retourne le choix de l'utilisateur moins un.
        // Cela permet de retourner l'indice de l'objet choisi dans la liste des objets consommables,
        // plutôt que le numéro choisi par l'utilisateur
        // (qui commence à 1, alors que les indices des éléments d'une liste en Java commencent à 0).
    }

}
