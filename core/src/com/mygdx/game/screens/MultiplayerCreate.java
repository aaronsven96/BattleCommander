package com.mygdx.game.screens;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.multiplayer.LobbyServerBak;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.game.multiplayer.Networking;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MultiplayerCreate extends AbstractScreen {
    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);
//        table.debugAll();

        String ipAddress = new Networking().getLocalIp();

        VisLabel ipLabel = new VisLabel("Room IP(s):");
        VisLabel ipList = new VisLabel(ipAddress);

        VisLabel roomName = new VisLabel("Room Name:");
        VisTextField roomNameInput = new VisTextField("Room Name");
        roomNameInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                roomNameInput.setText("");
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

        VisLabel passwordLabel = new VisLabel("Password");
        VisTextField passwordInput = new VisTextField("Password");
        passwordInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                passwordInput.setText("");
            }
        });

        VisTextButton create = new VisTextButton("Create");
        create.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_LOBBY_SCREEN, new Object[] {"create", new String[] {String.valueOf(ipList.getText()), roomNameInput.getText(), userNameInput.getText(), passwordInput.getText()}});
            }
        });

        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
//                visTextFieldStyle.font.getData().setScale(0.5f);
                ScreenManager.instance.popScreen();
            }
        });

        table.add(ipLabel);
        table.add(ipList);
        table.row();
        table.add(roomName);
        table.add(roomNameInput);
        table.row();
        table.add(userName);
        table.add(userNameInput);
        table.row();
        table.add(passwordLabel);
        table.add(passwordInput);
        table.row().colspan(2).expand().center();
        table.add(back).bottom().left();
        table.add(create).bottom().right();

        super.addActor(table);
    }
}
