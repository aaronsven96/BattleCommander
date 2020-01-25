package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class TitleScreen extends AbstractScreen{
    @Override
    public void buildStage() {
        VisUI.load();
        VisTable table = new VisTable();

        table.add(new VisTextButton("Start"));
        VisTextButton button = new VisTextButton("Start");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        table.setDebug(true);
        table.setFillParent(true);
        super.addActor(table);
    }
}
