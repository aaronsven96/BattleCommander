package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class BoardDrawer implements Disposable {
    private Batch batch;
    private ShapeDrawer drawer;
    Texture texture;

    BoardDrawer(Batch batch){
        this.batch = batch;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
    }

    public void drawHexes(boolean[][] whereToDraw){
        for(int i=0; i<whereToDraw.length; i++){
            for(int j=0; j<whereToDraw[0].length; j++){
                drawer.polygon(x, y, 6, 30,30,0,3);
            }
        }

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
