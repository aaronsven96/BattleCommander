package com.mygdx.game.screens;

import com.badlogic.gdx.Game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class ScreenManager {

    public static ScreenManager instance = new ScreenManager();

    private static Game game;

    private static Deque<AbstractScreen> screens = new ArrayDeque<>();

    public static void init(Game gameRunning){
        game = gameRunning;
    }

    public void popScreen(){
        screens.pop();
        if(!screens.isEmpty()) {
            game.setScreen(screens.peek());
        }
    }

    public void pushScreen(ScreenEnum screen){
        AbstractScreen screenInst = screen.getScreen();
        game.setScreen(screenInst);
        screens.push(screenInst);
    }
}
