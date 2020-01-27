package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MultiplayerSelect extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);
        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });
        table.add(back);
        super.addActor(table);
    }
}
