package org.propig.game.spacewar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceWar extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		font = new BitmapFont(Gdx.files.internal("spacewar/spacewar.fnt"),
				Gdx.files.internal("spacewar/spacewar.png"),false);
		font.setColor(Color.GREEN);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		//batch.draw(img, 0, 0);
		font.draw(batch, "我是好人北京", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		font.draw(batch, "hello libgdx", 0,0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
