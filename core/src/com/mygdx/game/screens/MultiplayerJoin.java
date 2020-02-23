package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

public class MultiplayerJoin extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);
        table.debugAll();

        VisLabel nameLabel = new VisLabel("Room IP");
        VisTextField nameInput = new VisTextField("Room IP");
        nameInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                nameInput.setText("");
            }
        });
      /*  name.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/

        VisLabel passwordLabel = new VisLabel("Password");
        VisTextField passwordInput = new VisTextField("Password");
        passwordInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                passwordInput.setText("");
            }
        });
   /*     password.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_CREATE_SCREEN);
            }
        });*/

        VisTextButton join = new VisTextButton("Join");
        join.addListener(new ChangeListener() {
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
        table.row().colspan(2).expand().center();
        table.add(back).bottom().left();
        table.add(join).bottom().right();

        super.addActor(table);
    }
}
