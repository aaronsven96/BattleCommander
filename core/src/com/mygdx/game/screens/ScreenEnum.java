package com.mygdx.game.screens;

import com.mygdx.game.multiplayer.Networking;

import sun.nio.ch.Net;

public enum ScreenEnum {
    TITLE_SCREEN{
        public AbstractScreen getScreen(Object[] args){
            return new TitleScreen();
        }
    },
    SELECTION_SCREEN{
        public AbstractScreen getScreen(Object[] args){
            return new SelectionScreen();
        }
    },
    GAME_SCREEN{
        public AbstractScreen getScreen(Object[] args){
            return new GameScreen(args);
        }
    },
    MULTIPLAYER_SELECT_SCREEN{
        public AbstractScreen getScreen(Object[] args){ return new MultiplayerSelect(); }
    },
    MULTIPLAYER_CREATE_SCREEN{
        public AbstractScreen getScreen(Object[] args) { return new MultiplayerCreate(); }
    },
    MULTIPLAYER_JOIN_SCREEN{
        public AbstractScreen getScreen(Object[] args){
            return new MultiplayerJoin();
        }
    },
    MULTIPLAYER_LOBBY_SCREEN{
        public AbstractScreen getScreen(Object[] args){
            if (args[0].toString() == "create") {
                return Networking.createServer((String[]) args[1]);
            }
            else if (args[0].toString() == "join") {
                return Networking.joinServer((String[]) args[1]);
            }
            else {
                return null;
            }
        }
    }
    ;


    public abstract AbstractScreen getScreen(Object[] args);
}
