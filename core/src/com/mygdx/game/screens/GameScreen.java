package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mygdx.game.BoardDrawer;
import com.mygdx.game.input.GameListener;

public class GameScreen extends AbstractScreen {

    @Override
    public void buildStage() {

        VisTable table = new VisTable();
        table.setFillParent(true);

        BoardDrawer boardDrawer = new BoardDrawer( 50, super.getViewport());

        table.add(boardDrawer);
        super.addActor(table);
    }
}
