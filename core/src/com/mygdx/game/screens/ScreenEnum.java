package com.mygdx.game.screens;

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
            return new MultiplayerLobby();
        }
    }
    ;


    public abstract AbstractScreen getScreen(Object[] args);
}
