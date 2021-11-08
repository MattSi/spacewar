package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.propig.game.spacewar.unit.BaseActor;

public class Shield extends BaseActor {
    public Shield(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/shields.png");

        Action pulse = Actions.sequence(
                Actions.scaleTo(0.8f, 0.8f, 1),
                Actions.scaleTo(0.65f, 0.65f, 1));
        addAction(Actions.forever(pulse));
    }
}
