package org.propig.game.spacewar.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.propig.game.spacewar.BaseActor;

public class Spaceship extends BaseActor implements Runnable {
    private Thrusters thrusters;
    private ThrusterEffect thrusterEffect;
    private Shield shield;
    public int shieldPower = 100;
    public int maxShieldPower = 100;
    private boolean canWarp = false;
    public int lazerPromotion = 0;

    public Spaceship(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("spacewar/spaceship.png");
        setBoundaryPolygon(8);

        setAcceleration(1000);
        setMaxSpeed(300);
        setDeceleration(1000);
        setRotation(90);
        setScale(0.5f);
        health = 50;
        maxHealth = 100;

        thrusters = new Thrusters(0,0,s);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(),
                getHeight()/2 - thrusters.getHeight()/2);

        shield = new Shield(0,0,s);

        addActor(shield);
        shield.centerAtPosition(getWidth()/2, getHeight()/2);

        thrusterEffect = new ThrusterEffect();
        thrusterEffect.setPosition(0, 32);
        thrusterEffect.setRotation(90);
        thrusterEffect.setScale(0.25f);
        addActor(thrusterEffect);

        canWarp = true;
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        float degreesPerSecond = 120; // rotation speed


        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            accelerationAtAngle(180);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            accelerationAtAngle(0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            accelerationAtAngle(90);
            thrusterEffect.start();
        } else {
            //thrusters.setVisible(false);
            thrusterEffect.stop();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            accelerationAtAngle(270);
        }

        shield.setOpacity(shieldPower*1.0f/maxShieldPower);
        if(shieldPower < 0)
            shield.setVisible(false);

        applyPhysics(delta);
        wrapAroundWorld();
        alignCamera();

    }

    public void warp(){
        if(getStage() == null  ) {
            return;
        }

        if(canWarp){
            return;
        }

        Warp warp1 = new Warp(0,0,getStage());
        warp1.centerAtActor(this);
        setPosition(MathUtils.random(550), MathUtils.random(700));
        Warp warp2 = new Warp(0,0,getStage());
        warp2.centerAtActor(this);


        Action pulse = Actions.sequence(
                Actions.fadeOut(0.2f),
                Actions.fadeIn(0.2f));
        addAction(Actions.run(this));
        addAction(Actions.after(Actions.repeat(7, pulse)));
        addAction(Actions.after(Actions.run(this)));


    }

    public void shoot(){
        if(getStage() ==null)
            return;

        Laser laser = new Laser(0,0,getStage());
        laser.centerAtActor(this);
        laser.setRotation(this.getRotation());
        laser.setMotionAngle(getRotation());
        laser.damage += lazerPromotion;


    }

    public void shootMissile(){
        Missile missile1 = new Missile(0,0, getStage());
        missile1.leftAtActor(this);
        missile1.setMotionAngle(90);

        Missile missile2 = new Missile(0,0, getStage());
        missile2.rightAtActor(this);
        missile2.setMotionAngle(90);
    }

    @Override
    public void run() {
       canWarp = !canWarp;
    }

    public boolean isInvincible (){
        return canWarp;
    }
}
