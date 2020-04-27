package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.models.ConfigurationFactory;
import com.mygdx.game.models.HexMap;
import com.mygdx.game.models.TriggerProximity;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.fullscreen = true;
        config.width = 1000;
        config.height = 1000;
        new LwjglApplication(new MyGdxGame(), config);

//        HexMap map = ConfigurationFactory.getInstance().makeHexMapFromConfig("hex_map2.json");
//        TriggerProximity trigger = ConfigurationFactory.getInstance().makeTriggerFromConfig("configuration/events/events.json");
//        System.out.println(trigger.isTriggered(map));
//        long start = System.nanoTime();
//        map.save("hex_map2.json");
//        long end = System.nanoTime();
//        long timeMs = end - start;
//        System.out.println("Took " + timeMs + "ms to execute");
    }
}
