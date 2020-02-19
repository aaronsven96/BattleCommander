package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.input.GameListener;
import com.mygdx.game.models.Command;
import com.mygdx.game.models.HexBoard;
import com.mygdx.game.models.HexMap;
import com.mygdx.game.models.Orders;
import com.mygdx.game.models.PlayerOrders;
import com.mygdx.game.models.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.mygdx.game.GameUtils.getVector2;

public class BoardDrawer extends Actor implements Disposable {
    private ShapeDrawer drawer;
    Texture texture;
    Vector3[][] centerHexes;
    GameListener gameListener;
    private static List<Color> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.CHARTREUSE, Color.CORAL
    , Color.CYAN, Color.DARK_GRAY);
    MoveAdapter moveAdapter;
    Map<String, TextureRegion> textureMap = new HashMap<>();
    HexMap hexMap;

    public BoardDrawer(Viewport viewport, HexMap hexMap) {
        this.hexMap = hexMap;
        centerHexes = calculateCenterHexes(50);
        gameListener = new GameListener(viewport, centerHexes);
        Gdx.input.setInputProcessor(gameListener);
        moveAdapter = new MoveAdapter(101, hexMap);
    }

    private void initDrawer(Batch batch){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);

        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 100, 100);
        drawer = new ShapeDrawer(batch, region);
        drawer.setColor(Color.BLUE);
    }

    public void drawPlayerOrders(PlayerOrders playerOrders){
        Map<Line, List<Line>> moves = new HashMap<>();
        int colorNum = 0;
        List<Integer> keys = new ArrayList<>(playerOrders.getUnitOrders().keySet());
        keys.sort(Comparator.naturalOrder());
        for(Integer unitId : keys) {
            Orders orders = playerOrders.getUnitOrders().get(unitId);
            orders.goToFirstCommand();
            Position start = orders.getStartPosition();
            gameListener.updateCamera(Gdx.graphics.getDeltaTime());
            while (orders.nextCommand()) {
                Line line = new Line(start, orders.getCurrentCommand().getTargetPosition(), colors.get(colorNum));
                moves.putIfAbsent(line, new ArrayList<>());
                moves.get(line).add(line);
                start = orders.getCurrentCommand().getTargetPosition();
            }
            colorNum ++;
        }
        for(Line key : moves.keySet()){
            List<Line> lines = moves.get(key);

            int numMoves = lines.size();
            int yOffset = (numMoves -1) * 5;
            int padding = numMoves == 1 ? 0 : (2*yOffset) / (numMoves -1);
            for(Line line: lines){
                drawer.setColor(line.getColor());
                Position start = line.getStartPosition();
                Position end = line.getEndPosition();
                Vector2 startV = GameUtils.getVector2(centerHexes[start.getX()][start.getY()]);
                Vector2 endV = GameUtils.getVector2(centerHexes[end.getX()][end.getY()]);
                Vector2 dis = endV.cpy().add(startV.cpy().scl(-1));
                float slope = dis.x/dis.y;

                if(slope == 0){
                    slope = 1;
                }else{
                    startV.y -= yOffset;
                    endV.y -= yOffset ;
                }
                float slopeP = 1/slope;
                Vector2 change = endV.cpy().sub(startV).nor();
                startV.x += yOffset * slopeP;
                endV.x += yOffset * slopeP;
                endV.add((change.cpy().scl(-20)));
                startV.add((change.cpy().scl(20)));

                yOffset = yOffset - padding;
                drawer.line(startV, endV, 3);
                drawer.filledCircle(endV.x, endV.y, 5);
                //drawer.line(arrowR, endV, 3);
                //drawer.line(arrowL, endV, 3);
            }
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Optional<Position> leftClick = gameListener.getLeftClickedHexPosition();
        Optional<Position> rightClick = gameListener.getRightClickedHexPosition();
        leftClick.ifPresent(position -> moveAdapter.leftClickHex(position));
        rightClick.ifPresent(position -> moveAdapter.rightClickHex(position));
        if (gameListener.cancelOrders()){
            moveAdapter.cancelCommand();
        }
        if(drawer == null){
            initDrawer(batch);
        }
        drawTextures(batch);
        drawHexes();
        drawPlayerOrders(moveAdapter.getCurrentOrders());
        gameListener.updateCamera(Gdx.graphics.getDeltaTime());
    }
    // Draws an array of hexes based on a boolean array

    public void drawHexes() {
        for(int i=0; i< centerHexes.length;i++){
            Vector3[] centerHex = centerHexes[i];
            for (int i1 = 0; i1 < centerHex.length; i1++) {
                Vector3 center = centerHex[i1];
                //if(!whereToDraw[i][i1]) {
                    //drawer.filledPolygon(center.x, center.y, 6, (float) (float)size, (float) size, 0, Color.RED, Color.WHITE);
                //}

                drawer.setColor(Color.BROWN);
                drawer.polygon(center.x, center.y, 6, (float) (float)50, (float) 50, 0, 3);
                drawer.circle(center.x, center.y, 30);
            }
        }
    }

    public void drawTextures(Batch batch) {
        List<HexBoard<String>> textures = hexMap.getTextures();
        for(HexBoard<String> layer: textures){
            for(int row = 0; row < layer.getNumColumns();row ++){
                for(int col = 0; col < layer.getNumColumns();col ++){
                    Optional<String> texturePath = layer.getHex(new Position(row, col));
                    if(texturePath.isPresent()) {
                        TextureRegion textureR;
                        if(!textureMap.containsKey(texturePath.get())){
                            Texture texture = new Texture(Gdx.files.internal(texturePath.get()));
                            textureR = new TextureRegion(texture);
                            textureMap.put(texturePath.get(), textureR);
                        }else{
                            textureR = textureMap.get(texturePath.get());
                        }
                        Vector3 center = centerHexes[row][col];
                        batch.draw(textureR, center.x - (textureR.getRegionWidth() >> 1), center.y - (textureR.getRegionHeight() >> 1));
                    }
                }
            }
        }
    }

    private Vector3[][] calculateCenterHexes(int size){
        int yOffset = 0;
        int xOffset = 0;
        Vector3[][] centers = new Vector3[hexMap.getMapShape().getNumColumns()][hexMap.getMapShape().getNumColumns()];
        for (int x = 0; x < hexMap.getMapShape().getNumColumns(); x++) {
            for (int y = 0; y < hexMap.getMapShape().getNumColumns(); y++) {

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
