package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * This class is a container for the HUD
 *
 * @author Mohammad Foysal
 */
public class HudContainer extends HudElement {
    final BitmapFont font;
    final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    /**
     * Create a new HudContainer at given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the HudContainer
     * @param y The Y co-ordinate of the HudContainer
     */
    HudContainer(int x, int y) {
        this.x = x;
        this.y = y;
        width = 400;
        height = 200;
        title = "Blank Container";
        shapeRenderer = GameEngine.getShapeRenderer();
        batch = GameEngine.getBatch();
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
        return MouseInput.isColliding(x, y, width, height);
    }
}