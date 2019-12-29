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

    // Draws an array of hexes based on a boolean array
    int spacing = 25;
    int size = 50;
    int half = size/2;
    public void drawHexes(boolean[][] whereToDraw){
        for(int x=0; x<whereToDraw.length; x++){
            for(int y=0; y<whereToDraw[x].length; y++){
                // If the row is odd, then offset it.
                if((y+1)%2== 1) {
                    drawer.polygon( size+spacing + (size+spacing)*2 * x, 0 + (size+spacing-half) * y, 6, 50, 50, 0, 1);
                }
                else {
                    drawer.polygon( 0 + (size+spacing)*2 * x, 0 + (size+spacing-half) * y, 6, 50, 50, 0, 1);
                }

            }
        }

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
