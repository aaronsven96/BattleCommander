package com.mygdx.game.screens;

public enum ScreenEnum {
    TITLE_SCREEN{
        public AbstractScreen getScreen(){
            return new TitleScreen();
        }
    },
    SELECTION_SCREEN{
        public AbstractScreen getScreen(){
            return new SelectionScreen();
        }
    },
    GAME_SCREEN{
        public AbstractScreen getScreen(){
            return new GameScreen();
        }
    },
    MULTIPLAYER_SELECT_SCREEN{
        public AbstractScreen getScreen(){
            return new MultiplayerSelect();
        }
    },
    MULTIPLAYER_CREATE_SCREEN{
        public AbstractScreen getScreen() { return new MultiplayerCreate(); }
    },
    MULTIPLAYER_JOIN_SCREEN{
        public AbstractScreen getScreen(){
            return new MultiplayerJoin();
        }
    }
    ;


    public abstract AbstractScreen getScreen();
}
