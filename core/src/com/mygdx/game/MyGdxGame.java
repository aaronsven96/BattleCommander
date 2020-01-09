package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    ShapeDrawer drawer;
    Texture texture;
    BoardDrawer boardDrawer;
    boolean[][] booleans = new boolean[5][5];

    @Override
    public void create() {
        batch = new SpriteBatch();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(img, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
        boardDrawer = new BoardDrawer(batch, 40);
        for(boolean[] i: booleans) {
            Arrays.fill(i, Boolean.TRUE);
        }


       /* booleans[0][0] = Boolean.FALSE;
        booleans[1][1] = Boolean.FALSE;
        booleans[1][2] = Boolean.FALSE;
        booleans[2][3] = Boolean.FALSE;
        booleans[2][4] = Boolean.FALSE;*/

        /*// This will be use to shape the board
        for(int x=0; x<5; x++){
            for(int y=0; y<5; y++){
                    if(x == y)
                        booleans[x][y] = Boolean.FALSE;
            }
        }*/
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        boardDrawer.drawHexes(booleans);
        //batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
