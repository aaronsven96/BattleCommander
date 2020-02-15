package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TriggerProximity implements Trigger {
    private String type;
    private int id1;
    private int id2;
    private int range;

    private TriggerProximity(String type, int id1, int id2, int range) {
        this.type = type;
        this.id1 = id1;
        this.id1 = id2;
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
        JsonObject trigger4 = trigger3.get("details").getAsJsonObject();

        String type = trigger3.get("type").getAsString();
        int id1 = trigger4.get("id1").getAsInt();
        int id2 = trigger4.get("id2").getAsInt();
        int range = trigger4.get("range").getAsInt();

        return new TriggerProximity(type, id1, id2, range);
    }

    @Override
    public boolean isTriggered(HexMap map) { // TODO: one unit/terrain per hex???
        List<Position> positions = new ArrayList<>();

        loop1:
        for (int i = 0; i < map.getUnits().getNumRows()) {
            for (int j = 0; j < map.getUnits().getNumColumns()) {
                if (positions.size() == 2) {
                    break loop1;
                }
                Position p = new Position(i, j);
                Optional<BasicUnit> optional = map.getUnits().getHex(p);
                if (optional.isPresent()) {
                    BasicUnit bu = optional.get();
                    if (bu.getId() == id1 || bu.getId() == id2) {
                        positions.add(p);
                    }
                }
            }
        }

        if (positions.size() == 2 && map.getUnits().isInProximity(positions.get(0), positions.get(1), range)) {
            return true;
        }

        loop2:
        for (int i = 0; i < map.getTerrain().getNumRows()) {
            for (int j = 0; j < map.getTerrain().getNumColumns()) {
                if (positions.size() == 2) {
                    break loop2;
                }
                Position p = new Position(i, j);
                Optional<Terrain> optional = map.getTerrain().getHex(p);
                if (optional.isPresent()) {
                    Terrain t = optional.get();
                    if (t.getID() == id1 || t.getID() == id2) {
                        positions.add(p);
                    }
                }
            }
        }

        if (positions.size() == 2 && map.getTerrain().isInProximity(positions.get(0), positions.get(1), range)) {
            return true;
        }

        return false;
    }
}
