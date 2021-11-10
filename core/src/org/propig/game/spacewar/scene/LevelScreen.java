package org.propig.game.spacewar.scene;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.propig.game.spacewar.BaseGame;
import org.propig.game.spacewar.unit.*;
import org.propig.game.spacewar.gameconst.ScoreConst;
import org.propig.game.spacewar.utils.ExplosionPool;

public class LevelScreen extends BaseScreen{
    boolean gameOver;
    Spaceship spaceship;
    Label shieldLabel;
    Label scoreLabel;
    ForeverLevel foreverLevel = new ForeverLevel(0,0,mainStage);
    ExplosionPool explosionPool;

    int bomb;
    int score;

    @Override
    protected void initialize() {
        new Sky(0,0,mainStage);
        new Sky(0, 700, mainStage);
        new Sky(0, 1400, mainStage);



        BaseActor.setWorldBounds(worldWidth,worldHeigth);

        spaceship = new Spaceship(200,100,mainStage);
        explosionPool = new ExplosionPool(100, 150, mainStage);

        bomb = 100;

        shieldLabel = new Label("" + spaceship.shieldPower, BaseGame.labelStyle);
        shieldLabel.setColor(Color.CYAN);


        scoreLabel = new Label("" + score, BaseGame.labelStyle);
        scoreLabel.setColor(Color.RED);

        BaseActor shieldIcon = new BaseActor(0,0, uiStage);
        shieldIcon.loadTexture("spacewar/shield-icon.png");

        BaseActor scoreIcon = new BaseActor(100,0, uiStage);
        scoreIcon.loadTexture("spacewar/coin-icon.png");



        uiStage.addActor(scoreIcon);
        uiStage.addActor(scoreLabel);


        uiTable.pad(10);
        uiTable.add(shieldIcon).top();
        uiTable.add(shieldLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(scoreIcon).top();
        uiTable.add(scoreLabel).top();


        gameOver = false;
    }

    @Override
    protected void update(float delta) {
        shieldLabel.setText(""+spaceship.shieldPower);
        scoreLabel.setText(""+score);
        if(gameOver) {
            foreverLevel.remove();
            return;
        }


        for(BaseActor bullet : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.EnemyBullet")){
            bullet = (EnemyBullet) bullet;
            if(bullet.overlaps(spaceship) && !spaceship.isInvincible()){
                spaceship.shieldPower -= ((EnemyBullet) bullet).damage;
                bullet.remove();
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


        for(BaseActor supply : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Supply")){
            supply = (Supply) supply;
            if(supply.overlaps(spaceship) ) {
                if (((Supply) supply).supplyType == Supply.SupplyType.SUPPLY_Health) {
                    spaceship.health = MathUtils.clamp(spaceship.health + supply.health, spaceship.health, spaceship.maxHealth);
                } else {
                    spaceship.shieldPower = MathUtils.clamp(spaceship.shieldPower + supply.health, spaceship.shieldPower, spaceship.maxShieldPower);
                }
                supply.remove();
            }
        }


        for(BaseActor enemy : BaseActor.getList(mainStage,"org.propig.game.spacewar.unit.Enemy")){
            enemy = (Enemy)enemy;
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
            for(BaseActor laserActor : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Laser")){
                if(laserActor.overlaps(enemy)){
                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(enemy);
                    boom.setAnimationPaused(false);

                    laserActor.remove();
                    ((Enemy) enemy).health -= ((Laser)laserActor).damage;
                    if(((Enemy) enemy).health <=0) {
                        enemy.remove();
                        score += ScoreConst.LazerScore;
                    }
                }
            }

        }
    }



    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.X) {
            spaceship.warp();
        }
        if(keycode == Input.Keys.Z) {
            if(BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Laser").size() < 10)
                spaceship.shoot();
        }
        if(keycode == Input.Keys.B){
            if(bomb > 0){
                for(BaseActor enemy : BaseActor.getList(mainStage,"org.propig.game.spacewar.unit.Enemy")){

                    Explosion boom = explosionPool.obtain();
                    boom.centerAtActor(enemy);
                    boom.setAnimationPaused(false);
                    enemy.remove();
                }

                for(BaseActor bullet : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.EnemyBullet")){
                    bullet.remove();
                }
                bomb--;
            }
        }

        return false;
    }

}
