package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.models.Position;

public class Line {
    private Position startPosition;
    private Position endPosition;
    private Color color;

    public Line(Position startPosition, Position endPosition, Color color) {
        this.color = color;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    @Override
    public boolean equals(Object object){
        if(!object.getClass().equals(Line.class)){
            throw new IllegalArgumentException("Argument is the wrong class");
        }
        Line  line = (Line) object;
        return startPosition.equals(line.getStartPosition()) && endPosition.equals(line.getEndPosition()) || startPosition.equals(line.getEndPosition()) && endPosition.equals(line.getStartPosition());
    }

    @Override
    public String toString(){
        return "Start Position " + startPosition.toString() + "End Position " + endPosition.toString();
    }

    @Override
    public int hashCode() {
        return startPosition.toString().hashCode() + endPosition.toString().hashCode();
    }

    public Color getColor() {
        return color;
    }
}
