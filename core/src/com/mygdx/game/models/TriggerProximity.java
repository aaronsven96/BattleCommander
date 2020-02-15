package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TriggerProximity implements Trigger {
    String type;
    int pid1;
    int pid2;
    int range;

    private TriggerProximity(String type, int pid1, int pid2, int range) {
        this.type = type;
        this.pid1 = pid1;
        this.pid1 = pid2;
        this.range = range;
    }

    /**
     * Returns the Trigger from the configuration file.
     *
     * @param config the file path
     * @return the Trigger from the configuration file
     */
    public static TriggerProximity getTriggerFromConfig(String config) {
        Gson gson = new Gson();
        JsonObject event = gson.fromJson(config, JsonObject.class);
        JsonArray trigger1 = event.get("events").getAsJsonArray();
        JsonObject trigger2 = trigger1.get(0).getAsJsonObject();
        JsonObject trigger3 = trigger2.get("trigger").getAsJsonObject();

        String type = trigger3.get("type").getAsString();
        int pid1 = trigger3.get("pid1").getAsInt();
        int pid2 = trigger3.get("pid2").getAsInt();
        int range = trigger3.get("range").getAsInt();

        return new TriggerProximity(type, pid1, pid2, range);
    }

    @Override
    public boolean isTriggered(HexMap map) {
        return false;
    }
}
