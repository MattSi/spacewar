package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EnemyCraftCircle extends BaseActor{
    int craftKind;
    int craftNumber;
    float timeInterval = 0f;
    float period = 0.8f;
    int x = 0;
    public EnemyCraftCircle(float x, float y, Stage s, int craftKind, int craftNumber) {
        super(x, y, s);

        this.craftKind = craftKind;
        this.craftNumber = craftNumber;
        this.x = MathUtils.random(60, 300);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(craftNumber <= 0){
            this.remove();
            return;
        }
        timeInterval += dt;
        if(timeInterval > period){
            timeInterval -= period;
            Stage s = getStage();
            if(craftKind == 1){
                new EnemyCraft1(x, 800, s, true);
            } else {
                new EnemyCraft2(x, 800, s, true);
            }
            craftNumber --;
        }
    }
}
