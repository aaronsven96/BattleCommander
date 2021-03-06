package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TriggerProximity implements Trigger {
    private String type;
    private int id1;
    private int id2;
    private int range;

    private TriggerProximity(String type, int id1, int id2, int range) {
        this.type = type;
        this.id1 = id1;
        this.id2 = id2;
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
        JsonObject trigger2 = trigger1.get(0).getAsJsonObject(); // TODO: add loop
        JsonObject trigger3 = trigger2.get("trigger").getAsJsonObject();
        JsonObject trigger4 = trigger3.get("details").getAsJsonObject();

        String type = trigger3.get("type").getAsString();
        int id1 = trigger4.get("id1").getAsInt();
        int id2 = trigger4.get("id2").getAsInt();
        int range = trigger4.get("range").getAsInt();

        return new TriggerProximity(type, id1, id2, range);
    }

    /**
     * Returns true if triggered, false otherwise.
     *
     * @param map the HexMap
     * @return true if triggered, false otherwise
     */
    @Override
    public boolean isTriggered(HexMap map) {
        return map.isInProximity(id1, id2, range);
    }
}