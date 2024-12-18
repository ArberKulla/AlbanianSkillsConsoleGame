package utils;

public class Stats {
    private int maxHp;
    private int hp;
    private int maxMana;
    private int mana;
    private int strength;
    private int defense;
    private int speed;

    public void setNeededExp(int neededExp) {
        this.neededExp = neededExp;
    }

    private int neededExp;
    private int exp;
    private int level;

    // Constructor
    public Stats(int hp, int mana, int strength, int defense, int speed) {
        this.maxHp = hp;
        this.hp = hp;
        this.maxMana = mana;
        this.mana = mana;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
        this.neededExp = 100;
        this.exp = 0;
        this.level = 1;
    }

    public boolean addExp(int added) {
        if (exp + added >= neededExp) {
            level++;
            exp = exp + added - neededExp;
            neededExp += 20;
            return true;
        } else {
            exp += added;
            return false;
        }
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addHealth(int points) {
        if (hp + points > maxHp) {
            hp = maxHp;
        } else {
            hp += points;
        }
    }

    public void addMana(int points) {
        if (mana + points > maxMana) {
            mana = maxMana;
        } else {
            mana += points;
        }
    }

    public void addToAll(int[] stats) {
        this.maxHp += stats[0];
        this.hp += stats[0];
        this.maxMana += stats[1];
        this.mana += stats[1];
        this.strength += stats[2];
        this.defense += stats[3];
        this.speed += stats[4];
    }

    // Getters and Setters

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "\n" +
                "Level: " + level + "   EXP: " + exp + "/" + neededExp + "\n" +
                "HP= " + hp + "/" + maxHp +
                ", Mana=" + mana + "/" + maxMana +
                ", Strength=" + strength +
                ", Defense=" + defense +
                ", Speed=" + speed;
    }
}
