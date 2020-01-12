package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.input.GameListener;
import com.mygdx.game.models.Position;

import java.util.Arrays;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    Texture img;
    ShapeDrawer drawer;
    Texture texture;
    BoardDrawer boardDrawer;
    ScreenViewport view;
    GameListener input ;
    boolean[][] booleans = new boolean[5][5];
    OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        view = new ScreenViewport(camera);
        view.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        //camera.zoom = 1/100;
        boardDrawer = new BoardDrawer(batch, 40);
        drawer = new ShapeDrawer(batch, region);
        input = new GameListener(camera, view, boardDrawer.getCenterHexes(), drawer);
        System.out.println(boardDrawer.getCenterHexes());
        Gdx.input.setInputProcessor(input);

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
        for(boolean[] i: booleans) {
            Arrays.fill(i, Boolean.TRUE);
        }
        float time = Gdx.graphics.getDeltaTime();
        input.updateCamera(time);
        view.apply();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        if(input.getMousePosition()!= null){
            drawer.circle(input.getMousePosition().x, input.getMousePosition().y, 10);
        }
        if(input.getSelectedHex().isPresent()){
            Position p = input.getSelectedHex().get();
            booleans[p.getX()][p.getY()] = false;
        }
        for(Position p: input.getMoves().keySet()){

            Position des = input.getMoves().get(p);
            Vector2 vector2 = new Vector2(boardDrawer.getCenterHexes()[p.getX()][p.getY()].x, boardDrawer.getCenterHexes()[p.getX()][p.getY()].y);
            Vector2 vector3 = new Vector2(boardDrawer.getCenterHexes()[des.getX()][des.getY()].x, boardDrawer.getCenterHexes()[des.getX()][des.getY()].y);

            drawer.line(vector2, vector3,3);
        }

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
