package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.game.multiplayer.Networking;

public class MultiplayerJoin extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);
        table.debugAll();

        String ipAddresses = new Networking().getLocalIp();
        VisDialog dialog = new VisDialog("Select Local IP");
        dialog.closeOnEscape();
        String[] addresses = ipAddresses.split(",");

        VisLabel ipLabel = new VisLabel("Local IP:");
        VisLabel ipList = new VisLabel("");

        for(String str:addresses) {
            VisTextButton button = new VisTextButton(str);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    ipList.setText(str);
                }
            });
            dialog.button(button).padBottom(3);
        }
        dialog.pack();
        dialog.centerWindow();

        VisLabel roomIp = new VisLabel("Room IP:");
        VisTextField roomIpInput = new VisTextField("Room IP");
        roomIpInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                roomIpInput.setText("");
            }
        });

        VisLabel userName = new VisLabel("Username:");
        VisTextField userNameInput = new VisTextField("Username");
        userNameInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                userNameInput.setText("");
            }
        });

        VisLabel roomPasswordLabel = new VisLabel("Password:");
        VisTextField roomPasswordInput = new VisTextField("Password");
        roomPasswordInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                roomPasswordInput.setText("");
            }
        });

        VisTextButton join = new VisTextButton("Join");
        join.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_LOBBY_SCREEN, new Object[] {"join", new String[] {String.valueOf(ipList.getText()), String.valueOf(roomIpInput.getText()), userNameInput.getText(), roomPasswordInput.getText()}});
            }
        });

        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });

        table.add(ipLabel);
        table.add(ipList);
        table.row();
        table.add(roomIp);
        table.add(roomIpInput);
        table.row();
        table.add(userName);
        table.add(userNameInput);
        table.row();
        table.add(roomPasswordLabel);
        table.add(roomPasswordInput);
        table.row().colspan(2).expand().center();
        table.add(back).bottom().left();
        table.add(join).bottom().right();

        super.addActor(table);
        super.addActor(dialog.fadeIn());
    }
}
