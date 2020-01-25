package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class TitleScreen extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();

        VisTextButton selectMap = new VisTextButton("Selection");
        selectMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.SELECTION_SCREEN, null);
            }
        });

        VisTextButton multiplayer = new VisTextButton("Multiplayer");
        multiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_SELECT_SCREEN, null);
            }
        });
    }
}
