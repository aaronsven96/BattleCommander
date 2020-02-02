package com.mygdx.game.models;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * All of the Commands given to a unit for a turn
 */
public class Orders {

    /* The acting unit's ID */
    private int unitId;

    private Position startPosition;

    /* The Commands the Orders contain */
    private Iterator<Command> commands;

    private List<Command> commandList;

    /* The current Command*/
    private Command currentCommand;

    public Orders(int unitId, List<Command> commands, Position startPosition) {
        this.unitId = unitId;
        this.startPosition = startPosition;
        this.commandList = commands;
        this.commands = commands.iterator();
    }

    /** Iterates to the next Command if it can, and returns a boolean of whether
     * there is a next command.
     */
    public boolean nextCommand() {
        boolean hasNext = commands.hasNext();
        if (hasNext) {
            currentCommand = commands.next();
        }
        return hasNext;
    }

    public void goToFirstCommand(){
        commands = commandList.iterator();
    }

    public int getUnitId() {
        return unitId;
    }

    public Position getStartPosition(){
        return startPosition;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getLength() {
        return commandList.size();
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }
}
