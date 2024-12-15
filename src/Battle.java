import enums.GameState;

import java.util.Random;

public class Battle {
    boolean isFinished = false;
    Enemy enemy;
    MainMenu menu = MainMenu.getInstance();
    GameSession session = GameSession.getInstance();

    public Battle() {
        enemy = new Enemy(session.player.fightsWon);
        menu.textArea.append("\n!!! You have been attacked by " + enemy.name+"\n"+enemy.showStats());
        if (enemy.stats.getSpeed() > session.player.stats.getSpeed()) {
            enemyTurn();
        }
    }

    public void enemyTurn() {
        enemy.isBlocking=false;
        int rnd = (int) Math.floor(Math.random()*10+1);
        switch (rnd){
            case (1): case (2): case (3): case (4): case(5):
                menu.textArea.append("\n" + enemy.name + " attacks!");
            enemyAttackPlayer(damaeTaken(enemy.stats.getStrength(), session.player.stats.getDefense()));
            break;
            case (6): case(7):
                int curMana = enemy.stats.getMana();
                if(curMana>20) {
                    menu.textArea.append("\n" + enemy.name + " casts a spell!");
                    enemyAttackPlayer(damaeTaken(enemy.stats.getStrength() + 10, session.player.stats.getDefense()));
                }
                else{
                    menu.textArea.append("\n" + enemy.name + " attacks!");
                    enemyAttackPlayer(damaeTaken(enemy.stats.getStrength(), session.player.stats.getDefense()));
                }
                break;
            case(8):
                menu.textArea.append("\n" + enemy.name + " is preparing his guard! +20 Mana!");
                enemy.isBlocking=true;
                break;
            case(9):
                menu.textArea.append("\n" + enemy.name + " drank a potion! +20 Mana!");
                break;
            case(10):
                menu.textArea.append("\n" + enemy.name + " drank a potion! +20 Health!");
                break;
        }

    }

    public void enemyAttackPlayer(int damage) {
        if (isFinished) {
            return;
        }
        Player player = session.player;
        if (player.isBlocking) {
            damage = damage / 2;
            player.stats.addMana(20);
        }
        player.stats.addHealth(-damage);
        int maxhp = player.stats.getMaxHp();
        int hp = player.stats.getHp();

        menu.textArea.append("\n" + player.name + " took " + damage + " Damage! HP: " + hp + "/" + maxhp);

        if (hp <= 0) {
            menu.startDialog(DialogEntities.GAME_OVER);
            isFinished = true;
        }
    }

    public void playerAttackEnemy(int damage) {
        if (isFinished) {
            return;
        }
        damage = damaeTaken(damage, enemy.stats.getDefense());
        if(enemy.isBlocking){damage=damage/2;}
        enemy.stats.addHealth(-damage);
        int maxhp = enemy.stats.getMaxHp();
        int hp = enemy.stats.getHp();


        menu.textArea.append("\n" + enemy.name + " took " + damage + " Damage! HP: " + hp + "/" + maxhp);
        enemyTurn();

        if (hp <= 0) {
            battleWon();
        }
    }

    public void battleWon() {
        String levelUp = session.player.addExp(enemy.stats.getExp());
        menu.textArea.append("\nGame: You won the battle and got " + enemy.stats.getExp() + " exp!" + levelUp);
        session.gameState = GameState.OPEN_WORLD;
        isFinished = true;
    }

    public int damaeTaken(int attack, int defense) {
        return Math.max(attack - defense, 0);
    }


}
