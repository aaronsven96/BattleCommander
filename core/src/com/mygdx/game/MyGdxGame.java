package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.models.Position;

import javax.xml.soap.Text;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    ShapeDrawer drawer;
    Texture texture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(img, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //batch.draw(img, 0, 0);
        drawHex(100,100,drawer);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    private void drawHex(int x, int y, ShapeDrawer drawer) {
        drawer.polygon(x, y, 6, 30,30,0,3);
        //drawer.filledPolygon(x,y,6,30);
    }
}
