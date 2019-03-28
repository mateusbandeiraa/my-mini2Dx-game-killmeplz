package entity;

import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player {
	private CollisionPoint point;
	private Sprite sprite;
	private static final float DIMENSIONS = 32;

	public Player() {
		point = new CollisionPoint(32f, 8f);
		sprite = new Sprite(new Texture("tanks_tankDesert2.png"));
		sprite.setSize(DIMENSIONS, DIMENSIONS);
	}

	public void update() {
		// preUpdate() must be called before any changes are made to the CollisionPoint
		point.preUpdate();
		// Move the point by 4 pixels on the X and Y axis
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (sprite.isFlipX())
				sprite.flip(true, false);
			point.set(point.getX() + 4f, point.getY());
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (!sprite.isFlipX())
				sprite.flip(true, false);
			point.set(point.getX() - 4f, point.getY());
		}
	}

	public void interpolate(float alpha) {
		// This method uses the lerp (linear interpolate) method from LibGDX
		// to interpolate between the previous and current positions
		// and set the render coordinates correctly
		point.interpolate(null, alpha);
	}

	public void render(Graphics g) {
		// Use the point's render coordinates to draw the sprite
		g.drawSprite(sprite, point.getRenderX(), point.getRenderY());
	}

	public CollisionPoint getPoint() {
		return point;
	}
	
	
}
