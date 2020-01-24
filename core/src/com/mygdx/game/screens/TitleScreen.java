package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class TitleScreen extends AbstractScreen{
    @Override
    public void buildStage() {
        VisUI.load();
        VisTable table = new VisTable();
        Button
        table.add(new VisTextButton("Start"));
        table.setFillParent(true);
        super.addActor(table);
    }
}
