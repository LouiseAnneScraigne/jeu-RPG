package util;

import rpg.*;

import java.util.ArrayList;

import rpg.Item.*;
import rpg.Hero.*;
import rpg.*;


public interface InputParser
{
    public int getAction(Hero hero, int size);
    public void choisirWeapon(Hero hero);
    public int choisirItem(ArrayList<Consumable> consumables);
    public int getNombreHero();
    public Hero getClasseHero();
    public int getCible(ArrayList<Combatant> enemies);
    public void choisirSpell(SpellCaster hero);

    public void checkForBossAttaque();
}