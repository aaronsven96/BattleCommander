package com.mygdx.game;

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
    int size;

    BoardDrawer(Batch batch, int size) {
        this.size = size;
        this.batch = batch;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
    }

    // Draws an array of hexes based on a boolean array

    public void drawHexes(boolean[][] whereToDraw) {
        int yOffset = 0;
        int xOffset = 0;
        for (int x = 0; x < whereToDraw.length; x++) {
            for (int y = 0; y < whereToDraw[x].length; y++) {
                drawer.polygon(100 + xOffset, 100 + yOffset, 6, size, size, 0, 3);
                yOffset += 2*size-10;
            }
            xOffset += 1.5 * size;
            yOffset = ((x+1) * size) -(5 * (x+1));

        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
