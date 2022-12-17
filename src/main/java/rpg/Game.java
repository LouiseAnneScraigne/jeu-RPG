package rpg;

import util.InputParser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import javax.sound.sampled.*;
import java.util.List;
import java.util.Scanner;


public class Game {

    private ArrayList<Consumable> consumables = new ArrayList<>();
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Combatant> heros = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Enemy> boss = new ArrayList<>();
    private ArrayList<String> EnemiesNames = new ArrayList<>();
    private ArrayList<String> BossNames = new ArrayList<>();

    private boolean playing = true, comeBoss = false;

    private InputParser parser;
    private Combatant actuelCombatant;

    private List<Combatant> combatants; // "Combatants" : Les héros et les ennemis mélangés


    public static Game context;

    public Game(InputParser parser)
    {
        clearConsole();
        this.debutGame();
        this.parser = parser;
        number = this.parser.getNombreHero();
        initialiseInventaire(number);
        initialiseNames();

        for(int i = 0; i< number; i++)
        {
            clearConsole();
            this.heroes.add(parser.getClasseHero());
        }

        for(int i = 0; i< number; i++)
        {
            Random random = new Random();
            int d = random.nextInt(6 - 4 + 1) + 4;
            int max = this.EnemiesNames.size() - 1;
            int n = random.nextInt(max - 0 + 1);
            Enemy enemy = new Enemy(this.EnemiesNames.get(n),4,d);
            this.enemies.add(enemy);
            this.EnemiesNames.remove(n);
        }

        Random random = new Random();
        int max = this.BossNames.size() - 1;
        int n = random.nextInt(max - 0 + 1);
        Enemy boss = new Enemy(this.BossNames.get(n),15, 5);
        this.boss.add(boss);
    }

    private void debutGame(){
        displayMessage("Bienvenue dans Mini CZAM, le plus simple des RPGs ! ");
    }


    public static void playGame() {
        if (Game.context != null) {
            throw new RuntimeException
                    ("Impossible de lancer plusieurs fois la partie...");
        }
        Game.context = new Game();
        Game.context.startCombat();
    }

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File mp3File = new File("Wilderness Travel  RPG Exploration Music 1 Hour  Instrumental.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3File);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        while(this.playing)
        {
            seeInfo();
            playHeroes();

            if(this.comeBoss && this.playing)
            {
               displayMessage("\n Le boss contre attaque");
                playBoss();

            }
            else if(this.playing)
            {
                // enemies plays
                displayMessage("\n Les ennemies contre-attaquent");
                playEnemy();
            }
        }
    }

    private void seeInfo() {
    }

    public enum Status {START_COMBAT, HERO_TURN, ENEMY_TURN, END_GAME}
    public Status status;

    public List<String> getHeroesStatus() {
        List<String> heroesStatus = new ArrayList<>();
        for (Combatant hero: this.heroes) {
            heroesStatus.add
                    ( hero.getClass().getSimpleName()
                            + "(" + hero.getHealthPoint() + ")"
                    );
        }
        return heroesStatus;
    }
    public List<String> getEnemiesStatus() {
        List<String> enemyStatus = new ArrayList<>();
        for (Combatant enemy: this.enemies) {
            enemyStatus.add
                    ( enemy.getClass().getSimpleName()
                            + "(" + enemy.getHealthPoint() + ")"
                    );
        }
        return enemyStatus;
    }
    ListIterator<Combatant> combatantListIterator;



    // L'instanciation de "Game" ne peut se faire que par "playGame"
    public Game() {}

    private void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush(); //vide la mémoire
    }

    public static int number;


    private void playEnemy()
    {
        Random random = new Random();
        // Pour chaque ennemi, sélectionner un héros au hasard et le faire combattre
        for (Enemy actuel : this.enemies)
        {
            int bound = Math.max(this.heroes.size() - 1, 0); // Garantir que la limite est positive
            int index = random.nextInt(bound + 1);

            // S'assurer que l'index 0 est valide
            if (this.heroes.size() > 0 && index == 0) {
                Hero hero = this.heroes.get(index);
                displayMessage(actuel.getName() + " inflige " + actuel.getDamage() + " dommages à " + hero.getName());
                actuel.fight(hero);

                // Si le héros est tué, le retirer de la liste des héros
                if (hero.getHealthPoint() == 0) {
                    this.heroes.remove(hero);
                }

                // Si tous les héros sont morts, afficher un message de game over et arrêter la partie
                if (this.heroes.size() == 0) {
                    displayMessage("\n Tous les héros sont morts... \n GAME OVER !");
                    this.playing = false;
                }
            }
        }

    }

    private void playHeroes()
    {
        ArrayList<Combatant> actuelEnemy;
        Random random = new Random();
        for (Hero hero : this.heroes) {
            Hero actuel = (Hero) hero;
            clearConsole();
            afficherInventaire();
            afficherHeroes();
            if (!this.comeBoss) {
                afficherEnemies();
                actuelEnemy = (ArrayList<Combatant>) this.enemies.clone();
            } else {
                afficherBoss();
                actuelEnemy = (ArrayList<Combatant>) this.boss.clone();
            }
            System.out.println(" \n" + actuel.getName() + " doit maintenant jouer !");

            int choix = this.parser.getAction(actuel, this.consumables.size());
            int cible;
            boolean actionFinished = false;
            while (!actionFinished) {
                switch (choix) {
                    case 1 -> {
                        if (actuel instanceof SpellCaster) {
                            if (actuel instanceof Healer) {
                                if (((SpellCaster) actuel).getSpellName().equals("Ritual d'asssainissement")) {
                                    cible = parser.getCible(this.heros);
                                    actuel.fight(this.heroes.get(cible));
                                } else {
                                    cible = parser.getCible(actuelEnemy);
                                    actuel.fight(actuelEnemy.get(cible));
                                    if (actuelEnemy.get(cible).getHealthPoint() == 0) {
                                        actuelEnemy.remove(cible);
                                    }
                                }
                            }
                        }
                        if (!(actuel instanceof Healer)) {
                            cible = parser.getCible(actuelEnemy);
                            actuel.fight(actuelEnemy.get(cible));

                            if (actuelEnemy.get(cible).getHealthPoint() == 0) {
                                actuelEnemy.remove(cible);
                            }
                        }
                        actionFinished = true;
                    }
                    case 2 -> {
                        if (actuel instanceof SpellCaster) {
                            parser.choisirSpell((SpellCaster) actuel);
                        } else {
                            parser.choisirWeapon(actuel);
                        }
                        choix = 1;
                    }
                    case 3 -> {
                        int item = parser.choisirItem(this.consumables);
                        actuel.choisirItem(this.consumables.get(item));
                        this.consumables.remove(item);
                        actionFinished = true;
                    }
                    case 4 -> actionFinished = true;
                }
            }

            if ((actuelEnemy.size() == 0) && !this.comeBoss) {
                System.out.println("Tous les ennemis sont morts, le boss final se prépare");
                this.comeBoss = true;
            } else if (this.comeBoss && actuelEnemy.size() == 0) {
                System.out.println("Bravo, vous avez vaincu tous les ennemies. Vous avez remporté la partie !");
                break;
            } else {
                parser.checkForBossAttaque();
                if (!this.comeBoss)
                    this.enemies = (ArrayList<Enemy>) actuelEnemy.clone();
                else
                    this.boss = (ArrayList<Enemy>) actuelEnemy.clone();
            }
        }

    }


    private void playBoss()
    {
        Random random = new Random();
        for(int i=0; i < (this.heroes.size()/2)+0.5; i++)
        {
            int max = this.heroes.size() - 1;
            int t = random.nextInt(max - 0 + 1) + 0;
            Enemy actuel = (Enemy) this.boss.get(0);
            System.out.println(actuel.getName() + " inflige " + actuel.getDamage() + " dommages à " + this.heroes.get(t).getName());
            actuel.fight(this.heroes.get(t));
            if(this.heroes.get(t).getHealthPoint() == 0)
            {
                this.heroes.remove(t);
            }
        }

        if(this.heroes.size() == 0)
        {
            System.out.println("\n Tous les héros sont morts... \n GAME OVER !");
            this.playing = false;
        }

    }


    public void generateCombat() {
        playHeroes();
        playEnemy();
        playBoss();
        shuffleFighters();
        // Initialise un "curseur" pour parcourir la liste des combattants
        combatantListIterator = combatants.listIterator();
    }

    public void startCombat() {
        // Combat avec de nouveaux ennemis tant qu'il y a des héros actifs
        if (this.heroes.size() > 0) {
            this.status = Status.START_COMBAT;
            generateCombat();
        } else {
            this.status = Game.Status.END_GAME;
        }
    }

    private void initialiseNames()
    {
        this.EnemiesNames.add("Sly");
        this.EnemiesNames.add("Megahertz");
        this.EnemiesNames.add("Vortex");
        this.EnemiesNames.add("Black Death");

        this.BossNames.add("Big BOSS");
    }

    private void initialiseInventaire(int n)
    {
        Random random = new Random();
        int i = 0;
        while(i<n)
        {
            int r = random.nextInt(6 - 0 + 1) + 0;

            switch(r)
            {
                case 0:
                    this.consumables.add(new Potion("potion endurance \uD83E\uDDCB"));
                    break;
                case 1:
                    this.consumables.add(new Potion("potion mystérieuse \uD83C\uDF78"));
                    break;
                case 2:
                    this.consumables.add(new Food("verre de lait \uD83E\uDD5B"));
                    break;
                case 3:
                    this.consumables.add(new Food("salade grecque \uD83E\uDD57"));
                    break;
                case 4:
                    this.consumables.add(new Food("oeufs au plat \uD83C\uDF73"));
                    break;
                case 5:
                    this.consumables.add(new Food("fromage \uD83E\uDDC0"));
                    break;
                case 6:
                    this.consumables.add(new Food("cuisse de poulet \uD83C\uDF57"));
                    break;
            }
            i++;
        }
    }

    private void afficherHeroes()
    {
        System.out.print("Heros :");
        for(int i=0;i<this.heroes.size();i++)
        {
            if(i!=0)
            {
                System.out.println("-");
            }
            Combatant actuel = heroes.get(i);
            System.out.print(" " + actuel.getName() + "(" + actuel.getHealthPoint() +"\uD83E\uDE84"+ "-" + actuel.getDefense() + "\uD83D\uDEE1" + "-" + actuel.getMana()+ "♥" + ")" + " ");
        }
    }

    private void afficherEnemies()
    {
        displayMessage("\nEnemies :");
        for(int i=0;i<this.enemies.size();i++)
        {
            if(i!=0)
            {
                System.out.println("-");
            }
            Combatant actuel = enemies.get(i);
            System.out.print(" " + actuel.getName() + "(" + actuel.getHealthPoint() +"♥" + ")"+ " ");
        }
    }

    private void afficherBoss()
    {
        System.out.print("Boss :");
        System.out.print(" " + this.boss.get(0).getName() + "(" + this.boss.get(0).getHealthPoint() +"♥" + ")"+ " " );
        System.out.println(" ]\n");
    }

    private void afficherInventaire() // affiche l'inventaire du héros
    {
        System.out.print("Inventaire :");
        for(int i=0;i<this.consumables.size();i++) //parcourt la liste des objets consommables du héros et affiche le nom de chaque objet.
        {
            if(i!=0)
            {
                System.out.print(" |");
            }
            System.out.print(" " + consumables.get(i).getName());
        }
        System.out.println(" ]");
    }


    // Mélange les héros avec les ennemis dans une liste pour le combat
    private void shuffleFighters() {
        this.combatants = new ArrayList<>();
        this.combatants.addAll(this.heroes);
        this.combatants.addAll(this.enemies);
        Collections.shuffle(this.combatants);
    }

    public void startNextFighterTurn() {

        if (this.heroes.size() == 0) {
            this.status = Game.Status.END_GAME;
        } else if (enemies.size() == 0) {
            this.status = Game.Status.START_COMBAT;
            generateCombat();
        } else {

            // Récupère le combattant suivant en déplaçant le curseur de liste
            if (!combatantListIterator.hasNext()) {
                // Si on est à la fin de la liste, l'itérateur est réinitialisé
                combatantListIterator = combatants.listIterator();
            }
            this.actuelCombatant = combatantListIterator.next();

            if (this.actuelCombatant instanceof Hero) {
                //--> "instanceof" permet de valider si la variable "fighter",
                //    qui est de type "Fighter", contient bien une instance de la
                //    sous-classe "Hero".
                this.status = Game.Status.HERO_TURN;
            } else {
                this.status = Game.Status.ENEMY_TURN;
            }

        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
