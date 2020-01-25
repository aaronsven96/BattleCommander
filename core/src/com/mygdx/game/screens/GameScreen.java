package com.mygdx.game.screens;

import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;

public class GameScreen extends AbstractScreen {
    @Override
    public void buildStage() {
        VisUI.load();
        VisTable table = new VisTable();
    }
}
