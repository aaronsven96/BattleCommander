package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.models.ConfigurationFactory;
import com.mygdx.game.models.HexMap;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.fullscreen = true;
        //config.width = 1000;
        //config.height = 1000;
        new LwjglApplication(new MyGdxGame(), config);
//		HexMap map = ConfigurationFactory.instance.makeHexMapFromConfig("configuration/Maps/HexMap.json");
    }
}
