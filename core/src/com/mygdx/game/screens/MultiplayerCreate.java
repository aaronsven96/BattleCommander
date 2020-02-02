package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

public class MultiplayerCreate extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);

        VisTextField name = new VisTextField("Room Name");
      /*  name.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/
        table.add(name);

        VisTextField password = new VisTextField("Password");
   /*     password.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/
        table.add(password);

        VisTextButton create = new VisTextButton("Create");
        create.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN, null);
            }
        });
        table.add(create);

        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });
        table.add(back);



        super.addActor(table);
    }
}
