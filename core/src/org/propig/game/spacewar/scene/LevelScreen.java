package org.propig.game.spacewar.scene;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import org.propig.game.spacewar.BaseGame;
import org.propig.game.spacewar.unit.*;
import org.propig.game.spacewar.gameconst.DamageConst;
import org.propig.game.spacewar.gameconst.ScoreConst;

public class LevelScreen extends BaseScreen{
    boolean gameOver;
    Spaceship spaceship;
    Label healthLabel;
    Label shieldLabel;
    Label scoreLabel;

    int leftRocks;
    int score;
    float timeInterval=0;
    int limit=1;



    @Override
    protected void initialize() {
        new Sky(0,0,mainStage);
        new Sky(0, 700, mainStage);
        new Sky(0, 1400, mainStage);
        BaseActor.setWorldBounds(worldWidth,worldHeigth);

        spaceship = new Spaceship(200,100,mainStage);

        //new Rock(300, 500, mainStage);
//        new Rock(300, 300, mainStage);
//        new Rock(300, 500, mainStage);
//        new Rock(200, 500, mainStage);
//        new Rock(100, 500, mainStage);
//        new Rock(100, 600, mainStage);
//        new Rock(100, 600, mainStage);
//        new Rock(200, 500, mainStage);

//        new Enemy1(100, 800, mainStage);
//        new Enemy1(150, 800, mainStage);
//        new Enemy1(200, 800, mainStage);

        leftRocks = 0;

        healthLabel = new Label("" + spaceship.health, BaseGame.labelStyle);
        healthLabel.setColor(Color.RED);

        shieldLabel = new Label("" + spaceship.shieldPower, BaseGame.labelStyle);
        shieldLabel.setColor(Color.CYAN);


        scoreLabel = new Label("" + leftRocks, BaseGame.labelStyle);
        scoreLabel.setColor(Color.RED);

        BaseActor healthIcon = new BaseActor(0,0, uiStage);
        healthIcon.loadTexture("spacewar/heart-icon.png");

        BaseActor shieldIcon = new BaseActor(0,0, uiStage);
        shieldIcon.loadTexture("spacewar/shield-icon.png");

        BaseActor scoreIcon = new BaseActor(100,0, uiStage);
        scoreIcon.loadTexture("spacewar/coin-icon.png");

        uiStage.addActor(healthLabel);
        uiStage.addActor(healthIcon);

        uiStage.addActor(healthLabel);
        uiStage.addActor(healthIcon);

        uiStage.addActor(scoreIcon);
        uiStage.addActor(scoreLabel);


        uiTable.pad(10);
        uiTable.add(healthIcon).top();
        uiTable.add(healthLabel).top();
        uiTable.add(shieldIcon).top();
        uiTable.add(shieldLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(scoreIcon).top();
        uiTable.add(scoreLabel).top();


        gameOver = false;
    }

    @Override
    protected void update(float delta) {
        healthLabel.setText(""+spaceship.health);
        shieldLabel.setText(""+spaceship.shieldPower);
        scoreLabel.setText(""+score);
        if(gameOver)
            return;

        timeInterval += delta;
        if(timeInterval > 0.6f && limit>0){
            timeInterval=0.0f;
            limit--;
            //new Enemy1(150, 800, mainStage);
            new Supply(250, 800, mainStage, Supply.SupplyType.SUPPLY_Health);

            new Supply(350, 800, mainStage, Supply.SupplyType.SUPPLY_Shield);
        }

        if(BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Rock").size() < 8 &&
        leftRocks > 0) {
            new Rock(MathUtils.random(0,300), MathUtils.random(700, 730), mainStage);
            leftRocks--;
        }


        for(BaseActor bullet : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.EnemyBullet")){
            bullet = (EnemyBullet) bullet;
            if(bullet.overlaps(spaceship) && !spaceship.isInvincible()){
                spaceship.shieldPower -= ((EnemyBullet) bullet).damage;
                if(spaceship.shieldPower <=0){
                    spaceship.shieldPower = 0;
                    Explosion boom = new Explosion(0,0,mainStage);
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


        for(BaseActor rockActor : BaseActor.getList(mainStage,"org.propig.game.spacewar.unit.Rock")){
            rockActor = (Rock)rockActor;
            if(rockActor.overlaps(spaceship) && !spaceship.isInvincible()){
                spaceship.shieldPower -= DamageConst.RockDamage;
                if(spaceship.shieldPower <=0){
                    spaceship.shieldPower = 0;
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    gameOver = true;
                }else{
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(rockActor);
                    ((Rock) rockActor).health -= DamageConst.SpaceshipDamage;
                    if(((Rock) rockActor).health <=0) {
                        rockActor.remove();
                        score += ScoreConst.CollisionScore;
                    }
                }
            }
            for(BaseActor laserActor : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Laser")){
                if(laserActor.overlaps(rockActor)){
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    ((Rock) rockActor).health -= DamageConst.BulletDamage;
                    if(((Rock) rockActor).health <=0) {
                        rockActor.remove();
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
            if(BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Laser").size() < 5)
                spaceship.shoot();
        }
        return false;
    }

}
