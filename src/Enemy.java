import interfaces.BattleEntity;
import utils.Stats;

import java.util.Random;

public class Enemy{
    String[] randomNamed = {"Grimfang Marauder", "Duskscale Raider", "Rotclaw Scavenger", "Ashen Wisp", "Brambleback Prowler", "Hollowshade Trickster", "Cragtooth Gnoll", "Rustvine Stalker", "Emberpaw Jackal", "Cinderspit Imp", "Frostbite Goblin", "Thornspire Bandit", "Dreadgleam Cultist", "Riftwing Harrier", "Swamplurker Leech", "Boneclaw Ravager", "Shalegrip Kobold", "Gutterfang Thief", "Murkveil Shadow", "Shardspike Vermin"};

    Stats stats;
    String name;

    public Enemy(int fightWon){
        double multiplier = 1+0.1*(fightWon-1);
        Random random = new Random();
        name = randomNamed[random.nextInt(randomNamed.length)];

        int hp = (int) (Math.random()*10*multiplier+50*multiplier);
        int mana = (int) (Math.random()*10*multiplier+20*multiplier);
        int strength = (int) (Math.random()*5*multiplier+15*multiplier);
        int defense = (int) (Math.random()*5*multiplier+5*multiplier);
        int speed = (int) (Math.random()*3*multiplier+2*multiplier);
        int exp =  (int) (Math.random()*10*multiplier+50*multiplier);

        stats = new Stats(hp, mana, strength, defense, speed);
        stats.setExp(exp);
        stats.setNeededExp((int) (exp*multiplier+10*multiplier));
        stats.setLevel((int) multiplier);
    }

    public String showStats(){
        return name+" Stats:"+stats.toString();
    }

}
