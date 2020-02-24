package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

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
//        VisTextField.VisTextFieldStyle visTextFieldStyle = VisUI.getSkin().get(VisTextField.VisTextFieldStyle.class);
//        visTextFieldStyle.font.getData().setScale(0.5f);

        // The following code loops through the available network interfaces
        // Keep in mind, there can be multiple interfaces per device, for example
        // one per NIC, one per active wireless and the loopback
        // In this case we only care about IPv4 address ( x.x.x.x format )
        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        String ipAddress = new String("");
        for(String str:addresses)
        {
            if (str.equals("127.0.0.1")) {
                continue;
            }
            else if (ipAddress.equals("")) {
                ipAddress = str;
            }
            else {
                ipAddress = ipAddress + ", " + str;
            }
        }

        VisLabel ipLabel = new VisLabel("Room IP(s):");
        VisLabel ipList = new VisLabel(ipAddress);
        VisLabel roomName = new VisLabel("Room Name:");
        VisTextField nameInput = new VisTextField("Room Name");
        nameInput.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                nameInput.setText("");
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
                ScreenManager.instance.pushScreen(ScreenEnum.MULTIPLAYER_LOBBY_SCREEN, null);
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
        table.add(nameInput);
        table.row();
        table.add(passwordLabel);
        table.add(passwordInput);
        table.row().colspan(2).expand().center();
        table.add(back).bottom().left();
        table.add(create).bottom().right();

        super.addActor(table);
    }
}
