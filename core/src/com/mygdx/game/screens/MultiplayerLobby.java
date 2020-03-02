package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.game.multiplayer.LobbyServerBak;
import com.mygdx.game.multiplayer.Networking;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextArea;

public class MultiplayerLobby extends AbstractScreen {
    VisTable table1 = new VisTable();
    VisTable table2 = new VisTable();
    VisTextArea chatArea = new VisTextArea();
    VisTextField input = new VisTextField("Chat");
    VisTextButton back = new VisTextButton("Back");

    public void addChat(String text) {
        chatArea.appendText(text);
        chatArea.appendText("\n");
    }

    public void buildStage() {
        table1.setFillParent(true);
        table1.debugAll();
//        table2.setFillParent(true);
        table2.debugAll();


        chatArea.setFillParent(true);
        chatArea.setTouchable(Touchable.disabled);


        input.setWidth(chatArea.getWidth());
        input.addListener(new FocusListener() {
//            @Override
            public void focusLost(FocusEvent event) {
                if (input.getText() == "") {
                    input.setText("Chat");
                }
            }
        });

        input.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                input.setText("");
            }
        });
        input.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == 66) {
                    chatArea.appendText(input.getText());
                    chatArea.appendText("\n");
                    input.setText("");
                }
//                System.out.println(keycode);
                return super.keyDown(event, keycode);
            }
        } );


        back.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });

        table1.row().colspan(2).expand().center();
        table1.add(chatArea).bottom().left();
        table1.row();
        table2.columnDefaults(1).width(100);
        table2.columnDefaults(2).width(200);
        table2.add(back).bottom().left();
        table2.add(input).width(200).bottom().left();
        table1.add(table2).bottom().left();
//        table.add(input);

        super.addActor(table1);
        System.out.println("The lobby screen is showing...");
    }
}
