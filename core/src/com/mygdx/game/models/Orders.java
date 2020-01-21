package com.mygdx.game.models;


import java.util.ArrayList;


/**
 * All of the Commands given to a unit for a turn
 */
public class Orders {

    /* The acting unit's ID */
    private int unitId;

    /* The number of Commands in the Orders */
    private int length;

    /* The Commands the Orders contain */
    private ArrayList<Command> commands;

    /* The current Command*/
    private Command currentCommand;

    /* The index of the current Command */
    private int currentCommandIndex;

    public Orders(int unitId, int length, ArrayList<Command> commands) {
        this.unitId = unitId;
        this.length = length;
        this.commands = commands;
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    /** Iterates to the next Command if it can, and returns a boolean of whether
     * there is a next command
     */
    public boolean nextCommand() {
        if (currentCommandIndex < length) {
            currentCommand = commands.get(currentCommandIndex+1);
            currentCommandIndex += 1;
            return true;
        } else {
            return false;
        }
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
