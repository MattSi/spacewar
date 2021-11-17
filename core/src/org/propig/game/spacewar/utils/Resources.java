package org.propig.game.spacewar.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Resources {
    public Animation<TextureRegion> enemyCraft1Bullet = loadTexture("spacewar/tiles/tile_0000.png");
    public Animation<TextureRegion> enemyCraft2Bullet = loadTexture("spacewar/tiles/tile_0003.png");
    public Animation<TextureRegion> explosionAsset = loadAnimationFromSheet("spacewar/explosion.png", 6,6,0.03f, false);
    public Animation<TextureRegion> enemyCraft1 = loadTexture("spacewar/ships/ship_0009.png");
    public Animation<TextureRegion> enemyCraft2 = loadTexture("spacewar/ships/ship_0005.png");
    public Animation<TextureRegion> lazerP = loadTexture("spacewar/tiles/tile_0025.png");






    private static Resources instance;

    public static Resources getInstance(){
        if (instance == null){
            instance = new Resources();
        }

        return instance;
    }
    /**
     *  Convenience method for creating a 1-frame animation from a single texture.
     *  @param fileName names of image file
     *  @return animation created (useful for storing multiple animations)
     */
    public Animation<TextureRegion> loadTexture(String fileName)
    {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }
    /**
     * Creates an animation from images stored in separate files.
     * @param fileNames array of names of files containing animation images
     * @param frameDuration how long each frame should be displayed
     * @param loop should the animation loop
     * @return animation created (useful for storing multiple animations)
     */
    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop)
    {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<>();

        for (String fileName : fileNames) {
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }

        Animation<TextureRegion> anim = new Animation<>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);

        return anim;
    }

    public static Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols, float frameDuration, boolean loop)
    {
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array<>();

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                textureArray.add( temp[r][c] );

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        return anim;
    }
}
