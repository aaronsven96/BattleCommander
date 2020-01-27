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
    };


    public abstract AbstractScreen getScreen();
}
