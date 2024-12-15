import enums.GameState;
import utils.Stats;

public class Battle {
    boolean isFinished = false;
    Enemy enemy;
    Player player;
    MainMenu menu = MainMenu.getInstance();
    GameSession session = GameSession.getInstance();

    public Battle(){
        enemy = new Enemy(session.player.fightsWon);
        menu.textArea.append("\n!!! You have been attacked by "+enemy.name);
        if(enemy.stats.getSpeed()>session.player.stats.getSpeed()){
            enemyTurn();
        }
    }

    public void enemyTurn(){
        enemyAttackPlayer(damaeTaken(enemy.stats.getStrength(),session.player.stats.getDefense()));
    }

    public void enemyAttackPlayer(int damage){
        if(isFinished){return;}
        Player player = session.player;
        if(player.isBlocking){
            damage=damage/2;
            player.isBlocking=false;
            player.stats.addMana(20);
        }
        player.stats.addHealth(-damage);
        int maxhp = player.stats.getMaxHp();
        int hp = player.stats.getHp();

        menu.textArea.append("\n"+enemy.name+" attacks!\n"+player.name+" took "+damage+" Damage! HP: "+hp+"/"+maxhp);

        if(hp<=0){
            menu.startDialog(DialogEntities.GAME_OVER);
            isFinished=true;
        }
    }

    public void playerAttackEnemy(int damage){
        if(isFinished){return;}
        damage = damaeTaken(damage,enemy.stats.getDefense());
        enemy.stats.addHealth(-damage);
        int maxhp = enemy.stats.getMaxHp();
        int hp = enemy.stats.getHp();


        menu.textArea.append("\n"+enemy.name+" took "+damage+" Damage! HP: "+hp+"/"+maxhp);
        enemyTurn();

        if(hp<=0){
            battleWon();
        }
    }

    public void battleWon(){
        String levelUp = session.player.addExp(enemy.stats.getExp());
        menu.textArea.append("\nGame: You won the battle and got "+enemy.stats.getExp()+" exp!"+levelUp);
        session.gameState = GameState.OPEN_WORLD;
        isFinished=true;
    }

    public int damaeTaken(int attack, int defense){
        return Math.max(attack - defense, 0);
    }


}
