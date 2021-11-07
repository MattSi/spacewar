package org.propig.game.spacewar.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spaceship extends BaseActor {
    private Thrusters thrusters;
    private ThrusterEffect thrusterEffect;
    private Shield shield;
    public int shieldPower = 100;

    public Spaceship(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("spacewar/spaceship.png");
        setBoundaryPolygon(8);

        setAcceleration(1000);
        setMaxSpeed(200);
        setDeceleration(1000);
        setRotation(90);
        setScale(0.5f);

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

        shield.setOpacity(shieldPower/100f);
        if(shieldPower < 0)
            shield.setVisible(false);

        applyPhysics(delta);
        wrapAroundWorld();
        alignCamera();


    }

    public void warp(){
        if(getStage() == null)
            return;

        Warp warp1 = new Warp(0,0,getStage());
        warp1.centerAtActor(this);
        setPosition(MathUtils.random(800), MathUtils.random(600));
        Warp warp2 = new Warp(0,0,getStage());
        warp2.centerAtActor(this);
    }

    public void shoot(){
        if(getStage() ==null)
            return;

        Laser laser = new Laser(0,0,getStage());
        laser.centerAtActor(this);
        laser.setRotation(this.getRotation());
        laser.setMotionAngle(getRotation());
    }
}