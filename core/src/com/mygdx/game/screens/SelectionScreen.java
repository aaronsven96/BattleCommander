package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kotcrab.vis.ui.widget.VisList;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mygdx.game.models.ConfigurationGetter;

public class SelectionScreen extends AbstractScreen {

    String selectedMap;

    @Override
    public void buildStage() {
        VisTable table = new VisTable();
        table.setFillParent(true);
        table.debug();

        VisList<String> visList = new VisList<>();
        visList.setItems(getItemsFromConfig().toArray());

        visList.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                selectedMap = visList.getSelected();
                return true;
            }
        });

        table.add(visList).colspan(2).expand().center();
        table.row();

        VisTextButton back = new VisTextButton("Back");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
            }
        });

        VisTextButton select = new VisTextButton("Select");
        select.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.instance.popScreen();
                Object[] args = new Object[1];
                args[0] = selectedMap;
                ScreenManager.instance.pushScreen(ScreenEnum.GAME_SCREEN, args);
            }
        });

        table.add(back).bottom().left();
        table.add(select).bottom().right();

        super.addActor(table);
    }

    private Array<String> getItemsFromConfig(){
        Gson gson = new Gson();
        JsonArray array = gson.fromJson(ConfigurationGetter.getConfiguration("configuration/Maps/MapList.json"), JsonArray.class);
        Array<String> maps = new Array<>();
        array.forEach(i->maps.add(i.getAsString()));
        return maps;
    }
}
