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
    int worldWidth;
    int worldHeight;
    Spaceship spaceship;
    Label healthLabel;
    Label scoreLabel;

    ProgressBar progressBar;
    int leftRocks;
    int score;



    @Override
    protected void initialize() {
        new Sky(0,0,mainStage);
        new Sky(0, 700, mainStage);
        new Sky(0, 1400, mainStage);
        //new TiledActor(0,0, mainStage);
        BaseActor.setWorldBounds(500,700);

        spaceship = new Spaceship(200,100,mainStage);

        new Rock(300, 500, mainStage);
//        new Rock(300, 300, mainStage);
//        new Rock(300, 500, mainStage);
//        new Rock(200, 500, mainStage);
//        new Rock(100, 500, mainStage);
//        new Rock(100, 600, mainStage);
//        new Rock(100, 600, mainStage);
//        new Rock(200, 500, mainStage);

        leftRocks = 0;

        healthLabel = new Label(" x " + spaceship.shieldPower, BaseGame.labelStyle);
        healthLabel.setColor(Color.RED);

        scoreLabel = new Label(" x " + leftRocks, BaseGame.labelStyle);
        scoreLabel.setColor(Color.RED);

        BaseActor healthIcon = new BaseActor(0,0, uiStage);
        healthIcon.loadTexture("spacewar/heart-icon.png");

        BaseActor scoreIcon = new BaseActor(100,0, uiStage);
        scoreIcon.loadTexture("spacewar/coin-icon.png");

        uiStage.addActor(healthLabel);
        uiStage.addActor(healthIcon);

        uiStage.addActor(scoreIcon);
        uiStage.addActor(scoreLabel);


        uiTable.pad(10);
        uiTable.add(healthIcon).top();
        uiTable.add(healthLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(scoreIcon).top();
        uiTable.add(scoreLabel).top();


        gameOver = false;
    }

    @Override
    protected void update(float delta) {
        healthLabel.setText(" x " + spaceship.shieldPower);
        scoreLabel.setText(" x " + score);
        if(gameOver)
            return;


        if(BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Rock").size() < 8 &&
        leftRocks > 0) {
            new Rock(MathUtils.random(0,300), MathUtils.random(700, 730), mainStage);
            leftRocks--;
        }

        for(BaseActor rockActor : BaseActor.getList(mainStage,"org.propig.game.spacewar.unit.Rock")){
            if(rockActor.overlaps(spaceship)){
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
                    rockActor.remove();
                    score += ScoreConst.CollisionScore;
                }
            }

            for(BaseActor laserActor : BaseActor.getList(mainStage, "org.propig.game.spacewar.unit.Laser")){
                if(laserActor.overlaps(rockActor)){
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    rockActor.remove();
                    score += ScoreConst.LazerScore;
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
