package com.mygdx.game.models;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * All of the Commands given to a unit for a turn
 */
public class Orders {

    /* The acting unit's ID */
    private int unitId;

    /* The number of Commands in the Orders */
    private int length;

    /* The Commands the Orders contain */
    private Iterator<Command> commands;

    /* The current Command*/
    private Command currentCommand;

    public Orders(int unitId, int length, ArrayList<Command> commands) {
        this.unitId = unitId;
        this.length = length;
        this.commands = commands.iterator();
    }

    /** Iterates to the next Command if it can, and returns a boolean of whether
     * there is a next command.
     */
    public boolean nextCommand() {
        if (commands.hasNext()) {
            currentCommand = commands.next();
        }
        return commands.hasNext();
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getLength() {
        return length;
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }
}
