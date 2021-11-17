package org.propig.game.spacewar.scene;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.propig.game.spacewar.BaseActor;
import org.propig.game.spacewar.BaseGame;
import org.propig.game.spacewar.enemy.Enemy;
import org.propig.game.spacewar.enemy.EnemyBullet;
import org.propig.game.spacewar.enemy.EnemyCraft1;
import org.propig.game.spacewar.enemy.EnemyCraft2;
import org.propig.game.spacewar.explosion.Explosion;
import org.propig.game.spacewar.hero.Laser;
import org.propig.game.spacewar.hero.Missile;
import org.propig.game.spacewar.hero.Spaceship;
import org.propig.game.spacewar.hero.Supply;
import org.propig.game.spacewar.gameconst.ScoreConst;
import org.propig.game.spacewar.utils.EnemyBulletPool;
import org.propig.game.spacewar.utils.EnemyCraft1Pool;
import org.propig.game.spacewar.utils.EnemyCraft2Pool;
import org.propig.game.spacewar.utils.ExplosionPool;


public class LevelScreen extends BaseScreen{
    boolean gameOver;
    Spaceship spaceship;
    Label shieldLabel;
    Label scoreLabel;
    Label bombLabel;
    ForeverLevel foreverLevel;
    ExplosionPool explosionPool;

    int bomb;
    int score;
    float shootInterval=0.f;
    float missileInterval = 0.f;

    @Override
    protected void initialize() {
        new Background(0,0,mainStage);
        new Background(0, 700, mainStage);
        new Background(0, 1400, mainStage);

        EnemyBulletPool.stage = mainStage;
        EnemyBulletPool.getInstance();

        EnemyCraft1Pool.stage = mainStage;
        EnemyCraft1Pool.getInstance();

        EnemyCraft2Pool.stage = mainStage;
        EnemyCraft2Pool.getInstance();


       // walker = new Walker(300, 400, mainStage);


        BaseActor.setWorldBounds(worldWidth,worldHeigth);

        spaceship = new Spaceship(200,100,mainStage);
        explosionPool = new ExplosionPool(100, 150, mainStage);
        foreverLevel = new ForeverLevel(0,0,mainStage);


        bomb = 20;

        shieldLabel = new Label("" + spaceship.shieldPower, BaseGame.labelStyle);
        shieldLabel.setColor(Color.CYAN);


        scoreLabel = new Label("" + score, BaseGame.labelStyle);
        scoreLabel.setColor(Color.RED);

        bombLabel = new Label("" + bomb, BaseGame.labelStyle);
        bombLabel.setColor(Color.CYAN);

        BaseActor shieldIcon = new BaseActor(0,0, uiStage);
        shieldIcon.loadTexture("spacewar/shield-icon.png");

        BaseActor scoreIcon = new BaseActor(100,0, uiStage);
        scoreIcon.loadTexture("spacewar/coin-icon.png");

        BaseActor bombIcon = new BaseActor(0,0, uiStage);
        bombIcon.loadTexture("spacewar/bomb-icon.png");

        uiStage.addActor(scoreIcon);
        uiStage.addActor(scoreLabel);


        uiTable.pad(10);
        uiTable.add(shieldIcon).top();
        uiTable.add(shieldLabel).top();
        uiTable.add(bombIcon).top();
        uiTable.add(bombLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(scoreIcon).top();
        uiTable.add(scoreLabel).top();


        gameOver = false;
    }

    @Override
    protected void update(float delta) {
        shieldLabel.setText(""+spaceship.shieldPower);
        scoreLabel.setText(""+score);
        bombLabel.setText(""+bomb);
        if(gameOver) {
            foreverLevel.remove();
            return;
        }

        if(spaceship.lazerPromotion > 4){
            shootInterval +=delta;
            if(shootInterval > 0.2f) {
                spaceship.shoot();
                shootInterval -=0.2f;
            }
        }

        if(spaceship.lazerPromotion > 4){
            missileInterval +=delta;
            if(missileInterval > 1.5f) {
                spaceship.shootMissile();
                missileInterval -=1.5f;
            }
        }

        for(BaseActor bullet : BaseActor.getList(mainStage, "org.propig.game.spacewar.enemy.EnemyBullet")){
            bullet = (EnemyBullet) bullet;

            if(bullet.overlaps(spaceship) && !spaceship.isInvincible()){
                spaceship.shieldPower -= ((EnemyBullet) bullet).damage;
                EnemyBulletPool.getInstance().free(((EnemyBullet)bullet));
                if(spaceship.shieldPower <=0){
                    spaceship.shieldPower = 0;
                    Explosion boom = explosionPool.obtain();
                    boom.setAnimationPaused(false);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    gameOver = true;
                }
            }
        }


        for(BaseActor supply : BaseActor.getList(mainStage, "org.propig.game.spacewar.hero.Supply")){
            supply = (Supply) supply;
            if(supply.overlaps(spaceship) ) {
                if (((Supply) supply).supplyType == Supply.SupplyType.SUPPLY_Health) {
                    spaceship.health = MathUtils.clamp(spaceship.health + supply.health, spaceship.health, spaceship.maxHealth);
                } else if(((Supply) supply).supplyType == Supply.SupplyType.SUPPLY_Shield) {
                    spaceship.shieldPower = MathUtils.clamp(spaceship.shieldPower + supply.health, spaceship.shieldPower, spaceship.maxShieldPower);
                } else if(((Supply) supply).supplyType == Supply.SupplyType.SUPPLY_LAZERPROMOTION){
                    spaceship.lazerPromotion += 5;
                }
                supply.remove();
            }
        }


        for(BaseActor enemy : BaseActor.getList(mainStage,"org.propig.game.spacewar.enemy.Enemy")){
            enemy = (Enemy)enemy;
            if(!enemy.alive){
                tryRecycleCraft((Enemy) enemy);
                continue;
            }
            if(enemy.overlaps(spaceship) && !spaceship.isInvincible()){
                spaceship.shieldPower -= ((Enemy)enemy).damage;
                if(spaceship.shieldPower <=0){
                    spaceship.shieldPower = 0;
                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(spaceship);
                    boom.setAnimationPaused(false);

                    spaceship.remove();
                    gameOver = true;
                }
            }
            for(BaseActor laserActor : BaseActor.getList(mainStage, "org.propig.game.spacewar.hero.Laser")){
                if(laserActor.overlaps(enemy)){
                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(enemy);
                    boom.setAnimationPaused(false);

                    laserActor.remove();
                    ((Enemy) enemy).health -= ((Laser)laserActor).damage;
                    if(((Enemy) enemy).health <=0) {
                        tryRecycleCraft((Enemy) enemy);
                        score += ScoreConst.LazerScore;
                    }
                }
            }

            for(BaseActor missileActor : BaseActor.getList(mainStage, "org.propig.game.spacewar.hero.Missile")){
                if(missileActor.overlaps(enemy)){
                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(enemy);
                    boom.setAnimationPaused(false);

                    missileActor.remove();
                    ((Enemy) enemy).health -= ((Missile)missileActor).damage;
                    if(((Enemy) enemy).health <=0) {
                        tryRecycleCraft((Enemy) enemy);
                        score += ScoreConst.LazerScore;
                    }
                }
            }
        }
//
//        System.out.printf("%5d, %5d, %5d\n",
//                EnemyBulletPool.getInstance().getFree(),
//                EnemyCraft1Pool.getInstance().getFree(),
//                EnemyCraft2Pool.getInstance().getFree());
    }


    private void tryRecycleCraft(Enemy craft){
        if(craft.enemyKind == Enemy.EnemyKind.EnemyCraft1){
            EnemyCraft1Pool.getInstance().free((EnemyCraft1) craft);
        } else if( craft.enemyKind == Enemy.EnemyKind.EnemyCraft2){
            EnemyCraft2Pool.getInstance().free((EnemyCraft2) craft);
        } else {
            craft.remove();
            craft.alive=false;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.X) {
            spaceship.warp();
        }
        if(keycode == Input.Keys.Z) {
            if(BaseActor.getList(mainStage, "org.propig.game.spacewar.hero.Laser").size() < 10)
                spaceship.shoot();
        }
        if(keycode == Input.Keys.B){
            if(bomb > 0){
                for(BaseActor enemy : BaseActor.getList(mainStage,"org.propig.game.spacewar.enemy.Enemy")){
                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(enemy);
                    tryRecycleCraft((Enemy) enemy);
                }

                for(BaseActor bullet : BaseActor.getList(mainStage, "org.propig.game.spacewar.enemy.EnemyBullet")){
                    EnemyBulletPool.getInstance().free((EnemyBullet) bullet);
                }
                bomb--;
            }
        }

        return false;
    }

}
