package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HudContainer extends HudElement{

    private ShapeRenderer shapeRenderer;
    protected String title;
    private BitmapFont font;
    private SpriteBatch batch;

    public HudContainer() {
        x = 50;
        y = 50;
        width = 400;
        height = 200;
        title = "Blank Container";
        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();

        font = FontManager.getFont(16);
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Constants.primaryColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Constants.primaryDarkColor);
        shapeRenderer.rect(x, (height + y) - 20, width, 20);
        shapeRenderer.end();

        batch.begin();
        font.draw(batch, title, x + 5, (height + y) - 5);
        batch.end();
    }

    @Override
    public boolean isColliding() {
        if(MouseInput.isColliding(x, y, width, height)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

}
