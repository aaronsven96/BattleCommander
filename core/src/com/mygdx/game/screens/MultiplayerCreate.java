package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

public class MultiplayerCreate extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);

        VisLabel nameLabel = new VisLabel("Room Name");
        VisTextField nameInput = new VisTextField("Room Name");
      /*  name.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/

        VisLabel passwordLabel = new VisLabel("Room Password");
        VisTextField passwordInput = new VisTextField("Room Password");
   /*     password.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/

        VisTextButton create = new VisTextButton("Create");
        create.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN, null);
            }
        });

        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });

        table.add(nameLabel);
        table.add(nameInput);
        table.row();
        table.add(passwordLabel);
        table.add(passwordInput);
        table.row();
        table.add(back);
        table.row();

        super.addActor(table);
    }
}
