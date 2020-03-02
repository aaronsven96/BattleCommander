package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mygdx.game.BoardDrawer;
import com.mygdx.game.input.GameListener;
import com.mygdx.game.models.ConfigurationFactory;
import com.mygdx.game.models.HexMap;

public class GameScreen extends AbstractScreen {

    private final String mapConfig;

    public GameScreen(Object[] args) {
        mapConfig = (String) args[0];
    }

    @Override
    public void buildStage() {

        HexMap map = ConfigurationFactory.getInstance().makeHexMapFromConfig(mapConfig);
        VisTable table = new VisTable();
        table.setFillParent(true);

        BoardDrawer boardDrawer = new BoardDrawer(super.getViewport(), map);

        table.add(boardDrawer);
        super.addActor(table);
    }
}
