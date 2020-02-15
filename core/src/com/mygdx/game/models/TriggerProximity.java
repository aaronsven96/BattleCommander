package com.mygdx.game.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Optional;
import java.util.TreeSet;

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
    public boolean isTriggered(HexMap map) {
        TreeSet<Position> unitsPositions = new TreeSet<>();
        TreeSet<Position> terrainPositions = new TreeSet<>();

        for (int i = 0; i < map.getUnits().getNumRows()) {
            if (unitsPositions.size() == 2) {
                break;
            }
            for (int j = 0; j < map.getUnits().getNumColumns()) {
                if (unitsPositions.size() == 2) {
                    break;
                }
                Position p = new Position(i, j);
                Optional<BasicUnit> optional = map.getUnits().getHex(p);
                if (optional.isPresent()) {
                    BasicUnit bu = optional.get();
                    if (bu.getId() == id1 || bu.getId() == id2) {
                        unitsPositions.add(p);
                    }
                }
            }
        }

        if (map.getUnits().isInProximity(unitsPositions.first(), unitsPositions.last(), range)) {
            return true;
        }

        for (int i = 0; i < map.getTerrain().getNumRows()) {
            if (terrainPositions.size() == 2) {
                break;
            }
            for (int j = 0; j < map.getTerrain().getNumColumns()) {
                if (terrainPositions.size() == 2) {
                    break;
                }
                Position p = new Position(i, j);
                Optional<Terrain> optional = map.getTerrain().getHex(p);
                if (optional.isPresent()) {
                    Terrain t = optional.get();
                    if (t.getID() == id1 || t.getID() == id2) {
                        terrainPositions.add(p);
                    }
                }
            }
        }

        if (map.getTerrain().isInProximity(terrainPositions.first(), terrainPositions.last(), range)) {
            return true;
        }

        return false;
    }
}
