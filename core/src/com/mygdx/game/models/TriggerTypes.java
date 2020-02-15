package com.mygdx.game.models;

public enum TriggerTypes {
    PROXIMITY {
        public Trigger getTrigger(String config) {
            return TriggerProximity.getTriggerFromConfig(config);
        }
    }
}
