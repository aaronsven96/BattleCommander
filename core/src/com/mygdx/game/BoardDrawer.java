package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BoardDrawer implements Disposable {
    private Batch batch;
    private ShapeDrawer drawer;
    Texture texture;
    Vector3[][] centerHexes;
    int size;

    BoardDrawer(Batch batch, int size) {
        centerHexes = calculateCenterHexes(size);
        this.size = size;
        this.batch = batch;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
    }

    // Draws an array of hexes based on a boolean array

    public void drawHexes(boolean[][] whereToDraw) {
        for(int i=0; i< centerHexes.length;i++){
            Vector3[] centerHex = centerHexes[i];
            for (int i1 = 0; i1 < centerHex.length; i1++) {
                Vector3 center = centerHex[i1];
                if(!whereToDraw[i][i1]) {
                    drawer.filledPolygon(center.x, center.y, 6, (float) (float)size, (float) size, 0, Color.RED, Color.WHITE);
                }
                drawer.polygon(center.x, center.y, 6, (float) (float)size, (float) size, 0, 3);
                drawer.circle(center.x, center.y, 30);
            }
        }
    }

    public void drawTextures(boolean[][] whereToDraw) {
        Texture texture = new Texture(Gdx.files.internal("desktop/build/resources/main/badlogic.jpg"));

        int width = 40;
        int height = 40;

        TextureRegion region1 = new TextureRegion(texture, 0, 2, width, height);
        TextureRegion region2 = new TextureRegion(texture, 0, 0, width, height);

        for (int i = 0; i < centerHexes.length; i++) {
            Vector3[] centerHex = centerHexes[i];
            for (int j = 0; j < centerHex.length; j++) {
                Vector3 center = centerHex[j];
                batch.draw(region1, center.x - (float) width / 2, center.y - (float) height / 2);
                if (!whereToDraw[i][j]) {
                    batch.draw(region2, center.x - (float) width / 2, center.y - (float) height / 2);
                }
            }
        }
    }

    private Vector3[][] calculateCenterHexes(int size){
        int yOffset = 0;
        int xOffset = 0;
        Vector3[][] centers = new Vector3[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {

                centers[x][y] = new Vector3(xOffset, yOffset, 0);
                yOffset += 2*size-10;
            }
            xOffset += 1.5 * size;
            yOffset = ((x+1) * size) -(5 * (x+1));
        }
        return centers;
    }

    public Vector3[][] getCenterHexes(){
        return centerHexes;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
