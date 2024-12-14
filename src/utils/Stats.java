package utils;

public class Stats {
    private int hp;
    private int mana;
    private int strength;
    private int defense;
    private int speed;

    // Constructor
    public Stats(int hp, int mana, int strength, int defense, int speed) {
        this.hp = hp;
        this.mana = mana;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
    }

    public int[] getStats(){
        return new int[] {hp, mana, strength, defense, speed};
    }

    public void changeAll(int[] stats){
        this.hp = stats[0];
        this.mana = stats[1];
        this.strength = stats[2];
        this.defense = stats[3];
        this.speed = stats[4];
    }

    // Getters and Setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "\n" +
                "HP=" + hp +
                ", Mana=" + mana +
                ", Strength=" + strength +
                ", Defense=" + defense +
                ", Speed=" + speed;
    }
}
