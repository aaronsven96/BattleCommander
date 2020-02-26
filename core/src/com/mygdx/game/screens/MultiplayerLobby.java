package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.game.multiplayer.LobbyServer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisWindow;

public class MultiplayerLobby extends AbstractScreen {
    public MultiplayerLobby(String type) throws Exception {
        if (type == "server") {
            String[] serverArgs = new String[0];
            LobbyServer.main(serverArgs);
        }
        else if (type == "client") {
            
        }
    }

    public void buildStage() {
        VisTable table1 = new VisTable();
        VisTable table2 = new VisTable();
        table1.setFillParent(true);
        table1.debugAll();
//        table2.setFillParent(true);
        table2.debugAll();

        VisTextArea chatArea = new VisTextArea();
        chatArea.setFillParent(true);
        chatArea.appendText("\n");
        chatArea.appendText("\n");
        chatArea.setTouchable(Touchable.disabled);

        VisTextField input = new VisTextField("Chat");
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

        VisTextButton back = new VisTextButton("Back");
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
    }
}
