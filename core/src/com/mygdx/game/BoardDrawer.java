package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.input.GameListener;
import com.mygdx.game.models.Command;
import com.mygdx.game.models.Orders;
import com.mygdx.game.models.Position;

import java.util.Arrays;

import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.mygdx.game.GameUtils.getVector2;

public class BoardDrawer extends Actor implements Disposable {
    private ShapeDrawer drawer;
    Texture texture;
    Vector3[][] centerHexes;
    GameListener gameListener;
    Command command;
    Orders orders = new Orders(1,1, Arrays.asList(new Command("attack", 1, new Position(1,1))), new Position(2,2));
    int size;

    public BoardDrawer(int size, Viewport viewport) {
        centerHexes = calculateCenterHexes(size);
        gameListener = new GameListener(viewport, centerHexes);
        Gdx.input.setInputProcessor(gameListener);
        this.size = size;

    }

    private void initDrawer(Batch batch){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
    }

    public void drawOrders(){
        orders.goToFirstCommand();
        Position start = orders.getStartPosition();
        gameListener.updateCamera(Gdx.graphics.getDeltaTime());
        while(orders.nextCommand()){
            drawer.line(getVector2(centerHexes[start.getX()][start.getY()]), getVector2(centerHexes[orders.getCurrentCommand().getTargetPosition().getX()][orders.getCurrentCommand().getTargetPosition().getY()]), 4);
            start = orders.getCurrentCommand().getTargetPosition();
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        if(drawer == null){
            initDrawer(batch);
        }
        drawOrders();
        drawHexes(null);
    }
    // Draws an array of hexes based on a boolean array

    public void drawHexes(boolean[][] whereToDraw) {
        for(int i=0; i< centerHexes.length;i++){
            Vector3[] centerHex = centerHexes[i];
            for (int i1 = 0; i1 < centerHex.length; i1++) {
                Vector3 center = centerHex[i1];
                //if(!whereToDraw[i][i1]) {
                    //drawer.filledPolygon(center.x, center.y, 6, (float) (float)size, (float) size, 0, Color.RED, Color.WHITE);
                //}
                drawer.polygon(center.x, center.y, 6, (float) (float)size, (float) size, 0, 3);
                drawer.circle(center.x, center.y, 30);
            }
        }
    }

    public void drawTextures(boolean[][] whereToDraw) {
        //Texture texture = new Texture(Gdx.files.internal("desktop/build/resources/main/badlogic.jpg"));
        int width = 40;
        int height = 40;
        //TextureRegion region1 = new TextureRegion(texture, 0, 2, width, height);
        //TextureRegion region2 = new TextureRegion(texture, 0, 0, width, height);

        for (int i = 0; i < centerHexes.length; i++) {
            Vector3[] centerHex = centerHexes[i];
            for (int j = 0; j < centerHex.length; j++) {
                Vector3 center = centerHex[j];
                //batch.draw(region1, center.x - (float) width / 2, center.y - (float) height / 2);
                if (!whereToDraw[i][j]) {
                    //batch.draw(region2, center.x - (float) width / 2, center.y - (float) height / 2);
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
