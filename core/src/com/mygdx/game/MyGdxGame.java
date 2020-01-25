package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.input.GameListener;
import com.mygdx.game.models.ConfigurationFactory;
import com.mygdx.game.models.HexMap;
import com.mygdx.game.models.Position;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.screens.TitleScreen;

import java.util.Arrays;

import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.mygdx.game.screens.ScreenEnum.TITLE_SCREEN;

public class MyGdxGame extends Game {

    @Override
    public void create() {
        ScreenManager.init(this);
        ScreenManager.instance.pushScreen(TITLE_SCREEN);
    }

    @Override
    public void render() {
       super.render();
    }

    @Override
    public void dispose() {
    }
}
