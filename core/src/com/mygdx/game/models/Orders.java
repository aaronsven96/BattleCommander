package com.mygdx.game.models;


import lombok.Getter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


/**
 * All of the Commands given to a unit for a turn.
 */
@Getter
public class Orders implements Serializable {

    /* The acting unit's ID */
    private final int unitId;

    private final Position startPosition;

    /* The Commands the Orders contain */
    private Iterator<Command> commands;

    private final List<Command> commandList;

    /* The current Command */
    private Command currentCommand;

    public Orders(int unitId, List<Command> commands, Position startPosition) {
        this.unitId = unitId;
        this.startPosition = startPosition;
        this.commandList = commands;
        this.commands = commands.iterator();
    }

    /**
     * Iterates to the next Command if it can, and returns a boolean of whether
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

    public int getLength() {
        return commandList.size();
    }
}
