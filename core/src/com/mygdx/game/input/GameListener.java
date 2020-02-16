package com.mygdx.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.models.Position;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameListener implements InputProcessor {

    private static final float ZOOM_UPPER_BOUND = 3;
    private static final float ZOOM_LOWER_BOUND = 1;
    private static final float CAMERA_PAN_SPEED = 4;
    private static final float MAP_WIDTH_UPPER_BOUND = 1000;
    private static final float MAP_WIDTH_LOWER_BOUND = 0;
    private static final float MAP_DEPTH_UPPER_BOUND = 1000;
    private static final float MAP_DEPTH_LOWER_BOUND = 0;
    private Map<Integer, Boolean> isKeyPressed = new HashMap<>();
    private Optional<Position> selectedHex = Optional.empty();
    Vector3[][] centers;
    private Vector2 touchedDown;
    Map<Position, Position> moves = new HashMap<>();
    Vector3 currentPos;
    HexBoardAdapter adapter;
    private Position leftClick;
    private Position rightClick;
    private final Viewport viewport;
    private final OrthographicCamera camera;
    private float currentZoom = 0;
    private float elapsedTime = 0;

    /**
     * Constructor should take in values that the class will manipulate, camera, move ,etc
     */
    public GameListener(Viewport viewport, Vector3[][] centers) {
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.viewport = viewport;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        this.centers = centers;
        adapter = new HexBoardAdapter(centers, 30);
    }

    @Override
    public boolean keyDown(int keycode) {
        isKeyPressed.put(keycode, true);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        isKeyPressed.put(keycode, false);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchedDown = new Vector2(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Vector3 pos = camera.unproject(new Vector3(screenX, screenY,0));
        if(touchedDown.dst(new Vector2(screenX,screenY)) < 5) {
            Optional<Position> newSelectedHex = adapter.findClosestHex(currentPos);
            if (newSelectedHex.isPresent()) {
                System.out.println("x: " + newSelectedHex.get().getX() + " y: " + newSelectedHex.get().getY());
                System.out.println(button);
                if (Input.Buttons.LEFT == button){
                    leftClick = newSelectedHex.get();
                }
                else if(Input.Buttons.RIGHT == button){
                    rightClick = newSelectedHex.get();
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        currentPos = new Vector3(screenX, screenY, 0);
        camera.unproject(currentPos);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        currentZoom += ((float) amount) / 10;
        return true;
    }

    public void updateCamera(float time) {
        updateCameraPosition();
        elapsedTime += time;
        if (elapsedTime >= .01) {
            updateCameraZoom();
            elapsedTime = 0;
        }
        checkBoundaries();
    }

    private void updateCameraZoom() {
        if (Math.abs(camera.zoom - currentZoom) > .2) {
            if (currentZoom > camera.zoom) {
                camera.zoom += .1;
            }
            if (currentZoom < camera.zoom) {
                camera.zoom -= .1;
            }
        }
    }

    private void updateCameraPosition(){
        float panSpeed = CAMERA_PAN_SPEED * currentZoom;
        if(isKeyPressed.getOrDefault(Input.Keys.A,false)){
            camera.translate(-1 * panSpeed,0);
        }
        else if(isKeyPressed.getOrDefault(Input.Keys.D,false)){
            camera.translate(1 * panSpeed,0);
        }
        else if(isKeyPressed.getOrDefault(Input.Keys.S,false)){
            camera.translate(0,-1 * panSpeed);
        }
        else if(isKeyPressed.getOrDefault(Input.Keys.W,false)){
            camera.translate(0,1 * panSpeed);
        }
    }

    private void checkBoundaries() {
        currentZoom = MathUtils.clamp(currentZoom, ZOOM_LOWER_BOUND, ZOOM_UPPER_BOUND);

        camera.position.x = MathUtils.clamp(camera.position.x, MAP_WIDTH_LOWER_BOUND + (viewport.getWorldWidth()/2), MAP_WIDTH_UPPER_BOUND - (viewport.getWorldWidth()/2));
        camera.position.y = MathUtils.clamp(camera.position.y, MAP_DEPTH_LOWER_BOUND + (viewport.getWorldHeight()/2), MAP_DEPTH_UPPER_BOUND - (viewport.getWorldHeight()/2));
    }

    public Optional<Position> getSelectedHex(){
        return selectedHex;
    }

    public Vector3 getMousePosition(){
        return currentPos;
        //return new Vector2(camera.project(new Vector3(currentPos.x, currentPos.y, 0)).x , -camera.project(new Vector3(currentPos.x, currentPos.y, 0)).y);
        //return new Vector2(((currentPos.x *currentZoom)- viewport.getWorldWidth()/2)  , (-currentPos.y * currentZoom) + viewport.getWorldHeight()/2);
    }

    public Optional<Position> getRightClickedHexPosition(){
        Optional<Position> hex = Optional.ofNullable(rightClick);
        if(hex.isPresent()){System.out.println("right");}
        rightClick = null;
        return hex;
    }

    public Optional<Position> getLeftClickedHexPosition(){
        Optional<Position> hex = Optional.ofNullable(leftClick);
        leftClick = null;
        return hex;
    }

    public boolean cancelOrders(){
        return isKeyPressed.getOrDefault(Input.Keys.C,false);
    }

    public Map<Position, Position>getMoves(){
        return moves;
    }

}
